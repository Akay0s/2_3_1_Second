package entry1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import entry1.model.User;
import entry1.service.UserService;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home() {
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @GetMapping("/user/new")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new User());
        return "users-form";
    }

//    @PostMapping("/user/save")
//    public String saveUser(@ModelAttribute("user") User user) {
//        userService.save(user);
//        return "redirect:/users";
//    }

    @PostMapping("/user/save")
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        try {
            userService.save(user);
            return ResponseEntity.ok("redirect:/users");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Ошибка сохранения: " + e.getMessage());
        }
    }

    @GetMapping("/user/edit")
    public String showEditForm(@RequestParam("id") Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "users-form";
    }

    @PostMapping("/user/update")
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        try {
            userService.update(user);
            return ResponseEntity.ok("redirect:/users");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Ошибка обновления: " + e.getMessage());
        }
    }

    @PostMapping("/user/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.delete(id);
        return "redirect:/users";
    }

    @GetMapping("/user_data")
    @ResponseBody
    public User getUserData(@RequestParam("id") Long id) {
        return userService.findById(id);
    }

    @GetMapping("/users_data")
    @ResponseBody
    public List<User> getUsersData() {
        return userService.findAll();
    }
}
