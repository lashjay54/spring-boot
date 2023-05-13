package admin.profile.security;

import admin.profile.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SuccessHandler ajaxAuthenticationSuccessHandler;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
        throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(
            passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .headers()
            .frameOptions().disable()
            .and()
            .authorizeRequests()
            .antMatchers(Constants.Urls.LOGIN).permitAll()
            .antMatchers(Constants.Urls.REGISTER).permitAll()
            .antMatchers("/public/**").permitAll()
            .antMatchers("/js/**").permitAll()
            .antMatchers("/css/**").permitAll()
            .antMatchers("/h2-console/**").permitAll()
            .anyRequest().authenticated().and()
            .sessionManagement().and()
            .formLogin().loginPage(Constants.Urls.LOGIN)
            .permitAll()
            .failureUrl(Constants.Urls.LOGIN)
            .successHandler(ajaxAuthenticationSuccessHandler)
            .and()
            .logout()
            .logoutRequestMatcher(new AntPathRequestMatcher(Constants.Urls.LOG_OUT))
            .logoutSuccessUrl(Constants.Urls.LOGIN)
            .permitAll()
            .invalidateHttpSession(true)
            .and().csrf().disable();
    }
}
