package admin.profile.api.models;

import admin.profile.db.models.Person;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class PersonDAO {
    private Long id;
    private String username;
    private String password;
    private String role;
    private List<ProfileDAO> profileDAOS;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<ProfileDAO> getProfileDAOS() {
        return profileDAOS;
    }

    public void setProfileDAOS(List<ProfileDAO> profileDAOS) {
        this.profileDAOS = profileDAOS;
    }

    public static PersonDAO valueOf(Person person) {
        PersonDAO personDAO = new PersonDAO();
        BeanUtils.copyProperties(person, personDAO);
        return personDAO;
    }
}
