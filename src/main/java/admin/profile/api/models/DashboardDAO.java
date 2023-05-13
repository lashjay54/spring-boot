package admin.profile.api.models;

public class DashboardDAO {

    private Long loginActivity;
    private Long profileUpdateActivity;
    private String readMessages;
    private String unreadMessages;

    public Long getLoginActivity() {
        return loginActivity;
    }

    public void setLoginActivity(Long loginActivity) {
        this.loginActivity = loginActivity;
    }

    public Long getProfileUpdateActivity() {
        return profileUpdateActivity;
    }

    public void setProfileUpdateActivity(Long profileUpdateActivity) {
        this.profileUpdateActivity = profileUpdateActivity;
    }

    public String getReadMessages() {
        return readMessages;
    }

    public void setReadMessages(String readMessages) {
        this.readMessages = readMessages;
    }

    public String getUnreadMessages() {
        return unreadMessages;
    }

    public void setUnreadMessages(String unreadMessages) {
        this.unreadMessages = unreadMessages;
    }
}
