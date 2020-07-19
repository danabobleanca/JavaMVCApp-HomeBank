package homebank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import homebank.dao.LoginDao;
import homebank.model.Login;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	LoginDao loginDao;
	
	public LoginDao getLoginDao() {
		return loginDao;
	}

	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}

	public Login validateClientCredential(String username, String password) {
		Login login = loginDao.getLoginDetailsByUsernameAndPassword(username, password);
		return login;
	}

	
}
