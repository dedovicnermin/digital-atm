package tech.depaul.digitalatm.components;

import tech.depaul.digitalatm.controllers.request.ATMRequest;

public interface ATMValidator {
    boolean isValid(ATMRequest request);
}
