package view;

import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.DatabaseHandler;

public class addBoat extends JFrame {
	private JTextField txtFieldImeBroda;
	private JTextField textFieldGodisteBroda;
	private JLabel lblGodisteBroda;
	private JTextField textFieldDuzinaBroda;
	private JComboBox vrstaBrodaComboBox;
	private JTextField txtFieldCijenaPoDanu;
	private JLabel lblMaksimalniBrojOsoba;
	private JTextField textFieldMaksimalniBrojOsoba;
	private JLabel lblSlikaBroda;
	private JButton btnOdaberiSliku;
	private JButton btnSpremi;
	private JButton btnOdustani;
	
	ImageIcon imageSave = new ImageIcon("icons/save.png");
	ImageIcon imageCancel = new ImageIcon("icons/cancel.png");

	DatabaseHandler handler = DatabaseHandler.getInstance();

	String file = null;

	public addBoat() {

		createComponents();
		activateElements();

	}

	private void createComponents() {
		// TODO Auto-generated method stub

		setSize(650, 900);
		setTitle("Dodavanje Broda u bazu podataka");
		getContentPane().setLayout(null);

		JLabel lblImeBroda = new JLabel("Ime Broda");
		lblImeBroda.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblImeBroda.setHorizontalAlignment(SwingConstants.CENTER);
		lblImeBroda.setBounds(10, 11, 614, 38);
		getContentPane().add(lblImeBroda);

		txtFieldImeBroda = new JTextField();
		txtFieldImeBroda.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtFieldImeBroda.setHorizontalAlignment(SwingConstants.CENTER);
		txtFieldImeBroda.setBounds(10, 60, 614, 38);
		getContentPane().add(txtFieldImeBroda);
		txtFieldImeBroda.setColumns(10);

		lblGodisteBroda = new JLabel("Godiste Broda");
		lblGodisteBroda.setHorizontalAlignment(SwingConstants.CENTER);
		lblGodisteBroda.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblGodisteBroda.setBounds(10, 105, 614, 38);
		getContentPane().add(lblGodisteBroda);

		textFieldGodisteBroda = new JTextField();
		textFieldGodisteBroda.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldGodisteBroda.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldGodisteBroda.setColumns(10);
		textFieldGodisteBroda.setBounds(10, 154, 614, 38);
		getContentPane().add(textFieldGodisteBroda);

		JLabel lblDuzinaBroda = new JLabel("Duzina Broda");
		lblDuzinaBroda.setHorizontalAlignment(SwingConstants.CENTER);
		lblDuzinaBroda.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDuzinaBroda.setBounds(10, 203, 614, 38);
		getContentPane().add(lblDuzinaBroda);

		textFieldDuzinaBroda = new JTextField();
		textFieldDuzinaBroda.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldDuzinaBroda.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldDuzinaBroda.setColumns(10);
		textFieldDuzinaBroda.setBounds(10, 252, 614, 38);
		getContentPane().add(textFieldDuzinaBroda);

		JLabel lblVrstaBroda = new JLabel("Vrsta Broda");
		lblVrstaBroda.setHorizontalAlignment(SwingConstants.CENTER);
		lblVrstaBroda.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblVrstaBroda.setBounds(10, 302, 614, 38);
		getContentPane().add(lblVrstaBroda);

		vrstaBrodaComboBox = new JComboBox();
		vrstaBrodaComboBox.setModel(new DefaultComboBoxModel(new String[] { "Motorni Brod", "Gumenjak", "Jedrilica" }));
		vrstaBrodaComboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		vrstaBrodaComboBox.setBounds(10, 351, 614, 38);
		getContentPane().add(vrstaBrodaComboBox);

		JLabel lblCijenaPoDanu = new JLabel("Cijena dnevnog najma");
		lblCijenaPoDanu.setHorizontalAlignment(SwingConstants.CENTER);
		lblCijenaPoDanu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCijenaPoDanu.setBounds(10, 498, 614, 38);
		getContentPane().add(lblCijenaPoDanu);

		txtFieldCijenaPoDanu = new JTextField();
		txtFieldCijenaPoDanu.setHorizontalAlignment(SwingConstants.CENTER);
		txtFieldCijenaPoDanu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtFieldCijenaPoDanu.setColumns(10);
		txtFieldCijenaPoDanu.setBounds(10, 547, 614, 38);
		getContentPane().add(txtFieldCijenaPoDanu);

		lblMaksimalniBrojOsoba = new JLabel("Maksimalni broj osoba");
		lblMaksimalniBrojOsoba.setHorizontalAlignment(SwingConstants.CENTER);
		lblMaksimalniBrojOsoba.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMaksimalniBrojOsoba.setBounds(10, 400, 614, 38);
		getContentPane().add(lblMaksimalniBrojOsoba);

		textFieldMaksimalniBrojOsoba = new JTextField();
		textFieldMaksimalniBrojOsoba.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldMaksimalniBrojOsoba.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldMaksimalniBrojOsoba.setColumns(10);
		textFieldMaksimalniBrojOsoba.setBounds(10, 449, 614, 38);
		getContentPane().add(textFieldMaksimalniBrojOsoba);

		lblSlikaBroda = new JLabel("Slika broda");
		lblSlikaBroda.setHorizontalAlignment(SwingConstants.CENTER);
		lblSlikaBroda.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSlikaBroda.setBounds(10, 596, 614, 38);
		getContentPane().add(lblSlikaBroda);

		btnOdaberiSliku = new JButton("Odaberi sliku");
		btnOdaberiSliku.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnOdaberiSliku.setBounds(10, 645, 614, 38);
		getContentPane().add(btnOdaberiSliku);

		btnSpremi = new JButton("Spremi", imageSave);
		btnSpremi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSpremi.setBounds(10, 753, 265, 71);
		getContentPane().add(btnSpremi);

		btnOdustani = new JButton("Odustani", imageCancel);
		btnOdustani.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnOdustani.setBounds(359, 753, 265, 71);
		getContentPane().add(btnOdustani);

	}

