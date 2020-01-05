package com.example.demo.component;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component
public class LoginUserAuditorAware implements AuditorAware {

    @Override
    public Optional getCurrentAuditor() {
        return Optional.of("AdminServer");
    }

}