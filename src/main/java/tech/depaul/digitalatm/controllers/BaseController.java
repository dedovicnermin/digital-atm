package tech.depaul.digitalatm.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import tech.depaul.digitalatm.config.ATMUserDetails;

public class BaseController {

    public ATMUserDetails getCurrentUser() {
        return (ATMUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
