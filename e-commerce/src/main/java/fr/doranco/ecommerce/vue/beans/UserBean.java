package fr.doranco.ecommerce.vue.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import fr.doranco.ecommerce.entity.User;
import fr.doranco.ecommerce.metier.IUserMetier;
import fr.doranco.ecommerce.metier.UserMetier;

@ManagedBean(name = "userbean")
@SessionScoped
public class UserBean {
	private int id;
	@ManagedProperty(value = "DUPOND")
	private String nom;
	@ManagedProperty(value = "MICHEL")
	private String prenom;
	@ManagedProperty(value = "benoit@doranco.fr")
	private String email;
	private String password;
	private String errorMessage = "";
	private String validateMessage = "";
	private static List<User> userList;
	private final IUserMetier userMetier = new UserMetier();

	public UserBean() {
	}

	public List<User> getUsers() {
		try {
			return userMetier.getUsers();
		} catch (Exception e) {
			validateMessage = "";
			errorMessage = "Erreur technique lors de la récupération des utilisateurs : \n" + e.getMessage();
			e.printStackTrace();
		}
		return new ArrayList<User>();
	}

	public String connexionUser() {

		try {
			User user = userMetier.getUserByEmail(this.email);
			if (user == null) {
				validateMessage = "";
				this.errorMessage = "Email et/ou mot de passe incorrect(s) !";
				return "";
			}
			if (user.getPassword().equals(this.password)) {
				validateMessage = "connexion réussie";
				this.errorMessage = "";
				return "achats.xhtml";
			} else {
				validateMessage = "";
				this.errorMessage = "Email et/ou mot de passe incorrect(s) !";
				return "";
			}
		} catch (Exception e) {
			validateMessage = "";
			this.errorMessage = "Erreur technique lors de la connexion ! \n" + e.getMessage();
		}
		return "/pages/achats.xhtml";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getValidateMessage() {
		return validateMessage;
	}

	public void setValidateMessage(String validateMessage) {
		this.validateMessage = validateMessage;
	}

	public List<User> getUserList() {
		return userList;
	}

}