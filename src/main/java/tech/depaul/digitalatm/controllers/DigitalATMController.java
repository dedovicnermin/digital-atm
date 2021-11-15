package tech.depaul.digitalatm.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import tech.depaul.digitalatm.config.ATMUserDetails;
import tech.depaul.digitalatm.controllers.request.ATMRequest;
import tech.depaul.digitalatm.controllers.request.DigitalATMRequest;
import tech.depaul.digitalatm.data.pojo.ATMOperation;
import tech.depaul.digitalatm.service.DigitalATMService;


@Controller
@RequiredArgsConstructor
public class DigitalATMController extends BaseController  {

    private final DigitalATMService service;

    @GetMapping(value = "/")
    public RedirectView redirectToHome() {
        return new RedirectView("/home");
    }

    @GetMapping(value = "home")
    public String home(final Model model) {
        final ATMUserDetails userDetails = retrieveUserDetails();
        model.addAttribute("username", userDetails.getUsername());
        return "home";
    }



    @GetMapping(value = "/deposit")
    public String getDeposit() {
        return "deposit";
    }

    @PostMapping(value = "/deposit")
    public String deposit(@ModelAttribute DigitalATMRequest request, final Model model) {
        final ATMUserDetails atmUserDetails = retrieveUserDetails();
        service.executeDepositOnAccount(request,atmUserDetails);
        model.addAttribute("message", getSuccessMessage(atmUserDetails, request, ATMOperation.DEPOSIT));
        return "success";
    }




    @GetMapping(value = "/withdraw")
    public String getWithdraw() {
        return "withdraw";
    }

    @PostMapping(value = "/withdraw")
    public String withdraw( @ModelAttribute(value = "atm-request") DigitalATMRequest request, final Model model) {
        final ATMUserDetails atmUserDetails = retrieveUserDetails();
        service.executeWithdrawOnAccount(request, atmUserDetails);
        model.addAttribute("message", getSuccessMessage(atmUserDetails, request, ATMOperation.WITHDRAW));
        return "success";
    }



    @GetMapping(value = "/balance")
    public String getBalance() {
        return "balance";
    }


    private ATMUserDetails retrieveUserDetails() {
        return (ATMUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    private String getSuccessMessage(ATMUserDetails userDetails, ATMRequest request, ATMOperation operation) {
        return operation.name() + " $" + request.getAmount()
                +  " from " + userDetails.getUsername() + "'s account was successful";
    }
}
