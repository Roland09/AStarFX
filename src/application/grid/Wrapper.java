package application.grid;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import application.astar.AStarCell;
import application.astar.AStarGrid;
import application.astar.AStarAlgorithm;
import application.grid.Grid;
import application.grid.Cell;

public class Wrapper<T extends Cell> {

	AStarAlgorithm alg = new AStarAlgorithm();

	AStarGrid<T> g = null;
	AStarCell<T> s = null;
	AStarCell<T> e = null;

	List<T> path = null;
	
	/**
	 * Wrap visualization grid into A* grid, apply A* algorithm, unwrap cells.
	 */
	public List<T> findPath( Grid grid, T start, T goal, boolean allowDiagonals) {
		
		// convert visual grid to A* grid
		marshal(grid, start, goal, allowDiagonals);
		
		// stopwatch
		long time = System.nanoTime();
		
		List<AStarCell> aStarPath = alg.getPath(g, s, e, allowDiagonals);
		
		// show time needed for the algorithm
		System.out.println( "Calc Time: " + (System.nanoTime() - time) / 1_000_000d + " ms");
		
		// convert A* path cells to visual cells
		path = unmarshal( aStarPath);
		
		return path;
	}
	
	/**
	 * Wrap visualization grid into A* grid. 
	 */
	private void marshal( Grid grid, T start, T goal, boolean allowDiagonals) {
		
		g = new AStarGrid<T>( grid.getColumns(),grid.getRows());
		
		for( int row=0; row < grid.getRows(); row++) {
			for( int col=0; col < grid.getColumns(); col++) {
				
				T cell = (T) grid.getCell(col, row);
				
				g.setCell( cell, col, row, cell.isTraversable());
				
				if( row == start.getRow() && col == start.getColumn()) {
					s = g.getCell(col, row);
				}
				if( row == goal.getRow() && col == goal.getColumn()) {
					e = g.getCell(col, row);
				}
				
			}
		}
	}
	
	/**
	 * Unwrap A* cells into visualization cells. 
	 * @param path
	 * @return
	 */
	private List<T> unmarshal( Collection<AStarCell> path) {

		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(1);
		
		List<T> list = new ArrayList<>();
		for( AStarCell c: path) {
			T obj = (T) c.getObject();
			obj.setTextF( nf.format( c.getF()));
			obj.setTextG( nf.format( c.getG()));
			obj.setTextH( nf.format( c.getH()));
			list.add( obj);
		}

		return list;
	}
	
	public List<T> getOpenSnapshot( int index) {
		
		return unmarshal( alg.getSnapshots().get(index).getOpenset());
		
	}
	public List<T> getClosedSnapshot( int index) {
		
		return unmarshal( alg.getSnapshots().get(index).getClosedset());
		
	}
	
	public List<T> getPath() {
		return path;
	}
	
	public int getSnapshotCount() {
		return alg.getSnapshots().size();
	}
	
}
