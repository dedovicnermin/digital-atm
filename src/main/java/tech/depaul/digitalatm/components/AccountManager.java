package tech.depaul.digitalatm.components;

import tech.depaul.digitalatm.config.ATMUserDetails;

import java.math.BigDecimal;

public interface AccountManager {
    void depositToAccount(ATMUserDetails userDetails, BigDecimal amount);
    void withdrawFromAccount(ATMUserDetails userDetails, final BigDecimal amount);
}
