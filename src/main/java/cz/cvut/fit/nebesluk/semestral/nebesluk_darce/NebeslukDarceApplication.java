package cz.cvut.fit.nebesluk.semestral.nebesluk_darce;

import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.domain.Client;
import cz.cvut.fit.nebesluk.semestral.nebesluk_darce.service.ClientService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

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

/*	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.authorizeRequests().antMatchers("/").permitAll();
		return httpSecurity.build();
	}

*/
}
