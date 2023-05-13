package admin.profile.db.models;

import admin.profile.api.models.MessageDAO;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String subject;
    private String message;
    private Long msgDate;
    private Boolean isRead;
    @ManyToOne
    private Person person;

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getMsgDate() {
        return msgDate;
    }

    public void setMsgDate(Long msgDate) {
        this.msgDate = msgDate;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public static Message valueOf(MessageDAO messageDAO) {
        Message message = new Message();
        BeanUtils.copyProperties(messageDAO, message);
        message.setMessage(messageDAO.getMessage());
        message.setSubject(messageDAO.getSubject());
        message.setRead(messageDAO.getReadMessage());
        message.setMsgDate(messageDAO.getDateTime());
        message.setPerson(Person.valueOf(messageDAO.getPersonDAO()));
        return message;
    }
}
