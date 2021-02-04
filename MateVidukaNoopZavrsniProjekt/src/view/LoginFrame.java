package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import controller.DatabaseHandler;
import model.User;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JPasswordField;

public class LoginFrame extends JFrame {

	private JTextField txtFIeldUsername;
	private JButton btnPrijaviSe;
	private JButton btnRegistrirajSe;
	private JPasswordField passwordField;

	ImageIcon imageLogin = new ImageIcon("icons/login.png");
	ImageIcon imageRegister = new ImageIcon("icons/register.png");

	DatabaseHandler handler = DatabaseHandler.getInstance();

	public LoginFrame() {

		createComponents();
		setVisible(true);
		activateElements();
	}

	private void createComponents() {

		setSize(700, 400);
		setTitle("Charter Login");
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 684, 461);
		getContentPane().add(panel);
		panel.setLayout(null);

		txtFIeldUsername = new JTextField();
		txtFIeldUsername.setHorizontalAlignment(SwingConstants.CENTER);
		txtFIeldUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtFIeldUsername.setBounds(10, 59, 664, 45);
		panel.add(txtFIeldUsername);
		txtFIeldUsername.setColumns(10);

		JLabel lblNewLabel = new JLabel("USERNAME");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 11, 664, 49);
		panel.add(lblNewLabel);

		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(10, 115, 664, 49);
		panel.add(lblPassword);

		btnPrijaviSe = new JButton("PRIJAVI SE", imageLogin);
		btnPrijaviSe.setBackground(Color.LIGHT_GRAY);
		btnPrijaviSe.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnPrijaviSe.setBounds(10, 219, 284, 58);
		panel.add(btnPrijaviSe);

		btnRegistrirajSe = new JButton("REGISTRIRAJ SE", imageRegister);
		btnRegistrirajSe.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRegistrirajSe.setBackground(Color.LIGHT_GRAY);
		btnRegistrirajSe.setBounds(390, 219, 284, 58);
		panel.add(btnRegistrirajSe);

		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordField.setBounds(10, 163, 664, 45);
		panel.add(passwordField);

	}

	private void activateElements() {

		btnPrijaviSe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				String username = txtFIeldUsername.getText();
				String password = passwordField.getText();

				if (username.equals("admin")) {

					String qu = "SELECT * FROM users WHERE USERNAME=? and PASSWORD=? and TYPE=?";
					PreparedStatement pst;
					try {

						pst = handler.conn.prepareStatement(qu);
						pst.setString(1, username);
						pst.setString(2, password);
						pst.setString(3, "admin");

						ResultSet rs = pst.executeQuery();

						if (rs.next()) {
							System.out.println(rs.getString("USERNAME"));
							System.out.println(rs.getString("PASSWORD"));

							User user = new User(rs.getString("USERNAME"), rs.getString("PASSWORD"), rs.getInt("Id"));
							MainFrame.currentUser = user;

							java.awt.EventQueue.invokeLater(new Runnable() {
								public void run() {

									new MainFrame().setVisible(true);
									dispose();

								}
							});
						} else {

							JOptionPane.showMessageDialog(null, "Unjeli ste pogrešni username ili password", "Error",
									JOptionPane.ERROR_MESSAGE);
						}

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {

					String qu = "SELECT * FROM users WHERE USERNAME=? and PASSWORD=? and TYPE=?";
					PreparedStatement pst;
					try {

						pst = handler.conn.prepareStatement(qu);
						pst.setString(1, username);
						pst.setString(2, password);
						pst.setString(3, "user");

						ResultSet rs = pst.executeQuery();

						if (rs.next()) {
							System.out.println(rs.getString("USERNAME"));
							System.out.println(rs.getString("PASSWORD"));

							User user = new User(rs.getString("USERNAME"), rs.getString("PASSWORD"), rs.getInt("Id"));
							UserMainFrame.currentUser = user;

							java.awt.EventQueue.invokeLater(new Runnable() {
								public void run() {

									new UserMainFrame().setVisible(true);
									dispose();

								}
							});
						} else {

							JOptionPane.showMessageDialog(null, "Unjeli ste pogrešni username ili password", "Error",
									JOptionPane.ERROR_MESSAGE);
						}

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});

		btnRegistrirajSe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				String username = txtFIeldUsername.getText();
				String password = passwordField.getText();

				if (username.isEmpty() || password.isEmpty()) {

					JOptionPane.showMessageDialog(null, "Please fill in all fields", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;

				}

				String st = "INSERT INTO users VALUES (" + "'" + username + "'," + "'" + password + "'," + "'" + "user"
						+ "'," + "" + null + "" + ")";

				System.out.println(st);

				if (handler.execAction(st)) {

					JOptionPane.showMessageDialog(null, "Succes");

				} else {
					JOptionPane.showMessageDialog(null, "Failed", "Error", JOptionPane.ERROR_MESSAGE);

				}

			}
		});

	}
}
