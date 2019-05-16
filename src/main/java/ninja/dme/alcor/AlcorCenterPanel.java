package ninja.dme.alcor;

import java.awt.Color;
import java.awt.Font;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class AlcorCenterPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private final int GRID_ROWS = 1;
	private final int GRID_COLS = 1;

	private final int DATA_TABLE_ROWS = 31;
	private final int DATA_TABLE_COLS = 3;

	private final String FONT_NAME = "Arial";
	private final int FONT_STYLE = Font.BOLD;
	private final int FONT_SIZE = 11;
	private final int TABLE_BACKGROUND_COLOR = 0xfdfdf8;
	
	private final String[] dataTableTitles = { "Date", "Ilumination", "Phase" };

	private LayoutManager layout;
	private String[][] dataMatrix = new String[DATA_TABLE_ROWS][DATA_TABLE_COLS];
	private JTable table;
	private JScrollPane scrollPane;
	private Font font;
	private Color tableBackgroundColor;

	public AlcorCenterPanel() {
		font = new Font(FONT_NAME, FONT_STYLE, FONT_SIZE);
		tableBackgroundColor = new Color(TABLE_BACKGROUND_COLOR);
		buildNorthPanel();
	}

	public JTable getTable() {
		return this.table;
	}
	
	private void setGrid() {
		layout = new GridLayout(GRID_ROWS, GRID_COLS);
		this.setLayout(layout);
	}

	private void buildNorthPanel() {
		setGrid();
		dataMatrix = new String[31][3];
		table = new JTable(dataMatrix, dataTableTitles);
		scrollPane = new JScrollPane(table);
		table.setShowGrid(false);
		table.setBackground(tableBackgroundColor);
		table.setFont(font);
		this.add(scrollPane);
	}

}
