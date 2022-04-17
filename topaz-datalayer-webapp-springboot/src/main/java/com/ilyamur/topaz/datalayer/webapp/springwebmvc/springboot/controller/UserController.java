package com.ilyamur.topaz.datalayer.webapp.springwebmvc.springboot.controller;


import com.ilyamur.topaz.datalayer.core.entity.User;
import com.ilyamur.topaz.datalayer.core.service.UserFindingService;
import com.ilyamur.topaz.datalayer.webapp.springwebmvc.springboot.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collection;

@Controller
public class UserController {

    @Autowired
    private UserFindingService userFindingService;

    @RequestMapping(value = Constants.Path.ROOT, method = RequestMethod.GET)
    public String root() {
        return Constants.Template.INDEX;
    }

    @RequestMapping(value = Constants.Path.USERS, method = RequestMethod.GET)
    public String users(Model model) {
        Collection<User> users = userFindingService.findAll();
        model.addAttribute(Constants.Param.USERS, users);
        return Constants.Template.USERS;
    }

    @RequestMapping(value = Constants.Path.USERS_EMAIL_SEND, method = RequestMethod.GET)
    public String usersEmailSend(RedirectAttributes redirectAttributes,
                                 @RequestParam(Constants.Param.ID) int id,
                                 @RequestParam(Constants.Param.EMAIL_TEXT) String emailText) {
        User user = userFindingService.findById(id);
        user.sendEmail(emailText);
        redirectAttributes.addFlashAttribute(Constants.Param.USER_LOGIN, user.getLogin());
        redirectAttributes.addFlashAttribute(Constants.Param.EMAIL_TEXT, emailText);
        return "redirect:" + Constants.Path.USERS_EMAIL_REPORT;
    }

    @RequestMapping(value = Constants.Path.USERS_EMAIL_REPORT, method = RequestMethod.GET)
    public String usersEmailReport() {
        return Constants.Template.USERS_EMAIL_REPORT;
    }
}
