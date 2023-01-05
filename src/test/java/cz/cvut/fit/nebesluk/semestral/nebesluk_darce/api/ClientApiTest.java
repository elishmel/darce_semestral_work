package cz.cvut.fit.nebesluk.semestral.nebesluk_darce.api;

import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain.Client;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.exceptions.EntityAlreadyExistsException;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.service.ClientService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
// Api test (end to end i think) - Simulates call on the REST API
@WebMvcTest(ClientController.class)
@Import({})
public class ClientApiTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ClientService clientService;

    @Test
    public void RepeatedRegistrationOfUserResultsInException() throws Exception{
        Client client = new Client(0,"Test","Test", LocalDateTime.now(),LocalDateTime.now(),null);

        Mockito.when(clientService.Create(client)).thenThrow(EntityAlreadyExistsException.class);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/client/auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                "{\"authorId\":\"0\",\"name\":\"Kolac\",\"description\":\"Standardni kolac\",\n\"tags\":[],\n\"images\":[0]\n}"
                        ))
                .andExpect(MockMvcResultMatchers.status().isConflict());
    }

}
