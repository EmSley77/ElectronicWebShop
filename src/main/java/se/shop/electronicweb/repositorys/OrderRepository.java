package se.shop.electronicweb.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import se.shop.electronicweb.entity.Orderdetails;
//Emanuel sleyman
//2024-03-04
import java.util.List;

public interface OrderRepository extends JpaRepository<Orderdetails, Integer> {

    List<Orderdetails> findOrderdetailsByCustomerid(int id);
}
