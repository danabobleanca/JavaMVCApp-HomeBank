package homebank.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Account {
	@Id
	private String iban;
	private Double accountBalance ;
	
	@Autowired
	@ManyToOne
	private Client client;
	
	@OneToMany(mappedBy="account")
	private List<AccountTransaction> transactions;
	
	public String getIban() {
		return iban;
	}
	public void setIban(String iban) {
		this.iban = iban;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Double getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}
	
	
}
