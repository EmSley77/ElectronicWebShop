package se.shop.electronicweb.service;
//Emanuel sleyman
//2024-03-07

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
import java.util.Optional;

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


    public List<Orderline> basketOrderLines = new ArrayList<>();

    private int Amount = 1;

    public int getTotalCost() {
        int totalCost = 0;
        for (int i = 0; i < basketOrderLines.size(); i++) {
            totalCost += basketOrderLines.get(i).getCost();
        }
        return totalCost;
    }

    public void addToBasket(int id) {
        List<Electronic> products = electronicRepository.findByIdelectronic(id);
        boolean found = false;
        for (Electronic product : products) {
            for (Orderline orderline: basketOrderLines) {
                if (orderline.getProductid() == id) {
                    orderline.setQuantityamount(orderline.getQuantityamount() + 1);
                    orderline.setCost(product.getPrice() * orderline.getQuantityamount());
                    found = true;
                    break;
                }
            }
            if (product != null && product.getAvailable() >= 1 && !found) {
                Orderline orderline = new Orderline();
                orderline.setProductid(id);
                orderline.setQuantityamount(1);
                orderline.setCost(product.getPrice());
                orderline.setStatus("Packing");

                basketOrderLines.add(orderline);
            }
        }
    }

    public String setStatusSent(int id) {
        Optional<Orderline> item = orderLineRepository.findById(id);
        if (item.isPresent()) {
            Orderline order = item.get();
            order.setStatus("Sent");
            orderLineRepository.save(order);
            return "Sucessfully sent package";
        } else {
            return "something went wrong";
        }
    }

    @Transactional
    public String orderItems(String username, String password) {
        List<Customer> customer = customerRepository.findCustomerByUsername(username);
        if (customer.isEmpty()) {
            return "Customer not found";
        }
        if (basketOrderLines.isEmpty()) {
            return "Basket is empty, cannot proceed";
        }

        for (Customer c : customer) {
            Orderdetails orderdetails = new Orderdetails();
            orderdetails.setTime(Timestamp.valueOf(LocalDateTime.now()));
            orderdetails.setCustomerid(c.getIdcustomer());
            orderdetails.setTotalcost(getTotalCost());
            orderRepository.save(orderdetails);

            for (Orderline orderline : basketOrderLines) {
                List<Electronic> electronic = electronicRepository.findByIdelectronic(orderline.getProductid());
                for (Electronic e : electronic) {
                    int availableQuantity = e.getAvailable();
                    int orderAmount = orderline.getQuantityamount();
                    if (orderAmount <= availableQuantity) {
                        e.setAvailable(availableQuantity - orderAmount);
                    } else {
                        return "Not enough quantity available for product: " + e.getName();
                    }
                }
                orderline.setOrderdetailid(orderdetails.getIdorderdetails());
                orderline.setStatus("Packing");
                orderLineRepository.save(orderline);
            }
            basketOrderLines.clear();
            return "Items were ordered";
        }
        return "Something went wrong with the order";
    }


    public List<Orderline> getBasketItems() {
        return basketOrderLines;
    }

    public int getAvailability(int id) {
        List<Electronic> e = electronicRepository.findByIdelectronic(id);
        for (Electronic electronic : e) {
            return electronic.getAvailable();
        }
        return -1;
    }

    public List<Orderline> removeItemFromBasket(int input) {
        for (int i = 0; i < basketOrderLines.size(); i++) {
            if (input == basketOrderLines.get(i).getProductid()) {
                basketOrderLines.remove(i);
                break;
            }
        }
        return basketOrderLines;
    }

    public int getPrice(int id) {
        List<Electronic> findElectronic = electronicRepository.findByIdelectronic(id);
        if (findElectronic != null && !findElectronic.isEmpty()) {
            for (int i = 0; true; i++) {
                Electronic electronic = findElectronic.get(i);
                return electronic.getPrice();
            }
        }
        return 0;
    }

    public List<Orderline> removeAmountFromBasket(int productId) {
        int price = getPrice(productId);
        for (int i = 0; i < basketOrderLines.size(); i++) {
            Orderline item = basketOrderLines.get(i);
            if (productId == item.getProductid()) {
                int productAmountInBasket = item.getQuantityamount() - 1;
                int newPrice = price * productAmountInBasket;
                if (productAmountInBasket <= 0) {
                    basketOrderLines.remove(i);
                    break;
                } else {
                    item.setQuantityamount(productAmountInBasket);
                    item.setCost(newPrice);
                    for (Orderline orderline : basketOrderLines) {
                        if (orderline.getProductid() == item.getProductid()) {
                            orderline.setQuantityamount(productAmountInBasket);
                            break;
                        }
                    }
                }
            }
        }
        return basketOrderLines;
    }

    public List<Orderline> addAmountFromBasket(int productId) {
        int price = getPrice(productId);
        for (int i = 0; i < basketOrderLines.size(); i++) {
            Orderline item = basketOrderLines.get(i);
            if (productId == item.getProductid()) {
                int productAmountInBasket = item.getQuantityamount() + 1;
                int newPrice = price * productAmountInBasket;
                if (productAmountInBasket < getAvailability(productId)) {
                    item.setQuantityamount(productAmountInBasket);
                    item.setCost(newPrice);
                    for (Electronic e : electronicRepository.findAll()) {
                        for (Orderline orderline : basketOrderLines) {
                            if (orderline.getProductid() == item.getProductid() && e.getAvailable() >= productAmountInBasket) {
                                orderline.setQuantityamount(productAmountInBasket);
                                break;
                            }
                        }
                    }
                } else break;
            }
        }
        return basketOrderLines;
    }
}
