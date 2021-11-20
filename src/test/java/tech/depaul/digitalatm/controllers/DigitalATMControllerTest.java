package tech.depaul.digitalatm.controllers;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.servlet.view.RedirectView;
import tech.depaul.digitalatm.TestUtils;
import tech.depaul.digitalatm.config.ATMUserDetails;
import tech.depaul.digitalatm.controllers.request.DigitalATMRequest;
import tech.depaul.digitalatm.service.DigitalATMService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DigitalATMControllerTest {

    private static final String HOME = "home";
    private static final String DEPOSIT = "deposit";
    private static final String WITHDRAW = "withdraw";
    private static final String BALANCE = "balance";
    private static final String SUCCESS = "success";



    @Mock private DigitalATMService mockAtmService;
    @Mock private SecurityService mockSecurityService;

    private Model model;
    private DigitalATMController controller;

    @BeforeEach
    void setModel() {
        model = new ExtendedModelMap();
        controller = new DigitalATMController(mockAtmService, mockSecurityService);
    }


    @Test
    void whenRedirectToHome_returnsCorrectRedirect() {
        final RedirectView actual = controller.redirectToHome();
        assertThat(actual).isNotNull();
        assertThat(actual.getUrl()).isEqualTo("/home");
    }

    @Test
    void whenRedirectedToHome_withValidUser_returnsHomeViewWithModelPopulated() {
        when(mockSecurityService.getCurrentUser()).thenReturn(TestUtils.getTestUserDetails());
        final String actual = controller.home(model);
        assertThat(model.getAttribute("username")).isEqualTo("TEST");
        assertThat(actual).isEqualTo(HOME);

    }

    @Test
    void whenUserRetrievesDepositPage_controllerReturnsCorrectView() {
        assertThat(controller.getDeposit(model)).isEqualTo(DEPOSIT);
    }

    @Test
    void whenUserSubmitsDeposit_withValidRequest_ThenReturnsSuccess() {
        final DigitalATMController controller = new DigitalATMController(mockAtmService, mockSecurityService);
        final ATMUserDetails testUserDetails = TestUtils.getTestUserDetails();
        final DigitalATMRequest atmRequest = new DigitalATMRequest("10.00");

        when(mockSecurityService.getCurrentUser()).thenReturn(testUserDetails);
        doNothing().when(mockAtmService).executeDepositOnAccount(atmRequest,testUserDetails);

        final String actual = controller.deposit(atmRequest, model);
        assertThat(actual).isEqualTo(SUCCESS);
        assertThat(model.getAttribute("message")).isEqualTo("DEPOSIT $10.00 from TEST's account was successful");
    }

    @Test
    void whenValidUserDirectsToWithdraw_controllerReturnsCorrectView() {
        assertThat(controller.getWithdraw(model)).isEqualTo(WITHDRAW);
        assertThat(model.getAttribute("digitalAtmRequest")).isEqualTo(new DigitalATMRequest(null));

    }

    @Test
    void whenValidUserDirectsToWithDraw_andPostsValidRequest_controllerReturnsCorrectView() {
        final DigitalATMRequest digitalATMRequest = new DigitalATMRequest("10.00");
        final ATMUserDetails testUserDetails = TestUtils.getTestUserDetails();

        when(mockSecurityService.getCurrentUser()).thenReturn(testUserDetails);
        doNothing().when(mockAtmService).executeWithdrawOnAccount(digitalATMRequest,testUserDetails);

        final String actual = controller.withdraw(digitalATMRequest, model);
        assertThat(actual).isEqualTo(SUCCESS);
        assertThat(model.getAttribute("message")).isEqualTo("WITHDRAW $10.00 from TEST's account was successful");
    }

    @Test
    void whenValidUserDirectsToViewBalance_isPresentedCorrectViewWithBalance() {
        final ATMUserDetails testUserDetails = TestUtils.getTestUserDetails();
        final String expectedBalance = "100.00";

        when(mockSecurityService.getCurrentUser()).thenReturn(testUserDetails);
        when(mockAtmService.retrieveAccountBalance(testUserDetails)).thenReturn(expectedBalance);

        final String actual = controller.getBalance(model);
        assertThat(actual).isEqualTo(BALANCE);
        assertThat(model.getAttribute("balance")).isEqualTo(expectedBalance);
        assertThat(model.getAttribute("username")).isEqualTo(testUserDetails.getUsername());

    }





}
