package fr.esgi.demo.repository;

import fr.esgi.demo.domain.Phone;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneRepository extends CrudRepository<Phone, Long> {
    public List<Phone> findByStolen(boolean stolen);
    public Phone findById(int id);
    public Phone findBySerialNumber(String serialNumber);
}
