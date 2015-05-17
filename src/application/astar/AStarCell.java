package application.astar;

/**
 * A virtual cell which defines if it is traversable. The cell is used to store the f,g,h values of the A* algorithm.
 * 
 * @param <T> You can use {@link #obj} to store information about e. g. an external object.
 */
public class AStarCell<T> implements Cloneable {
	
	int col;
	int row;
	boolean isTraversable;
	
	/**
	 * A pointer to an object of your choice. Unused in the A* algorithm.
	 * Usually you convert your grid to the (virtual) A* grid, then find the path.
	 * Afterwards you'd want to find out which of your cells are on the path.
	 */
	T obj;
	
	double g;
	double f;
	double h;
	
	AStarCell<T> cameFrom;
	
	public AStarCell( int col, int row, boolean isPath, T obj) {
		this.col=col;
		this.row=row;
		this.isTraversable = isPath;
		this.obj = obj;
	}
	
	public T getObject() {
		return obj;
	}
	
	public double getF() {
		return f;
	}
	
	public double getG() {
		return g;
	}

	public double getH() {
		return h;
	}

	/**
	 * Cloning only used in order to show the steps of the A* algorithm.
	 */
	public AStarCell<T> clone() {
		
		AStarCell<T> clonedCell = new AStarCell<T>( col, row, isTraversable, obj);
		clonedCell.f =f;
		clonedCell.g = g;
		clonedCell.h = h;
		
		if( cameFrom != null) {
			clonedCell.cameFrom = cameFrom.clone();
		}
		
		return clonedCell;
		
	}
}
