package tech.depaul.digitalatm.controllers.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DigitalATMRequest implements ATMRequest {
    private String amount;
}
