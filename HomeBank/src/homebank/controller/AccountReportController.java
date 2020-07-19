package homebank.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import homebank.model.AccountTransaction;
import homebank.model.CreditAccount;
import homebank.model.CurrentAccount;
import homebank.model.RatesGraph;
import homebank.model.SavingsAccount;
import homebank.service.AccountService;

@Controller
public class AccountReportController {

	@Autowired
	AccountPaymentController apc;
	@Autowired
	AccountService accountService;
	
	@RequestMapping(value="/currentAccountReport", method=RequestMethod.GET)
	public ModelAndView getTransactions(ModelAndView mv, HttpSession session, CurrentAccount currentAccount, SavingsAccount savingsAccount, CreditAccount creditAccount) {
		mv.setViewName("currentAccountReport");
		apc.accountsGetAttribute(mv, session, currentAccount, savingsAccount,creditAccount);
		CurrentAccount ca=(CurrentAccount) session.getAttribute("currentAccount");
		if(ca.getIban().equals("You do not have a current account")){
			ModelAndView noReport=new ModelAndView();
			noReport.setViewName("noTransactionReport");
			return noReport;
		}else {
			List<AccountTransaction> transactions=accountService.getReport(ca.getIban());
			mv.addObject("transactions", transactions);
			return mv;
		}
	}
	
	
	@RequestMapping(value="/savingsAccountReport", method=RequestMethod.GET)
	public ModelAndView getSavingsTransactions(ModelAndView mv, HttpSession session, CurrentAccount currentAccount, SavingsAccount savingsAccount, CreditAccount creditAccount) {
		mv.setViewName("savingsAccountReport");
		apc.accountsGetAttribute(mv, session, currentAccount, savingsAccount,creditAccount);
		SavingsAccount sa=(SavingsAccount) session.getAttribute("savingsAccount");
		List<AccountTransaction> transactions=accountService.getReport(sa.getIban());
		if(sa.getIban().equals("You do not have a saving account")){
			ModelAndView noReport=new ModelAndView();
			noReport.setViewName("noTransactionReport");
			return noReport;
		}else {
			accountService.addingInterestToSavingAccount(sa.getIban());
			mv.addObject("transactions", transactions);
			return mv;
		}
	}
	
	
	@RequestMapping(value="/creditAccountReport", method=RequestMethod.GET)
	public ModelAndView getCreditTransactions(ModelAndView mv, HttpSession session, CurrentAccount currentAccount, SavingsAccount savingsAccount, CreditAccount creditAccount) {
		mv.setViewName("creditAccountReport");
		apc.accountsGetAttribute(mv, session, currentAccount, savingsAccount,creditAccount);
		CreditAccount ca=(CreditAccount) session.getAttribute("creditAccount");
		if(ca.getIban().equals("You do not have a credit account")){
			ModelAndView noReport=new ModelAndView();
			noReport.setViewName("noTransactionReport");
			return noReport;
		}
		else {
			List<AccountTransaction> transactions=accountService.getReport(ca.getIban());
			mv.addObject("transactions", transactions);
			return mv;
		}
	}
	
	
	@RequestMapping(value="/ratesGraph", method=RequestMethod.GET)
	public ModelAndView calculateRate(ModelAndView mv, HttpSession session, CurrentAccount currentAccount, SavingsAccount savingsAccount, CreditAccount creditAccount) {
		apc.accountsGetAttribute(mv, session, currentAccount, savingsAccount,creditAccount);
		mv.setViewName("ratesGraph");
		CreditAccount credit=(CreditAccount) session.getAttribute("creditAccount");
		if(credit.getIban().equals("You do not have a credit account")){
			ModelAndView noReport=new ModelAndView();
			noReport.setViewName("noTransactionReport");
			return noReport;
		}else {
			accountService.saveRatesGraph(credit, 36);
			Double totalPaidForCredit=accountService.getTotalPaidForCredit(credit.getIban());
			Double remainingToPay=accountService.creditRemainingToPay(credit.getIban());
			List<RatesGraph> ratesList=accountService.getRatesGraph(credit.getIban());
			mv.addObject("ratesList", ratesList);
			mv.addObject("totalPaid", totalPaidForCredit);
			mv.addObject("remainingToPay", remainingToPay);
			return mv;
		}
	}
	
}
