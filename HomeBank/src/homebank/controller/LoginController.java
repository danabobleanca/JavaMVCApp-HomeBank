package homebank.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import homebank.model.CreditAccount;
import homebank.model.CurrentAccount;
import homebank.model.Login;
import homebank.model.SavingsAccount;
import homebank.service.AccountService;
import homebank.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	LoginService loginService;
	
	@Autowired 
	AccountService accountService;

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String loginUser(Model model){
		model.addAttribute("loginDetails", new Login());
		return "loginPage";
	}
	
	@RequestMapping(value ="/loginSuccess" ,method=RequestMethod.POST)
	public ModelAndView loginSuccess(@Valid @ModelAttribute("loginDetails") Login clientCredential,BindingResult bindingResult, HttpSession session){
		if(bindingResult.hasErrors()){
			return new ModelAndView("loginPage");
		}
		
		ModelAndView modelAndView = new ModelAndView("welcome");
		Login login = loginService.validateClientCredential(clientCredential.getUsername(),clientCredential.getPassword());
		if(login!= null){
			modelAndView.addObject("login", login);
			CurrentAccount currentAccount= accountService.getCurrentAccountDetailsByClientUsername(login.getUsername()).get(0);
			SavingsAccount savingsAccount= accountService.getSavingsAccountDetailsByClientUsername(login.getUsername()).get(0);
			CreditAccount creditAccount=accountService.getCreditAccountDetailsByClientUsername(login.getUsername()).get(0);
			getNavigationData(modelAndView, session, currentAccount, savingsAccount, creditAccount);
			return modelAndView;
		}else{
			 modelAndView = new ModelAndView("notFound");
		}
		return modelAndView;
	}
	
	
	public ModelAndView getNavigationData(ModelAndView modelAndView, HttpSession session, CurrentAccount currentAccount, SavingsAccount savingsAccount, CreditAccount creditAccount) {
		accountsSetAttribute(session, currentAccount, savingsAccount, creditAccount);
		getModelDetails(modelAndView, currentAccount, savingsAccount, creditAccount);
		return modelAndView;
		
	}

	public void accountsSetAttribute(HttpSession session, CurrentAccount currentAccount, SavingsAccount savingsAccount, CreditAccount creditAccount) {
		session.setAttribute("currentAccount", currentAccount);
		session.setAttribute("savingsAccount", savingsAccount);
		session.setAttribute("creditAccount", creditAccount);
	}
	
	public ModelAndView getModelDetails(ModelAndView modelAndView, CurrentAccount currentAccount, SavingsAccount savingsAccount, CreditAccount creditAccount ) {
		modelAndView.addObject("iban", currentAccount.getIban());
		modelAndView.addObject("balance", currentAccount.getAccountBalance());
		modelAndView.addObject("ibanSavings", savingsAccount.getIban());
		modelAndView.addObject("balanceSavings", savingsAccount.getAccountBalance());
		modelAndView.addObject("ibanCredit", creditAccount.getIban());
		modelAndView.addObject("balanceCredit", creditAccount.getAccountBalance());
		return modelAndView;
	}
}
