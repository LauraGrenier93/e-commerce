package fr.doranco.ecommerce.metier;


import java.util.List;

import fr.doranco.ecommerce.entity.User;
import fr.doranco.ecommerce.model.dao.IUserDao;
import fr.doranco.ecommerce.model.dao.UserDao;

public class UserMetier implements IUserMetier {

	private final IUserDao userDao = new UserDao();
	
	@Override
	public User getUserByEmail(String email) throws Exception {
		if (email == null || email.trim().isEmpty()) {
			throw new IllegalArgumentException("L'email doit être renseigné !");
		}
		return userDao.getUserByEmail(email);
	}
	
	@Override
	public List<User> getUsers() throws Exception {
		
		return userDao.getUsers();
	}

	@Override
	public User addUser(User user) throws Exception {
		if (user == null) {
			throw new NullPointerException("l'utilisateur à ajouter est NULL !");
		}
		if (user.getNom() == null || user.getNom().trim().isEmpty()
				|| user.getPrenom() == null || user.getPrenom().trim().isEmpty()) {
			throw new IllegalArgumentException();
		}
		user.setNom(user.getNom().trim().toUpperCase());
		User addedUser = userDao.addUser(user);
		return addedUser;
	}


}
