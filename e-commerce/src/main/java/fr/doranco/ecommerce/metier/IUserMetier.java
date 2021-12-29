package fr.doranco.ecommerce.metier;

import java.util.List;

import fr.doranco.ecommerce.entity.User;

public interface IUserMetier {
	User getUserByEmail(String email) throws Exception;
	
	List<User> getUsers() throws Exception;

	User addUser(User user) throws Exception;
}
