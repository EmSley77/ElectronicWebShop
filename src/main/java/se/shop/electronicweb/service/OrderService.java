package se.shop.electronicweb.service;
//Emanuel sleyman
//2024-03-06
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import se.shop.electronicweb.entity.Customer;
import se.shop.electronicweb.entity.Orderdetails;
import se.shop.electronicweb.repositorys.CustomerRepository;
import se.shop.electronicweb.repositorys.ElectronicRepository;
import se.shop.electronicweb.repositorys.OrderRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@SessionScope
public class OrderService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderRepository orderRepository;

         public List<Orderdetails> getOrders (String name){
            List<Orderdetails> orderdetails = new ArrayList<>();
            List<Customer> customer = customerRepository.findCustomerByUsername(name);
            for (Customer c : customer) {
                List<Orderdetails> orders = orderRepository.findOrderdetailsByCustomerid(c.getIdcustomer());
                orderdetails.addAll(orders);
            }
            return orderdetails;
        }

}
