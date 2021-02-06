package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import controller.DatabaseHandler;
import net.proteanit.sql.DbUtils;

/**
 * Klasa Ponuda vidljiva je samo korisnicima, kada se kreira korisnik ima mogucnost viditi sve dostupne brodove
 * i njihove fotografije. Odabire brod pritiskom na tipku odaberi, upisuje svoje ime i prezime. Podatak koji
 * mu je username aplikacija sama odraduje. Jo� nedostaje unjeti datumPocetka i datumPovratka. kada je sve uneseno
 * pritiskom na gumb rezerviraj podatci o rezervaciji spremaju se u bazu u tablicu rezervacija a brod koji je rezerviran,
 * njegova dostupnost se updatea u tablici brodovi u false.
 */
public class Ponuda extends JFrame {

	private JTextField textFieldImeIprezime;
	private JTextField textFieldUsername;
	private JDateChooser dateChooserPolazak;
	private JScrollPane scrollPane;
	private JTable list;

	DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
	private JLabel lblZaSLikuBroda;
	private JButton btnOdaberi;

	private ImageIcon imageSlikaBroda;
	private JButton btnIzadi;
	private JButton btnRezerviraj;
	private JDateChooser dateChooserPovratka;

	ImageIcon imageSelect = new ImageIcon("icons/select.png");
	ImageIcon imageExit = new ImageIcon("icons/exit2.png");
	ImageIcon imageReserve = new ImageIcon("icons/reserve.png");

	private ResultSet rs;
	


	public Ponuda() {

		createElements();
		populateList();
		activateElements();

		setVisible(true);
	}
	
	/**
	 * Metoda koja kreira sve komponente GUI-a
	 */

	private void createElements() {
		// TODO Auto-generated method stub

		setSize(1300, 529);
		getContentPane().setLayout(null);
		setResizable(false);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1284, 495);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblImeIPrezime = new JLabel("Ime i Prezime");
		lblImeIPrezime.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblImeIPrezime.setBounds(10, 112, 153, 32);
		panel.add(lblImeIPrezime);

		textFieldImeIprezime = new JTextField();
		textFieldImeIprezime.setBounds(173, 115, 211, 32);
		panel.add(textFieldImeIprezime);
		textFieldImeIprezime.setColumns(10);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUsername.setBounds(10, 167, 153, 32);
		panel.add(lblUsername);

		textFieldUsername = new JTextField();
		textFieldUsername.setColumns(10);
		textFieldUsername.setBounds(173, 176, 211, 32);
		textFieldUsername.setEditable(false);
		panel.add(textFieldUsername);
		textFieldUsername.setText(UserMainFrame.currentUser.getUsername());

		JLabel lblDatumPolaska = new JLabel("Datum polaska");
		lblDatumPolaska.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDatumPolaska.setBounds(10, 234, 153, 32);
		panel.add(lblDatumPolaska);

		
		Date najranijiDatum = parseDate("2021-02-01");
		Date najkasnijiDatum = parseDate("2021-02-28");
		
		dateChooserPolazak = new JDateChooser();
		dateChooserPolazak.setBounds(173, 234, 211, 32);
		dateChooserPolazak.setDateFormatString("dd/MM/yyyy");
		dateChooserPolazak.setSelectableDateRange(najranijiDatum, najkasnijiDatum);
		panel.add(dateChooserPolazak);

		JLabel lblDatumPovratka = new JLabel("Datum povratka");
		lblDatumPovratka.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDatumPovratka.setBounds(10, 296, 153, 32);
		panel.add(lblDatumPovratka);

		dateChooserPovratka = new JDateChooser();
		dateChooserPovratka.setBounds(173, 296, 211, 32);
		dateChooserPovratka.setDateFormatString("dd/MM/yyyy");
		dateChooserPovratka.setSelectableDateRange(najranijiDatum, najkasnijiDatum);
		panel.add(dateChooserPovratka);

		btnRezerviraj = new JButton("Rezerviraj", imageReserve);
		btnRezerviraj.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnRezerviraj.setBounds(10, 361, 374, 58);
		panel.add(btnRezerviraj);

		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(410, 42, 864, 205);
		panel.add(scrollPane);

		list = new JTable();
		scrollPane.setViewportView(list);

