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

    public static class Param {
        public static final String ID = "id";
        public static final String USERS = "users";
        public static final String USER_LOGIN = "userLogin";
        public static final String EMAIL_TEXT = "emailText";
    }

    @RequestMapping(value = WebUrl.USERS, method = RequestMethod.GET)
    @Transactional
    public String users(Model model) {
        Collection<User> users = userRepository.getAll();
        model.addAttribute(Param.USERS, users);
        return WebUrl.USERS;
    }

    @RequestMapping(value = WebUrl.USERS_EMAIL_SEND, method = RequestMethod.GET)
    @Transactional
    public String usersEmailSend(RedirectAttributes redirectAttributes,
                                 @RequestParam(Param.ID) int id,
                                 @RequestParam(Param.EMAIL_TEXT) String emailText) {
        User user = userRepository.findById(id);
        user.sendEmail(emailText);
        redirectAttributes.addFlashAttribute(Param.USER_LOGIN, user.getLogin());
        redirectAttributes.addFlashAttribute(Param.EMAIL_TEXT, emailText);
        return "redirect:" + WebUrl.USERS_EMAIL_REPORT;
    }

    @RequestMapping(value = WebUrl.USERS_EMAIL_REPORT, method = RequestMethod.GET)
    @Transactional
    public String usersEmailReport(Model model) {
        return WebUrl.USERS_EMAIL_REPORT;
    }
}
