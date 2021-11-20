package tech.depaul.digitalatm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

@SpringBootTest(classes = DigitalAtmApplication.class)
class DigitalAtmApplicationTests {

    @Test
    void contextLoads() {
        Assertions.assertTimeout(Duration.ofSeconds(5), () -> DigitalAtmApplication.main(new String[]{}));
    }

}