	private void activateElements() {

		btnOdaberiSliku.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				FileDialog dialog = new FileDialog((Frame) null, "Select File to Open");
				dialog.setMode(FileDialog.LOAD);
				dialog.setVisible(true);
				file = dialog.getFile();
				System.out.println(file + " chosen.");

			}
		});

		btnSpremi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				// TODO Auto-generated method stub
				
				handler = DatabaseHandler.getInstance();

				String slikaBroda = "slikeBrodova/" + file;
				String imeBroda = txtFieldImeBroda.getText();
				String godisteBroda = textFieldGodisteBroda.getText();
				String duzinaBroda = textFieldDuzinaBroda.getText();
				String vrstaBroda = vrstaBrodaComboBox.getSelectedItem().toString();
				String kapacitetBroda = textFieldMaksimalniBrojOsoba.getText();
				String cijenaPoDanu = txtFieldCijenaPoDanu.getText();

				if (slikaBroda.isEmpty() || imeBroda.isEmpty() || godisteBroda.isEmpty() || duzinaBroda.isEmpty()
						|| vrstaBroda.isEmpty() || kapacitetBroda.isBlank() || cijenaPoDanu.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please fill in all fields", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				String qu = "INSERT INTO brodovi VALUES (" + "'" + imeBroda + "'," + "'" + godisteBroda + "'," + "'"
						+ duzinaBroda + "'," + "'" + vrstaBroda + "'," + "'" + kapacitetBroda + "'," + "'"
						+ cijenaPoDanu + "'," + "'" + slikaBroda + "'," + "" + null + "," + "" + true + ""  + ")";

				if (handler.execAction(qu)) {

					JOptionPane.showMessageDialog(null, "Succes");

				} else {
					JOptionPane.showMessageDialog(null, "Failed", "Error", JOptionPane.ERROR_MESSAGE);

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
