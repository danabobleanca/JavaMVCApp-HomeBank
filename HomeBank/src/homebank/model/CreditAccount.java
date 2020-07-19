package homebank.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;

@Entity
public class CreditAccount extends Account {
	
	@Enumerated(EnumType.STRING)
	TypeOfAccount typeOfAccount;
	
	@OneToMany(mappedBy="creditAccount")
	List<RatesGraph> rates;

}
