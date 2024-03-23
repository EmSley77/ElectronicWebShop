package se.shop.electronicweb.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.shop.electronicweb.service.AdminService;

@Controller
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping("createadmin")
    public String createLogin(@RequestParam String name,
                              @RequestParam String lastN,
                              @RequestParam String email,
                              @RequestParam String adress,
                              @RequestParam String username,
                              @RequestParam String password,
                              Model model)
    {
        String newLogin =  adminService.createAdmin(name,lastN,email,adress,username,password);
        model.addAttribute("createlogin",newLogin);
        if (newLogin.equals("Admin created, welcome"))  {
            return "redirect:/adminpage.html";
        } else {
            return "redirect:/loginpage.html";
        }
    }

    @GetMapping("order")
    public String getOrders(@RequestParam String username, Model model) {
        model.addAttribute("orderresult", adminService.getorders(username));
        return "ordertreatmentpage";
    }

    @PostMapping("additem")
    public String additem(@RequestParam  String company,
                          @RequestParam  String categori,
                          @RequestParam  String name,
                          @RequestParam  double price,
                          @RequestParam  String color,
                          @RequestParam  String size,
                          Model model)
    {
        model.addAttribute("add", adminService.addItem(company, categori, name, price, color, size));
        return "redirect:/adminpage.html";
    }
    @PostMapping("remove")
    public String removeItem(int id, Model model){
        model.addAttribute("removeitem", adminService.removeItem(id));
        adminService.allElectronics();
        return "redirect:/adminpage.html";
    }

    @GetMapping("all")
    public String getAllElectronics(Model model) {
        model.addAttribute("items", adminService.allElectronics());
        return "allitemsinstorepage";
    }

    @GetMapping("loginpage")
    public String loginPage(Model model) {
        model.addAttribute("loginpage", " ");
        return "redirect:/loginpage.html";
    }
    @GetMapping("webpage")
    public String webPage(Model model) {
        model.addAttribute("loginpage", " ");
        return "redirect:/electronics.html";
    }


    @GetMapping("adminpage")
    public String adminPanel(Model model) {
        model.addAttribute("loginpage", " ");
        return "redirect:/adminpage.html";
    }

}
