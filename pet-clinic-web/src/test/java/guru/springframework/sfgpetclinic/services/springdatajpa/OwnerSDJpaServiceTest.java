package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {
    public static final String LAST_NAME = "Smith";
    @Mock
    OwnerRepository ownerRepository;

    @Mock
    PetRepository petRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSDJpaService service;

    Owner returnOwner;

    @BeforeEach
    void setUp() {
        returnOwner = Owner.builder().build();
        returnOwner.setLastName(LAST_NAME);
    }

    @Test
    void findAll() {
        Set<Owner> owners = new HashSet<>();
        owners.add(Owner.builder().build());
        owners.add(Owner.builder().build());

        when(ownerRepository.findAll()).thenReturn(owners);

        Set<Owner> owners1 = service.findAll();
        
        assertNotNull(owners);
        
        assertEquals(owners1.size(), 2);
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));

        Owner owner = service.findById(1L);

        assertNotNull(owner);


    }

    @Test
    void save() {
        Owner ownerToSave = Owner.builder().build();

        when(ownerRepository.save(any())).thenReturn(ownerToSave);

        Owner owner = service.save(ownerToSave);

        assertNotNull(owner);

        verify(ownerRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnOwner);

        verify(ownerRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);

        verify(ownerRepository, times(1)).deleteById(any());
    }

    @Test
    void findByLastName() {

        returnOwner.setLastName(LAST_NAME);
        when(ownerRepository.findByLastName(any())).thenReturn(returnOwner);

        Owner Smith = service.findByLastName(LAST_NAME);

        assertEquals(LAST_NAME, Smith.getLastName());

        verify(ownerRepository, times(1)).findByLastName(any());
    }
}