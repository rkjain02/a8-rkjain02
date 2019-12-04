package a8;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeListener;

public class ConwayGameView extends JFrame{

	private JSpotBoard _board;
	private JPanel top_panel;
	private JLabel _message;
	private JSlider sizeOfField;
	private JButton clearButton;
	private JButton play;
	private JButton randomize;
	private JLabel surviveThresholdMinText = new JLabel("Survive Threshold Minimum:");
	private JLabel surviveThresholdMaxText = new JLabel("Survive Threshold Maximum:");
	private JLabel birthThresholdMinText = new JLabel("Birth Threshold Minimum:");
	private JLabel birthThresholdMaxText = new JLabel("Birth Threshold Maximum:");
	private JTextField surviveThresholdMin;
	private JTextField surviveThresholdMax;
	private JTextField birthThresholdMin;
	private JTextField birthThresholdMax;
	private JButton torusOn;
	private JButton torusOff;
	
	public ConwayGameView() {
		
		this.setTitle("Conway Game");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		top_panel = new JPanel();
		top_panel.setLayout(new BorderLayout());
		this.setContentPane(top_panel);
		
		sizeOfField = new JSlider(JSlider.VERTICAL, 10, 500, 10);
		sizeOfField.setMajorTickSpacing(70);
		sizeOfField.setMinorTickSpacing(35);
		sizeOfField.setPaintTicks(true);
		sizeOfField.setPaintLabels(true);
		
		surviveThresholdMin = new JTextField(10);
		surviveThresholdMax = new JTextField(10);
		birthThresholdMin = new JTextField(10);
		birthThresholdMax = new JTextField(10);
		
		_message = new JLabel("Welcome to Conway Game of Life.");
		
		_board = new JSpotBoard(sizeOfField.getValue(), sizeOfField.getValue());
		
		for(Spot s : _board) {
			s.setBackground(Color.WHITE); 
			s.setSpotColor(Color.BLUE);
		}
		
		top_panel.setLayout(new BorderLayout());
		
		JPanel sliderField = new JPanel();
		sliderField.setLayout(new BorderLayout());
		
		JPanel torusField = new JPanel();
		torusField.setLayout(new BorderLayout());
		
		JPanel leftField = new JPanel();
		leftField.setLayout(new BorderLayout());
		
		JPanel buttonField = new JPanel();
		buttonField.setLayout(new BorderLayout());
		
		JPanel surviveMinField = new JPanel();
		surviveMinField.setLayout(new BorderLayout());
		
		JPanel surviveMaxField = new JPanel();
		surviveMaxField.setLayout(new BorderLayout());
		
		JPanel surviveField = new JPanel();
		surviveField.setLayout(new BorderLayout());
			
		JPanel birthMinField = new JPanel();
		birthMinField.setLayout(new BorderLayout());
		
		JPanel birthMaxField = new JPanel();
		birthMaxField.setLayout(new BorderLayout());
		
		JPanel birthField = new JPanel();
		birthField.setLayout(new BorderLayout());
		
		JPanel textField = new JPanel();
		textField.setLayout(new BorderLayout());
		
		clearButton = new JButton("Clear");
		
		play = new JButton("Play");
		
		randomize = new JButton("Randomize");
		
		torusOn = new JButton("Torus Mode On");
		torusOff = new JButton("Torus Mode Off");
		
		ButtonGroup torus = new ButtonGroup();
		torus.add(torusOn);
		torus.add(torusOff);
		
		buttonField.add(play, BorderLayout.CENTER);
		buttonField.add(clearButton, BorderLayout.PAGE_END);
		buttonField.add(randomize, BorderLayout.PAGE_START);
		
		sliderField.add(_message, BorderLayout.SOUTH);
		sliderField.add(sizeOfField, BorderLayout.NORTH);
		
		torusField.add(torusOn, BorderLayout.NORTH);
		torusField.add(torusOff, BorderLayout.SOUTH);
		
		leftField.add(sliderField, BorderLayout.NORTH);
		leftField.add(torusField, BorderLayout.SOUTH);
		
		surviveMinField.add(surviveThresholdMinText, BorderLayout.WEST);
		surviveMinField.add(surviveThresholdMin, BorderLayout.EAST);
		
		surviveMaxField.add(surviveThresholdMaxText, BorderLayout.WEST);
		surviveMaxField.add(surviveThresholdMax, BorderLayout.EAST);
		
		surviveField.add(surviveMinField, BorderLayout.WEST);
		surviveField.add(surviveMaxField, BorderLayout.EAST);
		
		birthMinField.add(birthThresholdMinText, BorderLayout.WEST);
		birthMinField.add(birthThresholdMin, BorderLayout.EAST);
		
		birthMaxField.add(birthThresholdMaxText, BorderLayout.WEST);
		birthMaxField.add(birthThresholdMax, BorderLayout.EAST);
		
		birthField.add(birthMinField, BorderLayout.WEST);
		birthField.add(birthMaxField, BorderLayout.EAST);
		
		textField.add(surviveField, BorderLayout.WEST);
		textField.add(birthField, BorderLayout.EAST);
		
		top_panel.add(leftField, BorderLayout.EAST);
		
		top_panel.add(buttonField, BorderLayout.WEST);
		top_panel.add(textField, BorderLayout.SOUTH);
		
		top_panel.add(_board, BorderLayout.CENTER);
		
	}

	public JSpotBoard getBoard() {
		return _board;
	}
	public JPanel getPanel() {
		return top_panel;
	}
	public JLabel getMessage() {
		return _message;
	}
	public JButton getClearButton() {
		return clearButton;
	}
	public JButton getPlayButton() {
		return play;
	}
	public JButton getRandomizeButton() {
		return randomize;
	}
	public JButton getTorusOnButton() {
		return torusOn;
	}
	public JButton getTorusOffButton() {
		return torusOff;
	}
 	
	public void setDimensionsOfBoard(int dimensions) {
		_board = new JSpotBoard(dimensions, dimensions);
	}
	
 	public int getSurviveThresholdMin() {
 		return Integer.parseInt(surviveThresholdMin.getText());
 	}
 	
 	public int getSurviveThresholdMax() {
 		return Integer.parseInt(surviveThresholdMax.getText());
 	}
 	public int getBirthThresholdMin() {
 		return Integer.parseInt(birthThresholdMin.getText());
 	}
 	public int getBirthThresholdMax() {
 		return Integer.parseInt(birthThresholdMax.getText());
 	}
 	
 	public void setSurviveThresholdMax(String max) {
		surviveThresholdMax.setText(max);
	}
	
	public void setBirthThresholdMax(String max) {
		birthThresholdMax.setText(max);
	}
	
	public void setSurviveThresholdMin(String min) {
		surviveThresholdMin.setText(min);
	}
	
	public void setBirthThresholdMin(String min) {
		birthThresholdMin.setText(min);
	}
	
	public void addSliderListener(ChangeListener listener) {
		sizeOfField.addChangeListener(listener);
	}
	public void addClearListener(ActionListener listener) {
		clearButton.addActionListener(listener);
	}
	public void addPlayListener(ActionListener listener) {
		play.addActionListener(listener);
	}
	public void addRandomizeListener(ActionListener listener) {
		randomize.addActionListener(listener);
	}
	public void addBoardListener(SpotListener listener) {
		_board.addSpotListener(listener);
	}
	public void addTorusOnListener(ActionListener listener) {
		torusOn.addActionListener(listener);
	}
	public void addTorusOffListener(ActionListener listener) {
		torusOff.addActionListener(listener);
	}
}
