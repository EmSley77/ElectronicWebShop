package se.shop.electronicweb.repositorys;
//Emanuel sleyman
//2024-03-04
import org.springframework.data.jpa.repository.JpaRepository;
import se.shop.electronicweb.entity.Customer;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findCustomerByUsernameAndPassword(String username, String password);

    List<Customer> findCustomerByIdcustomer(int id);

    List<Customer> findCustomerByUsername(String name);


}
