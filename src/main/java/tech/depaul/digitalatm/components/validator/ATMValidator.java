package tech.depaul.digitalatm.components.validator;

import tech.depaul.digitalatm.controllers.request.ATMRequest;

import java.math.BigDecimal;

public interface ATMValidator {
    BigDecimal validate(ATMRequest request);
}
