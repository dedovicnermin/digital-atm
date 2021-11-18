package tech.depaul.digitalatm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.depaul.digitalatm.components.validator.ATMValidator;
import tech.depaul.digitalatm.components.AccountManager;
import tech.depaul.digitalatm.components.validator.AmountValidator;
import tech.depaul.digitalatm.config.ATMUserDetails;
import tech.depaul.digitalatm.controllers.request.ATMRequest;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class DigitalATMService implements ATMService {

    private final AccountManager accountManager;
    private final ATMValidator validator = new AmountValidator();


    public void executeWithdrawOnAccount(final ATMRequest request, final ATMUserDetails userDetails) {
        final BigDecimal amount = validator.validate(request);
        accountManager.withdrawFromAccount(userDetails, amount);
    }

    public void executeDepositOnAccount(final ATMRequest request, final ATMUserDetails userDetails) {
        final BigDecimal amount = validator.validate(request);
        accountManager.depositToAccount(userDetails, amount);
    }

    public String retrieveAccountBalance(final ATMUserDetails userDetails) {
        return accountManager.getAccountBalance(userDetails).toString();
    }
}
