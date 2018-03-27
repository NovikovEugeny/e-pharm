package by.pharmsystem.userservice.service;

import by.pharmsystem.userservice.entity.User;
import by.pharmsystem.userservice.repository.UserRepository;
import by.pharmsystem.userservice.service.exception.ConflictException;
import by.pharmsystem.userservice.service.exception.NoSuchUser;
import by.pharmsystem.userservice.service.util.ConstantStorage;
import by.pharmsystem.userservice.service.util.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SimpleMailMessage mailMessage;
    @Autowired
    private MailSender mailSender;

    @Override
    public User signIn(String id, String password) {
        User user = userRepository.findOne(id);
        user.setPassword(null);
        return user;
    }

    @Override
    public User signUp(User user) {
        UserValidator.validateSignUp(user);

        if (userRepository.findOne(user.getId()) != null) {
            throw new ConflictException();
        }

        String encPassword = encoder.encode(user.getPassword());
        user.setPassword(encPassword);
        user.setRole(ConstantStorage.CLIENT);

        userRepository.save(user);

        user.setPassword(null);
        return user;
    }

    @Override
    public void signUpEmpl(User user) {
        UserValidator.validateSignUp(user);

        if (userRepository.findOne(user.getId()) != null) {
            throw new ConflictException();
        }

        String encPassword = encoder.encode(user.getPassword());
        user.setPassword(encPassword);
        user.setRole(user.getRole());

        userRepository.save(user);
    }

    @Override
    public void changeRole(String id, String newRole) {
        UserValidator.validateRole(newRole);

        User user = userRepository.findOne(id);

        if (user == null) {
            throw new NoSuchUser();
        }

        user.setRole(newRole);
        userRepository.save(user);
    }
}
