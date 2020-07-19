package homebank.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import homebank.model.AccountTransaction;
import homebank.model.CreditAccount;
import homebank.model.CurrentAccount;
import homebank.model.RatesGraph;
import homebank.model.SavingsAccount;
import homebank.service.AccountService;

@Controller
public class AccountPaymentController {
	
	@Autowired
	AccountService accountService;
	@Autowired
	LoginController login;
	
	public ModelAndView accountsGetAttribute(ModelAndView mv, HttpSession session, CurrentAccount currentAccount, SavingsAccount savingsAccount, CreditAccount creditAccount) {
		currentAccount=(CurrentAccount) session.getAttribute("currentAccount");
		savingsAccount=(SavingsAccount) session.getAttribute("savingsAccount");
		creditAccount=(CreditAccount) session.getAttribute("creditAccount");
		login.getModelDetails(mv, currentAccount, savingsAccount, creditAccount);
		return mv;
		
	}
	
	@RequestMapping(value="/portofolio" ,method=RequestMethod.GET)
	public ModelAndView accesPortofolio(ModelAndView mv, HttpSession session, CurrentAccount currentAccount, SavingsAccount savingsAccount, CreditAccount creditAccount){	
		mv.setViewName("welcome");
		accountsGetAttribute(mv, session, currentAccount, savingsAccount,creditAccount);
		return mv;
	}
	
	@RequestMapping(value="/paymentBetweenMyAccounts", method=RequestMethod.GET)
	public ModelAndView paymentsBetweenAccounts(ModelAndView mv, HttpSession session, CurrentAccount currentAccount, SavingsAccount savingsAccount, CreditAccount creditAccount) {
		mv.setViewName("paymentBetweenMyAccounts");
		accountsGetAttribute(mv, session, currentAccount, savingsAccount,creditAccount);
		return mv;
	}
	
	@RequestMapping(value="/paymentInRon", method=RequestMethod.GET)
	public ModelAndView paymentsInRon(ModelAndView mv, HttpSession session, CurrentAccount currentAccount, SavingsAccount savingsAccount, CreditAccount creditAccount) {
		mv.setViewName("paymentInRon");
		accountsGetAttribute(mv, session, currentAccount, savingsAccount,creditAccount);
		return mv;
	}
	
	@RequestMapping(value="/saving", method=RequestMethod.GET)
	public ModelAndView saving(ModelAndView mv, HttpSession session, CurrentAccount currentAccount, SavingsAccount savingsAccount, CreditAccount creditAccount) {
		mv.setViewName("saving");
		accountsGetAttribute(mv, session, currentAccount, savingsAccount,creditAccount);
		return mv;
	}
	
	@RequestMapping(value="/refund", method=RequestMethod.GET)
	public ModelAndView refundInRon(ModelAndView mv, HttpSession session, CurrentAccount currentAccount, SavingsAccount savingsAccount, CreditAccount creditAccount) {
		mv.setViewName("refund");
		accountsGetAttribute(mv, session, currentAccount, savingsAccount,creditAccount);
		return mv;
	}
	
	
	@RequestMapping(value= {"/paymentProcessed","/paymentInRonProcessed","/savingPaymentProcessed"}, method=RequestMethod.POST)
	public ModelAndView paymentProcessed(HttpSession session, @RequestParam("ibanPayment") String myIban, @RequestParam("sumToTransfer") Double amountTransferred, 
						@RequestParam ("otherIban") String otherIban,ModelAndView mv ,CurrentAccount currentAccount, SavingsAccount savingsAccount, CreditAccount creditAccount) {
		
		accountsGetAttribute(mv, session, currentAccount, savingsAccount,creditAccount);
		Boolean transfer=accountService.processingTransfer(myIban, amountTransferred, otherIban);

		if(transfer) {
			mv.setViewName("paymentProcessed");
			mv.addObject("myIban", myIban);
			mv.addObject("otherIban", otherIban);
			session.setAttribute("payment",amountTransferred );
		
			accountService.updateSenderBalanceAccount(myIban, amountTransferred);
			accountService.updateReceiverBalanceAccount(otherIban, amountTransferred);
			return mv;
		}
		else {
			ModelAndView mvFailed=new ModelAndView("transactionFailed");
			String transactionFailed=accountService.transactionFailed(myIban, otherIban, amountTransferred);
			mvFailed.addObject("messageFail", transactionFailed);
			return mvFailed;
		}
	}
	

	
	@RequestMapping(value= {"/refundPaymentProcessed"}, method=RequestMethod.POST)
	public ModelAndView refundProcessed(HttpSession session, @RequestParam("ibanPayment") String myIban, @RequestParam("sumToTransfer") Double amountTransferred, 
						@RequestParam ("otherIban") String otherIban,ModelAndView mv ,CurrentAccount currentAccount, SavingsAccount savingsAccount, CreditAccount creditAccount) {
		accountsGetAttribute(mv, session, currentAccount, savingsAccount,creditAccount);
		CreditAccount credit=(CreditAccount) session.getAttribute("creditAccount");
		Boolean transfer=accountService.processingTransfer(myIban, amountTransferred, otherIban);
		Boolean valuePaidBiggerThanCredit=accountService.valuePaidBiggerThanCredit(amountTransferred, credit.getAccountBalance());
		ModelAndView mvFailed=new ModelAndView("transactionFailed");
		String transactionFailed=accountService.transactionFailed(myIban, otherIban, amountTransferred);
		mvFailed.addObject("messageFail", transactionFailed);
		
		if(valuePaidBiggerThanCredit) {
			if(transfer) {
				mv.setViewName("paymentProcessed");
				mv.addObject("myIban", myIban);
				mv.addObject("otherIban", otherIban);
				session.setAttribute("payment",amountTransferred );
				accountService.updateSenderBalanceAccount(myIban, amountTransferred);
				accountService.updateCreditbalanceAccount(otherIban,amountTransferred);
				return mv;
			}
			else {return mvFailed;}
		}
		return mvFailed;
	}
	
	
}
