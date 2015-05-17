package application.input;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import application.grid.Cell;
import application.grid.CellType;

/**
 * Change the cell type in the cells while you keep the button pressed.
 * Primary button: add blocks
 * Secondary button: remove blocks
 */
public class MousePaintGestures {

	public void makePaintable( Node node) {
		node.setOnMousePressed( onMousePressedEventHandler);
		node.setOnDragDetected( onDragDetectedEventHandler);
		node.setOnMouseDragEntered(onMouseDragEnteredEventHandler);
	}
	
	EventHandler<MouseEvent> onMousePressedEventHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {

			setType( event);

		}
	};

	EventHandler<MouseEvent> onDragDetectedEventHandler = event -> {

		// start full press-drag-release gesture
		Cell cell = (Cell) event.getSource();
		cell.startFullDrag();

	};
	
	EventHandler<MouseEvent> onMouseDragEnteredEventHandler = event -> {

		// keep on painting while mouse is being dragged
		setType( event);
			
	};
	
	private void setType( MouseEvent event) {

		CellType type = null;
		if( event.isPrimaryButtonDown()) {
			type = CellType.OBSTACLE;
		} else if( event.isSecondaryButtonDown()) {
			type = CellType.TRAVERSABLE;
		} else {
			// no action on unhandled mouse buttons
			return;
		}
	
		Cell cell = (Cell) event.getSource();
		cell.setType(type);
		
		// change existing path highlight
		cell.removeHighlight();

	}

}
