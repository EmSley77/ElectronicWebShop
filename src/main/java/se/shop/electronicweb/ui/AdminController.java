package se.shop.electronicweb.ui;
//Emanuel sleyman
//2024-03-23
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.shop.electronicweb.enumhelp.Helper;
import se.shop.electronicweb.service.AddOrderService;
import se.shop.electronicweb.service.AdminService;

@Controller
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    AddOrderService orderService;

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
        if (newLogin.equals(Helper.ADMIN_REG.getMessage()))  {
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
                          @RequestParam  int price,
                          @RequestParam  String color,
                          @RequestParam  String size,
                          @RequestParam  int available,
                          Model model)
    {
        model.addAttribute("add", adminService.addItem(company, categori, name, price, color, size, available));
        return "redirect:/adminpage.html";
    }

    @PostMapping("removefrominventory")
    public String removeItemFromDb(int id, Model model){
        model.addAttribute("removeitem", adminService.removeItem(id));
        model.addAttribute("items",adminService.allElectronics());
        return "allitemsinstorepage";
    }

    @PostMapping("sendpackage")
    public String send(Model model, int id) {
        model.addAttribute("orders", adminService.ordersReady());
        model.addAttribute("packaging", orderService.setStatusSent(id));
        return "orderspage";
    }

    @GetMapping("getorderlines")
    public String getOrder(Model model) {
        model.addAttribute("orders", adminService.ordersReady());
        return "orderspage";
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
        return "redirect:/electronicspage.html";
    }


    @GetMapping("adminpage")
    public String adminPanel(Model model) {
        model.addAttribute("loginpage", " ");
        return "redirect:/adminpage.html";
    }

}
