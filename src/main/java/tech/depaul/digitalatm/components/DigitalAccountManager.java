package tech.depaul.digitalatm.components;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tech.depaul.digitalatm.config.ATMUserDetails;
import tech.depaul.digitalatm.data.model.Account;
import tech.depaul.digitalatm.data.repos.AccountRepository;

import java.math.BigDecimal;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DigitalAccountManager implements AccountManager  {

    private final AccountRepository repository;

    public void depositToAccount(final ATMUserDetails userDetails, final BigDecimal amount) {
        final Long userId = userDetails.getUserId();
        final Account account = findAccountWithUserId(userId);
        final BigDecimal bigDecimal = BigDecimal.valueOf(account.getBalance());
        final BigDecimal updatedAmount = bigDecimal.add(amount);
        account.setBalance(updatedAmount.doubleValue());
        repository.save(account);
    }

    public void withdrawFromAccount(final ATMUserDetails userDetails, final BigDecimal amount) {
        final Long userId = userDetails.getUserId();
        final Account account = findAccountWithUserId(userId);
        final BigDecimal balance = formatBalanceFromAccount(account);
        final BigDecimal subtractedAmount = balance.subtract(amount);
        if (subtractedAmount.doubleValue() < 0.00) {
            throw new DigitalAtmException("Withdrawing this amount would put you in the negative");
        }
        account.setBalance(subtractedAmount.doubleValue());
        repository.save(account);
    }

    public BigDecimal getAccountBalance(final ATMUserDetails userDetails) {
        final Account account = findAccountWithUserId(userDetails.getUserId());
        return formatBalanceFromAccount(account);
    }

    private Account findAccountWithUserId(final Long userID) {
        final Optional<Account> optionalAccount = repository.findDistinctByUserId(userID);
       return optionalAccount.orElseThrow(() -> new DigitalAtmException("Account could not be found"));
    }

    private BigDecimal formatBalanceFromAccount(final Account account) {
        return BigDecimal.valueOf(account.getBalance());
    }


}
