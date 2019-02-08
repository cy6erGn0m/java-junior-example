package com.levelp.example.web;

import com.levelp.example.*;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import java.util.List;

@Controller
public class AddSubjectController {
    @Autowired
    private EntityManager em;

    @Autowired
    private UsersDAO users;

    @Autowired
    private SubjectsDAO subjects;

    @Autowired
    private List<PassportVerificationService> allVerificationServices;

    @Autowired
    @Qualifier("police")
    private PassportVerificationService policeVerificationService;

    @GetMapping(path = "/add-subject")
//    = @RequestMapping(method = RequestMethod.GET, path = "/add-subject")
    public String addSubjectForm(@ModelAttribute(name = "subject") AddSubjectFormBean form) {
        return "add-subject";
    }

    @PostMapping(path = "/add-subject")
    public String postForm(
            @ModelAttribute(name = "subject") AddSubjectFormBean form,
            BindingResult bindingResult
    ) {
        boolean verified = false;

        for (PassportVerificationService service : allVerificationServices) {
            if (service.isValid(form.getName(), form.getPassportNumber())) {
                verified = true;
                break;
            }
        }

        if (!verified) {
            bindingResult.addError(new FieldError("subject", "name",
                    "Паспортные данные неверны."));
            return "add-subject";
        }

        Engineer engineer = null;

        for (User user : users.listUsers()) {
            if (user instanceof Engineer) {
                engineer = (Engineer) user;
            }
        }

        if (engineer == null) {
            bindingResult.addError(new ObjectError("subject", "Нет инженера."));
            return "add-subject";
        }

        em.getTransaction().begin();
        try {
            Subject created = subjects.createSubject(engineer,
                    SubjKind.BUILDING,
                    form.getCadNumber(), form.getAddress());

            created.setTitle(form.getTitle());
            em.getTransaction().commit();
        } catch (Throwable t) {
            em.getTransaction().rollback();

            if (isConstraintViolation(t)) {
                bindingResult.addError(new FieldError("subject", "cadNumber",
                        "Объект с таким кадастровым номером уже существует."));
            } else {
                bindingResult.addError(new ObjectError("subject",
                        "Не удалось создать объект: " + t.getMessage() + "."));
            }

            return "add-subject";
        }

        return "redirect:/";
    }

    private boolean isConstraintViolation(Throwable error) {
        if (error instanceof ConstraintViolationException) return true;
        if (error.getCause() == error) return false;
        return isConstraintViolation(error.getCause());
    }
}