		lblZaSLikuBroda = new JLabel("");
		lblZaSLikuBroda.setBounds(423, 269, 436, 215);
		panel.add(lblZaSLikuBroda);

		btnOdaberi = new JButton("Odaberi", imageSelect);
		btnOdaberi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnOdaberi.setBounds(881, 258, 279, 59);
		panel.add(btnOdaberi);

		btnIzadi = new JButton("Izadi", imageExit);
		btnIzadi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnIzadi.setBounds(881, 375, 279, 59);
		panel.add(btnIzadi);

	}

	
	/**
	 * Metoda kojom se iz baze podataka iz tablice brodovi puni
	 * JTable s podatcima o svim dostupnim brodovima.
	 */
	private void populateList() {

		list.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Naziv", "Godiste", "Duzina", "Vrsta", "Kapacitet", "Cijena", "Id" }));

		try {

			String query = "select * from brodovi where dostupnost=1";
			PreparedStatement pst = databaseHandler.conn.prepareStatement(query);
			rs = pst.executeQuery();
			list.setModel(DbUtils.resultSetToTableModel(rs));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Metoda kojom dodajemo ActionListenere na JButtone.
	 * 
	 * bntOdaberi -> slika odabranog broda se u JTableu nalazi na poziciji 6, iscitava se
	 * i kreira se novi ImageIcon koji se postavlja na lblZaSlikuBroda.
	 * 
	 * btnIzadi -> gasi trentuni prozor i otvara UserMainFrame.
	 * 
	 * btnRezerviraj -> u bazu podataka upisuje koji korisnik je rezervirao
	 * koji brod.
	 * 
	 */
	private void activateElements() {

		btnOdaberi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				String value = list.getModel().getValueAt(list.getSelectedRow(), 6).toString();
				imageSlikaBroda = new ImageIcon(value);
				lblZaSLikuBroda.setIcon(imageSlikaBroda);

			}
		});

		btnIzadi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				java.awt.EventQueue.invokeLater(new Runnable() {
					public void run() {

						new UserMainFrame().setVisible(true);
						dispose();

					}
				});

			}
		});

		btnRezerviraj.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				int usersId = UserMainFrame.currentUser.getId();
				int brodId = Integer.valueOf(list.getModel().getValueAt(list.getSelectedRow(), 7).toString());
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date datumPolaska = dateChooserPolazak.getDate();
				sdf.format(datumPolaska);
				Date datumPovratka = dateChooserPovratka.getDate();
				sdf.format(datumPolaska);
				
				System.out.println(usersId + " " + brodId);

				int issueButton = JOptionPane.YES_NO_OPTION;
				int issueResult = JOptionPane.showConfirmDialog(null,
						UserMainFrame.currentUser.getUsername() + ", Jeste li sigurni da �elite rezervirati brod "
								+ list.getModel().getValueAt(list.getSelectedRow(), 0).toString(),
						"Confirmation", issueButton);
				if (issueResult == JOptionPane.YES_OPTION) {

					String str = "INSERT INTO rezervacije(usersId,brodoviId,datumPolaska,datumPovratka) VALUES (" + "'"
							+ usersId + "'," + "'" + brodId + "'," + "'" + datumPolaska + "'," + "'"
							+ datumPovratka + "'" + ")";

					String str2 = "UPDATE brodovi SET dostupnost = "+ 0 +" WHERE Id = " + brodId + "";

					System.out.println(str + "\n" + str2);

					if (databaseHandler.execAction(str) && databaseHandler.execAction(str2)) {

						JOptionPane.showMessageDialog(null, "Brod uspje�no rezerviran");
					} else {
						JOptionPane.showMessageDialog(null, "Pogre�ka pri rezervaciji broda", "Pogre�ka",
								JOptionPane.ERROR_MESSAGE);

					}

				}

			}
		});

	}
	
	/**
	 * Metoda kojom kreiramo Date jednostavnog yyyy-MM-dd formata
	 * @param date
	 * 
	 */
	
	 public static Date parseDate(String date) {
	     try {
	         return new SimpleDateFormat("yyyy-MM-dd").parse(date);
	     } catch (ParseException e) {
	         return null;
	     }
	  }


}
