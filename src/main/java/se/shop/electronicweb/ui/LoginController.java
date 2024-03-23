package se.shop.electronicweb.ui;
//Emanuel sleyman
//2024-03-22
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import se.shop.electronicweb.service.LoginService;

@Controller
public class LoginController {

    @Autowired
    LoginService service;


    @PostMapping("loginattempt")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {

        String loginResult = service.login(username, password);
        if (loginResult.equals("Login granted, welcome:") && service.getRole(username) == 0) {
            model.addAttribute("login", loginResult);
            return "redirect:/electronics.html";
        }
        else if (loginResult.equals("Login granted, welcome:") && service.getRole(username) == 1) {
            model.addAttribute("login", loginResult);
            return "redirect:/adminpage.html";
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
        if (newLogin.equals("Login credentials created, welcome"))  {
            return "redirect:/loginpage.html";
        } else {
            return "redirect:/loginpage.html";
        }
    }


}
