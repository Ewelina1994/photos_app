package pl.klobut.photos_app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.klobut.photos_app.model.User;
import pl.klobut.photos_app.repo.UserRepo;
import pl.klobut.photos_app.services.UserDetailServicesImpl;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    UserDetailServicesImpl userDetailServices;
    private UserRepo userRepo;


    @Autowired
    public WebSecurityConfig(UserDetailServicesImpl userDetailServices, UserRepo userRepo) {
        this.userDetailServices = userDetailServices;
        this.userRepo=userRepo;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser(new User("Jan", passwordEncoder().encode("Jan123"), Collections.singleton(new SimpleGrantedAuthority("user"))));
        auth.userDetailsService(userDetailServices);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers("/h2-console/**").permitAll()
//                .antMatchers("/test1").access("hasRole('USER')")
//                .antMatchers("/uploadImage").access("hasRole('ADMIN')")
//                .and()
//                .formLogin().permitAll();
        http.authorizeRequests()
//                .antMatchers("/uploadImage").hasRole("ADMIN")
//                .and()
//                .formLogin().permitAll();
                .antMatchers("/console/**").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().permitAll();

        http.csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .headers().disable()
                .httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void addUser() {
        User user= new User("admin", passwordEncoder().encode("admin"), "ADMIN");
        User user2= new User("tester", passwordEncoder().encode("tester"), "USER");
        User user3 = new User("klient", passwordEncoder().encode("klient"), "USER");
        userRepo.save(user);
        userRepo.save(user2);
        userRepo.save(user3);
    }

}
