package ninja.dme.alcor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ShowDialogListener implements ActionListener {

	private JFrame jframe;

	public ShowDialogListener(JFrame jframe) {
		this.jframe = jframe;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JOptionPane.showMessageDialog(this.jframe, "Teste Message", "Sobre este programa", JOptionPane.INFORMATION_MESSAGE);

	}

}
