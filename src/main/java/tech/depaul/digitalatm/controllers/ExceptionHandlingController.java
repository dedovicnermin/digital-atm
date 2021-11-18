package tech.depaul.digitalatm.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tech.depaul.digitalatm.components.DigitalAtmException;

@Controller
@Slf4j
public class ExceptionHandlingController {

    @ExceptionHandler({DigitalAtmException.class})
    public String accountError(final DigitalAtmException e, final Model model) {
        final String message = e.getMessage();
        log.info(e.getMessage());
        e.printStackTrace();
        model.addAttribute("message", message);
        return "templates/error";
    }
}
