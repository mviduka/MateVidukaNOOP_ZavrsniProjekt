package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.DatabaseHandler;

import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class removeBoat extends JFrame {

	private JComboBox brodoviComboBox;
	private JButton btnOdustani;
	private JButton btnUkloni;

	ImageIcon imageRemove = new ImageIcon("icons/remove.png");
	ImageIcon imageCancel = new ImageIcon("icons/cancel.png");

	DatabaseHandler databaseHandler = DatabaseHandler.getInstance();

	public removeBoat() {

		createElements();
		activateElements();
		setResizable(false);
		setVisible(true);
		getData();

	}

	private void createElements() {
		// TODO Auto-generated method stub

		setTitle("Brisanje broda iz baze podataka");
		setSize(600, 500);
		getContentPane().setLayout(null);

		brodoviComboBox = new JComboBox();
		brodoviComboBox.setFont(new Font("Tahoma", Font.PLAIN, 22));
		brodoviComboBox.setBounds(10, 88, 564, 61);
		getContentPane().add(brodoviComboBox);

		btnUkloni = new JButton("Ukloni", imageRemove);
		btnUkloni.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUkloni.setBounds(10, 185, 564, 55);
		getContentPane().add(btnUkloni);

		btnOdustani = new JButton("Odustani", imageCancel);
		btnOdustani.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnOdustani.setBounds(10, 395, 564, 55);
		getContentPane().add(btnOdustani);

	}

	private void getData() {

		PreparedStatement pst;
		try {
			String query = "SELECT * FROM brodovi";
			pst = databaseHandler.conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				brodoviComboBox.addItem(rs.getString("Ime"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void activateElements() {

		btnUkloni.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				try {
					String query = "DELETE FROM brodovi WHERE Ime=?";
					PreparedStatement ps = databaseHandler.conn.prepareStatement(query);
					ps.setString(1, brodoviComboBox.getSelectedItem().toString());
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Uspješno ste obrisali brod iz baze");

					dispose();
					new removeBoat().setVisible(true);
					

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		btnOdustani.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				java.awt.EventQueue.invokeLater(new Runnable() {
					public void run() {

						new MainFrame().setVisible(true);
						dispose();

					}
				});

			}
		});

	}
}
