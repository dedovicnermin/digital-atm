package tech.depaul.digitalatm.controllers.request;

import tech.depaul.digitalatm.data.pojo.ATMOperation;

public interface ATMRequest {
    ATMOperation getOperation();
    void setOperation(ATMOperation operation);

    Double getRequestAmount();
    void setRequestAmount();
}
