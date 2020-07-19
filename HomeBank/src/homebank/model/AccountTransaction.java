package homebank.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class AccountTransaction {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Enumerated(EnumType.STRING)
	TypeOfOperation typeOfOperation;
	@ManyToOne
	Account account;
	private Double transactionAmmount;
	
	@Temporal(TemporalType.TIMESTAMP)
	Date date;
	
	public AccountTransaction() {}
	public AccountTransaction(TypeOfOperation typeOfOperation, Account account, Double transactionAmmount) {
		super();
		this.typeOfOperation = typeOfOperation;
		this.account = account;
		this.transactionAmmount = transactionAmmount;
		this.date = new Date();
				
	}
	

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Double getTransactionAmmount() {
		return transactionAmmount;
	}
	public void setTransactionAmmount(Double transactionAmmount) {
		this.transactionAmmount = transactionAmmount;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public TypeOfOperation getTypeOfOperation() {
		return typeOfOperation;
	}
	public void setTypeOfOperation(TypeOfOperation typeOfOperation) {
		this.typeOfOperation = typeOfOperation;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
}
