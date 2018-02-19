package by.pharmsystem.userservice.service;

import by.pharmsystem.userservice.entity.User;
import by.pharmsystem.userservice.repository.UserRepository;
import by.pharmsystem.userservice.service.exception.ConflictException;
import by.pharmsystem.userservice.service.exception.NoSuchEmail;
import by.pharmsystem.userservice.service.util.ConstantStorage;
import by.pharmsystem.userservice.service.util.generator.IdGenerator;
import by.pharmsystem.userservice.service.util.generator.PasswordGenerator;
import by.pharmsystem.userservice.service.util.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Value("${change.role.message}")
    private String changeRoleMessage;
    @Value("${delete.account.message}")
    private String deleteAccountMessage;
    @Value("${registration.message}")
    private String registrationMessage;

    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SimpleMailMessage mailMessage;
    @Autowired
    private MailSender mailSender;

    @Override
    public User signIn(String login, String password) {
        User user = userRepository.findByLogin(login);
        user.setPassword(null);
        return user;
    }

    @Override
    public void signUp(Map<String, String> data) {
        /*админ вводит имя, email, роль
        система генерирует id и пароль
        и если регистрация прошла успешно,
        происходит отправка клиенту письма,
        что он зарегистрирован и может забрать
        конверт с эл.картой и паролем от системы
        этот же пароль явл. паролем от эл. карты*/

        UserValidator.validateSignUp(data);

        if (userRepository.findByLogin(data.get(ConstantStorage.LOGIN)) != null) {
            throw new ConflictException();
        }

        User user = new User();
        user.setId(IdGenerator.generate(data));
        user.setName(data.get(ConstantStorage.NAME));
        user.setLogin(data.get(ConstantStorage.LOGIN));
        user.setRole(data.get(ConstantStorage.ROLE));

        String password = PasswordGenerator.generate(user);
        System.out.println(password);
        String encPassword = encoder.encode(password);
        user.setPassword(encPassword);

        userRepository.save(user);

        //TODO utf in message from properties file and remove unused code
        sendEmail(registrationMessage, user.getLogin());
    }

    @Override
    public void delete(String email) {
        User user = userRepository.findByLogin(email);

        if (user == null) {
            throw new NoSuchEmail();
        }

        userRepository.deleteByLogin(email);
        sendEmail(deleteAccountMessage, email);
    }

    @Override
    public void changeRole(String email, String newRole) {
        UserValidator.validateRole(newRole);

        User user = userRepository.findByLogin(email);

        if (user == null) {
            throw new NoSuchEmail();
        }

        user.setRole(newRole);
        userRepository.save(user);

        sendEmail(changeRoleMessage + " " + newRole, email);
    }

    @Override
    public void changeLogin(Map<String, String> data) {
        String login = data.get(ConstantStorage.LOGIN);
        String newLogin = data.get(ConstantStorage.NEW_LOGIN);
        String password = data.get(ConstantStorage.PASSWORD);

        UserValidator.validateLogin(newLogin);

        User user = userRepository.findByLogin(login);

        if (user == null || !encoder.matches(password, user.getPassword())) {
            throw new NoSuchEmail();
        }

        user.setLogin(newLogin);
        userRepository.save(user);
    }

    private void sendEmail(String text, String email) {
        mailMessage.setText(text);
        mailMessage.setTo(email);
        mailSender.send(mailMessage);
    }
}
