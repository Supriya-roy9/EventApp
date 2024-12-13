package org.thoughtworks.movieBooking.customers.view;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.thoughtworks.movieBooking.App;
import org.thoughtworks.movieBooking.exceptions.UserNameAlreadyExistsException;
import org.thoughtworks.movieBooking.users.repository.UserRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ActiveProfiles("test")
@WithMockUser
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CustomerControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;



    @BeforeEach
    public void beforeEach() {

    }

    @Test
    public void shouldSaveCustomerDetailsWhenSignUpIsInvoked() throws Exception, UserNameAlreadyExistsException {
        final String requestJson = "{" +
                "\"username\": \"JohnSmith2\"," +
                "\"name\": \"John\"," +
                "\"email\": \"john@gmail.com\"," +
                "\"phone\": \"9876543210\"," +
                "\"gender\": \"Male\"," +
                "\"password\": \"John@9876\"" +
                "}";

        mockMvc.perform(post("/customer/register")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson)).andExpect(status().isCreated());
    }

    @Test
    public void shouldThrowUserNameAlreadyExistsExceptionWhenUserAlreadyExists() throws Exception {
        final String requestJson = "{" +
                "\"username\": \"JohnSmith\"," +
                "\"name\": \"John\"," +
                "\"email\": \"john@gmail.com\"," +
                "\"phone\": \"9876543210\"," +
                "\"gender\": \"MALE\"," +
                "\"password\": \"John@9876\"" +
                "}";

        mockMvc.perform(post("/customer/register")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson)).andExpect(status().isCreated());

        mockMvc.perform(post("/customer/register")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson)).andExpect(status().isConflict());

    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenTheUserNameIsEmpty() throws Exception {
        final String requestJson = "{" +
                "\"username\": \"\"," +
                "\"name\": \"John\"," +
                "\"email\": \"john@gmail.com\"," +
                "\"phone\": \"9876543210\"," +
                "\"gender\": \"MALE\"," +
                "\"password\": \"John@9876\"" +
                "}";

        mockMvc.perform(post("/customer/register").contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)).andExpect(status().isBadRequest());
    }

    @Test
    public void shouldThrowEmailAlreadyExistsExceptionWhenEmailIdAlreadyExists() throws Exception {
        final String requestJson1 = "{" +
                "\"username\": \"John1\"," +
                "\"name\": \"John\"," +
                "\"email\": \"john@gmail.com\"," +
                "\"phone\": \"9876543210\"," +
                "\"gender\": \"MALE\"," +
                "\"password\": \"John@9876\"" +
                "}";

        final String requestJson2 = "{" +
                "\"username\": \"John2\"," +
                "\"name\": \"John\"," +
                "\"email\": \"john@gmail.com\"," +
                "\"phone\": \"9876543210\"," +
                "\"gender\": \"MALE\"," +
                "\"password\": \"John@9876\"" +
                "}";

        mockMvc.perform(post("/customer/register").contentType(MediaType.APPLICATION_JSON).content(requestJson1)).andExpect(status().isCreated());

        mockMvc.perform(post("/customer/register").contentType(MediaType.APPLICATION_JSON).content(requestJson2)).andExpect(status().isConflict());

    }

    @Test
    public void shouldThrowPhoneNumberAlreadyTakenExceptionWhenTheUserEntersExistingPhone() throws Exception {
        final String requestJson1 = "{" +
                "\"username\": \"John1\"," +
                "\"name\": \"John\"," +
                "\"email\": \"john@gmail.com\"," +
                "\"phone\": \"9876543210\"," +
                "\"gender\": \"MALE\"," +
                "\"password\": \"John@9876\"" +
                "}";

        final String requestJson2 = "{" +
                "\"username\": \"John2\"," +
                "\"name\": \"John\"," +
                "\"email\": \"john2@gmail.com\"," +
                "\"phone\": \"9876543210\"," +
                "\"gender\": \"MALE\"," +
                "\"password\": \"John@9876\"" +
                "}";

        mockMvc.perform(post("/customer/register").contentType(MediaType.APPLICATION_JSON).content(requestJson1)).andExpect(status().isCreated());

        mockMvc.perform(post("/customer/register").contentType(MediaType.APPLICATION_JSON).content(requestJson2)).andExpect(status().isConflict());

    }


}
