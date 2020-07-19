package homebank.dao;

import homebank.model.Login;

public interface LoginDao {
	Login getLoginDetailsByUsernameAndPassword(String username,String password);

}
