package sicv.business.map;

import java.util.Iterator;
import java.util.List;

import sicv.lib.Position;
import sicv.model.localization.Cross;
import sicv.model.localization.Grid;

public class GridController {
	private static final int MAX_LEVEL = 4;
	private static final int MIN_LEVEL = 0;
	private List<Grid>[] levels;
	private List<Grid> firstLevels;
	private List<Grid> secondLevels;
	private List<Grid> thirdLevels;
	private List<Grid> fourthLevels;
	private List<Grid> fifthLevels;
	
	public GridController() {
	
		this.levels[0] = this.fifthLevels;
		this.levels[1] = this.secondLevels;
		this.levels[2] = this.thirdLevels;
		this.levels[3] = this.fourthLevels;
		this.levels[4] = this.fifthLevels;
	}
	
	
	public void insert(Grid grid)
	{
		List<Grid> fatherLevel;
		
		if (grid.getLevel() == MIN_LEVEL) {
			firstLevels.add(grid);
		}
		else{
			fatherLevel = levels[grid.getLevel()-1];
			for (Iterator<Grid> iterator = fatherLevel.iterator(); iterator.hasNext();) {
				Grid current =  iterator.next();
				if (current.getId() == grid.getFather()) {
					current.insert(grid);
				}
				
			}
		}
	}
	
	public Cross searchCrossByPosition(Position position)
	{
	   return this.search(position, 0);	
	}
	
	private Cross search(Position position,int currentLevel)
	{
		List<Grid> list = this.levels[currentLevel];
		
		for (Iterator<Grid> grids = list.iterator(); grids.hasNext();) {
			Grid grid = grids.next();
			
			if (grid.getGeographicLimit().contains(position)) {
				
				if (currentLevel == MAX_LEVEL) {
					for (Iterator<Cross> crosses = grid.getCrosses().iterator(); crosses.hasNext();){
						Cross cross = crosses.next();
						
						if (cross.getGeographicLimit().contains(position)) {
							return cross;
						}
				  }
				}else{
					search(position, currentLevel+1);
				}
				       
			}
		} 
		throw new RuntimeException("erro");
	}
}
