package dme.alcor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class AlcorMenuBar extends JMenuBar implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final String about = "<html>" + "       <h3>Alcor</h3><hr>"
			+ "       <p>Calculates the illuminated percentage of the Moon<br>" + "       for a given date.</p>"
			+ "       <p>The Conway algorithm is used for<br>"
			+ "       the calculation so precision is not very high.<br>" + "       <hr>"
			+ "       <p>This was my first App writen in Java and with a<br>"
			+ "       GUI. I made it because, well, some people like to know<br>"
			+ "       when the Moon is crescent and I also like the Moon.</p>" + "       <hr>"
			+ "       <p>On Github:<br>"
			+ "       <a href=\"https://github.com/dvdme/alcor\">https://github.com/dvdme/alcor</a></p>"
			+ "       <p>The code is available under the terms of the<br>"
			+ "       <a href=\"http://www.eclipse.org/legal/epl-v10.html\">Eclipse Public License</a><br></p>"
			+ "       </html>";
	//private JMenuBar menu_bar;
	private JMenu menu;
	private JMenuItem item_about, item_exit;

	public AlcorMenuBar() {
		//menu_bar = new JMenuBar();
		menu = new JMenu("Options");
		item_about = new JMenuItem("About");
		item_about.addActionListener(this);
		menu.add(item_about);

		menu.addSeparator();

		item_exit = new JMenuItem("Exit");
		item_exit.addActionListener(this);
		menu.add(item_exit);

		this.add(menu);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == item_about) {
			JOptionPane.showMessageDialog(this, about, "Sobre este programa", JOptionPane.INFORMATION_MESSAGE);
		} else if (e.getSource() == item_exit) {
			System.exit(0);
		}

	}

}
