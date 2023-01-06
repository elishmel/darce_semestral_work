package cz.cvut.fit.nebesluk.semestral.nebesluk_darce;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/api/client/auth","/api/image","/api/item/*/request/*","/api/item/*/offer/*")
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.PUT,"/api/*")
                .authenticated().and().httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/api/*")
                .authenticated().and()
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.DELETE,"/api/*")
                .authenticated().and()
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll();
        return httpSecurity.build();
    }

    @Bean
    UserDetailsManager userDetailsManager(DataSource dataSource) throws Exception {
        var conf = new JdbcUserDetailsManager(dataSource);
        try {
            conf.createUser(
                    User.withUsername("Lumir").
                            password(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("1234")).
                            roles("USER", "ADMIN").
                            build()
            );
            conf.createUser(
                    User.withUsername("Esmeralda").
                    password(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("1234")).
                            roles("USER").build()
            );
        } catch (Exception e){

        }
        return conf;
    }
}
