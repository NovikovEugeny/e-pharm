package by.pharmsystem.userservice.controller;

import by.pharmsystem.userservice.entity.User;
import by.pharmsystem.userservice.security.SecurityService;
import by.pharmsystem.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

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
        setCookie(user, response);
        return userService.signIn(user.getId(), user.getPassword());
    }

    @PostMapping("/sign-up")
    public User signUp(@RequestBody User user, HttpServletResponse response) {
        setCookie(user, response);
        return userService.signUp(user);
    }

    @PostMapping("/sign-up-employee")
    public void signUpEmpl(@RequestBody User user) {
        userService.signUpEmpl(user);
    }

    @PostMapping("/log-out")
    public void logout(HttpServletResponse response) {
        deleteCookie(response);
    }

    @PutMapping("/change-role/{id}/{role}/")
    public void changeRole(@PathVariable String id, @PathVariable String role) {
        userService.changeRole(id, role);
    }

    private void setCookie(User user, HttpServletResponse response) {
        String token = securityService.setAuthentication(user.getId(), user.getPassword());
        Cookie cookie = new Cookie(tokenName, token);
        cookie.setPath(cookiePath);
        response.addCookie(cookie);
    }

    private void deleteCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie(tokenName, "");
        cookie.setPath(cookiePath);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
