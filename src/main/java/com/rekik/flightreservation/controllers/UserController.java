package com.rekik.flightreservation.controllers;

import com.rekik.flightreservation.entities.Flight;
import com.rekik.flightreservation.entities.User;
import com.rekik.flightreservation.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/")
    public String showIndex(){
        return "index";
    }

    @RequestMapping("/showreg")
    public String showRegistrationPage(ModelMap modelMap){
        modelMap.addAttribute("user",new User());
        return "login/registerUser";
    }

    @RequestMapping("/showlogin")
    public String showLoginPage(@ModelAttribute("user") User user){
        return "login/login";
    }


    @RequestMapping(value = "/postreg", method = RequestMethod.POST)
    public String register(@ModelAttribute("user") User user){

        userRepository.save(user);
        return "login/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute("user") User user,@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        ModelMap modelMap){
        user = userRepository.findByEmail(email);
        modelMap.addAttribute("flight", new Flight());
        if(user.getPassword().equals(password)){
            return "findflights";
        }else {
            modelMap.addAttribute("msg","Invalid username or password. Please try again");
            return "login/login";
        }

    }



}
