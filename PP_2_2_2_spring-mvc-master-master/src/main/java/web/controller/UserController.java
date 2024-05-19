package web.controller;

import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showAllUsers(Model model) {
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("allUsers", allUsers);
        return "all-users";
    }

    @GetMapping("/add")
    public String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user-info";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String updateUserInfo(@RequestParam("userId") int id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "user-info";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("userId") int id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}