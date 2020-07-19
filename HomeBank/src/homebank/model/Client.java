package homebank.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Client {
	@Id
	private Integer id;
	private String name;
	private String cnp;
	@OneToMany(mappedBy="client")
	private List<Account> accounts;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCnp() {
		return cnp;
	}
	public void setCnp(String cnp) {
		this.cnp = cnp;
	}
	
	public Client() {}
	public Client(Integer id, String name, String cnp) {
		super();
		this.id = id;
		this.name = name;
		this.cnp = cnp;
	}
	
}
