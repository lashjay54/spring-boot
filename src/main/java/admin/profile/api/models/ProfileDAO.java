package admin.profile.api.models;

import admin.profile.db.models.Profile;
import org.springframework.beans.BeanUtils;

public class ProfileDAO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String dateOfBirth;
    private String address;
    private String city;
    private String country;
    private String postal;
    private Boolean defaultBillingAddress;
    private Boolean defaultShippingAddress;
    private Boolean registerProfile;
    private Long dateTime;
    private PersonDAO personDAO;

    public Long getDateTime() {
        return dateTime;
    }

    public void setDateTime(Long dateTime) {
        this.dateTime = dateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public Boolean getDefaultBillingAddress() {
        return defaultBillingAddress;
    }

    public void setDefaultBillingAddress(Boolean defaultBillingAddress) {
        this.defaultBillingAddress = defaultBillingAddress;
    }

    public Boolean getDefaultShippingAddress() {
        return defaultShippingAddress;
    }

    public void setDefaultShippingAddress(Boolean defaultShippingAddress) {
        this.defaultShippingAddress = defaultShippingAddress;
    }

    public Boolean getRegisterProfile() {
        return registerProfile;
    }

    public void setRegisterProfile(Boolean registerProfile) {
        this.registerProfile = registerProfile;
    }

    public PersonDAO getPersonDAO() {
        return personDAO;
    }

    public void setPersonDAO(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public static ProfileDAO valueOf(Profile profile) {
        ProfileDAO profileDAO = new ProfileDAO();
        BeanUtils.copyProperties(profile, profileDAO);
        PersonDAO personDAO = PersonDAO.valueOf(profile.getPerson());
        profileDAO.setDateOfBirth(profile.getDob());
        profileDAO.setPersonDAO(personDAO);
        return profileDAO;
    }
}
