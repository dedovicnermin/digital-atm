package tech.depaul.digitalatm.service;

import org.springframework.stereotype.Service;
import tech.depaul.digitalatm.controllers.request.DigitalATMRequest;

import java.math.BigDecimal;

@Service
public class DigitalATMService  {

    public void executeDepositOnAccount() {

    }


    public boolean executeWithdrawOnAccount(final DigitalATMRequest request) {
        try {
            final BigDecimal dec = new BigDecimal(request.getAmount());
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }

        return false;
    }


    public void retrieveAccountBalance() {

    }
}
