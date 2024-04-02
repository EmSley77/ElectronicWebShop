package se.shop.electronicweb.ui;
//Emanuel sleyman
//2024-03-05

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.shop.electronicweb.entity.Electronic;
import se.shop.electronicweb.service.ElectronicService;
import se.shop.electronicweb.service.OrderService;


import java.util.List;


@Controller
public class ElectronicController {

    @Autowired
    OrderService orderService;

    @Autowired
    ElectronicService electronicService;


    @GetMapping("/electronics")
    public String getProduct(@RequestParam(name = "search", required = false) String search, Model model) {
        List<Electronic> findElectronics;
        if (search != null && !search.isEmpty()) {
            findElectronics = electronicService.searchForProducts(search);
        } else {
            findElectronics = electronicService.getAll();
        }
        model.addAttribute("electronics", findElectronics);

        return "searchresultpage";
    }

    @GetMapping("/search")
    public String getSearch(Model model) {
        List<Electronic> electronics = electronicService.getAll();
        model.addAttribute("returnsearch", " ");

        model.addAttribute("electronics", electronics);

        return "searchresultpage";
    }

    @GetMapping("homescreen")
    public String homePage() {
        return "redirect:/loginpage.html";
    }

    @GetMapping("/phones")
    public String getPhones(Model model) {
        if (!electronicService.findPhones().isEmpty()) {
            model.addAttribute("phonelist", electronicService.findPhones());
            return "phonepage";
        } else return "redirect:/electronicspage.html";

    }
    @GetMapping("/accessories")
    public String getAccessories(Model model) {
        if (!electronicService.findAccesories().isEmpty()) {
            model.addAttribute("accessorieslist", electronicService.findAccesories());
            return "accesoriespage";
        } else return "redirect:/electronicspage.html";

    }
    @GetMapping("/tv")
    public String getTv(Model model) {
        if (!electronicService.findTvs().isEmpty()) {
            model.addAttribute("tvlist", electronicService.findTvs());
            return "tvpage";
        } else return "redirect:/electronicspage.html";

    }
    @GetMapping("/computers")
    public String getComputers(Model model) {
        if (!electronicService.findComputers().isEmpty()) {
            model.addAttribute("computerlist", electronicService.findComputers());
            return "computerspage";
        } else return "redirect:/electronicspage.html";
    }

    @GetMapping("/videogame")
    public String getVideoGame(Model model) {
        if (!electronicService.findVideoGame().isEmpty()) {
            model.addAttribute("videoglist", electronicService.findVideoGame());
            return "videogamepage";
        } else return "redirect:/electronicspage.html";
    }


}
