package se.shop.electronicweb.ui;
//Emanuel sleyman
//2024-03-06

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.shop.electronicweb.entity.Orderdetails;
import se.shop.electronicweb.service.OrderService;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public String orders(@RequestParam(name = "username", required = false) String username, Model model) {
        List<Orderdetails> orderdetailsList;
        orderdetailsList = orderService.getOrders(username);
        model.addAttribute("orders", orderdetailsList);
        return "orderhistory";
    }

}
