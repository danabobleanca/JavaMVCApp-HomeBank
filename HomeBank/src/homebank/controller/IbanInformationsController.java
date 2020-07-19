package homebank.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import homebank.model.CreditAccount;
import homebank.model.CurrentAccount;
import homebank.model.SavingsAccount;

@Controller
public class IbanInformationsController {
	
	@Autowired
	AccountPaymentController accountController;

	@RequestMapping(value="/currentAccountInformations", method=RequestMethod.GET)
	public ModelAndView getCurrentAccountInformations(ModelAndView mv, HttpSession session) {

		CurrentAccount cra=(CurrentAccount) session.getAttribute("currentAccount");
		if(cra.getIban().equals("You do not have a current account")){
			ModelAndView noReport=new ModelAndView();
			noReport.setViewName("noTransactionReport");
			return noReport;
		}else {
			mv.setViewName("currentAccountDetails");
			mv.addObject("iban", cra.getIban());
			mv.addObject("name", cra.getClient().getName());
			mv.addObject("balance", cra.getAccountBalance());
			return mv;
		}
	}
	
	@RequestMapping(value="/savingsAccountInformations", method=RequestMethod.GET)
	public ModelAndView getSavingsAccountInformations(ModelAndView mv, HttpSession session) {

		SavingsAccount sa=(SavingsAccount) session.getAttribute("savingsAccount");
		if(sa.getIban().equals("You do not have a saving account")){
			ModelAndView noReport=new ModelAndView();
			noReport.setViewName("noTransactionReport");
			return noReport;
		}else {
			mv.setViewName("savingsAccountDetails");
			mv.addObject("iban", sa.getIban());
			mv.addObject("name", sa.getClient().getName());
			mv.addObject("balance", sa.getAccountBalance());
			return mv;
		}
	}
	
}
