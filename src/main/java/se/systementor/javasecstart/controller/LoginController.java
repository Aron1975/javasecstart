package se.systementor.javasecstart.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import se.systementor.javasecstart.model.User;
import se.systementor.javasecstart.services.UserService;

@Controller
@RequiredArgsConstructor

public class LoginController {

    //@Autowired
    private final UserService userService;

    @GetMapping(path="/login")
    public String login(Model model) {
        model.addAttribute("activeFunction", "login");
        return "login";
    }

    @GetMapping(path="/regUser")
    public String showRegForm(Model model) {
        model.addAttribute("activeFunction", "regUser");
        model.addAttribute("user", new User());
        return "regUser";
    }

    @PostMapping("/regUser")
    public String regUser(@ModelAttribute User user, Model model) {
        try {
            userService.registerNewUser(user.getUsername(), user.getPassword());
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "/regUser";
        }
    }
}
