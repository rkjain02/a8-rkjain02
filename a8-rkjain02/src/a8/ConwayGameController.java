package a8;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ConwayGameController {

	private ConwayGameView theView;
	private ConwayGameModel theModel;
	
	public ConwayGameController(ConwayGameView theView, ConwayGameModel theModel) {
		
		this.theView = theView;
		this.theModel = theModel;
		
		this.theView.addSliderListener(new ConwayListener());
		this.theView.addClearListener(new ConwayListener());
		this.theView.addPlayListener(new ConwayListener());
		this.theView.addRandomizeListener(new ConwayListener());
		this.theView.addBoardListener(new ConwayListener());
		this.theView.addTorusOnListener(new ConwayListener());
		this.theView.addTorusOffListener(new ConwayListener());
	}
	
	class ConwayListener implements ActionListener, ChangeListener, SpotListener {

		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			if(button.equals(theView.getClearButton())) {
				clearGame();
			}
			else if(button.equals(theView.getPlayButton())) {
				playGame();
			}
			else if(button.equals(theView.getRandomizeButton())){
				randomizeGame();
			}
			else if(button.equals(theView.getTorusOnButton())) {
				theModel.setTorusMode(true);
			}
			else if(button.equals(theView.getTorusOffButton())) {
				theModel.setTorusMode(false);
			}
			
		}

		public void stateChanged(ChangeEvent e) {
			JSlider source = (JSlider) e.getSource();
		    if (!source.getValueIsAdjusting()) {
		    	
		    	theView.getPanel().remove(theView.getBoard());
		    	int sizeOfField = (int)source.getValue();
		    	theView.setDimensionsOfBoard(sizeOfField);
		        
		        for(Spot s : theView.getBoard()) {
					s.setBackground(Color.WHITE);
					s.setSpotColor(Color.BLUE);
				}
		        
		        theView.getPanel().add(theView.getBoard(), BorderLayout.CENTER);
		        theView.getPanel().updateUI();
		        
		        theView.getBoard().addSpotListener(this);
		    }
			
		}
		
		private void clearGame() {
			for (Spot s : theView.getBoard()) {
				s.clearSpot();
				s.setSpotColor(Color.BLUE);
				s.setBackground(Color.WHITE);
			}
			theView.getPanel().add(theView.getBoard(), BorderLayout.CENTER);
			theView.getMessage().setText("Welcome to Conway Game of Life.");
			theView.getPanel().updateUI();
		    
		    theModel.setTorusMode(false);
		    theModel.setTorusCount(0);
		    
		    theView.setSurviveThresholdMin("2");
		    theView.setSurviveThresholdMax("3");
		    theView.setBirthThresholdMin("3");
		    theView.setBirthThresholdMax("3");
		    
		}
		
		private void playGame() {

			if(!theModel.getTorusMode()) {
				boolean colorChange[][] = new boolean[theView.getBoard().getSpotWidth()][theView.getBoard().getSpotHeight()];
				for(Spot s : theView.getBoard()) {
					int liveCells = 0;
					int deadCells = 0;
					
					if(s.getSpotX() == 0 && s.getSpotY() == 0) {
						if(theView.getBoard().getSpotAt(s.getSpotX(), s.getSpotY()+1).getBackground().equals(Color.BLACK) ) {
							liveCells++;
						}
						else {
							deadCells++;
						}
						if(theView.getBoard().getSpotAt(s.getSpotX()+1, s.getSpotY()+1).getBackground().equals(Color.BLACK) ) {
							liveCells++;
						}
						else {
							deadCells++;
						}
						if(theView.getBoard().getSpotAt(s.getSpotX()+1, s.getSpotY()).getBackground().equals(Color.BLACK) ) {
							liveCells++;
						}
						else {
							deadCells++;
						}
					}
					
					else if(s.getSpotX() == 0 && s.getSpotY() == theView.getBoard().getSpotHeight()-1) {
						if(theView.getBoard().getSpotAt(s.getSpotX(), s.getSpotY()-1).getBackground().equals(Color.BLACK) ) {
							liveCells++;
						}
						else {
							deadCells++;
						}
						if(theView.getBoard().getSpotAt(s.getSpotX()+1, s.getSpotY()).getBackground().equals(Color.BLACK) ) {
							liveCells++;
						}
						else {
							deadCells++;
						}
						if(theView.getBoard().getSpotAt(s.getSpotX()+1, s.getSpotY()-1).getBackground().equals(Color.BLACK) ) {
							liveCells++;
						}
						else {
							deadCells++;
						}
					}
					
					else if(s.getSpotY() == 0 && s.getSpotX() == theView.getBoard().getSpotWidth()-1) {
						if(theView.getBoard().getSpotAt(s.getSpotX()-1, s.getSpotY()).getBackground().equals(Color.BLACK) ) {
							liveCells++;
						}
						else {
							deadCells++;
						}
						if(theView.getBoard().getSpotAt(s.getSpotX(), s.getSpotY()+1).getBackground().equals(Color.BLACK) ) {
							liveCells++;
						}
						else {
							deadCells++;
						}
						if(theView.getBoard().getSpotAt(s.getSpotX()-1, s.getSpotY()+1).getBackground().equals(Color.BLACK) ) {
							liveCells++;
						}
						else {
							deadCells++;
						}
					}
					
					else if(s.getSpotY() == theView.getBoard().getSpotHeight()-1 && s.getSpotX() == theView.getBoard().getSpotWidth()-1) {
						if(theView.getBoard().getSpotAt(s.getSpotX()-1, s.getSpotY()).getBackground().equals(Color.BLACK) ) {
							liveCells++;
						}
						else {
							deadCells++;
						}
						if(theView.getBoard().getSpotAt(s.getSpotX(), s.getSpotY()-1).getBackground().equals(Color.BLACK) ) {
							liveCells++;
						}
						else {
							deadCells++;
						}
						if(theView.getBoard().getSpotAt(s.getSpotX()-1, s.getSpotY()-1).getBackground().equals(Color.BLACK) ) {
							liveCells++;
						}
						else {
							deadCells++;
						}
					}
					
					else if(s.getSpotX() == 0) {
						if(s.getSpotY() >=1 && s.getSpotY() <= theView.getBoard().getSpotHeight()-1) {
							if(theView.getBoard().getSpotAt(s.getSpotX()+1, s.getSpotY()).getBackground().equals(Color.BLACK) ) {
								liveCells++;
							}
							else {
								deadCells++;
							}
							if(theView.getBoard().getSpotAt(s.getSpotX()+1, s.getSpotY()-1).getBackground().equals(Color.BLACK) ) {
								liveCells++;
							}
							else {
								deadCells++;
							}
							if(theView.getBoard().getSpotAt(s.getSpotX(), s.getSpotY()+1).getBackground().equals(Color.BLACK) ) {
								liveCells++;
							}
							else {
								deadCells++;
							}
							if(theView.getBoard().getSpotAt(s.getSpotX(), s.getSpotY()-1).getBackground().equals(Color.BLACK) ) {
								liveCells++;
							}
							else {
								deadCells++;
							}
							if(theView.getBoard().getSpotAt(s.getSpotX()+1, s.getSpotY()+1).getBackground().equals(Color.BLACK) ) {
								liveCells++;
							}
							else {
								deadCells++;
							}
						}
					}
					
					else if(s.getSpotX() == theView.getBoard().getSpotWidth()-1) {
						if(s.getSpotY() >=1 && s.getSpotY() <=theView.getBoard().getSpotHeight()-1) {
							if(theView.getBoard().getSpotAt(s.getSpotX()-1, s.getSpotY()).getBackground().equals(Color.BLACK) ) {
								liveCells++;
							}
							else {
								deadCells++;
							}
							if(theView.getBoard().getSpotAt(s.getSpotX()-1, s.getSpotY()+1).getBackground().equals(Color.BLACK) ) {
								liveCells++;
							}
							else {
								deadCells++;
							}
							if(theView.getBoard().getSpotAt(s.getSpotX()-1, s.getSpotY()-1).getBackground().equals(Color.BLACK) ) {
								liveCells++;
							}
							else {
								deadCells++;
							}
							if(theView.getBoard().getSpotAt(s.getSpotX(), s.getSpotY()+1).getBackground().equals(Color.BLACK) ) {
								liveCells++;
							}
							else {
								deadCells++;
							}
							if(theView.getBoard().getSpotAt(s.getSpotX(), s.getSpotY()-1).getBackground().equals(Color.BLACK) ) {
								liveCells++;
							}
							else {
								deadCells++;
							}
						}
					}
					
					else if(s.getSpotY() == 0) {
						if(s.getSpotX() >=1 && s.getSpotX() <= theView.getBoard().getSpotWidth()-1) {
							if(theView.getBoard().getSpotAt(s.getSpotX(), s.getSpotY()+1).getBackground().equals(Color.BLACK) ) {
								liveCells++;
							}
							else {
								deadCells++;
							}
							if(theView.getBoard().getSpotAt(s.getSpotX()-1, s.getSpotY()+1).getBackground().equals(Color.BLACK) ) {
								liveCells++;
							}
							else {
								deadCells++;
							}
							if(theView.getBoard().getSpotAt(s.getSpotX()+1, s.getSpotY()+1).getBackground().equals(Color.BLACK) ) {
								liveCells++;
							}
							else {
								deadCells++;
							}
							if(theView.getBoard().getSpotAt(s.getSpotX()-1, s.getSpotY()).getBackground().equals(Color.BLACK) ) {
								liveCells++;
							}
							else {
								deadCells++;
							}
							if(theView.getBoard().getSpotAt(s.getSpotX()+1, s.getSpotY()).getBackground().equals(Color.BLACK) ) {
								liveCells++;
							}
							else {
								deadCells++;
							}
						}
					}
					
					else if(s.getSpotY() == theView.getBoard().getSpotHeight()-1) {
						if(s.getSpotX() >=1 && s.getSpotX() <= theView.getBoard().getSpotWidth()-1) {
							if(theView.getBoard().getSpotAt(s.getSpotX(), s.getSpotY()-1).getBackground().equals(Color.BLACK) ) {
								liveCells++;
							}
							else {
								deadCells++;
							}
							if(theView.getBoard().getSpotAt(s.getSpotX()+1, s.getSpotY()-1).getBackground().equals(Color.BLACK) ) {
								liveCells++;
							}
							else {
								deadCells++;
							}
							if(theView.getBoard().getSpotAt(s.getSpotX()-1, s.getSpotY()-1).getBackground().equals(Color.BLACK) ) {
								liveCells++;
							}
							else {
								deadCells++;
							}
							if(theView.getBoard().getSpotAt(s.getSpotX()+1, s.getSpotY()).getBackground().equals(Color.BLACK) ) {
								liveCells++;
							}
							else {
								deadCells++;
							}
							if(theView.getBoard().getSpotAt(s.getSpotX()-1, s.getSpotY()).getBackground().equals(Color.BLACK) ) {
								liveCells++;
							}
							else {
								deadCells++;
							}
						}
					}
					
					else {
						if(theView.getBoard().getSpotAt(s.getSpotX()-1, s.getSpotY()-1).getBackground().equals(Color.BLACK)) {
							liveCells++;
						}
						else {
							deadCells++;
						}
						if(theView.getBoard().getSpotAt(s.getSpotX()-1, s.getSpotY()).getBackground().equals(Color.BLACK)) {
							liveCells++;
						}
						else {
							deadCells++;
						}
						if(theView.getBoard().getSpotAt(s.getSpotX()-1, s.getSpotY()+1).getBackground().equals(Color.BLACK)) {
							liveCells++;
						}
						else {
							deadCells++;
						}
						if(theView.getBoard().getSpotAt(s.getSpotX(), s.getSpotY()+1).getBackground().equals(Color.BLACK)) {
							liveCells++;
						}
						else {
							deadCells++;
						}
						if(theView.getBoard().getSpotAt(s.getSpotX()+1, s.getSpotY()+1).getBackground().equals(Color.BLACK)) {
							liveCells++;
						}
						else {
							deadCells++;
						}
						if(theView.getBoard().getSpotAt(s.getSpotX()+1, s.getSpotY()).getBackground().equals(Color.BLACK)) {
							liveCells++;
						}
						else {
							deadCells++;
						}
						if(theView.getBoard().getSpotAt(s.getSpotX()+1, s.getSpotY()-1).getBackground().equals(Color.BLACK)) {
							liveCells++;
						}
						else {
							deadCells++;
						}
						if(theView.getBoard().getSpotAt(s.getSpotX(), s.getSpotY()-1).getBackground().equals(Color.BLACK)) {
							liveCells++;
						}
						else {
							deadCells++;
						}
					}
					
					if(s.getBackground().equals(Color.BLACK)) {
						if(liveCells >= theView.getSurviveThresholdMin() && liveCells <= theView.getSurviveThresholdMax()) {
							colorChange[s.getSpotX()][s.getSpotY()] = true;
						}
						else {
							colorChange[s.getSpotX()][s.getSpotY()] = false;
						}
					}
					else {
						if(liveCells >= theView.getBirthThresholdMin() && liveCells <= theView.getBirthThresholdMax()) {
							colorChange[s.getSpotX()][s.getSpotY()] = true;
						}
					}
				}
				
				for(Spot s : theView.getBoard()) {
					if(colorChange[s.getSpotX()][s.getSpotY()]) {
						s.setBackground(Color.BLACK);
					}
					else {
						s.setBackground(Color.WHITE);
					}
				}
			}
			else {
				
				if(theModel.getTorusCount() == 0) {
					
					theModel.torusBoard = new JSpotBoard(4*theView.getBoard().getSpotWidth(), theView.getBoard().getSpotHeight());
					
					for(Spot s : theModel.torusBoard) {
						s.setBackground(Color.WHITE);
						
					}
					for(int i=0; i<theView.getBoard().getSpotWidth(); i++) {
						for(int j=0; j<theView.getBoard().getSpotHeight(); j++) {
							theModel.torusBoard.getSpotAt(i, j).setBackground(theView.getBoard().getSpotAt(i, j).getBackground());
						}
					}
				}
				theModel.setTorusCount(1);
				
				boolean colorChange[][] = new boolean[theModel.torusBoard.getSpotWidth()][theModel.torusBoard.getSpotHeight()];
				
				for(Spot s : theModel.torusBoard) {
					int liveCells = 0;
					int deadCells = 0;
					
					if(s.getSpotX() == 0 && s.getSpotY() == 0) {
						if(theModel.torusBoard.getSpotAt(s.getSpotX(), s.getSpotY()+1).getBackground().equals(Color.BLACK) ) {
							liveCells++;
						}
						else {
							deadCells++;
						}
						if(theModel.torusBoard.getSpotAt(s.getSpotX()+1, s.getSpotY()+1).getBackground().equals(Color.BLACK) ) {
							liveCells++;
						}
						else {
							deadCells++;
						}
						if(theModel.torusBoard.getSpotAt(s.getSpotX()+1, s.getSpotY()).getBackground().equals(Color.BLACK) ) {
							liveCells++;
						}
						else {
							deadCells++;
						}
					}
					
					else if(s.getSpotX() == 0 && s.getSpotY() == theModel.torusBoard.getSpotHeight()-1) {
						if(theModel.torusBoard.getSpotAt(s.getSpotX(), s.getSpotY()-1).getBackground().equals(Color.BLACK) ) {
							liveCells++;
						}
						else {
							deadCells++;
						}
						if(theModel.torusBoard.getSpotAt(s.getSpotX()+1, s.getSpotY()).getBackground().equals(Color.BLACK) ) {
							liveCells++;
						}
						else {
							deadCells++;
						}
						if(theModel.torusBoard.getSpotAt(s.getSpotX()+1, s.getSpotY()-1).getBackground().equals(Color.BLACK) ) {
							liveCells++;
						}
						else {
							deadCells++;
						}
					}
					
					else if(s.getSpotY() == 0 && s.getSpotX() == theModel.torusBoard.getSpotWidth()-1) {
						if(theModel.torusBoard.getSpotAt(s.getSpotX()-1, s.getSpotY()).getBackground().equals(Color.BLACK) ) {
							liveCells++;
						}
						else {
							deadCells++;
						}
						if(theModel.torusBoard.getSpotAt(s.getSpotX(), s.getSpotY()+1).getBackground().equals(Color.BLACK) ) {
							liveCells++;
						}
						else {
							deadCells++;
						}
						if(theModel.torusBoard.getSpotAt(s.getSpotX()-1, s.getSpotY()+1).getBackground().equals(Color.BLACK) ) {
							liveCells++;
						}
						else {
							deadCells++;
						}
					}
					
					else if(s.getSpotY() == theModel.torusBoard.getSpotHeight()-1 && s.getSpotX() == theModel.torusBoard.getSpotWidth()-1) {
						if(theModel.torusBoard.getSpotAt(s.getSpotX()-1, s.getSpotY()).getBackground().equals(Color.BLACK) ) {
							liveCells++;
						}
						else {
							deadCells++;
						}
						if(theModel.torusBoard.getSpotAt(s.getSpotX(), s.getSpotY()-1).getBackground().equals(Color.BLACK) ) {
							liveCells++;
						}
						else {
							deadCells++;
						}
						if(theModel.torusBoard.getSpotAt(s.getSpotX()-1, s.getSpotY()-1).getBackground().equals(Color.BLACK) ) {
							liveCells++;
						}
						else {
							deadCells++;
						}
					}
					
					else if(s.getSpotX() == 0) {
						if(s.getSpotY() >=1 && s.getSpotY() <= theModel.torusBoard.getSpotHeight()-1) {
							if(theModel.torusBoard.getSpotAt(s.getSpotX()+1, s.getSpotY()).getBackground().equals(Color.BLACK) ) {
								liveCells++;
							}
							else {
								deadCells++;
							}
							if(theModel.torusBoard.getSpotAt(s.getSpotX()+1, s.getSpotY()-1).getBackground().equals(Color.BLACK) ) {
								liveCells++;
							}
							else {
								deadCells++;
							}
							if(theModel.torusBoard.getSpotAt(s.getSpotX(), s.getSpotY()+1).getBackground().equals(Color.BLACK) ) {
								liveCells++;
							}
							else {
								deadCells++;
							}
							if(theModel.torusBoard.getSpotAt(s.getSpotX(), s.getSpotY()-1).getBackground().equals(Color.BLACK) ) {
								liveCells++;
							}
							else {
								deadCells++;
							}
							if(theModel.torusBoard.getSpotAt(s.getSpotX()+1, s.getSpotY()+1).getBackground().equals(Color.BLACK) ) {
								liveCells++;
							}
							else {
								deadCells++;
							}
						}
					}
					
					else if(s.getSpotX() == theModel.torusBoard.getSpotWidth()-1) {
						if(s.getSpotY() >=1 && s.getSpotY() <=theModel.torusBoard.getSpotHeight()-1) {
							if(theModel.torusBoard.getSpotAt(s.getSpotX()-1, s.getSpotY()).getBackground().equals(Color.BLACK) ) {
								liveCells++;
							}
							else {
								deadCells++;
							}
							if(theModel.torusBoard.getSpotAt(s.getSpotX()-1, s.getSpotY()+1).getBackground().equals(Color.BLACK) ) {
								liveCells++;
							}
							else {
								deadCells++;
							}
							if(theModel.torusBoard.getSpotAt(s.getSpotX()-1, s.getSpotY()-1).getBackground().equals(Color.BLACK) ) {
								liveCells++;
							}
							else {
								deadCells++;
							}
							if(theModel.torusBoard.getSpotAt(s.getSpotX(), s.getSpotY()+1).getBackground().equals(Color.BLACK) ) {
								liveCells++;
							}
							else {
								deadCells++;
							}
							if(theModel.torusBoard.getSpotAt(s.getSpotX(), s.getSpotY()-1).getBackground().equals(Color.BLACK) ) {
								liveCells++;
							}
							else {
								deadCells++;
							}
						}
					}
					
					else if(s.getSpotY() == 0) {
						if(s.getSpotX() >=1 && s.getSpotX() <= theModel.torusBoard.getSpotWidth()-1) {
							if(theModel.torusBoard.getSpotAt(s.getSpotX(), s.getSpotY()+1).getBackground().equals(Color.BLACK) ) {
								liveCells++;
							}
							else {
								deadCells++;
							}
							if(theModel.torusBoard.getSpotAt(s.getSpotX()-1, s.getSpotY()+1).getBackground().equals(Color.BLACK) ) {
								liveCells++;
							}
							else {
								deadCells++;
							}
							if(theModel.torusBoard.getSpotAt(s.getSpotX()+1, s.getSpotY()+1).getBackground().equals(Color.BLACK) ) {
								liveCells++;
							}
							else {
								deadCells++;
							}
							if(theModel.torusBoard.getSpotAt(s.getSpotX()-1, s.getSpotY()).getBackground().equals(Color.BLACK) ) {
								liveCells++;
							}
							else {
								deadCells++;
							}
							if(theModel.torusBoard.getSpotAt(s.getSpotX()+1, s.getSpotY()).getBackground().equals(Color.BLACK) ) {
								liveCells++;
							}
							else {
								deadCells++;
							}
						}
					}
					
					else if(s.getSpotY() == theModel.torusBoard.getSpotHeight()-1) {
						if(s.getSpotX() >=1 && s.getSpotX() <= theModel.torusBoard.getSpotWidth()-1) {
							if(theModel.torusBoard.getSpotAt(s.getSpotX(), s.getSpotY()-1).getBackground().equals(Color.BLACK) ) {
								liveCells++;
							}
							else {
								deadCells++;
							}
							if(theModel.torusBoard.getSpotAt(s.getSpotX()+1, s.getSpotY()-1).getBackground().equals(Color.BLACK) ) {
								liveCells++;
							}
							else {
								deadCells++;
							}
							if(theModel.torusBoard.getSpotAt(s.getSpotX()-1, s.getSpotY()-1).getBackground().equals(Color.BLACK) ) {
								liveCells++;
							}
							else {
								deadCells++;
							}
							if(theModel.torusBoard.getSpotAt(s.getSpotX()+1, s.getSpotY()).getBackground().equals(Color.BLACK) ) {
								liveCells++;
							}
							else {
								deadCells++;
							}
							if(theModel.torusBoard.getSpotAt(s.getSpotX()-1, s.getSpotY()).getBackground().equals(Color.BLACK) ) {
								liveCells++;
							}
							else {
								deadCells++;
							}
						}
					}
			 		
					else {
						if(theModel.torusBoard.getSpotAt(s.getSpotX()-1, s.getSpotY()-1).getBackground().equals(Color.BLACK)) {
							liveCells++;
						}
						else {
							deadCells++;
						}
						if(theModel.torusBoard.getSpotAt(s.getSpotX()-1, s.getSpotY()).getBackground().equals(Color.BLACK)) {
							liveCells++;
						}
						else {
							deadCells++;
						}
						if(theModel.torusBoard.getSpotAt(s.getSpotX()-1, s.getSpotY()+1).getBackground().equals(Color.BLACK)) {
							liveCells++;
						}
						else {
							deadCells++;
						}
						if(theModel.torusBoard.getSpotAt(s.getSpotX(), s.getSpotY()+1).getBackground().equals(Color.BLACK)) {
							liveCells++;
						}
						else {
							deadCells++;
						}
						if(theModel.torusBoard.getSpotAt(s.getSpotX()+1, s.getSpotY()+1).getBackground().equals(Color.BLACK)) {
							liveCells++;
						}
						else {
							deadCells++;
						}
						if(theModel.torusBoard.getSpotAt(s.getSpotX()+1, s.getSpotY()).getBackground().equals(Color.BLACK)) {
							liveCells++;
						}
						else {
							deadCells++;
						}
						if(theModel.torusBoard.getSpotAt(s.getSpotX()+1, s.getSpotY()-1).getBackground().equals(Color.BLACK)) {
							liveCells++;
						}
						else {
							deadCells++;
						}
						if(theModel.torusBoard.getSpotAt(s.getSpotX(), s.getSpotY()-1).getBackground().equals(Color.BLACK)) {
							liveCells++;
						}
						else {
							deadCells++;
						}
					}
					
					if(s.getBackground().equals(Color.BLACK)) {
						if(liveCells >= theView.getSurviveThresholdMin() && liveCells <= theView.getSurviveThresholdMax()) {
							colorChange[s.getSpotX()][s.getSpotY()] = true;
						}
						else {
							colorChange[s.getSpotX()][s.getSpotY()] = false;
						}
					}
					else {
						if(liveCells >= theView.getBirthThresholdMin() && liveCells <= theView.getBirthThresholdMax()) {
							colorChange[s.getSpotX()][s.getSpotY()] = true;
						}
					}
				}
				
				for(Spot s : theModel.torusBoard) {
					if(colorChange[s.getSpotX()][s.getSpotY()]) {
						s.setBackground(Color.BLACK);
					}
					else {
						s.setBackground(Color.WHITE);
					}
				}
				
				boolean torusWrap = false;
				int lastWrapColumn = 0;
				
				for(int i=theView.getBoard().getSpotWidth(); i<theModel.torusBoard.getSpotWidth(); i++) {
					for(int j=0; j<theModel.torusBoard.getSpotHeight(); j++) {
						if(theModel.torusBoard.getSpotAt(i,j).getBackground().equals(Color.BLACK)) {
							lastWrapColumn = i;
							torusWrap = true;
						}
					}
				}
				
				boolean torusSecondWrap = false;
				int secondLastWrapColumn = 0;
				
				for(int i=2*theView.getBoard().getSpotWidth(); i<theModel.torusBoard.getSpotWidth(); i++) {
					for(int j=0; j<theModel.torusBoard.getSpotHeight(); j++) {
						if(theModel.torusBoard.getSpotAt(i, j).getBackground().equals(Color.BLACK)) {
							secondLastWrapColumn = i;
							torusSecondWrap = true;
						}
					}
				}
				
				boolean torusThirdWrap = false;
				int thirdLastWrapColumn = 0;
				
				for(int i=3*theView.getBoard().getSpotWidth(); i<theModel.torusBoard.getSpotWidth(); i++) {
					for(int j=0; j<theModel.torusBoard.getSpotHeight(); j++) {
						if(theModel.torusBoard.getSpotAt(i, j).getBackground().equals(Color.BLACK)) {
							thirdLastWrapColumn = i;
							torusThirdWrap = true;
						}
					}
				}
				
				
				
				for(int i=0; i<theView.getBoard().getSpotWidth(); i++) {
					for(int j=0; j<theView.getBoard().getSpotHeight(); j++) {
						theView.getBoard().getSpotAt(i, j).setBackground(theModel.torusBoard.getSpotAt(i, j).getBackground());		
					}
				}
	
	
				if(torusThirdWrap) {
					for(int i=3*theView.getBoard().getSpotWidth(); i<=thirdLastWrapColumn; i++) {
						for(int j=0; j<theModel.torusBoard.getSpotHeight(); j++) {
							theView.getBoard().getSpotAt(i-3*theView.getBoard().getSpotWidth(), j).setBackground(theModel.torusBoard.getSpotAt(i, j).getBackground());
						}
					}
				}
				else if(torusSecondWrap) {
					for(int i=2*theView.getBoard().getSpotWidth(); i<=secondLastWrapColumn; i++) {
						for(int j=0; j<theModel.torusBoard.getSpotHeight(); j++) {
							theView.getBoard().getSpotAt(i-2*theView.getBoard().getSpotWidth(), j).setBackground(theModel.torusBoard.getSpotAt(i, j).getBackground());
						}
					}
				}
				else if (torusWrap) {
					for(int i=theView.getBoard().getSpotWidth(); i<=lastWrapColumn; i++) {
						for(int j=0; j<theView.getBoard().getSpotHeight(); j++) {
							theView.getBoard().getSpotAt((i-theView.getBoard().getSpotWidth()), j).setBackground(theModel.torusBoard.getSpotAt(i, j).getBackground());
							
						}
					}
				}	
			}		
				
		}	
		
		private void randomizeGame() {
			for(Spot s : theView.getBoard()) {
				if(Math.random() <=.5) {
					s.setBackground(Color.WHITE);
				}
				else {
					s.setBackground(Color.BLACK);
				}
			}
		}
		
		public void spotClicked(Spot spot) {
			if(spot.getBackground().equals(Color.WHITE)) {
				spot.setBackground(Color.BLACK);
			}
			else {
				spot.setBackground(Color.WHITE);
			}

		}

		@Override
		public void spotEntered(Spot spot) {
			spot.highlightSpot();

		}

		@Override
		public void spotExited(Spot spot) {
			spot.unhighlightSpot();
		}
	
	}
}
