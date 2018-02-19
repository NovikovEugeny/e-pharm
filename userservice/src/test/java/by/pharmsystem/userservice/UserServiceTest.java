package by.pharmsystem.userservice;

import by.pharmsystem.userservice.entity.User;
import by.pharmsystem.userservice.repository.UserRepository;
import by.pharmsystem.userservice.service.UserServiceImpl;
import by.pharmsystem.userservice.service.exception.BadRequestException;
import by.pharmsystem.userservice.service.exception.ConflictException;
import by.pharmsystem.userservice.service.util.ConstantStorage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private BCryptPasswordEncoder encoder;
    @Mock
    private SimpleMailMessage mailMessage;
    @Mock
    private MailSender mailSender;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testSignIn() {
        String login = "login";
        String password = "password";

        User testUser = new User();
        testUser.setLogin(login);
        testUser.setPassword(password);

        when(userRepository.findByLogin(login)).thenReturn(testUser);

        User user = userService.signIn(login, password);
        assertEquals(user.getLogin(), testUser.getLogin());
        assertNull(user.getPassword());
    }

    @Test
    public void testSignUpValidation() {
        String correctLogin = "james@gmail.com";
        String incorrectLogin = "james@gmail";

        Map<String, String> data = new HashMap<>();
        data.put(ConstantStorage.NAME, "James");
        data.put(ConstantStorage.LOGIN, incorrectLogin);
        data.put(ConstantStorage.ROLE, "client");
        data.put(ConstantStorage.REGION, "1");
        data.put(ConstantStorage.POLYCLINIC_NUMBER, "12");
        data.put(ConstantStorage.PATIENT_CARD_NUMBER, "123456");

        //check validator working
        boolean validationExc = false;
        try {
            userService.signUp(data);
        } catch (BadRequestException exc) {
            validationExc = true;
        }
        assertEquals(validationExc, true);

        //check email conflict
        boolean conflictEmailExc = false;
        try {
            data.put(ConstantStorage.LOGIN, correctLogin);
            when(userRepository.findByLogin(correctLogin)).thenReturn(new User());

            userService.signUp(data);
        } catch (ConflictException exc) {
            conflictEmailExc = true;
        }
        assertEquals(conflictEmailExc, true);

        //check correct working
        when(userRepository.findByLogin(correctLogin)).thenReturn(null);
        when(encoder.encode("pswd")).thenReturn("test1234");
        userService.signUp(data);
    }
}

