package se.shop.electronicweb.ui;
//Emanuel sleyman
//2024-03-05

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.shop.electronicweb.service.AddOrderService;

@Controller
public class AddOrderController {

    @Autowired
    AddOrderService addOrderService;

    LoginController c = new LoginController();

    @PostMapping("/basket")
    public String addToBasket(@RequestParam(name = "id") int id,
                              Model model) {
        addOrderService.addToBasket(id);
        model.addAttribute("basketItems", addOrderService.getBasketItems());
        return "order_formpage";
    }


    @PostMapping("/addorder")
    public String doSetOrder(@RequestParam int personId, Model model) {
        personId = c.id;
        model.addAttribute("addorder",addOrderService.orderItems(personId));
        return "thankspage";
    }



}