package application.astar;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Snapshot per step. Used for visualization of the steps (open/closed set; f,g,h values) in the A* path.
 */
public class AStarSnapshot {

	Set<AStarCell> openset = new HashSet<>();
	Set<AStarCell> closedset = new HashSet<>();
	
	public AStarSnapshot( Collection<AStarCell> open, Collection<AStarCell> closed) {
		
		for( AStarCell cell: open) {
			openset.add( cell.clone());
		}
		for( AStarCell cell: closed) {
			closedset.add( cell.clone());
		}
	}

	public Set<AStarCell> getOpenset() {
		return openset;
	}

	public void setOpenset(Set<AStarCell> openset) {
		this.openset = openset;
	}

	public Set<AStarCell> getClosedset() {
		return closedset;
	}

	public void setClosedset(Set<AStarCell> closedset) {
		this.closedset = closedset;
	}
	
	
}
