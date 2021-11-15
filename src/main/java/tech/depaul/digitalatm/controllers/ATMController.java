package tech.depaul.digitalatm.controllers;

import org.springframework.http.ResponseEntity;

public interface ATMController {
    ResponseEntity deposit();
    ResponseEntity withdraw();
    ResponseEntity displayBalance();
}
