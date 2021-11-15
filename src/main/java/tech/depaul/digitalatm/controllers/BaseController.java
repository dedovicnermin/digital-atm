package tech.depaul.digitalatm.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import tech.depaul.digitalatm.config.ATMUserDetails;

public abstract class BaseController {

    protected ATMUserDetails getCurrentUser() {
        return (ATMUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
