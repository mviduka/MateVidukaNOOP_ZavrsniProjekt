package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTextField;
import javax.swing.ListModel;

import com.toedter.calendar.JDateChooser;

import controller.DatabaseHandler;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

public class Ponuda extends JFrame {

	private JTextField textFieldImeIprezime;
	private JTextField textFieldUsername;
	private JDateChooser dateChooserPolazak;
	private JScrollPane scrollPane;
	private JTable list;

	DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
	private JLabel lblZaSLikuBroda;

	public Ponuda() {

		createElements();
		populateList();

		setVisible(true);
	}

	private void createElements() {
		// TODO Auto-generated method stub

		setSize(1020, 529);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1008, 495);
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
		panel.add(textFieldUsername);

		JLabel lblDatumPolaska = new JLabel("Datum polaska");
		lblDatumPolaska.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDatumPolaska.setBounds(10, 234, 153, 32);
		panel.add(lblDatumPolaska);

		dateChooserPolazak = new JDateChooser();
		dateChooserPolazak.setBounds(173, 234, 211, 32);
		panel.add(dateChooserPolazak);

		JLabel lblDatumPovratka = new JLabel("Datum povratka");
		lblDatumPovratka.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDatumPovratka.setBounds(10, 296, 153, 32);
		panel.add(lblDatumPovratka);

		JDateChooser dateChooserPovratak = new JDateChooser();
		dateChooserPovratak.setBounds(173, 296, 211, 32);
		panel.add(dateChooserPovratak);

		JButton btnRezerviraj = new JButton("Rezerviraj");
		btnRezerviraj.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnRezerviraj.setBounds(10, 361, 374, 58);
		panel.add(btnRezerviraj);

		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(410, 42, 588, 205);
		panel.add(scrollPane);

		list = new JTable();
		scrollPane.setViewportView(list);

		lblZaSLikuBroda = new JLabel("");
		lblZaSLikuBroda.setBounds(410, 269, 277, 179);
		panel.add(lblZaSLikuBroda);

	}

	private void populateList() {

		list.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Naziv", "Godiste", "Duzina", "Vrsta", "Kapacitet", "Cijena", "Id" }));

		try {

			String query = "select * from brodovi";
			PreparedStatement pst = databaseHandler.conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			list.setModel(DbUtils.resultSetToTableModel(rs));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void activateElements() {

		System.out.println(list.getSelectedRow());
		int column = 0;
		int row = 0;
		String value = list.getModel().getValueAt(row, column).toString();
		System.out.println(value);

	}

}
