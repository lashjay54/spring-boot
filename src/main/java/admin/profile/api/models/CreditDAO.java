package admin.profile.api.models;

import admin.profile.db.models.Credit;
import org.springframework.beans.BeanUtils;

public class CreditDAO {

    private Long id;
    private String creditCardType;
    private String expirationDate;
    private String cardHolderName;
    private String creditCardNumber;
    private Boolean defaultOrPreferred;
    private PersonDAO personDAO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreditCardType() {
        return creditCardType;
    }

    public void setCreditCardType(String creditCardType) {
        this.creditCardType = creditCardType;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public Boolean getDefaultOrPreferred() {
        return defaultOrPreferred;
    }

    public void setDefaultOrPreferred(Boolean defaultOrPreferred) {
        this.defaultOrPreferred = defaultOrPreferred;
    }

    public PersonDAO getPersonDAO() {
        return personDAO;
    }

    public void setPersonDAO(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public static CreditDAO valueOf(Credit credit) {
        CreditDAO creditDAO = new CreditDAO();
        BeanUtils.copyProperties(credit, creditDAO);
        creditDAO.setCardHolderName(credit.getCardHolderName());
        creditDAO.setCreditCardNumber(credit.getCreditCardNumber());
        creditDAO.setCreditCardType(credit.getCreditCardType());
        creditDAO.setDefaultOrPreferred(credit.getDefaultOrPreferred());
        creditDAO.setExpirationDate(credit.getExpirationDate());
        creditDAO.setPersonDAO(PersonDAO.valueOf(credit.getPerson()));
        return creditDAO;
    }
}
