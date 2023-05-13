package admin.profile.db.models;

import admin.profile.api.models.CreditDAO;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Entity
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cardType;
    private String expDate;
    private String holderName;
    private String cardNumber;
    private Boolean preferred;
    @ManyToOne
    private Person person;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreditCardType() {
        return cardType;
    }

    public void setCreditCardType(String creditCardType) {
        this.cardType = creditCardType;
    }

    public String getExpirationDate() {
        return expDate;
    }

    public void setExpirationDate(String expDate) {
        this.expDate = expDate;
    }

    public String getCardHolderName() {
        return holderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.holderName = cardHolderName;
    }

    public String getCreditCardNumber() {
        return cardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.cardNumber = creditCardNumber;
    }

    public Boolean getDefaultOrPreferred() {
        return preferred;
    }

    public void setDefaultOrPreferred(Boolean defaultOrPreferred) {
        this.preferred = defaultOrPreferred;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public static Credit valueOf(CreditDAO creditProfileDAO) {
        Credit creditProfile = new Credit();
        BeanUtils.copyProperties(creditProfileDAO, creditProfile);
        creditProfile.setCardHolderName(creditProfileDAO.getCardHolderName());
        creditProfile.setCreditCardNumber(creditProfileDAO.getCreditCardNumber());
        creditProfile.setCreditCardType(creditProfileDAO.getCreditCardType());
        creditProfile.setDefaultOrPreferred(creditProfileDAO.getDefaultOrPreferred());
        creditProfile.setExpirationDate(creditProfileDAO.getExpirationDate());
        Person person = Person.valueOf(creditProfileDAO.getPersonDAO());
        creditProfile.setPerson(person);
        return creditProfile;
    }
}
