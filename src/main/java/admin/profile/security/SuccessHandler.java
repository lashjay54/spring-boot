package admin.profile.security;

import admin.profile.api.models.ActivityDAO;
import admin.profile.api.models.PersonDAO;
import admin.profile.db.models.Person;
import admin.profile.services.MainService;
import admin.profile.constants.Constants;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private MainService mainService;

    public SuccessHandler() {
        setDefaultTargetUrl(Constants.Urls.HOME);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        ActivityDAO activityDAO = new ActivityDAO();
        HttpSession httpSession = request.getSession();
        Object principal = authentication.getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        Person person = mainService.findPersonByUserName(username);
        httpSession.setAttribute("personId", person.getId());
        httpSession.setAttribute("role", person.getRole());
        activityDAO.setDateTime(DateTime.now().getMillis());
        activityDAO.setType("LOGIN");
        activityDAO.setPersonDAO(PersonDAO.valueOf(person));
        mainService.saveActivity(activityDAO);
        super.onAuthenticationSuccess(request, response, authentication);
    }

}
