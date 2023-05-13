package admin.profile.db.models;

import admin.profile.api.models.ReplyDAO;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Entity
public class MessageReply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    @ManyToOne
    private Message firstMessage;
    private Long replyDate;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Message getFirstMessage() {
        return firstMessage;
    }

    public void setFirstMessage(Message firstMessage) {
        this.firstMessage = firstMessage;
    }

    public Long getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(Long replyDate) {
        this.replyDate = replyDate;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public static MessageReply valueOf(ReplyDAO replyDAO) {
        MessageReply reply = new MessageReply();
        BeanUtils.copyProperties(replyDAO, reply);
        reply.setFirstMessage(Message.valueOf(replyDAO.getMessageDAO()));
        reply.setMessage(replyDAO.getReplyMessage());
        reply.setRead(replyDAO.getReadReply());
        reply.setReplyDate(replyDAO.getDateTime());
        reply.setPerson(Person.valueOf(replyDAO.getPersonDAO()));
        return reply;
    }
}
