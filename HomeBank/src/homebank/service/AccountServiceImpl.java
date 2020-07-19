package homebank.service;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import homebank.dao.AccountDao;
import homebank.model.Account;
import homebank.model.AccountTransaction;
import homebank.model.CreditAccount;
import homebank.model.CurrentAccount;
import homebank.model.RatesGraph;
import homebank.model.SavingsAccount;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	AccountDao accountDao;
	
	@Override
	public List<CurrentAccount> getCurrentAccountDetailsByClientUsername(Object username){
		return accountDao.getCurrentAccountDetailsByClientUsername(username);
	}
	@Override
	public List<SavingsAccount> getSavingsAccountDetailsByClientUsername(Object username){
		return accountDao.getSavingsAccountDetailsByClientUsername(username);
	}

	@Override
	public List<CreditAccount> getCreditAccountDetailsByClientUsername(Object username) {
		return accountDao.getCreditAccountDetailsByClientUsername(username);
	}
	
	public Double getTotalPaidForCredit(String iban) {
		return accountDao.getTotalPaidForCredit(iban);
	}
	@Override
	public Boolean sufficientFunds(String iban, Double valueOfTransaction) {
		return accountDao.sufficientFunds(iban, valueOfTransaction);
	}
	@Override
	public Double getBalanceFromAccount(String iban) {
		return accountDao.getBalanceFromAccount(iban);
	}
	@Override
	public Boolean accountExists(String iban) {
		return accountDao.accountExists(iban);
	}
	@Override
	public Account getClientAccount(String iban) {
		return accountDao.getClientAccount(iban);
	}
	@Override
	public Boolean processingTransfer(String myIban, Double transferred, String otherIban) {
		return accountDao.processingTransfer(myIban, transferred, otherIban);
	}
	@Override
	public void updateSenderBalanceAccount(String iban,Double balance){
		accountDao.updateSenderBalanceAccount(iban,balance);
	}
	
	@Override
	public void updateReceiverBalanceAccount(String iban,Double balance){
		accountDao.updateReceiverBalanceAccount(iban,balance);
	}
	@Override
	public void updateCreditbalanceAccount(String iban, Double transaction) {
		accountDao.updateCreditbalanceAccount(iban, transaction);
		
	}
	@Override
	public List<AccountTransaction>getReport(String iban) {
		return accountDao.getReport(iban);
	}
	
	public List<RatesGraph> getRatesGraph(String iban) {
		return accountDao.getRatesGraph(iban);
	}
	
	public void saveRatesGraph(CreditAccount credit, Integer months) {
		accountDao.saveRatesGraph(credit, months);
	}
	
	public Double creditRemainingToPay(String iban) {
		return accountDao.creditRemainingToPay(iban);
	}
	
	public Boolean valuePaidBiggerThanCredit(Double valueOfTransaction, Double creditRemainingToPay) {
		return accountDao.valuePaidBiggerThanCredit(valueOfTransaction, creditRemainingToPay);
	}
	@Override
	public void addingInterestToSavingAccount(String iban) {
		accountDao.addingInterestToSavingAccount(iban);
		
	}
	@Override
	public String transactionFailed(String myIban, String otherIban, Double valueOfTransaction) {
		String messageFailure;
		if(myIban.equals(otherIban)) {
			messageFailure="The sender IBAN and the receiver IBAN must be distinct";
		}else if(!accountExists(otherIban)){
			messageFailure="The IBAN you introduced does not exist!";
		}else if(valueOfTransaction<=0.00){
			messageFailure="Amount which you want to transfer must be positive!!";
		}else if(valueOfTransaction>getBalanceFromAccount(myIban)){
			messageFailure="Insufficient funds!!";
		}else {
			messageFailure="The amount you want to pay for credit is bigger than value of credit";
		}
		return messageFailure;
	}
}
