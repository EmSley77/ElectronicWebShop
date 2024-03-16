package se.shop.electronicweb.service;
//Emanuel sleyman
//2024-03-07

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import se.shop.electronicweb.entity.Customer;
import se.shop.electronicweb.entity.Electronic;
import se.shop.electronicweb.entity.Orderdetails;
import se.shop.electronicweb.entity.Orderline;
import se.shop.electronicweb.repositorys.CustomerRepository;
import se.shop.electronicweb.repositorys.ElectronicRepository;
import se.shop.electronicweb.repositorys.OrderLineRepository;
import se.shop.electronicweb.repositorys.OrderRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@SessionScope
public class AddOrderService {

    @Autowired
    OrderLineRepository orderLineRepository;
    @Autowired
    ElectronicRepository electronicRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderRepository orderRepository;

    private List<Orderdetails> basketItemsOrderDetails = new ArrayList<>();
    private List<Orderline> basketOrderLines = new ArrayList<>();

    public void addToBasket(int id, int amount) {
        List<Electronic> product = electronicRepository.findByIdelectronic(id);
        for (Electronic p : product) {
            Orderdetails orderIn = new Orderdetails();
            orderIn.setTime(Timestamp.valueOf(LocalDateTime.now()));
            orderIn.setQuantity(amount);
            orderIn.setProductid(id);
            orderIn.setTotalcost(p.getPrice() * amount);

            Orderline orderline = new Orderline();
            orderline.setOrderdetailid(orderIn.getIdorderdetails());
            orderline.setProductid(id);
            orderline.setQuantityamount(amount);
            orderline.setStatus("pending");
            orderline.setRecieved("Packing goods");

            basketItemsOrderDetails.add(orderIn);
            basketOrderLines.add(orderline);
        }
    }

    //Order the items
    public Object orderItems(int username) {
        List<Customer> findCustomer = customerRepository.findCustomerByIdcustomer(username);
        if (findCustomer != null && !basketItemsOrderDetails.isEmpty()) {
            for (Customer c : findCustomer) {
                for (Orderdetails orderdetails : basketItemsOrderDetails) {
                    orderdetails.setCustomerid(c.getIdcustomer());
                    orderRepository.save(orderdetails);
                    for (Orderline orderline : basketOrderLines) {
                        if (orderline.getProductid() == orderdetails.getProductid()) {
                            orderline.setOrderdetailid(orderdetails.getIdorderdetails());
                            orderLineRepository.save(orderline);
                        }
                    }
                }
            }
            basketOrderLines.clear();
            basketItemsOrderDetails.clear();
            return "Items were ordered";
        } else {
            return "Basket is empty or customer not found.";
        }
    }

    //print out all the information about order
    public List<Orderdetails> getBasketItemsOrderDetails() {
        return basketItemsOrderDetails;
    }

    public List<Orderdetails> removeItemFromBasket(int input, int amount) {
        for (int i = 0; i< basketItemsOrderDetails.size(); i++) {
            if (input == basketItemsOrderDetails.get(i).getProductid() && amount == basketItemsOrderDetails.get(i).getQuantity()) {
                basketItemsOrderDetails.remove(i);
                break;
            }
        }
        return basketItemsOrderDetails;
    }
}
