package by.pharmsystem.userservice.service;

import by.pharmsystem.userservice.entity.User;

public interface UserService {

    User signIn(String id, String password);

    User signUp(User user);

    void signUpEmpl(User user);

    void changeRole(String id, String newRole);
}
