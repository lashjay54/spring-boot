package admin.profile.api.models;

import java.util.List;

public class ResponseDAO {

    private PersonDAO personDAO;
    private ProfileDAO profileDAO;
    private CreditDAO creditProfileDAO;
    private List<ProfileDAO> profileDAOS;
    private List<CreditDAO> creditProfileDAOS;
    private MessageDAO messageDAO;
    private List<MessageDAO> messageDAOS;
    private List<ReplyDAO> replyDAOS;
    private ReplyDAO replyDAO;
    private DashboardDAO dashboardDAO;
    private String errorMessage;
    private String successMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public DashboardDAO getDashboardDAO() {
        return dashboardDAO;
    }

    public void setDashboardDAO(DashboardDAO dashboardDAO) {
        this.dashboardDAO = dashboardDAO;
    }

    public ReplyDAO getReplyDAO() {
        return replyDAO;
    }

    public void setReplyDAO(ReplyDAO replyDAO) {
        this.replyDAO = replyDAO;
    }

    public List<ReplyDAO> getReplyDAOS() {
        return replyDAOS;
    }

    public void setReplyDAOS(List<ReplyDAO> replyDAOS) {
        this.replyDAOS = replyDAOS;
    }

    public List<MessageDAO> getMessageDAOS() {
        return messageDAOS;
    }

    public void setMessageDAOS(List<MessageDAO> messageDAOS) {
        this.messageDAOS = messageDAOS;
    }

    public PersonDAO getPersonDAO() {
        return personDAO;
    }

    public void setPersonDAO(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public ProfileDAO getProfileDAO() {
        return profileDAO;
    }

    public void setProfileDAO(ProfileDAO profileDAO) {
        this.profileDAO = profileDAO;
    }

    public List<ProfileDAO> getProfileDAOS() {
        return profileDAOS;
    }

    public void setProfileDAOS(List<ProfileDAO> profileDAOS) {
        this.profileDAOS = profileDAOS;
    }

    public CreditDAO getCreditProfileDAO() {
        return creditProfileDAO;
    }

    public void setCreditProfileDAO(CreditDAO creditProfileDAO) {
        this.creditProfileDAO = creditProfileDAO;
    }

    public List<CreditDAO> getCreditProfileDAOS() {
        return creditProfileDAOS;
    }

    public void setCreditProfileDAOS(List<CreditDAO> creditProfileDAOS) {
        this.creditProfileDAOS = creditProfileDAOS;
    }

    public MessageDAO getMessageDAO() {
        return messageDAO;
    }

    public void setMessageDAO(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }
}
