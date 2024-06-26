package se.shop.electronicweb.ui;
//Emanuel sleyman
//2024-03-22
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import se.shop.electronicweb.entity.Orderdetails;
import se.shop.electronicweb.enumhelp.Helper;
import se.shop.electronicweb.service.AddOrderService;
import se.shop.electronicweb.service.LoginService;

@Controller
public class LoginController {

    @Autowired
    LoginService service;

    @Autowired
    AddOrderService addOrderService;

    @PostMapping("loginattempt")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {

        String loginResult = service.login(username, password);
        if (loginResult.equals(Helper.LOGIN_GRANTED.getMessage()) && service.getRole(username) == 0) {
            model.addAttribute("login", loginResult);
            return "redirect:/electronicspage.html";
        }
        else if (loginResult.equals(Helper.LOGIN_GRANTED.getMessage()) && service.getRole(username) == 1) {
            model.addAttribute("login", loginResult);
            return "redirect:/adminpanelpage.html";
        }
        else return "redirect:/loginpage.html";
    }

    @PostMapping("createlogin")
    public String createLogin(@RequestParam String name,
                              @RequestParam String lastN,
                              @RequestParam String email,
                              @RequestParam String adress,
                              @RequestParam String username,
                              @RequestParam String password,
                              Model model)
    {
        String newLogin =  service.createLogin(name,lastN,email,adress,username,password);
        model.addAttribute("createlogincred",newLogin);
        if (newLogin.equals(Helper.NEW_LOGIN_CREATED.getMessage()))  {
            return "redirect:/loginpage.html";
        }
        else {
            return "redirect:/loginpage.html";
        }
    }


}
