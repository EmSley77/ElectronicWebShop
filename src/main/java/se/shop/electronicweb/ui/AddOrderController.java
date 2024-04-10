package se.shop.electronicweb.ui;
//Emanuel sleyman
//2024-03-05

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.shop.electronicweb.repositorys.ElectronicRepository;
import se.shop.electronicweb.service.AddOrderService;
import se.shop.electronicweb.service.ElectronicService;

import java.util.Objects;

@Controller
public class AddOrderController {
    @Autowired
    ElectronicRepository electronicRepository;

    @Autowired
    AddOrderService addOrderService;

    @PostMapping("/basket")
    public String addToBasket(@RequestParam int id, Model model) {
        if (addOrderService.getAvailability(id) >= 1) {
            addOrderService.addToBasket(id);
            model.addAttribute("basketItems", addOrderService.getBasketItems());
            return "order_formpage";
        }
        else {
            model.addAttribute("electronics", electronicRepository.findAll());
            return "searchresultpage";
        }
    }

    @GetMapping("/addorder")
    public String getOrderInfo(Model model) {
        model.addAttribute("basket", addOrderService.getBasketItems());
        model.addAttribute("totalcost",addOrderService.getTotalCost());
        return "orderpage";
    }


    @PostMapping("/orderpage")
    public String setOrder(Model model, @RequestParam String username, @RequestParam String password) {
        String order = addOrderService.orderItems(username, password);
        if (order.equals("Items were ordered")) {
            model.addAttribute("order", addOrderService.orderItems(username,password));
            return "thankspage";
        }
        else{
            model.addAttribute("basketItems", addOrderService.getBasketItems());
            double cost = addOrderService.getTotalCost();
            model.addAttribute("totalcost", cost);
            return "orderpage";
        }
    }
}




