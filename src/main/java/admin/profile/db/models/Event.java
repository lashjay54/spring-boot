package admin.profile.db.models;

import admin.profile.api.models.ActivityDAO;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String eventType;
    private Long eventDate;
    @ManyToOne
    private Person person;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Long getEventDate() {
        return eventDate;
    }

    public void setEventDate(Long eventDate) {
        this.eventDate = eventDate;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public static Event valueOf(ActivityDAO activityDAO) {
        Event activity = new Event();
        BeanUtils.copyProperties(activityDAO, activity);
        activity.setPerson(Person.valueOf(activityDAO.getPersonDAO()));
        activity.setEventDate(activityDAO.getDateTime());
        activity.setEventType(activityDAO.getType());
        return activity;
    }
}
