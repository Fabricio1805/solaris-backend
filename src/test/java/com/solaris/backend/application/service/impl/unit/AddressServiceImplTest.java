package com.solaris.backend.application.service.impl.unit;

import com.solaris.backend.application.dto.AddressDTO;
import com.solaris.backend.application.exception.NotFoundException;
import com.solaris.backend.application.mapper.AddressMapper;
import com.solaris.backend.application.repository.AddressRepository;
import com.solaris.backend.application.service.impl.AddressServiceImpl;
import com.solaris.backend.domain.entity.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddressServiceImplTest {
    @Mock
    private AddressMapper addressMapper;

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressServiceImpl addressService;

    private Address address;
    private AddressDTO addressDTO;

    @BeforeEach
    void setUp() {
        address = new Address();
        address.setId(1L);

        addressDTO = mock(AddressDTO.class);
    }

    @Test
    void save() {

        when(addressMapper.toEntity(addressDTO)).thenReturn(address);
        when(addressRepository.save(address)).thenReturn(address);
        when(addressMapper.toDTO(address)).thenReturn(addressDTO);

        AddressDTO result = addressService.save(addressDTO);

        assertNotNull(result);

        verify(addressMapper).toEntity(addressDTO);
        verify(addressRepository).save(address);
        verify(addressMapper).toDTO(address);
    }

    @Test
    void update() {

        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));
        when(addressRepository.save(address)).thenReturn(address);
        when(addressMapper.toDTO(address)).thenReturn(addressDTO);

        AddressDTO result = addressService.update(1L, addressDTO);

        assertNotNull(result);

        verify(addressRepository).findById(1L);
        verify(addressMapper).updateEntityFromDTO(addressDTO, address);
        verify(addressRepository).save(address);
        verify(addressMapper).toDTO(address);
    }

    @Test
    void findById() {

        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));
        when(addressMapper.toDTO(address)).thenReturn(addressDTO);

        AddressDTO result = addressService.findById(1L);

        assertNotNull(result);

        verify(addressRepository).findById(1L);
        verify(addressMapper).toDTO(address);
    }

    @Test
    void findAll() {

        List<Address> addresses = List.of(address);
        List<AddressDTO> dtos = List.of(addressDTO);

        when(addressRepository.findAll()).thenReturn(addresses);
        when(addressMapper.toDTOList(addresses)).thenReturn(dtos);

        List<AddressDTO> result = addressService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());

        verify(addressRepository).findAll();
        verify(addressMapper).toDTOList(addresses);
    }

    @Test
    void delete() {

        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));

        addressService.delete(1L);

        verify(addressRepository).findById(1L);
        verify(addressRepository).delete(address);
    }

    @Test
    void findEntityById() {

        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));

        Address result = addressService.findEntityById(1L);

        assertNotNull(result);
        assertEquals(address, result);

        verify(addressRepository).findById(1L);
    }

    @Test
    void findEntityById_ShouldThrowNotFoundException() {

        when(addressRepository.findById(1L)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> addressService.findEntityById(1L)
        );

        assertEquals("Endereço não encontrado", exception.getMessage());

        verify(addressRepository).findById(1L);
    }

    @Test
    void findById_ShouldThrowNotFoundException() {

        when(addressRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(
                NotFoundException.class,
                () -> addressService.findById(1L)
        );

        verify(addressRepository).findById(1L);
    }

    @Test
    void update_ShouldThrowNotFoundException() {

        when(addressRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(
                NotFoundException.class,
                () -> addressService.update(1L, addressDTO)
        );

        verify(addressRepository).findById(1L);

        verify(addressRepository, never()).save(any());
    }

    @Test
    void delete_ShouldThrowNotFoundException() {

        when(addressRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(
                NotFoundException.class,
                () -> addressService.delete(1L)
        );

        verify(addressRepository).findById(1L);

        verify(addressRepository, never()).delete(any());
    }

}