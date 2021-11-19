package tech.depaul.digitalatm.config;

import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import tech.depaul.digitalatm.data.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@EqualsAndHashCode
public class ATMUserDetails implements UserDetails {

    private static final long serialVersionUID = -669094690872875452L;
    private final String username;
    private final String password;
    private final Long userId;
    private final List<GrantedAuthority> authorities = new ArrayList<>();

    public ATMUserDetails(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.userId= user.getId();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getUserId() {
        return this.userId;
    }

}
