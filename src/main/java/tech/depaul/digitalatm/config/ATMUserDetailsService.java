package tech.depaul.digitalatm.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tech.depaul.digitalatm.data.model.User;
import tech.depaul.digitalatm.data.repos.UserRepository;


@Service
@RequiredArgsConstructor
@Slf4j
public class ATMUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userRepository.findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User details not found for the user : " + username)
                );
        log.info(user.toString());
        return new ATMUserDetails(user);
    }
}
