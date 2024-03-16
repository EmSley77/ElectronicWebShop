package se.shop.electronicweb.repositorys;
//Emanuel sleyman
//2024-03-04
import org.springframework.data.jpa.repository.JpaRepository;
import se.shop.electronicweb.entity.Orderline;

public interface OrderLineRepository extends JpaRepository<Orderline, Integer> {
}
