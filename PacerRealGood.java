import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;

/**
 * Driver for the pace calculator application.
 * @author Daniel
 *
 */
public class PacerRealGood {
	private JFrame frame;
	private JTabbedPane tabbedPane;
	private TabPanel tabDistance;
	private TabPanel tabPace;
	private TabPanel tabTime;
        
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
		frame.setBounds(100, 100, 650, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		addTabs();
	}
	
	/**
	 * Add tabs to JTabbedPane
	 */
	private void addTabs(){
		tabDistance = new TabPanel(TabPanel.BL, TabPanel.TL, TabPanel.TR, TabPanel.BR);
		tabbedPane.addTab("Distance", tabDistance);
		
		tabPace = new TabPanel(TabPanel.TL, TabPanel.BL, TabPanel.TR, TabPanel.BR);
		tabbedPane.addTab("Pace", tabPace);

		tabTime = new TabPanel(TabPanel.TL, TabPanel.TR, TabPanel.BL, TabPanel.BR);
		tabbedPane.addTab("Time", tabTime);
	}
}

/**
 * Serves as an individual panel for a tab, using Java's JTabbedPane class. 
 * @author Daniel
 *
 */
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
	private JPanel moduleCompute;

	private JTextField txtPaceMinutes;
	private JTextField txtPaceSeconds;
	private JTextField txtTimeHours;
	private JTextField txtTimeMinutes;
	private JTextField txtTimeSeconds;
	private JTextField txtDistance;

	private JButton computeButton;
	private JButton resetButton;
	
	/**
	 * Uses the constants TabPanel.TL, etc to set which module will be placed where in this tab.
	 * TabPanel.TL : Top left
	 * TabPanel.TR : Top right
	 * TabPanel.BL : Bottom left
	 * TabPanel.BR : Bottom right
	 * The modules are placed depending on the order of the constants. 
	 * So if the first constant is TabPanel.TL, the distance module will be placed in the top left corner. 
	 * @param positionDistance
	 * @param positionPace
	 * @param positionTime
	 * @param positionCompute
	 */
	public TabPanel(String positionDistance, String positionPace, String positionTime, String positionCompute){
		setLayout(new MigLayout("", "[grow][grow]", "[grow][grow]"));
		
		moduleDistance = new JPanel();
		moduleDistance.setLayout(new MigLayout("", "[]", "[]"));
		fillModuleDistance(!TabPanel.BL.equals(positionDistance));
		add(moduleDistance, "cell "+ positionDistance +",grow");
		
		
		modulePace = new JPanel();
		modulePace.setLayout(new MigLayout("", "[][grow][grow]", "[][]"));
		fillModulePace();
		add(modulePace, "cell "+ positionPace +",grow");
		
		moduleTime = new JPanel();
		moduleTime.setLayout(new MigLayout("", "[]", "[]"));
		fillModuleTime();
		add(moduleTime, "cell "+ positionTime +",grow");

		moduleCompute = new JPanel();
		moduleCompute.setLayout(new BorderLayout());
		Dimension compDim = new Dimension(100, 65);
		computeButton = new JButton("Compute");
		computeButton.setPreferredSize(compDim);
		computeButton.addActionListener(new ComputeActionListener(this));
		resetButton = new JButton("Reset");
		resetButton.setPreferredSize(compDim);
		resetButton.addActionListener(new ResetActionListener(this));
		JPanel tempPanel = new JPanel();
		tempPanel.setLayout(new BorderLayout());
		tempPanel.add(computeButton, BorderLayout.SOUTH);
		tempPanel.add(resetButton, BorderLayout.CENTER);
		moduleCompute.add(tempPanel, BorderLayout.EAST);
		add(moduleCompute, "cell "+ positionCompute +",grow");
		
		//assign mode so that top right panel is always answer box, this way we know how to compute answer
		if(TabPanel.BL.equals(positionDistance)){
			mode = DISTANCE;
			txtDistance.setEnabled(false);
		}else if(TabPanel.BL.equals(positionPace)){
			mode = PACE;
			txtPaceMinutes.setEnabled(false);
			txtPaceSeconds.setEnabled(false);
		}else if(TabPanel.BL.equals(positionTime)){
			mode = TIME;
			txtTimeHours.setEnabled(false);
			txtTimeMinutes.setEnabled(false);
			txtTimeSeconds.setEnabled(false);
		}else{
			mode = BAD;
		}
	}

	/**
	 * Computes answer as long as there are no error conditions, 
	 * and places the answer into the appropriate box. 
	 * If there are blank boxes they will be cleared to zero.
	 */
	public void computeAnswer(){
		if(mode == BAD){
			return;
		}
		                
		replaceEmptysWithZero();
		DecimalFormat d = new DecimalFormat("#.##");
		
		switch(mode){
			case DISTANCE:{
				double pace = PaceCalculations.GetTotalSeconds("0", txtPaceMinutes.getText(), txtPaceSeconds.getText());
				double time = PaceCalculations.GetTotalSeconds(txtTimeHours.getText(), txtTimeMinutes.getText(), txtTimeSeconds.getText());
				
				//Color highlighting for incorrect values
				if(pace < 0.0 || time < 0.0){
					txtPaceMinutes.setBackground(Color.PINK);
					txtPaceSeconds.setBackground(Color.PINK);
					txtTimeMinutes.setBackground(Color.PINK);
					txtTimeSeconds.setBackground(Color.PINK);
					txtTimeHours.setBackground(Color.PINK);
					
					txtDistance.setText("");
				}else{
					txtPaceMinutes.setBackground(Color.WHITE);
					txtPaceSeconds.setBackground(Color.WHITE);
					txtTimeMinutes.setBackground(Color.WHITE);
					txtTimeSeconds.setBackground(Color.WHITE);
					txtTimeHours.setBackground(Color.WHITE);
					
					txtDistance.setText("" + d.format(PaceCalculations.Distance(time, pace, 1)));
				}
			}
				break;
			case PACE:{
				double distance = PaceCalculations.SafeParseDouble(txtDistance.getText());
				double time = PaceCalculations.GetTotalSeconds(txtTimeHours.getText(), txtTimeMinutes.getText(), txtTimeSeconds.getText());
				
				//Color highlighting for incorrect values
				if(distance < 0.0 || time < 0.0){
					txtTimeHours.setBackground(Color.PINK);
					txtTimeMinutes.setBackground(Color.PINK);
					txtTimeSeconds.setBackground(Color.PINK);
					
					txtPaceMinutes.setText("");
					txtPaceSeconds.setText("");
					
				}else{
					txtTimeHours.setBackground(Color.WHITE);
					txtTimeMinutes.setBackground(Color.WHITE);
					txtTimeSeconds.setBackground(Color.WHITE);
					
					String parts[] = PaceCalculations.secondsToParts(PaceCalculations.Pace(time, distance, 1));
					txtPaceMinutes.setText(parts[1]);
					txtPaceSeconds.setText(parts[2]);
				}
			}
				break;
			case TIME:{
	
				//double distance = Double.parseDouble(txtDistance.getText());
				double distance = PaceCalculations.SafeParseDouble(txtDistance.getText());		//created SafeParseDouble
				double pace = PaceCalculations.GetTotalSeconds("0", txtPaceMinutes.getText(), txtPaceSeconds.getText());
				
				
				//Color highlighting for incorrect values
				if(distance < 0.0 || pace < 0.0){
					txtPaceMinutes.setBackground(Color.PINK);
					txtPaceSeconds.setBackground(Color.PINK);
					
					txtTimeHours.setText("");
					txtTimeMinutes.setText("");
					txtTimeSeconds.setText("");
					
				}else{
					txtPaceMinutes.setBackground(Color.WHITE);
					txtPaceSeconds.setBackground(Color.WHITE);
							
					String parts[] = PaceCalculations.secondsToParts(PaceCalculations.Time(distance, pace, 1));
					txtTimeHours.setText(parts[0]);
					txtTimeMinutes.setText(parts[1]);
					txtTimeSeconds.setText(parts[2]);
				}
			}
				break;
		}
	}
	
	/**
	 * Clears any boxes that are empty, setting them to zero.
	 */
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
	
	/**
	 * Clears all boxes to zero, regardless of existing content.
	 */
	public void reset(){
		txtDistance.setText("0");
		txtPaceMinutes.setText("0");
		txtPaceSeconds.setText("0");
		txtTimeHours.setText("0");
		txtTimeMinutes.setText("0");
		txtTimeSeconds.setText("0");
	}
	
	/**
	 * Fills the distance module with user interface components needed for input.
	 * @param eventSelector toggles display of the event combobox, true enables display.
	 */
	private void fillModuleDistance(boolean eventSelector){
		moduleDistance.add(new JLabel("Miles"), "cell 1 0");
                
		moduleDistance.add(new JLabel("Distance"), "cell 0 1,alignx trailing");

		txtDistance = new JTextField(10);
		moduleDistance.add(txtDistance, "cell 1 1,growx");

		if(eventSelector) {
			String[] eventNames = {"", "Marathon", "Half-Marathon", "5K", "5M", "8K", "10K", "15K", "10M", "20K", "15M", "25K", "30K", "20M"};
			final JComboBox cmbEvents = new JComboBox(eventNames);
			moduleDistance.add(new JLabel("Or select an event"), "cell 0 2,alignx trailing");
			moduleDistance.add(cmbEvents, "cell 1 2,alignx trailing");
			cmbEvents.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int eventSelected = cmbEvents.getSelectedIndex();

					double eventQuantities[] = {0.0, 26.21875, 13.109375, 5, 5, 8, 10, 15, 10, 20, 15, 25, 30, 20};
					double distance = eventQuantities[eventSelected];
					int needsConverting[] = {0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0};
					if(needsConverting[eventSelected] == 1){//event needs converting to miles
						distance = PaceCalculations.ConvertUnit(PaceCalculations.CONV_KILOMETERS_TO_MILES, distance);
					}
					txtDistance.setText(distance+"");
				}
			});
		}

	}
	/**
	 * Fills the pace module with user interface components needed for input.
	 */
	private void fillModulePace(){
		modulePace.add(new JLabel("Minutes"), "cell 1 0");
		
		modulePace.add(new JLabel("Seconds"), "cell 2 0");

		modulePace.add(new JLabel("Pace"), "cell 0 1,alignx trailing");

		txtPaceMinutes = new JTextField(5);
		modulePace.add(txtPaceMinutes, "cell 1 1,growx");
		
		txtPaceSeconds = new JTextField(5);
		modulePace.add(txtPaceSeconds, "cell 2 1,growx");
		
		modulePace.add(new JLabel("Per Mile"), "cell 1 2");
	}
	
	/**
	 * Fills the time module with user interface components needed for input.
	 */
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

/**
 * Provides an action listener interface for computing the answer on a tab.
 * @author Daniel
 *
 */
class ComputeActionListener implements ActionListener{
	private TabPanel currentPanel;
	
	public ComputeActionListener(TabPanel currentPanel){
		this.currentPanel = currentPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		currentPanel.computeAnswer();
		System.out.println(currentPanel.toString());
	}
}

/**
 * Provides an action listener interface for resetting all input boxes to zero on a tab.
 * @author Daniel
 *
 */
class ResetActionListener implements ActionListener{
	private TabPanel currentPanel;
	
	public ResetActionListener(TabPanel currentPanel){
		this.currentPanel = currentPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		currentPanel.reset();
	}
}


