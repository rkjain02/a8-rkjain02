package a8;

public class ConwayGame {

	public static void main(String[] args) {
		
		ConwayGameView theView = new ConwayGameView();
		ConwayGameModel theModel = new ConwayGameModel();
		ConwayGameController theController = new ConwayGameController(theView, theModel);
		theView.setSurviveThresholdMin("2");
		theView.setSurviveThresholdMax("3");
		theView.setBirthThresholdMin("3");
		theView.setBirthThresholdMax("3");
		
		theView.pack();
		theView.setVisible(true);	
	}
}
