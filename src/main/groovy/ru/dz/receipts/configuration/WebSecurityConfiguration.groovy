package ru.dz.receipts.configuration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import ru.dz.receipts.domain.Account
import ru.dz.receipts.repository.AccountRepository

/**
 * Created by Alex on 22.03.16.
 */
@EnableWebSecurity
@Configuration
class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().fullyAuthenticated().and().
                httpBasic().and().
                csrf().disable();

    }
}

@Configuration
class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter{

    @Autowired
    AccountRepository loginUserRepo;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Bean
    UserDetailsService userDetailsService() {
        return new UserDetailsService() {

            @Override
            public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
                Account loginUser = loginUserRepo.findByLogin(login);
                if(loginUser != null) {
                    return new User(loginUser.getLogin(), loginUser.getPassword(), AuthorityUtils.createAuthorityList("USER"));
                } else {
                    throw new UsernameNotFoundException("could not find the user '" + login + "'");
                }
            }

        };
    }
}