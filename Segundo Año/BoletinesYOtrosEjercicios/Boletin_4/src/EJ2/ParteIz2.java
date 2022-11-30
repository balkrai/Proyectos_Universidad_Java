package EJ2;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class ParteIz2 extends JPanel {
	private JRadioButton rd1, rd2;

	public ParteIz2() {
		this.setLayout(new GridLayout(2, 1));
		Border border = BorderFactory.createTitledBorder("Grupo c");
		ButtonGroup grupo = new ButtonGroup();
		
		rd1 = new JRadioButton("C1");
		rd2 = new JRadioButton("C2");
		
		rd1.setActionCommand("R1");
		rd2.setActionCommand("R2");

		
		rd1.setBorder(new EmptyBorder(5, 5, 20, 70));
		rd2.setBorder(new EmptyBorder(5, 5, 0, 70));
		grupo.add(rd1);
		grupo.add(rd2);
		add(rd1);
		add(rd2);
		this.setBorder(BorderFactory.createTitledBorder("Grupo c"));
	}
	
	public void PonActionListener(ActionListener a)
	{
		rd1.addActionListener(a);
		rd2.addActionListener(a);
	}
}
