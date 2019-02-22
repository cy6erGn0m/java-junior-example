package com.levelp.example;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
//@RepositoryRestResource(path = "subjects",
//        collectionResourceRel = "subjects")
public interface SubjectsDAO extends JpaRepository<Subject, Long> {
    Subject findSubjectByCadNum(String cadNum);

    Page<Subject> findByEngineer_IdAndSquareGreaterThanEqualOrderBySquare(
            long engineerId,
            int square,
            Pageable pageable
    );

    @Query("from Subject where kind = :kind")
    List<Subject> findSubjectsByKind(SubjKind kind);
}
