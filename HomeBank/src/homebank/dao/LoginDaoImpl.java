package homebank.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
 
import homebank.model.Login;
@Repository
public class LoginDaoImpl implements LoginDao{

	@Autowired
	private  HibernateTemplate hibernateTemplate;

 	public  HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public  void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
			this.hibernateTemplate = hibernateTemplate;
	}


	public  Login getLoginDetailsByUsernameAndPassword(String username,String password){
		DetachedCriteria detachedCriteria =  DetachedCriteria.forClass(Login.class);
		detachedCriteria.add(Restrictions.eq("username", username));
		detachedCriteria.add(Restrictions.eq("password", password));
		List<Login> findByCriteria = (List<Login>) hibernateTemplate.findByCriteria(detachedCriteria);
		if(findByCriteria !=null && findByCriteria.size()>0)
			return findByCriteria.get(0);
		else
			return null;

	}
}
