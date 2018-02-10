package by.pharmsystem.userservice.controller;

import by.pharmsystem.userservice.entity.User;
import by.pharmsystem.userservice.security.SecurityService;
import by.pharmsystem.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
public class UserController {

    @Value("${token.name}")
    private String tokenName;
    @Value("${cookie.path}")
    private String cookiePath;

    @Autowired
    private UserService userService;
    @Autowired
    private SecurityService securityService;

    @PostMapping("/sign-in")
    public User signIn(@RequestBody User user, HttpServletResponse response) {
        String token = securityService.setAuthentication(user.getLogin(), user.getPassword());
        Cookie cookie = new Cookie(tokenName, token);
        cookie.setPath(cookiePath);
        response.addCookie(cookie);
        return userService.signIn(user.getLogin(), user.getPassword());
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody Map<String, String> data, HttpServletResponse response) {
        /*data содержит:
        -name
        -login(email)
        -role
        -регион(1 цифра)
        -номер поликлиники(2 цифры)
        -номер карты(6 цифр)*/
        userService.signUp(data);
    }

    @PostMapping("/log-out")
    public void logout(HttpServletResponse response) {
        Cookie cookie = new Cookie(tokenName, "");
        cookie.setPath(cookiePath);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    @DeleteMapping("/delete-user/{email}/")
    public void delete(@PathVariable String email) {
        userService.delete(email);
    }

    @PatchMapping("/change-role/{email}/{role}/")
    public void changeRole(@PathVariable String email, @PathVariable String role) {
        userService.changeRole(email, role);
    }

    @PatchMapping("/change-login")
    public void changeLogin(@RequestBody Map<String, String> data) {
        userService.changeLogin(data);
    }
}
