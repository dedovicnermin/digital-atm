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
public class DigitalAccountManager  {

    private final AccountRepository repository;


    public boolean depositToAccount(final ATMUserDetails userDetails, final BigDecimal amount) {
        return false;
    }


    public boolean withdrawFromAccount(final ATMUserDetails userDetails, final BigDecimal amount) {
        final Long userId = userDetails.getUserId();
        final Optional<Account> optionalAccount = repository.findDistinctByUserId(userId);
        final Account account = optionalAccount.orElseThrow(() -> new DigitalAtmException("Account could not be found"));
        final BigDecimal bigDecimal = BigDecimal.valueOf(account.getBalance());
        final BigDecimal subtractedAmount = bigDecimal.subtract(amount);
        if (subtractedAmount.doubleValue() < 0.00) {
            throw new DigitalAtmException("Withdrawing this amount would put you in the negative");
        }

        account.setBalance(subtractedAmount.doubleValue());
        repository.save(account);
        return true;
    }
}
