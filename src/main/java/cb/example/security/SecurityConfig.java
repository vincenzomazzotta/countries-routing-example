package cb.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.cors()
                .and()
                .csrf().disable()
                .authorizeRequests().antMatchers("/v1/**", "/v1/public**","/v1/public/**", "/admin/health", "/v1/downloadFileDrive/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }

/*
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/allCountries","/setCapital");
    }
*/

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception
    {
        auth.inMemoryAuthentication()
                .withUser("admin")
                    .password("{noop}password")
                .roles("USER");
    }
}