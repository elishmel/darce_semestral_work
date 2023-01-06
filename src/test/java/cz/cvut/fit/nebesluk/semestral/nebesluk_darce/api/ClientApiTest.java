package cz.cvut.fit.nebesluk.semestral.nebesluk_darce.api;

import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.api.dto.client.ClientMapper;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.api.dto.client.NewClientDto;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain.Client;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.exceptions.EntityAlreadyExistsException;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.repository.ImageRepository;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.service.ClientService;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.service.ImageService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
// Api test (end to end i think) - Simulates call on the REST API
@AutoConfigureMockMvc
@ComponentScan("cz.cvut.fit")
@SpringBootTest
public class ClientApiTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ClientService clientService;

    @Test
    public void RepeatedRegistrationOfUserResultsInException() throws Exception{

        Client client = new Client();
        client.setUsername("Test");
        client.setRealName("Test");
        client.setProfilePicture(null);

        Mockito.when(clientService.Register(client, "1234")).thenThrow(EntityAlreadyExistsException.class);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/client/auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                "{\n\"password\": \"1234\",\n\"username\": \"Test\",\n\"realName\": \"Test\",\n\"image\": null\n}"))
                .andExpect(MockMvcResultMatchers.status().isConflict());
    }

}
