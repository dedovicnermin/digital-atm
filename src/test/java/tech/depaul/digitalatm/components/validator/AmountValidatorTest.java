package tech.depaul.digitalatm.components.validator;

import org.junit.jupiter.api.Test;
import tech.depaul.digitalatm.TestUtils;
import tech.depaul.digitalatm.components.DigitalAtmException;
import tech.depaul.digitalatm.controllers.request.DigitalATMRequest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AmountValidatorTest {

    private static final String INVALID_REQUEST = "Invalid request";
    private static final String AMOUNT_SHOULD_NOT_BE_NEGATIVE = "Amount should not be negative";


    private final ATMValidator validator = new AmountValidator();

    @Test
    void whenValidRequest_returnsBigDecimal() {
        final DigitalATMRequest validDigitalATMRequest = TestUtils.getValidDigitalATMRequest();
        final BigDecimal expected = new BigDecimal(validDigitalATMRequest.getAmount());
        final BigDecimal actual = validator.validate(validDigitalATMRequest);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void whenInvalidRequest_negative_thenThrowsException() {
        final DigitalATMRequest invalidDigitalAtmRequest = TestUtils.getInvalidDigitalAtmRequest();
        final DigitalAtmException exception = assertThrows(DigitalAtmException.class, () -> validator.validate(invalidDigitalAtmRequest));
        assertThat(exception.getMessage()).isEqualTo(AMOUNT_SHOULD_NOT_BE_NEGATIVE);
    }

    @Test
    void whenInvalidRequest_null_thenThrowsException() {
        final DigitalAtmException exception = assertThrows(DigitalAtmException.class, () -> validator.validate(null));
        assertThat(exception.getMessage()).isEqualTo(INVALID_REQUEST);
    }
}
