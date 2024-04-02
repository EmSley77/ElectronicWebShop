package se.shop.electronicweb.service;
//Emanuel sleyman
//2024-03-05
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.shop.electronicweb.entity.Electronic;
import se.shop.electronicweb.repositorys.ElectronicRepository;

import java.util.Collections;
import java.util.List;

@Service
public class ElectronicService {

    @Autowired
    ElectronicRepository electronicRepository;

    public ElectronicService(ElectronicRepository electronicRepository) {
        this.electronicRepository = electronicRepository;
    }

    public List<Electronic> searchForProducts(String search) {
        List<Electronic> electronics = electronicRepository.findByNameContainingOrCategoriContainingOrColorContaining(search, search, search);

        if (electronics != null) {
            return electronics;
        } else {
            return Collections.emptyList();
        }
    }
    public List<Electronic> getAll() {
        return electronicRepository.findAll();
    }

    public List<Electronic> findAccesories() {
        return electronicRepository.findByCategori("Accesories");
    }

    public List<Electronic> findComputers() {
        return electronicRepository.findByCategori("Computer");
    }

    public List<Electronic> findTvs() {
        return electronicRepository.findByCategori("TV");
    }

    public List<Electronic> findPhones() {
        return electronicRepository.findByCategori("Phone");
    }
}
