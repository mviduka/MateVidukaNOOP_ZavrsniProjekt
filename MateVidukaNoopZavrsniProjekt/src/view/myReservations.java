package view;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.activation.ActivateFailedException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
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

public class myReservations extends JFrame {

	private JCalendar calendar;
	ImageIcon imageExit2 = new ImageIcon("icons/exit2.png");
	List<JButton> list = new ArrayList<>();
	private JButton btnIzadi;
	private DatabaseHandler databaseHandler = DatabaseHandler.getInstance();

	public myReservations() {

		createComponents();
		activateElements();
		showReserved();
		setVisible(true);

	}

	private void createComponents() {
		// TODO Auto-generated method stub

		setSize(1000, 800);
		getContentPane().setLayout(null);

		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 984, 761);
		getContentPane().add(mainPanel);
		mainPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("KALENDAR");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 11, 964, 37);
		mainPanel.add(lblNewLabel);

		calendar = new JCalendar();
		calendar.setBounds(10, 59, 964, 625);
		mainPanel.add(calendar);

		Date najranijiDatum = parseDate("2021-02-01");
		Date najkasnijiDatum = parseDate("2021-02-28");

		calendar.setSelectableDateRange(najranijiDatum, najkasnijiDatum);

		btnIzadi = new JButton("Iza\u0111i", imageExit2);
		btnIzadi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnIzadi.setBounds(553, 695, 421, 55);
		mainPanel.add(btnIzadi);

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

	private void activateElements() {

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
	}

	private void showReserved() {

		try {

			String query = "select * from rezervacije where usersId=?";
			PreparedStatement pst = databaseHandler.conn.prepareStatement(query);
			pst.setInt(1, UserMainFrame.currentUser.getId());

			ResultSet rs = pst.executeQuery();

			String datumPolaska = null;
			String datumPovratka = null;
			int brodID = 0;
			if (rs.next()) {
				brodID = rs.getInt("brodoviId");
				datumPolaska = rs.getString("datumPolaska");
				datumPovratka = rs.getString("datumPovratka");
				System.out.println(brodID + " " + datumPolaska + " " + datumPovratka);

			}

			String[] datumP = datumPolaska.split(" ");
			String[] datumPovr = datumPovratka.split(" ");
			int danPocetka = Integer.valueOf(datumP[2]);
			int danPovratka = Integer.valueOf(datumPovr[2]);
			
			
			String query1 = "select * from brodovi where Id=?";
			PreparedStatement pst1 = databaseHandler.conn.prepareStatement(query1);
			pst1.setInt(1, brodID);
			ResultSet rs1 = pst1.executeQuery();
			String putanja = null;
			ImageIcon imageSlikaBroda = null;
			
			if(rs1.next()) {
				putanja = rs1.getString("Slika");
				imageSlikaBroda = new ImageIcon(putanja);
			}

			for (Iterator<JButton> iterator = list.iterator(); iterator.hasNext();) {
				JButton value = iterator.next();
				int dan = Integer.valueOf(value.getText());
				if (dan >= danPocetka && dan <= danPovratka) {
						value.setIcon(imageSlikaBroda);
						value.setEnabled(false);
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
