package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.Brod;
import model.User;

/**
 * MainFrame glavni je prozor adminskog dijela programa. preko njega mozemo uci u addBoat, removeBoat,Calendar i mozemo izaci
 * iz aplikacije, u addBoat dodajemo nove brodove u bazu podataka, removeBoat brisemo odabrani brod iz baze podataka i 
 * preko Calendara gledamo rezervacije za trazenog korisnika.
 */

public class MainFrame extends JFrame {
	private JLabel lblDobrodosli;
	private JMenu mnFile;
	private JMenuBar menuBar;
	private JButton btnDodajBrod;
	private JPanel mainPanel;

	ImageIcon imageBrod = new ImageIcon("icons/boat.png");
	ImageIcon imageBrodRemove = new ImageIcon("icons/removeBoat.png");
	ImageIcon imageCalendar = new ImageIcon("icons/calendar.png");
	ImageIcon imageExit = new ImageIcon("icons/exit.png");

	public static User currentUser;

	public static ArrayList<Brod> listaBrodova = new ArrayList<>();

	private JButton btnUkloniBrod;
	private JButton btnPregledajKalendar;
	private JButton btnIzlaz;
	

	public MainFrame() {

		createComponents();
		getCurrentUser();
		activateElements();
		setVisible(true);

	}
	
	/**
	 * Kreiramo komponente GUI-a
	 */

	private void createComponents() {

		setSize(750, 630);
		setTitle("Charter Booking Admin");
		getContentPane().setLayout(null);

		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 734, 29);
		getContentPane().add(menuBar);

		mnFile = new JMenu("File");
		menuBar.add(mnFile);

		mainPanel = new JPanel();
		mainPanel.setBounds(0, 28, 734, 563);
		getContentPane().add(mainPanel);
		mainPanel.setLayout(null);

		lblDobrodosli = new JLabel("New label");
		lblDobrodosli.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDobrodosli.setHorizontalAlignment(SwingConstants.CENTER);
		lblDobrodosli.setBounds(10, 11, 714, 71);
		mainPanel.add(lblDobrodosli);

		btnDodajBrod = new JButton("DODAJ BROD", imageBrod);
		btnDodajBrod.setBackground(Color.GRAY);
		btnDodajBrod.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDodajBrod.setBounds(227, 93, 278, 100);
		mainPanel.add(btnDodajBrod);

		btnUkloniBrod = new JButton("UKLONI BROD", imageBrodRemove);
		btnUkloniBrod.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnUkloniBrod.setBackground(Color.GRAY);
		btnUkloniBrod.setBounds(227, 204, 278, 100);
		mainPanel.add(btnUkloniBrod);

		btnPregledajKalendar = new JButton("KALENDAR", imageCalendar);
		btnPregledajKalendar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnPregledajKalendar.setBackground(Color.GRAY);
		btnPregledajKalendar.setBounds(227, 315, 278, 100);
		mainPanel.add(btnPregledajKalendar);

		btnIzlaz = new JButton("IZLAZ", imageExit);
		btnIzlaz.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnIzlaz.setBackground(Color.GRAY);
		btnIzlaz.setBounds(227, 426, 278, 100);
		mainPanel.add(btnIzlaz);

	}
	
	/**
	 * Metoda kojom na lblDobrodosli postavljamo tekst "Dobrodsli " + username trenutnog korisnika, koji ce 
	 * u ovom slucaju uvijek biti admin.
	 */

	private void getCurrentUser() {

		lblDobrodosli.setText("Dobrodošli " + currentUser.getUsername());

	}
	
	/**
	 * Postavljamo actionListenere na JBottune.
	 * 
	 * bntDodajBrod -> Pokrece JFrame addBoat i radi dispose() na trenutnom prozoru.
	 * 
	 * btnUkloniBroad -> Pokrece JFrame removeBoat i radi dispose() na trenutno prozoru.
	 * 
	 * btnIzlaz -> gasi aplikaciju i ispisuje da ju je admin ugasio.
	 */

	private void activateElements() {

		btnDodajBrod.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				java.awt.EventQueue.invokeLater(new Runnable() {
					public void run() {

						new addBoat().setVisible(true);
						dispose();

					}
				});

			}
		});
		btnIzlaz.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				System.exit(0);
				System.out.println("Shutdown by admin");
				
			}
		});

		btnUkloniBrod.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				java.awt.EventQueue.invokeLater(new Runnable() {
					public void run() {

						new removeBoat().setVisible(true);
						dispose();

					}
				});
			}
		});
		
		btnPregledajKalendar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				
				java.awt.EventQueue.invokeLater(new Runnable() {
					public void run() {

						new Calendar().setVisible(true);
						dispose();

					}
				});
			}
		});

	}
}
