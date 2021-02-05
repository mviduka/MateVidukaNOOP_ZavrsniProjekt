package model;

public class Rezervacija {
	
	public int usersId;
	public int brodoviId;
	public String datumPolaska;
	public String datumPovratka;
	
	public Rezervacija(int usersId, int brodoviId, String datumPolaska, String datumPovratka) {
		this.usersId = usersId;
		this.brodoviId = brodoviId;
		this.datumPolaska = datumPolaska;
		this.datumPovratka = datumPovratka;
		
		
	}

	public int getUsersId() {
		return usersId;
	}

	public void setUsersId(int usersId) {
		this.usersId = usersId;
	}

	public int getBrodoviId() {
		return brodoviId;
	}

	public void setBrodoviId(int brodoviId) {
		this.brodoviId = brodoviId;
	}

	public String getDatumPolaska() {
		return datumPolaska;
	}

	public void setDatumPolaska(String datumPolaska) {
		this.datumPolaska = datumPolaska;
	}

	public String getDatumPovratka() {
		return datumPovratka;
	}

	public void setDatumPovratka(String datumPovratka) {
		this.datumPovratka = datumPovratka;
	}
	
	

}
