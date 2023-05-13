package admin.profile.api.models;

import admin.profile.db.models.Message;
import org.springframework.beans.BeanUtils;

public class MessageDAO {
    private Long id;
    private String firstName;
    private String email;
    private String subject;
    private String message;
    private Long dateTime;
    Boolean readMessage;
    private PersonDAO personDAO;

    public Boolean getReadMessage() {
        return readMessage;
    }

    public void setReadMessage(Boolean readMessage) {
        this.readMessage = readMessage;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PersonDAO getPersonDAO() {
        return personDAO;
    }

    public void setPersonDAO(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public static MessageDAO valueOf(Message message) {
        MessageDAO messageDAO = new MessageDAO();
        BeanUtils.copyProperties(message, messageDAO);
        messageDAO.setReadMessage(message.getRead());
        messageDAO.setDateTime(message.getMsgDate());
        messageDAO.setMessage(message.getMessage());
        messageDAO.setSubject(message.getSubject());
        messageDAO.setPersonDAO(PersonDAO.valueOf(message.getPerson()));
        return messageDAO;
    }
}
