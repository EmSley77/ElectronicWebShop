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

    List<Orderdetails> basketDetails = new ArrayList<>();
    List<Orderline> basketOrderLines = new ArrayList<>();

    public int getTotalAmount() {
        int totalAmount=0;
        for (Orderdetails orderdetail : basketDetails) {
            totalAmount += orderdetail.getQuantity();
        }
        return totalAmount;
    }

    public double getTotalCost() {
        double totalCost=0;
        for (Orderdetails orderdetail : basketDetails) {
            totalCost += orderdetail.getTotalcost();
        }
        return totalCost;
    }

    public void addToBasket(int id) {
        List<Electronic> products = electronicRepository.findByIdelectronic(id);
        for (Electronic product : products) {
            Orderdetails orderDetail = new Orderdetails();
            orderDetail.setQuantity(1); //amount start with 1 chnage later :)
            orderDetail.setProductid(id);
            orderDetail.setTotalcost(product.getPrice() * orderDetail.getQuantity()); // * amount

            Orderline orderline = new Orderline();
            orderline.setOrderdetailid(orderDetail.getIdorderdetails());
            orderline.setProductid(id);
            orderline.setQuantityamount(orderDetail.getQuantity());
            orderline.setStatus("Packing");

            basketDetails.add(orderDetail);
            basketOrderLines.add(orderline);
        }
    }

    //Order the items and store in db
    public Object orderItems(int username) {
        List<Customer> findCustomer = customerRepository.findCustomerByIdcustomer(username);
        if (findCustomer != null && !basketDetails.isEmpty()) {
            for (Customer c : findCustomer) {
                for (Orderdetails orderdetails : basketDetails) {
                    orderdetails.setCustomerid(c.getIdcustomer());
                    if (!basketDetails.isEmpty()) {
                        orderdetails.setTime(Timestamp.valueOf(LocalDateTime.now()));
                        orderRepository.save(orderdetails);
                        for (Orderline orderline : basketOrderLines) {
                            if (orderline.getProductid() == orderdetails.getProductid()) {
                                orderline.setOrderdetailid(orderdetails.getIdorderdetails());
                                orderLineRepository.save(orderline);
                            }
                        }
                    } else {
                        return "something went wrong with basket";
                    }
                }
            }
            basketOrderLines.clear();
            basketDetails.clear();
            return "Items were ordered";
        } else {
            return "Basket is empty or customer not found.";
        }
    }

    public List<Orderdetails> getBasketItems() {
        return basketDetails;
    }

    public List<Orderdetails> removeItemFromBasket(int input) {
        for (int i = 0; i < basketDetails.size(); i++) {
            if (input == basketDetails.get(i).getProductid()) {
                basketDetails.remove(i);
                break;
            }
        }
        return basketDetails;
    }

    public double getPrice(int id) {
        List<Electronic> findElectronic = electronicRepository.findByIdelectronic(id);
        if (findElectronic != null && !findElectronic.isEmpty()) {
            for (int i = 0; true; i++) {
                Electronic electronic = findElectronic.get(i);
                return electronic.getPrice();
            }
        }
        return 0;
    }

    public List<Orderdetails> removeAmountFromBasket(int productId, int amount) {
        double price = getPrice(productId);
        for (int i = 0; i < basketDetails.size(); i++) {
            Orderdetails item = basketDetails.get(i);
            if (productId == item.getProductid()) {
                int productAmountInBasket = item.getQuantity() - amount;
                double newPrice = price * productAmountInBasket;
                if (productAmountInBasket <= 0) {
                    basketDetails.remove(i);
                    break;
                } else {
                    item.setQuantity(productAmountInBasket);
                    item.setTotalcost(newPrice);
                    for (Orderline orderline : basketOrderLines) {
                        if (orderline.getProductid() == item.getProductid()) {
                            orderline.setQuantityamount(productAmountInBasket);
                            break;
                        }
                    }
                }
            }
        }
        return basketDetails;
    }

    public List<Orderdetails> addAmountFromBasket(int productId, int amount) {
        double price = getPrice(productId);
        for (int i = 0; i < basketDetails.size(); i++) {
            Orderdetails item = basketDetails.get(i);
            if (productId == item.getProductid()) {
                int productAmountInBasket = item.getQuantity() + amount;
                double newPrice = price * productAmountInBasket;
                if (productAmountInBasket <= 0) {
                    basketDetails.remove(i);
                    break;
                } else {
                    item.setQuantity(productAmountInBasket);
                    item.setTotalcost(newPrice);
                    for (Orderline orderline : basketOrderLines) {
                        if (orderline.getProductid() == item.getProductid()) {
                            orderline.setQuantityamount(productAmountInBasket);
                            break;
                        }
                    }
                }
            }
        }
        return basketDetails;
    }


}
