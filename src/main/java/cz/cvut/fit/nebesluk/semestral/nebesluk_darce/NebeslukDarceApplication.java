package cz.cvut.fit.nebesluk.semestral.nebesluk_darce;

import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain.Client;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.JdbcUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Date;

@SpringBootApplication
public class NebeslukDarceApplication {

	public static void main(String[] args) {

		var context = SpringApplication.run(NebeslukDarceApplication.class, args);
		ClientService clientService = context.getBean(ClientService.class);

		Client client = new Client();
		client.setDateCreated(LocalDateTime.now());
		client.setDateLastLogon(LocalDateTime.now());
		client.setUsername("Test");
		client.setProfilePicture(null);
		client.setRealName("Test test");

		clientService.Create(client);


	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.authorizeRequests()
				.antMatchers("/api")
				.permitAll();


		return httpSecurity.build();
	}

	@Bean
	UserDetailsManager userDetailsManager(DataSource dataSource) throws Exception {
		var conf = new JdbcUserDetailsManager(dataSource);
		try {
			conf.createUser(
					User.withUsername("admin").
							password("{noop}1234").
							roles("USER", "ADMIN").
							build()
			);
		} catch (Exception e){

		}
		return conf;
	}
}
