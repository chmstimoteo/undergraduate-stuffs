package sicv.model.localization;

import java.util.Iterator;

import sicv.business.map.CrossRecord;
import sicv.data.ICrossRepository;
import sicv.data.IStreetPartRepository;
import sicv.data.mem.StreetPartRepositoryList;



public class Street {
 
	private String name;
	
	private int id;
	 	 
	private CrossRecord crossRecord;
	
	private IStreetPartRepository streetParts;

	public Street(ICrossRepository crossRepository) {
		this.crossRecord = new CrossRecord(crossRepository);
		this.streetParts = new StreetPartRepositoryList();
	}
	
	
	
	public Street(String name, int id) {
		super();
		this.name = name;
		this.id = id;
	}



	public void insert(Cross cross) {
		this.crossRecord.insert(cross);
	}
	
	public void removeCross(int id) {
		this.crossRecord.remove(id);
	}
	
	public Cross searchCross(int id) {
		return this.crossRecord.search(id);
	}
	
	
	public void update(Cross cross) {
		this.crossRecord.update(cross);
	}
	
	public void insert(StreetPart streetPart) {
		
		this.streetParts.insert(streetPart);
		
	}

	public boolean existCross(int id) {
		return this.crossRecord.exist(id);
	}
	
	public void removeStreetPart(int id) {
		this.streetParts.remove(id);		
	}

	
	public StreetPart searchStreetPart(int id) {
		return this.streetParts.search(id);
	}

	
	public void update(StreetPart streetPart) {
		this.streetParts.update(streetPart);		
	}
	
	public int getId() {
	
		return this.id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Iterator<StreetPart> getStreetParts() {
		
		return this.streetParts.iterator();
	}


	public void setId(int id) {
		this.id = id;
	}
	
	
	
	public Cross getReferenceCross()
	{
		if (this.streetParts.iterator().hasNext()) {
			return this.crossRecord.search(this.streetParts.iterator().next().getSource().getId());
		}
		throw new RuntimeException("erro");
	}
	 
}
 
