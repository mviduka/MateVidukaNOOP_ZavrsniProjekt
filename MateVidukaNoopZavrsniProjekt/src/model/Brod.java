package model;

import javax.swing.ImageIcon;
/**
 * 
 * 
 * Klasa Brod je model za Brod, podatci koji se nalaze u bazi podataka u tablici brod odgovaraju poljima koje
 * ima klasa Brod, tako da kada podatke o brodu izvlacimo iz baze, imamo ih gdje pospremiti. Novi brod dodaje se
 * u listu brodova.
 * @author Mate
 *
 */

public class Brod {

	private String imeBroda;
	private String godisteBroda;
	private String duzinaBroda;
	private int kapacitetBroda;
	private String vrstaBroda;
	private int cijenaPoDanu;
	private int id;
	private ImageIcon slikaBroda;

	public Brod(String imeBroda, String godisteBroda, String duzinaBroda, String vrstaBroda, int kapacitetBroda,
			int cijenaPoDanu, int id, String lokacijaSlike) {

		this.imeBroda = imeBroda;
		this.godisteBroda = godisteBroda;
		this.duzinaBroda = duzinaBroda;
		this.vrstaBroda = vrstaBroda;
		this.cijenaPoDanu = cijenaPoDanu;
		this.kapacitetBroda = kapacitetBroda;
		this.id = id;
		slikaBroda = new ImageIcon(lokacijaSlike);

	}

	public String getImeBroda() {
		return imeBroda;
	}

	public void setImeBroda(String imeBroda) {
		this.imeBroda = imeBroda;
	}

	public String getGodisteBroda() {
		return godisteBroda;
	}

	public void setGodisteBroda(String godisteBroda) {
		this.godisteBroda = godisteBroda;
	}

	public String getDuzinaBroda() {
		return duzinaBroda;
	}

	public void setDuzinaBroda(String duzinaBroda) {
		this.duzinaBroda = duzinaBroda;
	}

	public int getKapacitetBroda() {
		return kapacitetBroda;
	}

	public void setKapacitetBroda(int kapacitetBroda) {
		this.kapacitetBroda = kapacitetBroda;
	}

	public String getVrstaBroda() {
		return vrstaBroda;
	}

	public void setVrstaBroda(String vrstaBroda) {
		this.vrstaBroda = vrstaBroda;
	}

	public int getCijenaPoDanu() {
		return cijenaPoDanu;
	}

	public void setCijenaPoDanu(int cijenaPoDanu) {
		this.cijenaPoDanu = cijenaPoDanu;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ImageIcon getSlikaBroda() {
		return slikaBroda;
	}

	public void setSlikaBroda(ImageIcon slikaBroda) {
		this.slikaBroda = slikaBroda;
	}

}
