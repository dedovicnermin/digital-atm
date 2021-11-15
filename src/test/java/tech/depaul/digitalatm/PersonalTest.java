package tech.depaul.digitalatm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class PersonalTest {

    @Test
    void throwsExceptionOnAlphaNumeric() {
        Assertions.assertThrows(NumberFormatException.class,  () -> new BigDecimal("a2"));
    }

    @Test
    void throwsExceptionOnNull() {
        String x = null;
        Assertions.assertThrows(NullPointerException.class, () -> new BigDecimal(x));
    }

}
