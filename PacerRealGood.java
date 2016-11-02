import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;


public class PacerRealGood {
	private JFrame frame = null;
	private JTabbedPane tabbedPane = null;
	private TabPanel tabDistance;
	private JPanel tabPace;
	private JPanel tabTime;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PacerRealGood window = new PacerRealGood();
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
	public PacerRealGood() {
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
	}
	
	private void addTabs(){
		tabDistance = new TabPanel(TabPanel.TR, TabPanel.TL, TabPanel.BL, TabPanel.BR);
		tabbedPane.addTab("Distance", tabDistance);
		
		tabPace = new JPanel();
		tabPace.setLayout(new MigLayout("", "[]", "[]"));
		tabbedPane.addTab("Pace", tabPace);

		tabTime = new JPanel();
		tabTime.setLayout(new MigLayout("", "[]", "[]"));
		tabbedPane.addTab("Time", tabTime);
	}
}

class TabPanel extends JPanel{
	public static final String TL = "0 0";
	public static final String TR = "1 0";
	public static final String BL = "0 1";
	public static final String BR = "1 1";

	public static final char DISTANCE = 'd';
	public static final char PACE = 'p';
	public static final char TIME = 't';
	public static final char BAD = 'x';

	public char mode = BAD;
	
	private JPanel modulePace;
	private JPanel moduleTime;
	private JPanel moduleDistance;

	private JTextField txtPaceMinutes;
	private JTextField txtPaceSeconds;
	private JTextField txtTimeHours;
	private JTextField txtTimeMinutes;
	private JTextField txtTimeSeconds;
	private JTextField txtDistance;
	
	private JButton computeButton;
	
	public TabPanel(String positionDistance, String positionPace, String positionTime, String positionCompute){
		setLayout(new MigLayout("", "[grow][grow]", "[grow][grow]"));
		
		moduleDistance = new JPanel();
		moduleDistance.setLayout(new MigLayout("", "[]", "[]"));
		fillModuleDistance(moduleDistance, txtDistance);
		add(moduleDistance, "cell "+ positionDistance +",grow");
		
		
		modulePace = new JPanel();
		modulePace.setLayout(new MigLayout("", "[][grow][grow]", "[][]"));
		fillModulePace(modulePace, txtPaceMinutes, txtPaceSeconds);
		add(modulePace, "cell "+ positionPace +",grow");
		
		
		moduleTime = new JPanel();
		moduleTime.setLayout(new MigLayout("", "[]", "[]"));
		fillModuleTime(moduleTime, txtTimeHours, txtTimeMinutes, txtTimeSeconds);
		add(moduleTime, "cell "+ positionTime +",grow");

		computeButton = new JButton("Compute");
		computeButton.addActionListener(new ComputeActionListener(this));
		add(computeButton, "cell "+ positionCompute +",grow");
		
		//assign mode so that top right panel is always answer box, this way we know how to compute answer
		if(TabPanel.TR.equals(positionDistance)){
			mode = DISTANCE;
		}else if(TabPanel.TR.equals(positionPace)){
			mode = PACE;
		}else if(TabPanel.TR.equals(positionTime)){
			mode = TIME;
		}else{
			mode = BAD;
		}
	}
	
	public void computeAnswer(){
		if(mode == BAD){
			return;
		}
		
		switch(mode){
			case DISTANCE:
//				txtDistance.setText();
				System.out.println("DISTANCE IS ANSWER");
				break;
			case PACE:
				
				break;
			case TIME:
				
				break;
		}
	}
	
//	private void assignValues(){
//		if(mode == BAD){
//			return;
//		}
//		
//		switch(mode){
//			case DISTANCE:
//				break;
//			case PACE:
//				
//				break;
//			case TIME:
//				
//				break;
//		}
//	}
	
	private void fillModuleDistance(JPanel panel, JTextField distance){
		JLabel lblMinutes = new JLabel("Miles");
		panel.add(lblMinutes, "cell 1 0");

		JLabel lblDistance = new JLabel("Distance");
		panel.add(lblDistance, "cell 0 1,alignx trailing");

		distance = new JTextField(10);
		panel.add(distance, "cell 1 1,growx");
	}
	
	private void fillModulePace(JPanel panel, JTextField minutes, JTextField seconds){
		JLabel lblMinutes = new JLabel("Minutes");
		panel.add(lblMinutes, "cell 1 0");
		
		JLabel lblSeconds = new JLabel("Seconds");
		panel.add(lblSeconds, "cell 2 0");

		JLabel lblPace = new JLabel("Pace");
		panel.add(lblPace, "cell 0 1,alignx trailing");

		minutes = new JTextField(5);
		panel.add(minutes, "cell 1 1,growx");
		
		seconds = new JTextField(5);
		panel.add(seconds, "cell 2 1,growx");
	}
	
	private void fillModuleTime(JPanel panel, JTextField hours, JTextField minutes, JTextField seconds){
		JLabel lblHours = new JLabel("Hours");
		panel.add(lblHours, "cell 1 0");
		
		JLabel lblMinutes = new JLabel("Minutes");
		panel.add(lblMinutes, "cell 2 0");
		
		JLabel lblSeconds = new JLabel("Seconds");
		panel.add(lblSeconds, "cell 3 0");

		JLabel lblTime = new JLabel("Time");
		panel.add(lblTime, "cell 0 1,alignx trailing");

		hours = new JTextField(5);
		panel.add(hours, "cell 1 1,growx");
		
		minutes = new JTextField(5);
		panel.add(minutes, "cell 2 1,growx");
		
		seconds = new JTextField(5);
		panel.add(seconds, "cell 3 1,growx");
	}
	
	public String toString(){
		return 
			"Distance: " + txtDistance.getText() + " miles" + "\n" +
			"Pace: " + txtPaceMinutes.getText() + " mins, " + txtPaceSeconds.getText() + " secs" + "\n" +
			"Time: " + txtTimeHours.getText() + " hours, " + txtTimeMinutes.getText() + " mins, " + txtTimeSeconds.getText() + " secs" + "\n";
	}
}

class ComputeActionListener implements ActionListener{
	private TabPanel currentPanel;
	
	public ComputeActionListener(TabPanel currentPanel){
		this.currentPanel = currentPanel;
		System.out.println("created action listener");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(currentPanel.toString());
		currentPanel.computeAnswer();
	}
}
