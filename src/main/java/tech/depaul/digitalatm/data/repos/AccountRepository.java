package tech.depaul.digitalatm.data.repos;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tech.depaul.digitalatm.data.model.Account;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    Optional<Account> findDistinctByUserId(Long userId);
}
