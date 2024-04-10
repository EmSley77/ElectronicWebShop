package se.shop.electronicweb.ui;
//Emanuel sleyman
//2024-03-08
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.shop.electronicweb.service.AddOrderService;

@Controller
public class RemoveItemController {

    @Autowired
    AddOrderService service;

    //if needed
    @GetMapping("/getbasket")
    public String getBasket(Model model) {
        model.addAttribute("basketItems", service.getBasketItems());
     return "basketpage";
    }

    @PostMapping("/removeitem")
    public String removeItemInBasket(@RequestParam int input, Model model) {
        model.addAttribute("removeitem", service.removeItemFromBasket(input));
        model.addAttribute("basketItems", service.getBasketItems());
        return "basketpage";
    }

    @PostMapping("/removeitemamount")
    public String removeItemAmountInBasket(@RequestParam int input, Model model) {
        model.addAttribute("removeitem", service.removeAmountFromBasket(input));
        model.addAttribute("basketItems", service.getBasketItems());
        return "basketpage";
    }

    @PostMapping("addamount")
    public String addAmount(@RequestParam int input, Model model) {
        model.addAttribute("additem", service.addAmountFromBasket(input));
        model.addAttribute("basketItems", service.getBasketItems());
        return "basketpage";
    }
}
