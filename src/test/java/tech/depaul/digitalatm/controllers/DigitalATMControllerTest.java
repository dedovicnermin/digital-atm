package tech.depaul.digitalatm.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContext;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import tech.depaul.digitalatm.service.DigitalATMService;

import java.net.URI;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DigitalATMController.class)
@ExtendWith(MockitoExtension.class)
class DigitalATMControllerTest {

    @Autowired private MockMvc mvc;
    @MockBean private DigitalATMService atmService;



    @WithMockUser(value="spring")
    @Test
    void givenAuthRequestOnValidUser_shouldSucceedWithRedirect() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(new URI("/")).contentType(MediaType.APPLICATION_JSON)).andExpect(status().is(302));
    }

    @WithUserDetails(userDetailsServiceBeanName = "")
    @Test
    void onGetHome_withValidUser_returnsOK() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(new URI("/home"))).andExpect(status().isOk());
    }


}
