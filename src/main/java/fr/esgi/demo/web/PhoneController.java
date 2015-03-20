package fr.esgi.demo.web;

import fr.esgi.demo.domain.Phone;
import fr.esgi.demo.repository.PhoneRepository;
import fr.esgi.demo.web.dto.PhoneDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.google.common.collect.FluentIterable.from;

@RestController
@RequestMapping("/phone")
public class PhoneController {

    @Autowired
    private PhoneRepository phoneRepository;
    private ArrayList<PhoneDto> phone = new ArrayList<>();


    @RequestMapping(method = RequestMethod.GET)
    public ArrayList<PhoneDto> getPhones() {
        List<Phone> phones = from(phoneRepository.findAll()).toList();

        hydratePhoneListFromDatabaseList(phones);

        return phone;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/stolen")
    public List<PhoneDto> getPhonesStolen() {
        List<Phone> phones = from(phoneRepository.findByStolen(true)).toList();

        hydratePhoneListFromDatabaseList(phones);

        return phone;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public boolean getPhoneById(@PathVariable int id) {
        Phone phone = phoneRepository.findById(id);

        if (phone == null) {
            throw new RessNotFoundException();
        }

        return phone.getStolen();
    }



    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ArrayList<PhoneDto> putUpdateStatePhone(@PathVariable int id, @RequestBody HashMap<String, Boolean> stolen) {
        PhoneDto phoneDtoBefore = new PhoneDto();
        PhoneDto phoneDtoAfter = new PhoneDto();
        ArrayList<PhoneDto> result = new ArrayList<>();

        Phone phone = phoneRepository.findById(id);

        phoneDtoBefore.hydrateFromDatabase(phone);

        phone.setStolen(stolen.get("stolen").booleanValue());

        phoneRepository.save(phone);

        phoneDtoAfter.hydrateFromDatabase(phone);

        result.add(phoneDtoBefore);
        result.add(phoneDtoAfter);

        return result;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/withSerialNumber/{serialNumber}")
    public ArrayList<PhoneDto> putUpdateStateSerial(@PathVariable String serialNumber, @RequestBody HashMap<String, Boolean> stolen) {
        PhoneDto phoneDtoBefore = new PhoneDto();
        PhoneDto phoneDtoAfter = new PhoneDto();
        ArrayList<PhoneDto> result = new ArrayList<>();

        Phone phone = phoneRepository.findBySerialNumber(serialNumber);

        phoneDtoBefore.hydrateFromDatabase(phone);

        phone.setStolen(stolen.get("stolen").booleanValue());

        phoneRepository.save(phone);

        phoneDtoAfter.hydrateFromDatabase(phone);

        result.add(phoneDtoBefore);
        result.add(phoneDtoAfter);

        return result;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ArrayList<PhoneDto> deleteById(@PathVariable int id) {
        PhoneDto phoneDtoBefore = new PhoneDto();

        Phone currentPhone = phoneRepository.findById(id);

        phoneDtoBefore.hydrateFromDatabase(currentPhone);

        phoneRepository.delete(currentPhone);

        List<Phone> phones = from(phoneRepository.findAll()).toList();

        hydratePhoneListFromDatabaseList(phones);

        return phone;
    }
    @RequestMapping(method = RequestMethod.POST)
    public PhoneDto postCreatePhone(@RequestBody PhoneDto phoneDtoCreate) {
        Phone phoneNew = phoneDtoCreate.generateEntity();

        phoneRepository.save(phoneNew);

        phoneDtoCreate.setId(phoneNew.getId());

        return phoneDtoCreate;
    }
    private void hydratePhoneListFromDatabaseList(List<Phone> phoneList) {
        phone.clear();

        for (Phone currentPhoneBdd : phoneList) {
            PhoneDto phoneDto = new PhoneDto();

            phoneDto.hydrateFromDatabase(currentPhoneBdd);

            phone.add(phoneDto);
        }
    }
}
