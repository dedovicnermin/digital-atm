package tech.depaul.digitalatm.service;

public interface ATMService {
    void executeDepositOnAccount();
    void executeWithdrawOnAccount();
    void retrieveAccountBalance();
}
