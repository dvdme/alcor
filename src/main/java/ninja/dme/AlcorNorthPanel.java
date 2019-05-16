package ninja.dme;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AlcorNorthPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private final int GRID_ROWS = 2;
	private final int GRID_COLS = 3;
	private final int COMBO_BOX_COLOR = 0xfdfdf8;

	public final String MOON_LABEL = "[ Moon ]";
	public final String DATE_LABEL = " Date: ";
	public final String EMPTY_LABEL = "";
	public final String DATE_FORMAT = "dd/MM/yyyy";

	private LayoutManager layout;
	private Map<String, JLabel> labels;
	private JComboBox<String> comboBoxMonth, comboBoxYear;
	private DateFormat df;
	private Date date;
	private String now;
	private String months[] = new String[12];
	private String years[] = new String[100];
	private Color comboBoxColor;

	public AlcorNorthPanel() {
		df = new SimpleDateFormat(DATE_FORMAT);
		date = new Date();
		now = df.format(date);
		labels = new HashMap<String, JLabel>();
		comboBoxColor = new Color(COMBO_BOX_COLOR);
		buildNorthPanel();
	}

	public JComboBox getYearComboBox() {
		return this.comboBoxYear;
	}
	
	public JComboBox getMonthComboBox() {
		return this.comboBoxMonth;
	}
	
	public String getSelectedYear() {
		return this.years[this.getYearComboBox().getSelectedIndex()];
	}
	
	public String getSelectedMonth() {
		return this.months[this.getMonthComboBox().getSelectedIndex()];
	}
	
	private void setGrid() {
		layout = new GridLayout(GRID_ROWS, GRID_COLS);
		this.setLayout(layout);
	}

	private void setLabel(String labelText) {
		JLabel label = new JLabel(labelText);
		this.labels.put(labelText, label);
		this.add(label);
	}

	private void setComboBoxs() {

		this.months[0] = "January";
		this.months[1] = "February";
		this.months[2] = "March";
		this.months[3] = "April";
		this.months[4] = "May";
		this.months[5] = "June";
		this.months[6] = "July";
		this.months[7] = "August";
		this.months[8] = "September";
		this.months[9] = "October";
		this.months[10] = "November";
		this.months[11] = "December";

		this.comboBoxMonth = new JComboBox<String>(this.months);
		this.comboBoxMonth.setBackground(comboBoxColor);

		for (int i = 2000; i <= 2099; i++) {
			years[i - 2000] = "" + i;
		}
		
		this.comboBoxYear = new JComboBox<String>(this.years);
		this.comboBoxYear.setBackground(comboBoxColor);

		this.add(this.comboBoxMonth);
		this.add(this.comboBoxYear);
	}

	private void buildNorthPanel() {
		setGrid();
		setLabel(MOON_LABEL);
		setLabel(EMPTY_LABEL);
		setLabel(this.now);
		setLabel(DATE_LABEL);
		setComboBoxs();
	}

}
