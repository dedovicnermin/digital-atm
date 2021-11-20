package tech.depaul.digitalatm.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import tech.depaul.digitalatm.config.ATMUserDetails;

@Component
public class SecurityService {

    public ATMUserDetails getCurrentUser() {
        return (ATMUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
