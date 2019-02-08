package com.levelp.example;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("police")
public class PoliceVerificationService implements PassportVerificationService {
    @Override
    public boolean isValid(String name, String passport) {
        return name.equals("user1") && passport.equals("0000-11111");
    }
}
