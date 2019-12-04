package a8;

public class ConwayGameModel {
	
	private int dimensionsOfField;
	
	private JSpotBoard _board;
	
	private int surviveThresholdMax;
	
	private int surviveThresholdMin;
	
	private int birthThresholdMin;
	
	private int birthThresholdMax;
	
	private boolean torusMode;
	
	private int torusCount;
	
	public JSpotBoard torusBoard;
	
	public ConwayGameModel() {
		torusCount = 0;
	}
	
	public JSpotBoard getBoard() {
		return _board;
	}
	
	public int getDimensionsOfField() {
		return dimensionsOfField;
	}
	
	public void setDimensionsOfField(int dimensions) {
		_board = new JSpotBoard(dimensions, dimensions);
	}
	
	public int getSurviveThresholdMax() {
		return surviveThresholdMax;
	}
	
	public int getBirthThresholdMax() {
		return birthThresholdMax;
	}
	
	public int getSurviveThresholdMin() {
		return surviveThresholdMin;
	}
	
	public int getBirthThresholdMin() {
		return birthThresholdMin;
	}
	
	public void setSurviveThresholdMax(int max) {
		surviveThresholdMax = max;
	}
	
	public void setBirthThresholdMax(int max) {
		birthThresholdMax = max;
	}
	
	public void setSurviveThresholdMin(int min) {
		surviveThresholdMin = min;
	}
	
	public void setBirthThresholdMin(int min) {
		birthThresholdMin = min;
	}
	
	public int getTorusCount() {
		return torusCount;
	}
	
	public void setTorusCount(int newCount) {
		torusCount = newCount;
	}
	
	public boolean getTorusMode() {
		return torusMode;
	}
	
	public void setTorusMode(boolean mode) {
		torusMode = mode;
	}
	
}
