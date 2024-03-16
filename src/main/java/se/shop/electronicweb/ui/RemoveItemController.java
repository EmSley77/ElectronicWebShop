package se.shop.electronicweb.ui;
//Emanuel sleyman
//2024-03-08
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.shop.electronicweb.service.AddOrderService;

@Controller
public class RemoveItemController {

    @Autowired
    AddOrderService addOrderService;

    @PostMapping("/removeitem")
    public String removeItemInBasket(@RequestParam(name = "input") int input, Model model) {
        model.addAttribute("removeitem", addOrderService.removeItemFromBasket(input));
        model.addAttribute("basketItems", addOrderService.getBasketItemsOrderDetails());
        return "order_form";
    }
}
