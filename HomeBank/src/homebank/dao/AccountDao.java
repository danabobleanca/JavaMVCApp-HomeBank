package homebank.dao;


import java.util.List;
import homebank.model.Account;
import homebank.model.AccountTransaction;
import homebank.model.CreditAccount;
import homebank.model.CurrentAccount;
import homebank.model.RatesGraph;
import homebank.model.SavingsAccount;

public interface AccountDao {

	List<CurrentAccount> getCurrentAccountDetailsByClientUsername(Object username);
	List<SavingsAccount> getSavingsAccountDetailsByClientUsername (Object username);
	List<CreditAccount> getCreditAccountDetailsByClientUsername (Object username);
	Boolean sufficientFunds(String iban, Double valueOfTransaction);
	Double getBalanceFromAccount(String iban);
	Boolean accountExists(String iban);
	Account getClientAccount(String iban);
	Boolean processingTransfer(String  myIban, Double transferred, String otherIban);
	void updateSenderBalanceAccount(String iban,Double balance);
	void updateReceiverBalanceAccount(String iban,Double balance);
	void updateCreditbalanceAccount(String iban, Double transaction);
	List<AccountTransaction> getReport(String iban);
	void saveRatesGraph(CreditAccount credit, Integer months);
	List<RatesGraph> getRatesGraph(String iban) ;
	Double getTotalPaidForCredit(String iban);
	Double creditRemainingToPay(String iban);
	Boolean valuePaidBiggerThanCredit(Double valueOfTransaction, Double creditRemainingToPay);
	void addingInterestToSavingAccount(String iban);
}

