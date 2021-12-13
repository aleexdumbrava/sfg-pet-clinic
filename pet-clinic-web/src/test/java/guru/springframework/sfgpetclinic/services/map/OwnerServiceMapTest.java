package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OwnerServiceMapTest {

    OwnerServiceMap ownerServiceMap;
    String lastName = "Alex";
    @BeforeEach
    void setUp() {
        ownerServiceMap = new OwnerServiceMap(new PetTypeServiceMap(), new PetServiceMap());
        Owner owner = Owner.builder().build();
        owner.setLastName(lastName);
        ownerServiceMap.save(owner);
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerServiceMap.findAll();
        assertEquals(owners.size(), 1);
    }

    @Test
    void findById() {
        Owner owner = ownerServiceMap.findById(1L);
        assertEquals(1L, owner.getId());
    }

    @Test
    void save() {
        ownerServiceMap.save(Owner.builder().build());
        assertEquals(2, ownerServiceMap.map.size());
    }

    @Test
    void delete() {
        ownerServiceMap.delete(ownerServiceMap.findById(1L));

        assertEquals(ownerServiceMap.findAll().size(), 0);
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(1L);

        assertEquals(ownerServiceMap.findAll().size(), 0);
    }

    @Test
    void findByLastName() {
        Owner owner = ownerServiceMap.findByLastName(lastName);
        assertNotNull(owner);
        assertEquals(owner.getLastName(), lastName);
    }
}