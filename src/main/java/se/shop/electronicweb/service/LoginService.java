package se.shop.electronicweb.service;
//Emanuel sleyman
//2024-03-22
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import se.shop.electronicweb.entity.Customer;
import se.shop.electronicweb.repositorys.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
@SessionScope
public class LoginService {

    @Autowired
    CustomerRepository customerRepository;

    public String login(String username, String password) {
        List<Customer> loginPerson = customerRepository.findCustomerByUsernameAndPassword(username, password);
        for (Customer person : loginPerson) {
            if (person != null && username.equalsIgnoreCase(person.getUsername()) && password.equalsIgnoreCase(person.getPassword())) {
                return "Login granted, welcome:";
            } else return "Wrong credentials";
        }
        return "something went wrong";
    }

    public String createLogin(String name, String lastN, String email, String adress, String username, String password) {
        Customer newCustomer = new Customer();
        Optional<Customer> customer = customerRepository.findById(newCustomer.getIdcustomer());

        newCustomer.setName(name);
        newCustomer.setLastname(lastN);
        newCustomer.setEmail(email);
        newCustomer.setAddress(adress);
        newCustomer.setUsername(username);
        newCustomer.setPassword(password);
        newCustomer.setRole(0);
        if (customer.isEmpty() && !customer.isPresent()) {
            customerRepository.save(newCustomer);
            return "Login credentials created, welcome";
        } else return "something went wrong";
    }

    public int getRole(String username) {
        List<Customer> role = customerRepository.findCustomerByUsername(username);
        for (Customer c: role) {
            return c.getRole();
        }
        return -1;
    }

    public int getId(String username) {
        List<Customer> id = customerRepository.findCustomerByUsername(username);
        for (Customer c: id) {
            return c.getIdcustomer();
        }
        return -1;
    }
}
