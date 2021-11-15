package tech.depaul.digitalatm.components.validator;

import tech.depaul.digitalatm.components.DigitalAtmException;
import tech.depaul.digitalatm.controllers.request.ATMRequest;

import java.math.BigDecimal;

public class AmountValidator implements ATMValidator {

    @Override
    public BigDecimal validate(ATMRequest request) {
        try {
            final BigDecimal decimal = new BigDecimal(request.getAmount());
            if (decimal.doubleValue() < 0.00) {
                throw new DigitalAtmException("Amount should not be negative");
            }
            return decimal;
        } catch (NumberFormatException | NullPointerException e) {
            throw new DigitalAtmException("Invalid request");
        }
    }
}
