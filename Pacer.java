import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import javax.swing.border.TitledBorder;


public class Pacer {
	private JFrame frame = null;
	private JTabbedPane tabbedPane = null;
	private JPanel tabDistance = null;
	private JPanel tabDistance_1;
	private JPanel tabPace = null;
	private JPanel tabPace_1;
	private JPanel tabTime = null;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField txtTimeMinutes;
	private JTextField txtTimeSeconds;
	private JTextField txtTimeHours;


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
		addTabContentPace();
//		addTabContentTime();
	}
	
	private void addTabs(){
		tabDistance_1 = new JPanel();
		tabbedPane.addTab("Distance", tabDistance_1);

		tabPace_1 = new JPanel();
		tabbedPane.addTab("Pace", tabPace_1);

		tabTime = new JPanel();
		tabbedPane.addTab("Time", tabTime);
	}
	
	private void addTabContentDistance(){
		tabDistance_1.setLayout(new MigLayout("", "[grow][396px,grow]", "[90px,grow][28px,grow]"));
		JPanel tabDistanceInputs = new JPanel();
		tabDistanceInputs.setLayout(new MigLayout("", "[216px]", "[80px]"));
		
		//////////////
		JPanel panelPace = new JPanel();
		fillModulePace(panelPace);
		tabDistanceInputs.add(panelPace, "cell 0 0,alignx left,aligny top");
		tabDistance_1.add(tabDistanceInputs, "cell 0 0,alignx left,aligny top");
		
		JPanel panelAnswer = new JPanel();
		panelAnswer.setBorder(new TitledBorder(null, "JPanel title", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tabDistance_1.add(panelAnswer, "cell 1 0 1 2,grow");
		panelAnswer.setLayout(new MigLayout("", "[396px,grow]", "[90px,grow][28px,grow]"));
		
				JPanel tabDistanceAnswer = new JPanel();
				panelAnswer.add(tabDistanceAnswer, "cell 0 0 1 2,alignx center,growy");
				tabDistanceAnswer.setLayout(new MigLayout("", "[74px][74px]", "[200px]"));
				tabDistanceAnswer.add(new JLabel("Distance"), "cell 0 0,grow");
				JTextField textField_2 = new JTextField();
				textField_2.setColumns(5);
				tabDistanceAnswer.add(textField_2, "cell 1 0,growx");
				
				JPanel panel = new JPanel();
				tabDistance_1.add(panel, "cell 0 1,grow");
				
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
				panel_1.setLayout(new MigLayout("", "[][grow][grow][grow]", "[][]"));
				
				JLabel lblMinutes_1 = new JLabel("minutes");
				panel_1.add(lblMinutes_1, "cell 2 0");
				
				JLabel lblSeconds_1 = new JLabel("seconds");
				panel_1.add(lblSeconds_1, "cell 3 0");
				
				JLabel lblTime = new JLabel("Time");
				panel_1.add(lblTime, "cell 0 1,alignx trailing");
				
				txtTimeHours = new JTextField();
				panel_1.add(txtTimeHours, "cell 1 1,growx");
				txtTimeHours.setColumns(5);
				
				txtTimeMinutes = new JTextField();
				panel_1.add(txtTimeMinutes, "cell 2 1,growx");
				txtTimeMinutes.setColumns(5);
				
				txtTimeSeconds = new JTextField();
				panel_1.add(txtTimeSeconds, "cell 3 1,growx");
				txtTimeSeconds.setColumns(5);
	}
	

	private void addTabContentPace(){
		tabPace_1.setLayout(new MigLayout("", "[148px][148px]", "[56px]"));
		JPanel tabPaceInputs = new JPanel();
		tabPaceInputs.setLayout(new GridLayout(2,2));
		tabPaceInputs.add(new JLabel("Distance"));
		
		JTextField textField = new JTextField();
		textField.setColumns(5);
		tabPaceInputs.add(textField);
		
		tabPaceInputs.add(new JLabel("Time"));
		JTextField textField_1 = new JTextField();
		textField_1.setColumns(5);
		tabPaceInputs.add(textField_1);
		tabPace_1.add(tabPaceInputs, "cell 0 0,alignx left,aligny top");

		JPanel tabPaceAnswer = new JPanel();
		tabPaceAnswer.setLayout(new GridLayout(1,2));
		tabPaceAnswer.add(new JLabel("Pace"));
		JTextField textField_2 = new JTextField();
		textField_2.setColumns(5);
		tabPaceAnswer.add(textField_2);
		tabPace_1.add(tabPaceAnswer, "cell 1 0,alignx left,aligny center");
		
		textField_3 = new JTextField();
		tabPaceAnswer.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		tabPaceAnswer.add(textField_4);
		textField_4.setColumns(10);
	}
	
	private void addModuleDistance(){
		
	}
	
	public void fillModulePace(JPanel panel) {
		panel.setLayout(new MigLayout("", "[][grow][grow]", "[][]"));
		
		JLabel lblMinutes = new JLabel("minutes");
		panel.add(lblMinutes, "cell 1 0");
		
		JLabel lblSeconds = new JLabel("seconds");
		panel.add(lblSeconds, "cell 2 0");
		
		JLabel lblModulename = new JLabel("Pace");
		panel.add(lblModulename, "cell 0 1,alignx trailing");
		
		JTextField txtMinutes = new JTextField();
		panel.add(txtMinutes, "cell 1 1");
		txtMinutes.setColumns(5);
		
		JTextField txtSeconds = new JTextField();
		panel.add(txtSeconds, "cell 2 1,growx");
		txtSeconds.setColumns(5);
	}
}
