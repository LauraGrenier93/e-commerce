package fr.doranco.ecommerce.model.dao;

import java.util.List;

import fr.doranco.ecommerce.entity.User;

public interface IUserDao {
	User getUserByEmail(String email) throws Exception;

	List<User> getUsers() throws Exception;

	User addUser(User user) throws Exception;
}
