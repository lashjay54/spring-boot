package admin.profile.db.models;

import admin.profile.api.models.ProfileDAO;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Entity
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String dob;
    private String address;
    private String postal;
    private String city;
    private String country;
    private Boolean defaultBillingAddress = Boolean.FALSE;
    private Boolean defaultShippingAddress = Boolean.FALSE;
    private Boolean registerProfile = Boolean.FALSE;
    @ManyToOne
    private Person person;

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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public static Profile valueOf(ProfileDAO profileDAO) {
        Profile profile = new Profile();
        BeanUtils.copyProperties(profileDAO, profile);
        profile.setDob(profileDAO.getDateOfBirth());
        profile.setPerson(Person.valueOf(profileDAO.getPersonDAO()));
        return profile;
    }
}
