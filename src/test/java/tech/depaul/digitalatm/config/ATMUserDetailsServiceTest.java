package tech.depaul.digitalatm.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import tech.depaul.digitalatm.data.model.User;
import tech.depaul.digitalatm.data.repos.UserRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ATMUserDetailsServiceTest {

    @Mock
    private UserRepository mockRepo;

    private ATMUserDetailsService atmUserDetailsService;

    @BeforeEach
    void setup() {
        atmUserDetailsService = new ATMUserDetailsService(mockRepo);
    }

    @Test
    void loadByUserNameTest() {
        final User user = User.builder()
                .role("user")
                .username("heisenberg")
                .password("password")
                .build();
        final ATMUserDetails atmUserDetails = new ATMUserDetails(user);

        when(mockRepo.findByUsername(anyString())).thenReturn(Optional.of(user));

        assertThat(atmUserDetailsService.loadUserByUsername(user.getUsername()))
                .isEqualTo(atmUserDetails);
    }

    @Test
    void whenUsernameNotInSystem_throwsException() {
        when(mockRepo.findByUsername(anyString())).thenReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class, () -> atmUserDetailsService.loadUserByUsername("notInDBUsername"));
    }
}
