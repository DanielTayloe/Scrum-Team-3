import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;


public class PacerRealGood {
	private JFrame frame = null;
	private JTabbedPane tabbedPane = null;
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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		addTabs();
	}
	
	private void addTabs(){
		tabDistance = new TabPanel(TabPanel.TR, TabPanel.TL, TabPanel.BL, TabPanel.BR);
		tabbedPane.addTab("Distance", tabDistance);
		
		tabPace = new TabPanel(TabPanel.TL, TabPanel.TR, TabPanel.BL, TabPanel.BR);
//		tabPace.setLayout(new MigLayout("", "[]", "[]"));
		tabbedPane.addTab("Pace", tabPace);

		tabTime = new TabPanel(TabPanel.TL, TabPanel.BL, TabPanel.TR, TabPanel.BR);
//		tabTime.setLayout(new MigLayout("", "[]", "[]"));
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
	private JComboBox cmbDistanceUnits;
	private JComboBox cmbPerDistanceUnits;
                
	private JButton computeButton;
	
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
			txtDistance.setEnabled(false);
		}else if(TabPanel.TR.equals(positionPace)){
			mode = PACE;
			txtPaceMinutes.setEnabled(false);
			txtPaceSeconds.setEnabled(false);
		}else if(TabPanel.TR.equals(positionTime)){
			mode = TIME;
			txtTimeHours.setEnabled(false);
			txtTimeMinutes.setEnabled(false);
			txtTimeSeconds.setEnabled(false);
		}else{
			mode = BAD;
		}
	}

        private double toMeters(JComboBox combo, double quantity) {
            return quantity;
//            return PaceCalculations.ConvertUnit((byte)(PaceCalculations.CONV_MILES_TO_METERS+combo.getSelectedIndex()*4), quantity);
        }
        
        private double fromMeters(JComboBox combo, double quantity) {
            return quantity;
//            return PaceCalculations.ConvertUnit((byte)(PaceCalculations.CONV_METERS_TO_MILES+combo.getSelectedIndex()), quantity);            
        }        
        
	public void computeAnswer(){
		if(mode == BAD){
			return;
		}
		                
		replaceEmptysWithZero();
		DecimalFormat d;
		
		switch(mode){
			case DISTANCE:{
				double pace = PaceCalculations.GetTotalSeconds("0", txtPaceMinutes.getText(), txtPaceSeconds.getText());
				double time = PaceCalculations.GetTotalSeconds(txtTimeHours.getText(), txtTimeMinutes.getText(), txtTimeSeconds.getText());
				d = new DecimalFormat("#.##");
				txtDistance.setText("" + d.format(fromMeters(cmbDistanceUnits, PaceCalculations.Distance(time, toMeters(cmbPerDistanceUnits, pace), 1))));
			}
				break;
			case PACE:{
				double distance = Double.parseDouble(txtDistance.getText());
				double time = PaceCalculations.GetTotalSeconds(txtTimeHours.getText(), txtTimeMinutes.getText(), txtTimeSeconds.getText());
				d = new DecimalFormat("#.##");
				double temp = fromMeters(cmbPerDistanceUnits, PaceCalculations.Pace(time, toMeters(cmbDistanceUnits, distance), 1));
				txtPaceMinutes.setText("" + d.format(temp));
//				txtPaceSeconds.setText("" + d.format(PaceCalculations.Pace(time, distance, 1)));
			}
				break;
			case TIME:{
				double distance = Double.parseDouble(txtDistance.getText());
				double pace = PaceCalculations.GetTotalSeconds("0", txtPaceMinutes.getText(), txtPaceSeconds.getText());
				d = new DecimalFormat("#.##");
				txtTimeHours.setText("" + d.format(PaceCalculations.Time(toMeters(cmbDistanceUnits, distance), toMeters(cmbPerDistanceUnits, pace), 1)));
			}
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
                String[] unitStrings = { "Miles", "Kilometers", "Meters", "Yards" };
                cmbDistanceUnits = new JComboBox(unitStrings);
		moduleDistance.add(cmbDistanceUnits, "cell 1 0");
                
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
		
                String[] unitStrings = { "per Mile", "per Kilometer", "per Meter", "per Yard" };
                cmbPerDistanceUnits = new JComboBox(unitStrings);
		modulePace.add(cmbPerDistanceUnits, "cell 1 2,growx");
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
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		currentPanel.computeAnswer();
		System.out.println(currentPanel.toString());
	}
}


