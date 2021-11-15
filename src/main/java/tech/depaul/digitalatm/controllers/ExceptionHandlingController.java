package tech.depaul.digitalatm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tech.depaul.digitalatm.components.DigitalAtmException;

@Controller
public class ExceptionHandlingController {

    @ExceptionHandler({DigitalAtmException.class})
    public String accountError(final DigitalAtmException e, final Model model) {
        final String message = e.getMessage();
        model.addAttribute("errorMessage", message);
        return "error";
    }
}
