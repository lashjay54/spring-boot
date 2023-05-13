package admin.profile.api.models;

import admin.profile.db.models.Event;
import org.springframework.beans.BeanUtils;

public class ActivityDAO {
    private Long id;
    private String type;
    private Long dateTime;
    private PersonDAO personDAO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getDateTime() {
        return dateTime;
    }

    public void setDateTime(Long dateTime) {
        this.dateTime = dateTime;
    }

    public PersonDAO getPersonDAO() {
        return personDAO;
    }

    public void setPersonDAO(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public static ActivityDAO valueOf(Event event) {
        ActivityDAO activityDAO = new ActivityDAO();
        BeanUtils.copyProperties(event, activityDAO);
        activityDAO.setDateTime(event.getEventDate());
        activityDAO.setType(event.getEventType());
        activityDAO.setPersonDAO(PersonDAO.valueOf(event.getPerson()));
        return activityDAO;
    }
}
