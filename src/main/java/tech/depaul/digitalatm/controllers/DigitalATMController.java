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
import tech.depaul.digitalatm.controllers.request.DigitalATMRequest;


@Controller
@RequiredArgsConstructor
public class DigitalATMController extends BaseController  {

    private final Digi atmService;

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
    public String deposit(final ATMUserDetails userDetails, @ModelAttribute DigitalATMRequest request) {
        return null;
    }


    @GetMapping(value = "/withdraw")
    public String getWithdraw() {
        return "withdraw";
    }

    @PostMapping(value = "/withdraw")
    public String withdraw( @ModelAttribute(value = "atm-request") DigitalATMRequest request) {
        atmService.executeWithdrawOnAccount()
        return null;
    }

    @GetMapping(value = "/balance")
    public String getBalance() {
        return "balance";
    }

    private ATMUserDetails retrieveUserDetails() {
        return (ATMUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
