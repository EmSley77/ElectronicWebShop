package se.shop.electronicweb.service;

//Emanuel sleyman
//2024-03-22
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.shop.electronicweb.entity.Customer;
import se.shop.electronicweb.repositorys.CustomerRepository;

import java.util.List;


@Service
public class PersonDetailService {

    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> showDetails(String username) {
        List<Customer> findPerson = customerRepository.findCustomerByUsername(username);
        if (findPerson != null) {
            return findPerson;
        }else {
            return null;
        }
    }
}
