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
            //här ger den vad man söker efter
            findElectronics = electronicService.searchForProducts(search);
        } else {
            //om man inte söker ger den allt
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


}
