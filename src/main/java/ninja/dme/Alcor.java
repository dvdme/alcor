package ninja.dme;

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

	JLabel lb;
	JComboBox<String> ch_month;
	JComboBox<String> ch_year;

	JButton bt_show, bt_previous, bt_next, bt_exit, bt_reset, bt_crescent;

	static String months[] = new String[12];
	String years[] = new String[100];
	String[] tituloData = { "Date", "Ilumination", "Phase" };
	String[][] data_table;
	static JTable table;
	JScrollPane scrollPane;

	private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	private Date date = new Date();
	private String now = df.format(date);
	private MoonPhase moonPhase;

	int i = 0;
	int k = 0;

	static boolean crescent = true;

	private static final long serialVersionUID = 1L;

	private AlcorNorthPanel north;
	private AlcorCenterPanel center;
	private AlcorSouthPanel south;

	public Alcor() {

		super("Alcor");

		// System Look and Feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
		} catch (InstantiationException e1) {
		} catch (IllegalAccessException e1) {
		} catch (UnsupportedLookAndFeelException e1) {
		}
		;

		setJMenuBar(new AlcorMenuBar());
		north = new AlcorNorthPanel();
		getContentPane().add(north, BorderLayout.NORTH);
		center = new AlcorCenterPanel();
		getContentPane().add(center, BorderLayout.CENTER);
		south = new AlcorSouthPanel();
		south.addActionListenerToButtons(this);
		getContentPane().add(south, BorderLayout.SOUTH);

		// reset();
		// showOut( Integer.parseInt( years[ch_year.getSelectedIndex()] ),
		// (ch_month.getSelectedIndex()) );

		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.south.getButton(this.south.EXIT_BUTTON)) {
			System.exit(0);
		} else if (e.getSource() == this.south.getButton(this.south.SHOW_BUTTON)) {

			int year = Integer.parseInt(this.north.getSelectedYear());
			int month = Integer.parseInt(this.north.getSelectedMonth());
			this.showOut(0,0 );
		}

		if (e.getSource() == bt_next) {

			if (ch_month.getSelectedIndex() == 11) {
				ch_month.setSelectedIndex(0);
				if (ch_year.getSelectedIndex() != 99)
					ch_year.setSelectedIndex(ch_year.getSelectedIndex() + 1);
				else
					ch_year.setSelectedIndex(0);
			} else
				ch_month.setSelectedIndex(ch_month.getSelectedIndex() + 1);

			showOut(Integer.parseInt(years[ch_year.getSelectedIndex()]), (ch_month.getSelectedIndex()));
		}

		if (e.getSource() == bt_previous) {

			if (ch_month.getSelectedIndex() == 0) {
				ch_month.setSelectedIndex(11);
				if (ch_year.getSelectedIndex() != 0)
					ch_year.setSelectedIndex(ch_year.getSelectedIndex() - 1);
				else
					ch_year.setSelectedIndex(99);
			} else
				ch_month.setSelectedIndex(ch_month.getSelectedIndex() - 1);

			showOut(Integer.parseInt(years[ch_year.getSelectedIndex()]), (ch_month.getSelectedIndex()));
		}

		if (e.getSource() == bt_reset) {

			reset();

		}

		if (e.getSource() == bt_crescent) {

			crescentMoon(Integer.parseInt(years[ch_year.getSelectedIndex()]), (ch_month.getSelectedIndex()));
		}

	}

	public void showOut(int year, int month) {

		int lim = 0;
		int i, k;
		switch (month) {
		case 0:
			lim = 31;
			break;
		case 1:
			lim = 28;
			break;
		case 2:
			lim = 31;
			break;
		case 3:
			lim = 30;
			break;
		case 4:
			lim = 31;
			break;
		case 5:
			lim = 30;
			break;
		case 6:
			lim = 31;
			break;
		case 7:
			lim = 31;
			break;
		case 8:
			lim = 30;
			break;
		case 9:
			lim = 31;
			break;
		case 10:
			lim = 30;
			break;
		case 11:
			lim = 31;
			break;
		}

		k = 0;
		this.moonPhase = new MoonPhase(date);
		for (i = 1; i <= lim; i++) {
			this.center.getTable().setValueAt("" + i + " de " + months[month] + " de " + year, k, 0);
			table.setValueAt("" + ConwayPercentage(Conway(year, month + 1, i)), k, 1);
			table.setValueAt(
					"" + PhaseName(ConwayPercentage(Conway(year, month + 1, i)), isCrescentConway(year, month + 1, i)),
					k, 2);
			k++;
		}

	}

	static public double Conway(int year, int month, int day) {
		double r = year % 100;
		double res = 0;

		r %= 19;
		if (r > 9) {
			r -= 19;
		}
		r = ((r * 11) % 30) + month + day;
		if (month < 3) {
			r += 2;
		}
		r -= ((year < 2000) ? 4 : 8.3);
		r = Math.floor(r + 0.5) % 30;
		res = (r < 0) ? r + 30 : r;
		return res;
	}

	static public boolean isCrescentConway(int year, int month, int day) {

		if (ConwayPercentage(Conway(year, month, day)) >= ConwayPercentage(Conway(year, month, day - 1))) {
			return true;
		} else {
			return false;
		}

	}

	static public double ConwayPercentage(double age) {
		double age_per = 0.0;
		double res = 0.0;

		age_per = ((age) / 14) * 100;
		if (age >= 15) {
			age = 29 - age;
			age_per = ((age) / 14) * 100;
		}

		res = age_per * 100;
		res = Math.round(res);
		res = res / 100;

		return res;

	}

	static public String PhaseName(double percentage, boolean crescent) {

		String name = "";
		if (percentage >= 0 && percentage < 3 && crescent) {
			name = "New Moon";
		}
		if (percentage >= 3 && percentage < 35 && crescent) {
			name = "Waxing Crescent";
		}
		if (percentage >= 35 && percentage < 66 && crescent) {
			name = "First Quarter";
		}
		if (percentage >= 66 && percentage < 97 && crescent) {
			name = "Waxing Gibbous";
		}
		if (percentage >= 97 && percentage <= 100 && crescent) {
			name = "Full Moon";
		}
		if (percentage < 100 && percentage > 96 && !crescent) {
			name = "Full Moon";
		}
		if (percentage <= 96 && percentage > 66 && !crescent) {
			name = "Wanning Gibbous";
		}
		if (percentage <= 65 && percentage > 35 && !crescent) {
			name = "Third Quarter";
		}
		if (percentage <= 35 && percentage > 3 && !crescent) {
			name = "Wannig Crescent";
		}
		if (percentage <= 3 && percentage >= 0 && !crescent) {
			name = "New Moon";
		}

		return name;
	}

	public void reset() {

		String month = now.substring(3, 5);
		String year = now.substring(6);

		ch_month.setSelectedIndex(Integer.parseInt(month) - 1);
		ch_year.setSelectedIndex(Integer.parseInt(year) - 2000);
		showOut((Integer.parseInt(year)), (Integer.parseInt(month) - 1));

	}

	static public void crescentMoon(int year, int month) {

		int lim = 0;
		int i, k;
		switch (month) {
		case 0:
			lim = 31;
			break;
		case 1:
			lim = 28;
			break;
		case 2:
			lim = 31;
			break;
		case 3:
			lim = 30;
			break;
		case 4:
			lim = 31;
			break;
		case 5:
			lim = 30;
			break;
		case 6:
			lim = 31;
			break;
		case 7:
			lim = 31;
			break;
		case 8:
			lim = 30;
			break;
		case 9:
			lim = 31;
			break;
		case 10:
			lim = 30;
			break;
		case 11:
			lim = 31;
			break;
		}

		for (i = 0; i < 31; i++) {

			table.setValueAt("", i, 0);
			table.setValueAt("", i, 1);
			table.setValueAt("", i, 2);
		}

		k = 0;
		for (i = 1; i <= lim; i++)
			if (ConwayPercentage(Conway(year, month, i)) >= 10.0 && ConwayPercentage(Conway(year, month, i)) < 60.0
					&& isCrescentConway(year, month + 1, i)) {

				System.out.println("" + (i) + "  " + isCrescentConway(year, month + 1, i + 1));

				table.setValueAt("" + i + " de " + months[month] + " de " + year, k, 0);
				table.setValueAt("" + ConwayPercentage(Conway(year, month + 1, i)) + " %", k, 1);
				table.setValueAt(""
						+ PhaseName(ConwayPercentage(Conway(year, month + 1, i)), isCrescentConway(year, month + 1, i)),
						k, 2);
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
