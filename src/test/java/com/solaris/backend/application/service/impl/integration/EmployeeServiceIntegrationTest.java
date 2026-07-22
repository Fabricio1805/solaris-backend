package com.solaris.backend.application.service.impl.integration;

import com.solaris.backend.application.dto.AddressDTO;
import com.solaris.backend.application.exception.NotFoundException;
import com.solaris.backend.application.service.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class EmployeeServiceIntegrationTest {
    @Autowired
    private AddressService addressService;

    private AddressDTO dto;


    @BeforeEach
    void setUp() {
        dto = new AddressDTO(
                null,
                null,
                null,
                null,
                null,
                "83",
                "Rua A",
                "Salvador",
                "BA",
                "40000-000",
                "Brazil",
                "test",
                ""
        );;
    }

    @Test
    void shouldSaveAddress() {
        AddressDTO saved = addressService.save(dto);

        assertNotNull(saved);
        assertNotNull(saved.id());
        assertEquals("Rua A", saved.street());
    }

    @Test
    void shouldFindById() {
        AddressDTO saved = addressService.save(dto);

        AddressDTO found = addressService.findById(saved.id());

        assertNotNull(found);
        assertEquals(saved.id(), found.id());
        assertEquals("Rua A", found.street());
    }

    @Test
    void shouldUpdateAddress() {

        AddressDTO saved = addressService.save(dto);

        AddressDTO update = new AddressDTO(
                null,
                null,
                null,
                null,
                null,
                "100",
                "Rua Alterada",
                "Salvador",
                "BA",
                "40000-000",
                "Brazil",
                "test",
                ""
        );

        AddressDTO updated = addressService.update(saved.id(), update);

        assertEquals("Rua Alterada", updated.street());
        assertEquals("100", updated.number());
    }

    @Test
    void shouldDeleteAddress() {

        AddressDTO saved = addressService.save(dto);

        addressService.delete(saved.id());

        assertThrows(
                NotFoundException.class,
                () -> addressService.findById(saved.id())
        );
    }

    @Test
    void shouldFindAllAddresses() {

        addressService.save(dto);

        addressService.save(dto);

        assertTrue(addressService.findAll().size() >= 2);
    }

    @Test
    void shouldThrowExceptionWhenAddressNotExists() {

        assertThrows(
                NotFoundException.class,
                () -> addressService.findById(999L)
        );
    }
}
