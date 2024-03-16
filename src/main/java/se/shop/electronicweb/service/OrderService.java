package se.shop.electronicweb.service;
//Emanuel sleyman
//2024-03-06
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.shop.electronicweb.entity.Customer;
import se.shop.electronicweb.entity.Orderdetails;
import se.shop.electronicweb.repositorys.CustomerRepository;
import se.shop.electronicweb.repositorys.ElectronicRepository;
import se.shop.electronicweb.repositorys.OrderRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    ElectronicRepository electronicRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderRepository orderRepository;


         public List<Orderdetails> getOrders (String name){
            List<Orderdetails> orderdetails = new ArrayList<>();
            List<Customer> customer = customerRepository.findCustomerByUsername(name);
            for (Customer cust : customer) {
                List<Orderdetails> orders = orderRepository.findOrderdetailsByCustomerid(cust.getIdcustomer());
                orderdetails.addAll(orders);
            }
            return orderdetails;
        }

}
