package tech.depaul.digitalatm.service;

import tech.depaul.digitalatm.config.ATMUserDetails;
import tech.depaul.digitalatm.controllers.request.ATMRequest;
import tech.depaul.digitalatm.controllers.request.DigitalATMRequest;

public interface ATMService {
    void executeDepositOnAccount(ATMRequest request, ATMUserDetails userDetails);
    void executeWithdrawOnAccount(ATMRequest request, ATMUserDetails userDetails);
    String retrieveAccountBalance(ATMUserDetails userDetails);
}
