package admin.profile.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class ProfileUser extends User {

    private final Long userId;

    public ProfileUser(Long userId, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object user) {
        if (user instanceof ProfileUser) {
            return (this.userId.equals(((ProfileUser) user).getUserId()));
        }
        return false;
    }
}
