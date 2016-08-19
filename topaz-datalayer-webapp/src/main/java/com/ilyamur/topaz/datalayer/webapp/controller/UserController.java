package com.ilyamur.topaz.datalayer.webapp.controller;


import com.ilyamur.topaz.datalayer.core.entity.User;
import com.ilyamur.topaz.datalayer.core.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collection;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @Transactional
    public String users(Model model) {
        Collection<User> users = userRepository.getAll();
        model.addAttribute("users", users);
        return "users";
    }

    @RequestMapping(value = "/users/email/send", method = RequestMethod.GET)
    @Transactional
    public String usersEmailSend(RedirectAttributes redirectAttributes,
                                 @RequestParam int id, @RequestParam String emailText) {
        User user = userRepository.findById(id);
        user.sendEmail(emailText);
        redirectAttributes.addFlashAttribute("userLogin", user.getLogin());
        redirectAttributes.addFlashAttribute("emailText", emailText);
        return "redirect:/users/email/report";
    }

    @RequestMapping(value = "/users/email/report", method = RequestMethod.GET)
    @Transactional
    public String usersEmailReport(Model model) {
        return "users/email/report";
    }
}
