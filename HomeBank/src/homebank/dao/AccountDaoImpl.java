package homebank.dao;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import homebank.model.Account;
import homebank.model.AccountTransaction;
import homebank.model.CreditAccount;
import homebank.model.CurrentAccount;
import homebank.model.RatesGraph;
import homebank.model.SavingsAccount;
import homebank.model.TypeOfOperation;

@Repository
public class AccountDaoImpl implements AccountDao {
	@Autowired
	HibernateTemplate hibernateTemplate;
	AccountTransaction transactionObj=new AccountTransaction();
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
}

	@Override
	public List<CurrentAccount> getCurrentAccountDetailsByClientUsername(Object username)  {
		String queryString="select a FROM CurrentAccount a, Client c, Login l WHERE c.id=a.client.id AND a.client.id=l.client.id AND l.username=?";
		List<CurrentAccount> listCurrentAccounts=(List<CurrentAccount>) hibernateTemplate.find(queryString, username);
		if(listCurrentAccounts.size()>0) {
			return listCurrentAccounts;
		}
		else {
			CurrentAccount currentAccount=new CurrentAccount();
			currentAccount.setIban("You do not have a current account");
			currentAccount.setAccountBalance(0.00);
			listCurrentAccounts.add(currentAccount);
			return listCurrentAccounts;
		}
	}
	
	@Override
	public List<SavingsAccount> getSavingsAccountDetailsByClientUsername(Object username) {
		String queryString="SELECT s from SavingsAccount s, Client c, Login l WHERE c.id=s.client.id AND s.client.id=l.client.id AND l.username=?";
		List<SavingsAccount> listSavingsAccounts=(List<SavingsAccount>) hibernateTemplate.find(queryString, username);
		if(listSavingsAccounts.size()>0) {
			return listSavingsAccounts;
		}
		else {
			SavingsAccount savings=new SavingsAccount();
			savings.setIban("You do not have a saving account");
			savings.setAccountBalance(0.00);
			listSavingsAccounts.add(savings);
			return listSavingsAccounts;
		}
	}

	@Override
	public List<CreditAccount> getCreditAccountDetailsByClientUsername(Object username) {
		String queryString="SELECT c from CreditAccount c, Client cl, Login l WHERE cl.id=c.client.id AND c.client.id=l.client.id AND l.username=?";
		List<CreditAccount> listCreditAccounts=(List<CreditAccount>) hibernateTemplate.find(queryString, username);
		if(listCreditAccounts.size()>0) {
			return listCreditAccounts;
		}else {
			CreditAccount credit=new CreditAccount();
			credit.setIban("You do not have a credit account");
			credit.setAccountBalance(0.00);
			listCreditAccounts.add(credit);
			return listCreditAccounts;
		}
	}
	
	@Override
	public Account getClientAccount(String iban) {
		String verifyIfAccountExists="SELECT a FROM Account a WHERE a.iban=:iban";
		List<Account> accounts=(List<Account>) hibernateTemplate.findByNamedParam(verifyIfAccountExists, "iban", iban);
		for(Account a:accounts) {
			if(a.getIban().equals(iban)) {
				return a;
			} 
			
		}
		return null;
	}
	
	@Override
	public Boolean accountExists(String iban) {
		String verifyIfAccountExists="SELECT a FROM Account a WHERE a.iban=:iban";
		List<Account> accounts=(List<Account>) hibernateTemplate.findByNamedParam(verifyIfAccountExists, "iban", iban);
		for(Account a:accounts) {
			if(a.getIban().equals(iban)){
				return true;
			}
		}
		return false;
	}

	@Override
	public Double getBalanceFromAccount(String iban) {
		return getClientAccount(iban).getAccountBalance();
	}

	@Override
	public Boolean sufficientFunds(String iban, Double valueOfTransaction) {
		Double balance=getBalanceFromAccount(iban);
		if((balance-valueOfTransaction)<0) {
			return  false;
		}else
			return true;
	}


	@Override
	public Boolean processingTransfer(String myIban, Double valueOfTransaction, String otherIban) {
		Double balance=0.00;
		Double otherAccount=0.00;
		if(accountExists(myIban)&& accountExists(otherIban)) {
			if(!myIban.equals(otherIban)) {
				if((sufficientFunds(myIban, valueOfTransaction))&&(valueOfTransaction>0)) {
					balance+=getBalanceFromAccount(myIban)-valueOfTransaction;
					otherAccount+=getBalanceFromAccount(otherIban)+valueOfTransaction;
					return true;
				}
			}
		}
		return false;
	}
	
	public void updateSenderBalanceAccount(String iban,Double transaction) {
		Session session=hibernateTemplate.getSessionFactory().openSession();
		Transaction tx=session.beginTransaction();
		org.hibernate.Query query=session.createQuery("UPDATE Account a SET a.accountBalance=:balance WHERE a.iban=:iban");
		Double currentBalance=getBalanceFromAccount(iban)-transaction;;
		transactionObj=new AccountTransaction(TypeOfOperation.PAYMENT, getClientAccount(iban), transaction);
		session.persist(transactionObj);
		query.setParameter("balance", currentBalance);
		query.setParameter("iban", iban);
		query.executeUpdate();
		tx.commit();
	}
	
	public void updateReceiverBalanceAccount(String iban,Double transaction) {
		Session session=hibernateTemplate.getSessionFactory().openSession();
		Transaction tx=session.beginTransaction();
		org.hibernate.Query query=session.createQuery("UPDATE Account a SET a.accountBalance=:balance WHERE a.iban=:iban");
		Double currentBalance=getBalanceFromAccount(iban)+transaction;
		transactionObj=new AccountTransaction(TypeOfOperation.COLLECTION, getClientAccount(iban), transaction);
		session.persist(transactionObj);
		query.setParameter("balance", currentBalance);
		query.setParameter("iban", iban);
		query.executeUpdate();
		tx.commit();
	}
	
	
	@Override
	public void updateCreditbalanceAccount(String iban,Double transaction) {
		Session session=hibernateTemplate.getSessionFactory().openSession();
		Transaction tx=session.beginTransaction();
		org.hibernate.Query query=session.createQuery("UPDATE Account a SET a.accountBalance=:balance WHERE a.iban=:iban");
		Double currentBalance=getBalanceFromAccount(iban)-transaction;
		transactionObj=new AccountTransaction(TypeOfOperation.PAYMENT, getClientAccount(iban), transaction);
		session.persist(transactionObj);
		query.setParameter("balance", currentBalance);
		query.setParameter("iban", iban);
		query.executeUpdate();
		tx.commit();
	}
	
	@Override
	public List<AccountTransaction> getReport(String iban) {
		String queryTransactions="SELECT t FROM AccountTransaction t WHERE t.account.iban=:iban";
		List<AccountTransaction> transactions=(List<AccountTransaction>) hibernateTemplate.findByNamedParam(queryTransactions,"iban", iban);
		if(transactions.size()>0) {
			return transactions;
		}else 
			return null;
	}
	
	@Override
	public void saveRatesGraph(CreditAccount credit, Integer months) {
		Session session=hibernateTemplate.getSessionFactory().openSession();
		Transaction tx=session.beginTransaction();
		
		RatesGraph ratesGraph;
		Double rateToPay=0.00;	
		Integer currentMonth=5;
		Integer currentYear=2020;
		Double creditBalance=credit.getAccountBalance();
		Calendar calendar=GregorianCalendar.getInstance();
		for(int i=0;i<months;i++) {
			rateToPay=(Double)creditBalance/months;
			ratesGraph = new RatesGraph();
			ratesGraph.setId(i+1);
			ratesGraph.setRateToPay(rateToPay);
			ratesGraph.setCreditAccount(credit);

			calendar.set(Calendar.YEAR, currentYear);
			calendar.set(Calendar.MONTH, currentMonth);
			calendar.set(Calendar.DAY_OF_MONTH, 15);
			Date date=calendar.getTime();
			if(currentMonth>11) {
				currentMonth=0;
				calendar.set(Calendar.YEAR, currentYear++);
			}
			ratesGraph.setDateOfPayRate(date);
			currentMonth++;
				//rates.add(ratesGraph);
			session.merge(ratesGraph);
		}
		tx.commit();
	}
	public Double getTotalPaidForCredit(String iban) {
		Double totalPaidForCredit=0.00;
		String query="SELECT at FROM AccountTransaction at, Account c WHERE at.account.iban=c.iban AND at.account.iban=:iban";
		List<AccountTransaction> transactions=(List<AccountTransaction>) hibernateTemplate.findByNamedParam(query, "iban" ,iban);
		for(AccountTransaction at:transactions) {
			if(at.getTypeOfOperation().equals(TypeOfOperation.PAYMENT)) {
				totalPaidForCredit+=at.getTransactionAmmount();
			}
		}
		return totalPaidForCredit;
	} 
	
	public Double creditRemainingToPay(String iban) {
		//Double remainingToPay=0.00;
		Double valueOfCredit=getBalanceFromAccount(iban)+getTotalPaidForCredit(iban);
		return valueOfCredit-getTotalPaidForCredit(iban);
	}
	
	public Boolean valuePaidBiggerThanCredit(Double valueOfTransaction, Double creditRemainingToPay) {
		if(valueOfTransaction>creditRemainingToPay) {
			return false;
		}
		return true;
	}
	
	public Double calculateInterestForSavingsAccount(String iban) {
		Double ratePerMonthSavings=0.01;
		return ratePerMonthSavings*getBalanceFromAccount(iban);
	}
	
	public void addingInterestToSavingAccount(String iban) {
		Calendar calendar=Calendar.getInstance();
		Integer getDay=calendar.get(Calendar.DAY_OF_MONTH);
		if(getDay==19) {
			Double interestAmount=calculateInterestForSavingsAccount(iban);
			updateReceiverBalanceAccount(iban,interestAmount);
		}
	}
	
	public List<RatesGraph> getRatesGraph(String iban) {
		String query="SELECT rg FROM RatesGraph rg WHERE rg.creditAccount.iban=:iban";
		List<RatesGraph> rates=(List<RatesGraph>) hibernateTemplate.findByNamedParam(query, "iban", iban);
		if(rates.size()>0) {
			return rates;
		}else {
			RatesGraph rate=new RatesGraph();
			rate.setRateToPay(200.00);
			rates.add(rate);
			return rates;
		}
	}

}
