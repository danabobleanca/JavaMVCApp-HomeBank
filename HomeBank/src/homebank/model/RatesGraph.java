package homebank.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class RatesGraph {

	@Id
	private Integer id;
	private Double rateToPay;
	
	@Temporal(TemporalType.TIMESTAMP)
	Date dateOfPayRate;
	 
	private Integer amountToBePaid;
	private Integer amountPaid;
	@ManyToOne
	private CreditAccount creditAccount;
	

	public Date getDateOfPayRate() {
		return dateOfPayRate;
	}
	public void setDateOfPayRate(Date dateOfPayRate) {
		this.dateOfPayRate = dateOfPayRate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getRateToPay() {
		return rateToPay;
	}
	public void setRateToPay(Double rateToPay) {
		this.rateToPay = rateToPay;
	}
	public Integer getAmountToBePaid() {
		return amountToBePaid;
	}
	public void setAmountToBePaid(Integer amountToBePaid) {
		this.amountToBePaid = amountToBePaid;
	}
	public Integer getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(Integer amountPaid) {
		this.amountPaid = amountPaid;
	}
	public CreditAccount getCreditAccount() {
		return creditAccount;
	}
	public void setCreditAccount(CreditAccount creditAccount) {
		this.creditAccount = creditAccount;
	}


	@Override
	public String toString() {
		return "RatesGraph [id=" + id + ", rateToPay=" + rateToPay + ", amountToBePaid=" + amountToBePaid
				+ ", amountPaid=" + amountPaid + ", creditAccount=" + creditAccount + "]";
	}
}
