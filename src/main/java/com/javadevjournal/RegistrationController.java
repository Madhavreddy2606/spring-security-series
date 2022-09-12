package com.javadevjournal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private static final String REDIRECT = null;
	@Autowired
    private UserService userService;

    @GetMapping("/register")
    public String register(final Model model){
        model.addAttribute("userData", new User());
        return "account/register";
    }

    @PostMapping("/register")
    public String userRegistration(final @Validated  User userData, final BindingResult bindingResult, final Model model) throws UserAlreadyExistException{
        if(bindingResult.hasErrors()){
            model.addAttribute("registrationForm", userData);
            return "account/register";
        }
        userService.register(userData);
        return REDIRECT+"/starter";
    }
}