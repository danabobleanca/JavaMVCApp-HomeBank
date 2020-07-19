package homebank.service;

import java.util.List;

import homebank.model.Account;
import homebank.model.AccountTransaction;
import homebank.model.CreditAccount;
import homebank.model.CurrentAccount;
import homebank.model.RatesGraph;
import homebank.model.SavingsAccount;

public interface AccountService {
	
	public List<CurrentAccount> getCurrentAccountDetailsByClientUsername(Object username);
	public List<SavingsAccount> getSavingsAccountDetailsByClientUsername(Object username);
	public List<CreditAccount> getCreditAccountDetailsByClientUsername(Object username);
	public Boolean sufficientFunds(String iban, Double valueOfTransaction);
	public Double getBalanceFromAccount(String iban);
	public Boolean accountExists(String iban);
	public Account getClientAccount(String iban);
	public Boolean processingTransfer(String  myIban, Double transferred, String otherIban);
	public void updateSenderBalanceAccount(String iban,Double balance);
	public void updateReceiverBalanceAccount(String iban,Double balance);
	public void updateCreditbalanceAccount(String iban,Double transaction);
	public List<AccountTransaction> getReport(String iban);
	public void saveRatesGraph(CreditAccount credit, Integer months);
	public List<RatesGraph> getRatesGraph(String iban);
	public Double getTotalPaidForCredit(String iban);
	public Double creditRemainingToPay(String iban);
	public Boolean valuePaidBiggerThanCredit(Double valueOfTransaction, Double creditRemainingToPay);
	public void addingInterestToSavingAccount(String iban);
	public String transactionFailed(String myIban, String otherIban, Double valueOfTransaction);
}