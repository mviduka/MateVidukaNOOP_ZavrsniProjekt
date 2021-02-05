package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.User;

import java.awt.Color;

public class UserMainFrame extends JFrame {

	private JLabel lblDobrodosli;

	ImageIcon imageSearch = new ImageIcon("icons/search.png");
	ImageIcon imageExit = new ImageIcon("icons/exit.png");
	ImageIcon imageReservation = new ImageIcon("icons/desk-bell.png");

	private JButton btnPregledajPonudu;
	private JButton btnMojeRezervacije;
	private JButton btnIzlaz;

	public static User currentUser;

	public UserMainFrame() {

		createComponents();
		activateElements();
		setVisible(true);

	}

	private void createComponents() {

		setTitle("Charter");
		setSize(800, 700);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 784, 661);
		getContentPane().add(panel);
		panel.setLayout(null);

		lblDobrodosli = new JLabel("Dobrodosli");
		lblDobrodosli.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblDobrodosli.setHorizontalAlignment(SwingConstants.CENTER);
		lblDobrodosli.setBounds(10, 11, 764, 74);
		panel.add(lblDobrodosli);

		btnPregledajPonudu = new JButton("Pregledaj ponudu", imageSearch);
		btnPregledajPonudu.setBackground(Color.LIGHT_GRAY);
		btnPregledajPonudu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnPregledajPonudu.setBounds(225, 159, 339, 81);
		panel.add(btnPregledajPonudu);

		btnMojeRezervacije = new JButton("Moje rezervacije", imageReservation);
		btnMojeRezervacije.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnMojeRezervacije.setBackground(Color.LIGHT_GRAY);
		btnMojeRezervacije.setBounds(225, 283, 339, 81);
		panel.add(btnMojeRezervacije);

		btnIzlaz = new JButton("Izlaz", imageExit);
		btnIzlaz.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnIzlaz.setBackground(Color.LIGHT_GRAY);
		btnIzlaz.setBounds(225, 411, 339, 81);
		panel.add(btnIzlaz);

	}

	private void activateElements() {

		btnPregledajPonudu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				java.awt.EventQueue.invokeLater(new Runnable() {
					public void run() {

						new Ponuda().setVisible(true);
						dispose();

					}
				});

			}
		});
		
		btnMojeRezervacije.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				java.awt.EventQueue.invokeLater(new Runnable() {
					public void run() {

						new myReservations().setVisible(true);
						dispose();

					}
				});
				
			}
		});
		btnIzlaz.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				System.exit(1);
				System.out.println("Shutdown by user");
				
			}
		});
	}
}
