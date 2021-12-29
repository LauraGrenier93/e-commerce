package fr.doranco.ecommerce.enums;

public enum Genre {

	MONSIEUR ("M."),
	MADAME ("Mme"),
	MADEMOISELLE ("Mlle");
	
	private String genre;
	
	Genre(String genre) {
		this.genre = genre;
	}
	
	public String getGenre() {
		return this.genre;
	}
}
