package homebank.service;

import homebank.model.Login;

public interface LoginService {

	Login validateClientCredential(String username, String password);

}
