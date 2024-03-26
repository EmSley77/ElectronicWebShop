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
        for (int i = 0; i < loginPerson.size(); i++) {
            if (loginPerson != null && username.equalsIgnoreCase(loginPerson.get(i).getUsername()) && password.equalsIgnoreCase(loginPerson.get(i).getPassword())) {
                return "Login granted, welcome:";
            }
            else return "Wrong credentials";
        }
        return "something went wrong";
    }

    public String createLogin(String name, String lastN, String email, String adress, String username, String password) {
        Customer c = new Customer();
        Optional<Customer> customer = customerRepository.findById(c.getIdcustomer());

        c.setName(name);
        c.setLastname(lastN);
        c.setEmail(email);
        c.setAddress(adress);
        c.setUsername(username);
        c.setPassword(password);
        c.setRole(0);
        if (customer.isEmpty() && !customer.isPresent()) {
            customerRepository.save(c);
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
