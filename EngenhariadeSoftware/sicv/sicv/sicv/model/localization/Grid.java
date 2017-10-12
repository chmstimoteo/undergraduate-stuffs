package sicv.model.localization;

import java.util.ArrayList;
import java.util.List;

import sicv.lib.GeographicLimit;

public class Grid {

	
	private GeographicLimit geographicLimit;
	private int id;
	private int level;
	private int father;
	private List<Grid> grids;
	private List<Cross> crosses;
	
	

	public Grid(GeographicLimit geographicLimit, int level,int father) {
		super();
		this.geographicLimit = geographicLimit;
		this.level = level;
		this.grids = new ArrayList<Grid>();
		this.father = father;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public GeographicLimit getGeographicLimit() {
		return geographicLimit;
	}

	public void setGeographicLimit(GeographicLimit geographicLimit) {
		this.geographicLimit = geographicLimit;
	}

	public void insert(Grid grid)
	{
		this.grids.add(grid);
	}
	
	public void insert(Cross cross)
	{
		this.crosses.add(cross);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFather() {
		return father;
	}

	public void setFather(int father) {
		this.father = father;
	}

	public List<Cross> getCrosses() {
		return crosses;
	}
	
  	
}
