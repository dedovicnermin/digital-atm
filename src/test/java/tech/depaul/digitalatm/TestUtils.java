package tech.depaul.digitalatm;

import org.springframework.security.core.userdetails.UserDetails;
import tech.depaul.digitalatm.config.ATMUserDetails;
import tech.depaul.digitalatm.controllers.request.DigitalATMRequest;
import tech.depaul.digitalatm.data.model.Account;
import tech.depaul.digitalatm.data.model.User;

public final class TestUtils {
    private TestUtils() {}

    public static ATMUserDetails getTestUserDetails() {
        return new ATMUserDetails(getTestUser());
    }

    public static User getTestUser() {
        return User.builder()
                .id(1L)
                .username("TEST")
                .firstname("testFN")
                .lastname("testLN")
                .password("password")
                .role("USER")
                .build();
    }

    public static DigitalATMRequest getValidDigitalATMRequest() {
        return new DigitalATMRequest("10.00");
    }

    public static DigitalATMRequest getInvalidDigitalAtmRequest() {
        return new DigitalATMRequest("-10.00");
    }

    public static Account getTestAccount() {
        return Account.builder()
                .id(1L)
                .userId(1L)
                .balance(100.00)
                .build();
    }

}
