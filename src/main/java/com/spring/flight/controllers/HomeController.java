package com.spring.flight.controllers;

import com.spring.flight.enums.Roles;
import com.spring.flight.exceptions.UserNotCreatedException;
import com.spring.flight.models.User;
import com.spring.flight.services.UserService;
import com.spring.flight.validators.UserInfoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserInfoValidator userInfoValidator;

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/register")
    public String getRegistrationPage(Model model)
    {
        model.addAttribute("user",new User());
        model.addAttribute("availableRoles", Roles.getAllRoleNames());

        return "signup";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model, @RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout){
        if(error!=null)
        {
            model.addAttribute("error", "Invalid userName or Password ");
        }
        if(logout != null)
        {
            model.addAttribute("success", "ypu have been logged out successfully");
        }
        return "login";
    }

    @GetMapping("/access-denied")
    public String accessDenied(){
        return "access-denied";
    }

    @PostMapping("user-create")
    public String registerUser(Model model,
                               @ModelAttribute User user,
                               @RequestParam(value = "selectedRoles",required = false)List<String> selectedRoles){

        List<String> errors= userInfoValidator.validate(user);
        if(!errors.isEmpty()){
            model.addAttribute("error", errors);
            model.addAttribute("user", new User());
            model.addAttribute("availableRoles", Roles.getAllRoleNames());
            return "signup";
        }
        if (selectedRoles == null || selectedRoles.isEmpty()) {
            user.setRoles(Roles.USER.getRoleName());
        } else {
            user.setRoles(String.join(",", selectedRoles));
        }

        try {
            userService.registerUser(user);
        }
        catch (UserNotCreatedException e){
            model.addAttribute("error", e.getMessage());
            model.addAttribute("user", new User());
            model.addAttribute("availableRoles", Roles.getAllRoleNames());
            return "signup";
        }
        model.addAttribute("success", "User with username "+ user.getUserName() + "registered Successfully");
        model.addAttribute("user", new User());
        return "login";
    }
}

