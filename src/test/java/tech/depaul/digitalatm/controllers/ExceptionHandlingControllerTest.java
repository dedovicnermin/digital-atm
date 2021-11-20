package tech.depaul.digitalatm.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import tech.depaul.digitalatm.components.DigitalAtmException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ExceptionHandlingControllerTest {

    private static final String ERROR_PAGE = "error";

    @Test
    void whenAccountError_populatesModelWithException_andReturnsError() {
        final ExceptionHandlingController exceptionHandlingController = new ExceptionHandlingController();
        final String expectedMessage = "Something bad happened";
        final DigitalAtmException exception = new DigitalAtmException(expectedMessage);
        final Model model = new ExtendedModelMap();

        final String actual = exceptionHandlingController.accountError(exception, model);
        assertThat(actual).isEqualTo(ERROR_PAGE);
        assertThat(model.getAttribute("exception")).isEqualTo(expectedMessage);

    }

}
