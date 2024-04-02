package se.shop.electronicweb.repositorys;
//Emanuel sleyman
//2024-03-04
import org.springframework.data.jpa.repository.JpaRepository;
import se.shop.electronicweb.entity.Electronic;

import java.util.List;
import java.util.Optional;

public interface ElectronicRepository extends JpaRepository<Electronic, Integer> {
    List<Electronic> findByNameContainingOrCategoriContainingOrColorContaining(String searchTerm, String searchTerm1, String searchTerm2);

    List<Electronic> findByIdelectronic(int id);

    List<Electronic> findByCategori(String s);

    @Override
    Optional<Electronic> findById(Integer integer);
}
