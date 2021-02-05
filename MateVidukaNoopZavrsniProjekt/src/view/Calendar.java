package view;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.toedter.calendar.JCalendar;

import controller.DatabaseHandler;
import model.Rezervacija;
import javax.swing.JComboBox;

public class Calendar extends JFrame {
	private JCalendar calendar;
	private JButton btnIzlaz;
	private JButton showText;
	List<JButton> list = new ArrayList<>();
	ArrayList<Rezervacija> listaRezervacija = new ArrayList<>();

	ImageIcon imageExit2 = new ImageIcon("icons/exit2.png");
	DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
	private JComboBox usersComboBox;
	private JButton btnOdaberi;

	public Calendar() {
		createComponents();
		activateElements();
		setVisible(true);

		getAllReservations();
		showReserved();

	}

	private void activateElements() {
		// TODO Auto-generated method stub
		btnIzlaz.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				java.awt.EventQueue.invokeLater(new Runnable() {
					public void run() {

						new MainFrame().setVisible(true);
						dispose();

					}
				});

			}
		});

		btnOdaberi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				setToDefault();

				try {
					// TODO Auto-generated method stub

					String query = "Select ID from users where USERNAME=?";
					PreparedStatement pst = databaseHandler.conn.prepareStatement(query);
					pst.setString(1, usersComboBox.getSelectedItem().toString());
					ResultSet rs = pst.executeQuery();

					int usersId = 0;
					if (rs.next()) {

						usersId = rs.getInt("ID");
					}

					String query2 = "Select * from rezervacije where usersId=?";
					PreparedStatement pst2 = databaseHandler.conn.prepareStatement(query2);
					pst2.setInt(1, usersId);
					ResultSet rs2 = pst2.executeQuery();
					String datumPolaska = null;
					String datumPovratka = null;
					int brodId = 0;
					if (rs2.next()) {
						brodId = rs2.getInt("brodoviId");
						datumPolaska = rs2.getString("datumPolaska");
						datumPovratka = rs2.getString("datumPovratka");
					}

					String query1 = "Select * from brodovi where Id=?";
					PreparedStatement pst1 = databaseHandler.conn.prepareStatement(query1);
					pst1.setInt(1, brodId);

					ResultSet rs1 = pst1.executeQuery();
					String imeBroda = null;
					if (rs1.next()) {
						imeBroda = rs1.getString("Ime");
					}

					String[] datumP = datumPolaska.split(" ");
					String[] datumPovr = datumPovratka.split(" ");
					int danPocetka = Integer.valueOf(datumP[2]);
					int danPovratka = Integer.valueOf(datumPovr[2]);
					System.out.println(danPocetka + " " + danPovratka);

					for (Iterator<JButton> iterator = list.iterator(); iterator.hasNext();) {
						JButton value = iterator.next();
						int dan = Integer.valueOf(value.getText());
						if (dan >= danPocetka && dan <= danPovratka) {

							value.setText(value.getText() + " \n" + usersComboBox.getSelectedItem().toString()
									+ " je rezervirao brod " + imeBroda);
							value.setEnabled(false);
						}

					}

				} catch (Exception e1) {
					// TODO: handle exception
				}
			}
		});

	}

	private void createComponents() {
		// TODO Auto-generated method stub

		setSize(2000, 1200);
		getContentPane().setLayout(null);

		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 1924, 977);
		getContentPane().add(mainPanel);
		mainPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("KALENDAR");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(369, 11, 964, 37);
		mainPanel.add(lblNewLabel);

		calendar = new JCalendar();
		calendar.setBounds(10, 59, 1913, 918);
		Date najranijiDatum = parseDate("2021-02-01");
		Date najkasnijiDatum = parseDate("2021-02-28");

		calendar.setSelectableDateRange(najranijiDatum, najkasnijiDatum);
		mainPanel.add(calendar);

		btnIzlaz = new JButton("Izlaz", imageExit2);
		btnIzlaz.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnIzlaz.setBounds(1227, 988, 345, 51);
		getContentPane().add(btnIzlaz);

		usersComboBox = new JComboBox();
		usersComboBox.setFont(new Font("Tahoma", Font.BOLD, 18));
		usersComboBox.setBounds(10, 988, 444, 62);
		getContentPane().add(usersComboBox);

		btnOdaberi = new JButton("Odaberi", null);
		btnOdaberi.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnOdaberi.setBounds(489, 988, 345, 62);
		getContentPane().add(btnOdaberi);

		accesCalendar();

	}

	private void accesCalendar() {
		// TODO Auto-generated method stub

		Component compo[] = calendar.getDayChooser().getDayPanel().getComponents();
		for (Component comp : compo) {

			if (!(comp instanceof JButton))
				continue;

			JButton btn = (JButton) comp;
			list.add(btn);

		}

		System.out.println(list.size());
		int cnt = 0;
		for (Iterator<JButton> iterator = list.iterator(); iterator.hasNext();) {
			JButton value = iterator.next();
			if (cnt < 8 || cnt > 35) {
				iterator.remove();
			}
			cnt++;
		}

		System.out.println(list.size());
	}

	private void showReserved() {

		for (Rezervacija res : listaRezervacija) {

			try {

				String query = "Select * from users where Id=?";
				PreparedStatement pst = databaseHandler.conn.prepareStatement(query);
				pst.setInt(1, res.getUsersId());

				ResultSet rs = pst.executeQuery();

				String userName = null;
				if (rs.next()) {
					userName = rs.getString("USERNAME");
				}
				System.out.println(userName);
				usersComboBox.addItem(userName);

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

		}

	}

	private void getAllReservations() {

		try {

			String query = "select * from rezervacije";
			PreparedStatement pst = databaseHandler.conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			int usersId = 0;
			int brodoviId = 0;
			String datumPolaska = null;
			String datumPovratka = null;

			while (rs.next()) {
				usersId = rs.getInt("usersId");
				brodoviId = rs.getInt("brodoviId");
				datumPolaska = rs.getString("datumPolaska");
				datumPovratka = rs.getString("datumPovratka");

				Rezervacija res = new Rezervacija(usersId, brodoviId, datumPolaska, datumPovratka);
				System.out.println(usersId);
				listaRezervacija.add(res);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void setToDefault() {

		int cnt = 1;
		for (Iterator<JButton> iterator = list.iterator(); iterator.hasNext();) {
			JButton value = iterator.next();
			
			value.setEnabled(true);
			value.setText(String.valueOf(cnt));
			cnt++;
		}

	}

	public static Date parseDate(String date) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
}
