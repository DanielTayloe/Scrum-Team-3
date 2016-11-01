import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;


public class Pacer {
	private JFrame frame = null;
	private JTabbedPane tabbedPane = null;
	private JPanel tabDistance = null;
	private JPanel tabPace = null;
	private JPanel tabTime = null;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pacer window = new Pacer();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Pacer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		addTabs();
		addTabContentDistance();
//		addTabContentPace();
//		addTabContentTime();
	}
	
	private void addTabs(){
		tabDistance = new JPanel();
		tabbedPane.addTab("Distance", tabDistance);

		tabPace = new JPanel();
		tabbedPane.addTab("Pace", tabPace);

		tabTime = new JPanel();
		tabbedPane.addTab("Time", tabTime);
	}
	
	private void addTabContentDistance(){
		JPanel tabDistanceInputs = new JPanel();
		tabDistanceInputs.setLayout(new GridLayout(2,2));
		tabDistanceInputs.add(new JLabel("Pace"));
		tabDistanceInputs.add(new JTextField());
		tabDistanceInputs.add(new JLabel("Time"));
		tabDistanceInputs.add(new JTextField());
		tabDistance.add(tabDistanceInputs);

		JPanel tabDistanceAnswer = new JPanel();
		tabDistanceAnswer.setLayout(new GridLayout(1,2));
		tabDistanceAnswer.add(new JLabel("Distance"));
		tabDistanceAnswer.add(new JTextField());
		tabDistance.add(tabDistanceAnswer);
	}
}
