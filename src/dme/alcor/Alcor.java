package dme.alcor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Alcor extends JFrame implements ActionListener {

	JMenuBar menu_bar;
	JMenu menu;
	JMenuItem item_op1, item_op2;

	JPanel north, center, south;
	JLabel lb;
	static JComboBox<String> ch_month;
	static JComboBox<String> ch_year;

	JButton bt_show, bt_previous, bt_next, bt_exit, bt_reset, bt_crescent;

	static String months[] = new String[12]; 
	String years[] = new String[100];
	String [] tituloData = { "Date", "Ilumination", "Phase" };
	String [][] data_table;
	static JTable table;
	JScrollPane scrollPane;

	static DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	static Date data = new Date();
	static String now = df.format(data);

	int i=0;
	int k=0;

	static boolean crescent = true;

	String about = "<html>" +
			"       <h3>Alcor</h3><hr>" +
			"       <p>Calculates the illuminated percentage of the Moon<br>" +
			"       for a given date.</p>" +
			"       <p>The Conway algorithm is used for<br>" +
			"       the calculation so precision is not very high.<br>" +
			"       <hr>" +
			"       <p>This was my first App writen in Java and with a<br>" +
			"       GUI. I made it because, well, some people like to know<br>" +
			"       when the Moon is crescent and I also like the Moon.</p>" +
			"       <hr>" +
			"       <p>On Github:<br>" +
			"       <a href=\"https://github.com/dvdme/alcor\">https://github.com/dvdme/alcor</a></p>" +
			"       <p>The code is available under the terms of the<br>" +
			"       <a href=\"http://www.eclipse.org/legal/epl-v10.html\">Eclipse Public License</a><br></p>" +
			"       </html>";

	private static final long serialVersionUID = 1L;

	public Alcor() {

		super("Alcor");

		// System Look and Feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {} 
		catch (InstantiationException e1) {} 
		catch (IllegalAccessException e1) {} 
		catch (UnsupportedLookAndFeelException e1) {};


		menu_bar = new JMenuBar();
		menu = new JMenu("Options");
		item_op1 = new JMenuItem("About");
		item_op1.addActionListener(this);
		menu.add(item_op1);

		menu.addSeparator();

		item_op2 = new JMenuItem("Exit");
		item_op2.addActionListener(this);
		menu.add(item_op2);

		menu_bar.add(menu);
		setJMenuBar(menu_bar);

		north = new JPanel( new GridLayout(2,3) );

		lb = new JLabel("[ Moon ]");
		north.add(lb);

		lb = new JLabel("");
		north.add(lb);

		lb = new JLabel(now);
		north.add(lb);

		lb = new JLabel(" Date: ");
		north.add(lb);


		months[0] = "January";
		months[1] = "February";
		months[2] = "March";
		months[3] = "April";
		months[4] = "May";
		months[5] = "June";
		months[6] = "July";
		months[7] = "August";
		months[8] = "September";
		months[9] = "October";
		months[10] = "November";
		months[11] = "December";
		ch_month = new JComboBox<String>(months);
		ch_month.setBackground(new Color(0xfdfdf8 ));
		north.add(ch_month);

		for(i=2000;i<=2099;i++){
			years[i-2000]=""+i;
		}
		ch_year = new JComboBox<String>(years);
		ch_year.setBackground(new Color(0xfdfdf8 ));
		north.add(ch_year);



		getContentPane().add(north, BorderLayout.NORTH);

		center = new JPanel( new GridLayout(1,1) );

		data_table = new String[31][3];
		table = new JTable(data_table, tituloData);
		scrollPane = new JScrollPane(table);
		table.setShowGrid(false);
		table.setBackground(new Color(0xfdfdf8 ));

		table.setFont( new Font("Arial", Font.BOLD, 11) );
		center.add(scrollPane);
		getContentPane().add(center, BorderLayout.CENTER);

		south = new JPanel( new GridLayout(2,4) );

		bt_show = new JButton("Show Month");
		bt_show.addActionListener(this);
		bt_show.setBackground(new Color(0xfdfdf8 ));
		south.add(bt_show);

		lb = new JLabel("");
		south.add(lb);

		bt_previous = new JButton("Previous Month");
		bt_previous.addActionListener(this);
		bt_previous.setBackground(new Color(0xfdfdf8 ));
		south.add(bt_previous);

		bt_next = new JButton("Next Month");
		bt_next.addActionListener(this);
		bt_next.setBackground(new Color(0xfdfdf8 ));
		south.add(bt_next);

		bt_crescent = new JButton("Crescent Moon");
		bt_crescent.addActionListener(this);
		bt_crescent.setBackground(new Color(0xfdfdf8 ));
		south.add(bt_crescent);


		lb = new JLabel("");
		south.add(lb);


		bt_reset = new JButton("Reset");
		bt_reset.addActionListener(this);
		bt_reset.setBackground(new Color(0xfdfdf8 ));
		south.add(bt_reset);

		bt_exit = new JButton("Exit");
		bt_exit.addActionListener(this);
		bt_exit.setBackground(new Color(0xfdfdf8 ));
		south.add(bt_exit);

		getContentPane().add(south, BorderLayout.SOUTH);

		reset();
		showOut( Integer.parseInt( years[ch_year.getSelectedIndex()] ), (ch_month.getSelectedIndex()) );

		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public void actionPerformed(ActionEvent e){

		if( e.getSource() == item_op1 ){

			JOptionPane.showMessageDialog(this, about, "Sobre este programa", JOptionPane.INFORMATION_MESSAGE);
		}

		if( e.getSource() == bt_exit || e.getSource() == item_op2 ){
			System.exit(0);
		}
		if ( e.getSource() == bt_show ){

			showOut( Integer.parseInt( years[ch_year.getSelectedIndex()] ), (ch_month.getSelectedIndex()) );

		}

		if( e.getSource() == bt_next ){

			if( ch_month.getSelectedIndex() == 11 ){
				ch_month.setSelectedIndex( 0 );
				if( ch_year.getSelectedIndex() != 99)
					ch_year.setSelectedIndex( ch_year.getSelectedIndex()+1 );
				else
					ch_year.setSelectedIndex( 0 );
			}
			else
				ch_month.setSelectedIndex( ch_month.getSelectedIndex()+1 );

			showOut( Integer.parseInt( years[ch_year.getSelectedIndex()] ), (ch_month.getSelectedIndex()) );
		}

		if( e.getSource() == bt_previous ){

			if( ch_month.getSelectedIndex() == 0 ){
				ch_month.setSelectedIndex( 11 );
				if( ch_year.getSelectedIndex() != 0)
					ch_year.setSelectedIndex( ch_year.getSelectedIndex()-1 );
				else
					ch_year.setSelectedIndex( 99 );
			}
			else
				ch_month.setSelectedIndex( ch_month.getSelectedIndex()-1 );

			showOut( Integer.parseInt( years[ch_year.getSelectedIndex()] ), (ch_month.getSelectedIndex()) );
		}

		if( e.getSource() == bt_reset ){

			reset();

		}

		if( e.getSource() == bt_crescent){

			crescentMoon( Integer.parseInt( years[ch_year.getSelectedIndex()] ), (ch_month.getSelectedIndex()) );
		}




	}

	static public void showOut(int year, int month){

		int lim=0;
		int i, k;
		switch (month) {
		case 0: lim=31; break;
		case 1: lim=28; break;
		case 2: lim=31; break;
		case 3: lim=30; break;
		case 4: lim=31; break;
		case 5: lim=30; break;
		case 6: lim=31; break;
		case 7: lim=31; break;
		case 8: lim=30; break;
		case 9: lim=31; break;
		case 10: lim=30; break;
		case 11: lim=31; break;
		}

		k = 0;             
		for(i=1; i<=lim; i++){
			table.setValueAt(""+i+" de "+months[month]+" de "+year, k, 0);
			table.setValueAt(""+ConwayPercentage( Conway(year, month+1, i) ), k, 1);
			table.setValueAt(""+PhaseName(ConwayPercentage( Conway(year, month+1, i) ), isCrescentConway(year, month+1, i)), k, 2);
			k++;
		}

	}

	static public double Conway(int year, int month, int day){	
		double r = year % 100;
		double res = 0;

		r %= 19;
		if (r>9){ r -= 19;}
		r = ((r * 11) % 30) + month + day;
		if (month<3){r += 2;}
		r -= ((year<2000) ? 4 : 8.3);
		r = Math.floor(r+0.5)%30;
		res = (r < 0) ? r+30 : r;		
		return res;
	}

	static public boolean isCrescentConway(int year, int month, int day){

		if( ConwayPercentage(Conway(year, month, day)) >= ConwayPercentage(Conway(year, month, day-1 )) ){
			return true;  
		}
		else{
			return false;
		}

	}

	static public double ConwayPercentage(double age){
		double age_per=0.0;
		double res=0.0;

		age_per = ( (age) / 14 )* 100;
		if(age>=15){
			age = 29 - age;
			age_per = ( (age) / 14 )* 100;
		} 

		res = age_per * 100;
		res = Math.round(res);
		res = res / 100;

		return res;

	}

	static public String PhaseName(double percentage, boolean crescent){

		String name="";       
		if (percentage>=0 && percentage<3 && crescent) {name = "New Moon";}
		if (percentage>=3 && percentage<35 && crescent) {name = "Waxing Crescent";}
		if (percentage>=35 && percentage<66 && crescent) {name = "First Quarter";}
		if (percentage>=66 && percentage<97 && crescent) {name = "Waxing Gibbous";}
		if (percentage>=97 && percentage<=100 && crescent) {name = "Full Moon";}
		if (percentage<100 && percentage>96 && !crescent) {name = "Full Moon";}
		if (percentage<=96 && percentage>66 && !crescent) {name = "Wanning Gibbous";}
		if ( percentage<=65 && percentage>35 && !crescent ) {name = "Third Quarter";}
		if (percentage<=35 && percentage>3 && !crescent ) {name = "Wannig Crescent";}
		if (percentage<=3 && percentage>=0 && !crescent) {name = "New Moon";}

		return name;
	}

	static public void reset(){

		String month = now.substring(3,5);
		String year = now.substring(6);

		ch_month.setSelectedIndex( Integer.parseInt(month)-1 );
		ch_year.setSelectedIndex( Integer.parseInt(year)-2000 );
		showOut( (Integer.parseInt(year) ), (Integer.parseInt(month)-1) );

	}

	static public void crescentMoon(int year, int month ){

		int lim=0;
		int i, k;
		switch (month) {
		case 0: lim=31; break;
		case 1: lim=28; break;
		case 2: lim=31; break;
		case 3: lim=30; break;
		case 4: lim=31; break;
		case 5: lim=30; break;
		case 6: lim=31; break;
		case 7: lim=31; break;
		case 8: lim=30; break;
		case 9: lim=31; break;
		case 10: lim=30; break;
		case 11: lim=31; break;
		}

		for(i=0; i<31; i++) {

			table.setValueAt("", i, 0);
			table.setValueAt("", i, 1);
			table.setValueAt("", i, 2);
		}		

		k = 0;         
		for(i=1; i<=lim; i++)
			if( ConwayPercentage(Conway(year, month, i)) >= 10.0 && ConwayPercentage(Conway(year, month, i)) < 60.0 && isCrescentConway(year, month+1, i)){

				System.out.println(""+(i)+"  "+ isCrescentConway(year, month+1, i+1) );

				table.setValueAt(""+i+" de "+months[month]+" de "+year, k, 0);
				table.setValueAt(""+ConwayPercentage( Conway(year, month+1, i) )+" %", k, 1);
				table.setValueAt(""+PhaseName(ConwayPercentage( Conway(year, month+1, i) ), isCrescentConway(year, month+1, i)), k, 2);
				k++;
			}





	}


	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
				Alcor app = new Alcor();
				app.setVisible(true);
			}
		});
		
	}

}
