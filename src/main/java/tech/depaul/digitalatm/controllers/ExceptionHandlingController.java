package tech.depaul.digitalatm.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tech.depaul.digitalatm.components.DigitalAtmException;

@ControllerAdvice
@Slf4j
public class ExceptionHandlingController {

    @ExceptionHandler
    public String accountError(final DigitalAtmException e, final Model model) {
        model.addAttribute("exception", e.getMessage());
        return "error";
    }
}
