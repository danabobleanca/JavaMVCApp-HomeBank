package homebank.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class Login {
	@Id
	@NotEmpty(message="Username shoud not ne empty")
	private  String username;
	@NotEmpty(message="Password must not be empty")
	private String password;
	@Autowired
	@OneToOne
	private Client client;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public  void setPassword(String password) {
		this.password = password;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	
	
}
