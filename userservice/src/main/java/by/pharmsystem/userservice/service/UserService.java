package by.pharmsystem.userservice.service;

import by.pharmsystem.userservice.entity.User;

import java.util.Map;

public interface UserService {

    User signIn(String login, String password);

    void signUp(Map<String, String> data);

    void delete(String email);

    void changeRole(String email, String newRole);

    void changeLogin(Map<String, String> data);
}
