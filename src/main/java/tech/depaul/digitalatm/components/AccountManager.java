package tech.depaul.digitalatm.components;

import tech.depaul.digitalatm.config.ATMUserDetails;

import java.math.BigDecimal;

public interface AccountManager {
    void depositToAccount(final ATMUserDetails userDetails, final BigDecimal amount);
    void withdrawFromAccount(final ATMUserDetails userDetails, final BigDecimal amount);
    BigDecimal getAccountBalance(final ATMUserDetails userDetails);
}
