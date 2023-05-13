package admin.profile.api.models;

import admin.profile.db.models.MessageReply;
import org.springframework.beans.BeanUtils;

public class ReplyDAO {

    private Long id;
    private String replyMessage;
    private Long dateTime;
    private Boolean readReply;
    private MessageDAO messageDAO;
    private PersonDAO personDAO;

    public Boolean getReadReply() {
        return readReply;
    }

    public void setReadReply(Boolean readReply) {
        this.readReply = readReply;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReplyMessage() {
        return replyMessage;
    }

    public void setReplyMessage(String replyMessage) {
        this.replyMessage = replyMessage;
    }

    public MessageDAO getMessageDAO() {
        return messageDAO;
    }

    public void setMessageDAO(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    public PersonDAO getPersonDAO() {
        return personDAO;
    }

    public void setPersonDAO(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public Long getDateTime() {
        return dateTime;
    }

    public void setDateTime(Long dateTime) {
        this.dateTime = dateTime;
    }

    public static ReplyDAO valueOf(MessageReply reply) {
        ReplyDAO replyDAO = new ReplyDAO();
        BeanUtils.copyProperties(reply, replyDAO);
        replyDAO.setMessageDAO(MessageDAO.valueOf(reply.getFirstMessage()));
        replyDAO.setDateTime(reply.getReplyDate());
        replyDAO.setReadReply(reply.getRead());
        replyDAO.setReplyMessage(reply.getMessage());
        replyDAO.setPersonDAO(PersonDAO.valueOf(reply.getPerson()));
        return replyDAO;
    }
}
