package se.shop.electronicweb.service;

//Emanuel sleyman
//2024-03-23
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import se.shop.electronicweb.entity.Customer;
import se.shop.electronicweb.entity.Electronic;
import se.shop.electronicweb.entity.Orderdetails;
import se.shop.electronicweb.repositorys.CustomerRepository;
import se.shop.electronicweb.repositorys.ElectronicRepository;
import se.shop.electronicweb.repositorys.OrderRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@SessionScope
public class AdminService {

    @Autowired
    CustomerRepository customerRepository;

    //Orders need to get handled!
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ElectronicRepository electronicRepository;


    public String createAdmin(String name, String lastN, String email, String adress, String username, String password) {
        Customer c = new Customer();
        Optional<Customer> customer = customerRepository.findById(c.getIdcustomer());

        c.setName(name);
        c.setLastname(lastN);
        c.setEmail(email);
        c.setAddress(adress);
        c.setUsername(username);
        c.setPassword(password);
        c.setRole(1);
        if (customer.isEmpty() && !customer.isPresent()) {
            customerRepository.save(c);
            return "Admin created, welcome";

        } else return "something went wrong";
    }

    public List<Orderdetails> getorders(String username) {
        List<Orderdetails> orders = new ArrayList<>();
        List<Customer> customer = customerRepository.findCustomerByUsername(username);
        for (Customer c: customer) {
            List<Orderdetails> order = orderRepository.findOrderdetailsByCustomerid(c.getIdcustomer());
            for (Orderdetails o: order) {
                orders.add(o);
                return orders;

            }
        }
        return orders;
    }

    public String addItem(String company, String categori, String name, double price, String color, String size, int available) {
        Electronic electronic = new Electronic();

        electronic.setCompany(company);
        electronic.setCategori(categori);
        electronic.setName(name);
        electronic.setPrice(price);
        electronic.setColor(color);
        electronic.setSize(size);
        electronic.setAvailable(available);
        List<Electronic> findElectronic = electronicRepository.findAll();
        for (Electronic e: findElectronic) {
            if (electronic.getIdelectronic() == e.getIdelectronic()) {
                return "item already exists";
            }
            else {
                electronicRepository.save(electronic);
                return "Item was added";
            }
        }
        return "something went wrong here";
    }
    public List<Electronic> removeItem(int id) {
        List<Electronic> findelectronic = electronicRepository.findByIdelectronic(id);
        for (int i = 0; i < findelectronic.size() ;i++) {
            if (id == findelectronic.get(i).getIdelectronic()) {
                electronicRepository.delete(findelectronic.get(i));
                break;
            }
        }
        return findelectronic;
    }

    public List<Electronic> allElectronics() {
        return electronicRepository.findAll();
    }

    public List<Orderdetails> getAllOrders() {
        return orderRepository.findAll();
    }
}

