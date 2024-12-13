package org.thoughtworks.movieBooking.users.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.thoughtworks.movieBooking.App;
import org.thoughtworks.movieBooking.users.repository.User;
import org.thoughtworks.movieBooking.users.repository.UserRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserControllerIntegrationTest {

    private static final Logger log = LoggerFactory.getLogger(UserControllerIntegrationTest.class);
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void beforeEach(){
        userRepository.deleteAll();
    }

    @AfterEach
    public void afterEach(){
        userRepository.deleteAll();
    }

    @Test
    public void shouldLoginSuccessfullyWhenTheUserProvidesRightCredentials() throws Exception {
        String encodedPassword=passwordEncoder.encode("TestPassword@9");
        User user=new User("testUser",encodedPassword,"ROLE_USER");
        userRepository.save(user);

        final String requestJson="{"+
                "\"username\": \"testUser\","+
                "\"password\": \"TestPassword@9\""+
                "}";
        mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON).content(requestJson)).andExpect(status().isOk());
    }

    @Test
    public void shouldNotLoginTheUserWhenTheUserProvidesWrongCredentials() throws Exception {
        String encodedPassword=passwordEncoder.encode("TestPassword@9");
        User user=new User("testUser",encodedPassword,"ROLE_USER");
        userRepository.save(user);

        final String requestJson="{"+
                "\"username\": \"testUser\","+
                "\"password\": \"TestPassword@10\""+
                "}";
        mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON).content(requestJson)).andExpect(status().isForbidden());
    }

    @Test
    public void shouldThrowMethodArgumentIllegalExceptionWhenTheUserProvidesEmptyUsername() throws Exception {
        String encodedPassword=passwordEncoder.encode("TestPassword@9");
        User user=new User("testUser",encodedPassword,"ROLE_USER");
        userRepository.save(user);

        final String requestJson="{"+
                "\"username\": \"\","+
                "\"password\": \"TestPassword@10\""+
                "}";
        mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON).content(requestJson)).andExpect(status().isBadRequest());
    }

    @Test
    public void shouldThrowMethodArgumentIllegalExceptionWhenTheUserProvidesEmptyPassword() throws Exception {
        String encodedPassword=passwordEncoder.encode("TestPassword@9");
        User user=new User("testUser",encodedPassword,"ROLE_USER");
        userRepository.save(user);

        final String requestJson="{"+
                "\"username\": \"testUser\","+
                "\"password\": \"\""+
                "}";
        mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON).content(requestJson)).andExpect(status().isBadRequest());
    }


}
