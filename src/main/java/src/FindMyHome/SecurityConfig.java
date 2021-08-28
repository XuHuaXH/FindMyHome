package src.FindMyHome;

import javax.sql.DataSource;

import src.FindMyHome.JWT.JWTAuthenticationFilter;
import src.FindMyHome.JWT.JWTLoginFilter;
import src.FindMyHome.JWT.UserDetailsServiceImpl;
import src.FindMyHome.dao.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private final UserRepo userRepo;
    private DataSource dataSource;
    private UserDetailsService userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new UserDetailsServiceImpl();
//    };

    public SecurityConfig(UserRepo userRepo, UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepo = userRepo;
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers("/hello/**", "/api/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .addFilter(new JWTLoginFilter(authenticationManager()))
                .addFilter(new JWTAuthenticationFilter(authenticationManager()));

        // Allow access to h2-console during development
        // TODO: Remove this configuration before shipping
        http.headers().frameOptions().disable();

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserDetailsServiceImpl(userRepo)).passwordEncoder(bCryptPasswordEncoder);
    }


//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .formLogin().disable()
//                .authorizeRequests()
//                .antMatchers("/hello/**").authenticated()
//                .anyRequest().permitAll()
//                .and()
//                .logout()
//                .logoutUrl("/logout");
//
//    }

//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication().withUser("stefanlaioffer@gmail.com").password("123").authorities("ROLE_ADMIN");
//
//        auth
//                .jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery("SELECT emailId, password, enabled FROM users WHERE emailId=?")
//                .authoritiesByUsernameQuery("SELECT emailId, authorities FROM authorities WHERE emailId=?");
//
//    }

}
