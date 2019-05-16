package ninja.dme;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AlcorSouthPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private final String WRONG_BUTTON_NAME = "Provided button name was not found"; 
	
	private final int GRID_ROWS = 2;
	private final int GRID_COLS = 4;

	public final String SHOW_BUTTON = "Show Month";
	public final String PREVIOUS_BUTTON = "Previous Month";
	public final String NEXT_BUTTON = "Next Month";
	public final String CRESCENT_BUTTON = "Crescent Moon";
	public final String RESET_BUTTON = "Reset";
	public final String EXIT_BUTTON = "Exit";

	private LayoutManager layout;
	private Map<String, JButton> buttons;

	public AlcorSouthPanel() {
		buttons = new HashMap<String, JButton>();
		buildSouthPanel();
	}

	public void addActionListenerToButtons(ActionListener actionListener) {
		for (String key : this.buttons.keySet()) {
		    this.buttons.get(key).addActionListener(actionListener);
		}
	}
	
	public JButton getButton(String buttonName) {
		if(this.buttons.containsKey(buttonName)) {
			return this.buttons.get(buttonName);
		}else {
			throw new IllegalArgumentException(WRONG_BUTTON_NAME);
		}
	}
	
	private void setGrid() {
		layout = new GridLayout(GRID_ROWS, GRID_COLS);
		this.setLayout(layout);
	}

	private void setButton(String buttonText) {
		JButton button = new JButton(buttonText);
		this.buttons.put(buttonText, button);
		this.add(button);
	}
	
	private void addEmptyLabel() {
		JLabel label = new JLabel("");
		this.add(label);
	}

	private void buildSouthPanel() {
		setGrid();
		setButton(SHOW_BUTTON);
		addEmptyLabel();
		setButton(PREVIOUS_BUTTON);
		setButton(NEXT_BUTTON);
		setButton(CRESCENT_BUTTON);
		addEmptyLabel();
		setButton(RESET_BUTTON);
		setButton(EXIT_BUTTON);
	}

}
