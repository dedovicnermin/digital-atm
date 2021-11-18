package tech.depaul.digitalatm.controllers;

import lombok.RequiredArgsConstructor;
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
        final ATMUserDetails userDetails = getCurrentUser();
        model.addAttribute("username", userDetails.getUsername());
        return "home";
    }



    @GetMapping(value = "/deposit")
    public String getDeposit(final Model model) {
        model.addAttribute("digitalAtmRequest", new DigitalATMRequest());
        return "deposit";
    }

    @PostMapping(value = "/deposit")
    public String deposit(@ModelAttribute DigitalATMRequest digitalAtmRequest, final Model model) {
        final ATMUserDetails atmUserDetails = getCurrentUser();
        service.executeDepositOnAccount(digitalAtmRequest,atmUserDetails);
        model.addAttribute("message", getSuccessMessage(atmUserDetails, digitalAtmRequest, ATMOperation.DEPOSIT));
        return "success";
    }




    @GetMapping(value = "/withdraw")
    public String getWithdraw(final Model model) {
        model.addAttribute("digitalAtmRequest", new DigitalATMRequest());
        return "withdraw";
    }

    @PostMapping(value = "/withdraw")
    public String withdraw(@ModelAttribute DigitalATMRequest digitalAtmRequest, final Model model) {
        final ATMUserDetails atmUserDetails = getCurrentUser();
        service.executeWithdrawOnAccount(digitalAtmRequest, atmUserDetails);
        model.addAttribute("message", getSuccessMessage(atmUserDetails, digitalAtmRequest, ATMOperation.WITHDRAW));
        return "success";
    }



    @GetMapping(value = "/balance")
    public String getBalance(final Model model)
    {
        final ATMUserDetails currentUser = getCurrentUser();
        final String s = service.retrieveAccountBalance(currentUser);
        model.addAttribute("balance", s);
        model.addAttribute("username", currentUser.getUsername().toUpperCase());
        return "balance";
    }




    private String getSuccessMessage(ATMUserDetails userDetails, ATMRequest request, ATMOperation operation) {
        return operation.name() + " $" + request.getAmount()
                +  " from " + userDetails.getUsername() + "'s account was successful";
    }
}
