package model;

/**
 * Klasa User sluzi nam da bi preko logina izveli provjeru s bazom podataka. Ukoliko korisnik postoji u bazi
 * kreiramo novog Usera i preko njegovih polja na frameovima postavljamo username, odnosno id.
 * @author Mate
 *
 */

public class User {

	String username;
	String password;
	int id;

	public User(String username, String password, int id) {

		this.username = username;
		this.password = password;
		this.id = id;

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

}
