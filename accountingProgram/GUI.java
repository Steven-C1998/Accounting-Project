package accountingProgram;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

class GUI  {
	static JTextArea ta = new JTextArea();
	GUI() {
		JFrame frame = new JFrame("Accounting"); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 1000);
		JMenuBar mb = new JMenuBar();
		JMenu m1 = new JMenu("FILE");
		JMenu m2 = new JMenu("Help");
		mb.add(m1);
		mb.add(m2);
		JMenuItem m11 = new JMenuItem("Open");
		m11.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ButtonCall.openFiles();
			}
		});
		m1.add(m11);
		JPanel panel = new JPanel(); // the panel is not visible in output
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(boxlayout);
		panel.setBorder(new EmptyBorder(new Insets(0, 0, 150, 200)));
		JButton openMaster = new JButton ("Select Master File");
		openMaster.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ButtonCall.selectMaster();
			}
		});
		openMaster.setBounds(100,50,50,30);  
		panel.add(openMaster);
		JButton openRecords = new JButton ("Select Record File");
		openRecords.setBounds(100,50,50,30);  
		panel.add(openRecords);
		frame.getContentPane().add(BorderLayout.SOUTH, panel);
		frame.getContentPane().add(BorderLayout.NORTH, mb);
		frame.getContentPane().add(BorderLayout.CENTER, ta);
		frame.setVisible(true);
	}
}
