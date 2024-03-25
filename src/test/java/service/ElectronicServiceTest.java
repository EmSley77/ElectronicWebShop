package service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.shop.electronicweb.entity.Electronic;
import se.shop.electronicweb.repositorys.ElectronicRepository;
import se.shop.electronicweb.service.AddOrderService;
import se.shop.electronicweb.service.ElectronicService;

import java.util.ArrayList;
import java.util.List;


import static org.mockito.Mockito.mock;



import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;

@Service
public class ElectronicServiceTest {

    private ElectronicService electronicService;
    private ElectronicRepository electronicRepository;


    @BeforeEach
    void setUp() {
        electronicRepository = mock(ElectronicRepository.class);
        electronicService = new ElectronicService(electronicRepository);


    }

    @Test
    void testAllElectronics() {
        String search = "iphone";
        List<Electronic> expectedElectronics = new ArrayList<>();
        expectedElectronics.add(new Electronic("IPhone", "phone", "iphone 12", 12000, "blue", "6'" ));

        when(electronicRepository.findByNameContainingOrCategoriContainingOrColorContaining(search, search, search))
                .thenReturn(expectedElectronics);

        List<Electronic> result = electronicService.searchForProducts(search);

        assertEquals(expectedElectronics, result);
        System.out.println(result.toString());
        System.out.println(expectedElectronics.toString());
    }

    @Test
    void testGetAll () {
        List<Electronic> all = new ArrayList<>();

        all = electronicRepository.findAll();
        all.add(new Electronic("IPhone", "phone", "iphone 12", 12000, "blue", "6'" ));
        System.out.println(all.toString());
    }

}