package admin.profile.security;

import admin.profile.db.models.Person;
import admin.profile.services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component("profileUserDetailsService")
public class ProfileUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private MainService mainService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String username) {
        Person user = mainService.findPersonByUserName(username.trim());
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username.");
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getRole());
        grantedAuthorities.add(grantedAuthority);
        return new ProfileUser(user.getId(), user.getUsername().trim(), user.getPassword().trim(), grantedAuthorities);
    }

}
