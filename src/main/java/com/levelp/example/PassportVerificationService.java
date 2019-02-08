package com.levelp.example;

import org.springframework.stereotype.Service;

@Service
public interface PassportVerificationService {
    boolean isValid(String name, String passport);
}
