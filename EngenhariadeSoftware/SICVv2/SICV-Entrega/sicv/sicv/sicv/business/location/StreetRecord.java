package sicv.business.location;

import java.util.Iterator;

import sicv.data.IStreetRepository;
import sicv.model.location.Street;


public class StreetRecord {
 
	private IStreetRepository streetRepository;

	public StreetRecord(IStreetRepository streetRepository) {
		this.streetRepository = streetRepository;

	}

	public  void insert(Street street)
	{
		this.insert(street);
	}
	
	public Street search(int id){
		return this.streetRepository.search(id);
	}
	
	public  void remove(int id){
		
	}
	public  void update(Street street)
	{
		this.streetRepository.update(street);
		
	}
	public  Street search(String name)
	{
		return this.streetRepository.search(name);
	}
	
	public Iterator<Street> iterator(){
		return this.streetRepository.iterator();
	}

	
	 
}
 
