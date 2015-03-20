package fr.esgi.demo.web.dto;

import fr.esgi.demo.domain.Phone;

/**
 *
 * DTO (Data Tansfer Object) : objet qui va vivre à l'extérieur
 */
public class PhoneDto {
    private int id;
    private String firstName;
    private String lastName;
    private String serialNumber;
    private Boolean stolen;

    public PhoneDto() {

    }

    public void hydrateFromDatabase(Phone phone) {
        id = phone.getId();
        firstName = phone.getFirstName();
        lastName = phone.getLastName();
        serialNumber = phone.getSerialNumber();
        stolen = phone.getStolen();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Boolean getStolen() {
        return stolen;
    }

    public void setStolen(Boolean stolen) {
        this.stolen = stolen;
    }

    public Phone generateEntity() {
        Phone phone = new Phone();

        phone.setFirstName(firstName);
        phone.setLastName(lastName);
        phone.setSerialNumber(serialNumber);
        phone.setStolen(stolen);

        return phone;
    }
}
