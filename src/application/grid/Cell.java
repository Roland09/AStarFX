package application.grid;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * A cell on the grid.
 */
public class Cell extends StackPane {

	int column;
	int row;
	CellType type;
	CellMark mark;
	Label labelF;
	Label labelG;
	Label labelH;
	
	public Cell( String text, int column, int row, CellType type) {
		
		this.column = column;
		this.row = row;
		this.type = type;

		updateTypeStyle();

		labelF = new Label( text);
		labelF.getStyleClass().add( "label-f");

		AnchorPane anchorPane = new AnchorPane();

		labelG = new Label( "");
		labelG.getStyleClass().add( "label-g");
		AnchorPane.setTopAnchor(labelG, 5.);
		AnchorPane.setLeftAnchor(labelG, 5.);
		
		labelH = new Label( "");
		labelH.getStyleClass().add( "label-h");
		AnchorPane.setTopAnchor(labelH, 5.);
		AnchorPane.setRightAnchor(labelH, 5.);
		
		anchorPane.getChildren().addAll(labelG, labelH);
		
		getChildren().addAll( anchorPane, labelF);
	}

	public void highlight() {
		getStyleClass().add("path");
	}

	public void removeHighlight() {
		getStyleClass().remove("path");
	}
	
	public CellType getType() {
		return type;
	}

	public void setType( CellType type) {
		this.type = type;
		updateTypeStyle();
	}

	public void removeMark() {
		this.mark = null;
		updateMarkStyle();
	}

	public void setMark( CellMark mark) {
		this.mark = mark;
		updateMarkStyle();
	}
	
	public String toString() {
		return this.column + "/" + this.row;
	}

	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public boolean isTraversable()  {
		return type == CellType.TRAVERSABLE;
	}
	
	public void setTextF( String text) {
		labelF.setText(text);
	}

	public void setTextG( String text) {
		labelG.setText(text);
	}

	public void setTextH( String text) {
		labelH.setText(text);
	}

	/**
	 * Set styles depending on the cell type
	 */
	private void updateTypeStyle() {
		
		// remove existing styles
		getStyleClass().remove( "traversable");
		getStyleClass().remove( "obstacle");

		// set new styles
		switch( type) {
		case TRAVERSABLE:
			getStyleClass().add("traversable");
			break;
		case OBSTACLE:
			getStyleClass().add("obstacle");
			break;
		}
		
	}

	/**
	 * Set styles depending on the cell mark
	 */
	private void updateMarkStyle() {
		
		// remove existing styles
		getStyleClass().remove( "open");
		getStyleClass().remove( "closed");

		if( mark == null)
			return;
		
		// set new styles
		switch( mark) {
		case OPEN:
			getStyleClass().add("open");
			break;
		case CLOSED:
			getStyleClass().add("closed");
			break;
		}
		
	}
}
