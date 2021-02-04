package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.toedter.calendar.JCalendar;

public class Calendar extends JFrame {
	private JCalendar calendar;
	
	
	public Calendar() {
			createComponents();
			setVisible(true);
		
	}

	private void createComponents() {
		// TODO Auto-generated method stub
		
		setSize(1000,800);
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
		calendar.setBounds(10, 59, 964, 691);
		Date najranijiDatum = parseDate("2021-02-01");
		Date najkasnijiDatum = parseDate("2021-02-28");
		
		calendar.setSelectableDateRange(najranijiDatum, najkasnijiDatum);
		mainPanel.add(calendar);
		
		accesCalendar();
	
		
	}

	private void accesCalendar() {
		// TODO Auto-generated method stub
		
		int dayToBeSelected = 20;
		
		Component compo[] = calendar.getDayChooser().getDayPanel().getComponents();
		for(Component comp : compo) {
			
			if (!(comp instanceof JButton))
            continue;
			
			JButton btn = (JButton) comp;
            if (btn.getText().equals(String.valueOf(dayToBeSelected)))
                comp.setBackground(Color.red);
			
			
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
