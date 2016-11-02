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
		fillModuleDistance();
		add(moduleDistance, "cell "+ positionDistance +",grow");
		
		
		modulePace = new JPanel();
		modulePace.setLayout(new MigLayout("", "[][grow][grow]", "[][]"));
		fillModulePace();
		add(modulePace, "cell "+ positionPace +",grow");
		
		
		moduleTime = new JPanel();
		moduleTime.setLayout(new MigLayout("", "[]", "[]"));
		fillModuleTime();
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
		
		replaceEmptysWithZero();
		
		switch(mode){
			case DISTANCE:
				int pace = PaceCalculations.GetTotalSeconds("0", txtPaceMinutes.getText(), txtPaceSeconds.getText());
				int time = PaceCalculations.GetTotalSeconds(txtTimeHours.getText(), txtTimeMinutes.getText(), txtTimeSeconds.getText());
				txtDistance.setText("" + (pace * time));
				break;
			case PACE:
				
				break;
			case TIME:
				
				break;
		}
	}
	
	private void replaceEmptysWithZero(){
		if(txtDistance.getText().trim().equals("")){
			txtDistance.setText("0");
		}

		if(txtPaceMinutes.getText().trim().equals("")){
			txtPaceMinutes.setText("0");
		}

		if(txtPaceSeconds.getText().trim().equals("")){
			txtPaceSeconds.setText("0");
		}

		if(txtTimeHours.getText().trim().equals("")){
			txtTimeHours.setText("0");
		}

		if(txtTimeMinutes.getText().trim().equals("")){
			txtTimeMinutes.setText("0");
		}

		if(txtTimeSeconds.getText().trim().equals("")){
			txtTimeSeconds.setText("0");
		}
	}
	
	private void fillModuleDistance(){
		moduleDistance.add(new JLabel("Miles"), "cell 1 0");

		moduleDistance.add(new JLabel("Distance"), "cell 0 1,alignx trailing");

		txtDistance = new JTextField(10);
		moduleDistance.add(txtDistance, "cell 1 1,growx");
	}
	
	private void fillModulePace(){
		modulePace.add(new JLabel("Minutes"), "cell 1 0");
		
		modulePace.add(new JLabel("Seconds"), "cell 2 0");

		modulePace.add(new JLabel("Pace"), "cell 0 1,alignx trailing");

		txtPaceMinutes = new JTextField(5);
		modulePace.add(txtPaceMinutes, "cell 1 1,growx");
		
		txtPaceSeconds = new JTextField(5);
		modulePace.add(txtPaceSeconds, "cell 2 1,growx");
		
		modulePace.add(new JLabel("per Mile"), "cell 1 2,growx");
	}
	
	private void fillModuleTime(){
		moduleTime.add(new JLabel("Hours"), "cell 1 0");
		
		moduleTime.add(new JLabel("Minutes"), "cell 2 0");
		
		moduleTime.add(new JLabel("Seconds"), "cell 3 0");

		moduleTime.add(new JLabel("Time"), "cell 0 1,alignx trailing");

		txtTimeHours = new JTextField(5);
		moduleTime.add(txtTimeHours, "cell 1 1,growx");
		
		txtTimeMinutes = new JTextField(5);
		moduleTime.add(txtTimeMinutes, "cell 2 1,growx");
		
		txtTimeSeconds = new JTextField(5);
		moduleTime.add(txtTimeSeconds, "cell 3 1,growx");
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
		currentPanel.computeAnswer();
		System.out.println(currentPanel.toString());
	}
}


