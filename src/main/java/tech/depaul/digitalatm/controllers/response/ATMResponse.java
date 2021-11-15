package tech.depaul.digitalatm.controllers.response;

public interface ATMResponse {
    Boolean isSuccess();
    Integer getErrorCode();
    String getResponseBody();
}
