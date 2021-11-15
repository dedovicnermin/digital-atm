package tech.depaul.digitalatm.controllers.request;

import lombok.Data;

@Data
public class DigitalATMRequest implements ATMRequest {
    private String amount;
}
