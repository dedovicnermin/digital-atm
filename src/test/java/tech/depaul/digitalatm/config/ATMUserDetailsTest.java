package tech.depaul.digitalatm.config;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import tech.depaul.digitalatm.data.model.User;

import static org.assertj.core.api.Assertions.assertThat;

class ATMUserDetailsTest {

    @Test
    void onConstruction_containsOneGrantedAuthority() {
        final User user = User.builder()
                .role("user")
                .username("heisenberg")
                .password("password")
                .build();
        final ATMUserDetails atmUserDetails = new ATMUserDetails(user);
        final SimpleGrantedAuthority expected = new SimpleGrantedAuthority(user.getRole());
        assertThat(atmUserDetails.getAuthorities()).hasSize(1);
        assertThat(atmUserDetails.getAuthorities()).anyMatch(grantedAuthority -> grantedAuthority.equals(expected));
    }

    @Test
    void onConstructionContainsCorrectState() {
        final User user = User.builder()
                .role("user")
                .username("heisenberg")
                .password("password")
                .build();
        final ATMUserDetails atmUserDetails = new ATMUserDetails(user);
        assertThat(atmUserDetails.getPassword()).isEqualTo(user.getPassword());
        assertThat(atmUserDetails.getUsername()).isEqualTo(user.getUsername());
        assertThat(atmUserDetails.isAccountNonExpired()).isTrue();
        assertThat(atmUserDetails.isCredentialsNonExpired()).isTrue();
        assertThat(atmUserDetails.isCredentialsNonExpired()).isTrue();
        assertThat(atmUserDetails.isEnabled()).isTrue();
        assertThat(atmUserDetails.isAccountNonLocked()).isTrue();
        assertThat(atmUserDetails.getUserId()).isEqualTo(user.getId());
    }
}
