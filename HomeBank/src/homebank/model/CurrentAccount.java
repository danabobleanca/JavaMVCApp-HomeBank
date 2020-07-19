package homebank.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Entity
public class CurrentAccount extends Account {

	@Enumerated(EnumType.STRING)
	TypeOfAccount typeOfAccount;
	
}