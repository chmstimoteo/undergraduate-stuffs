package sicv.data.mem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sicv.data.IStreetRepository;
import sicv.model.location.Street;


public class StreetRepositoryList implements IStreetRepository {
 
	private List<Street> streets;

     public StreetRepositoryList() {
	
    	 this.streets = new ArrayList<Street>();
    	 
     }
     
     
	
	public void insert(Street street) {
		
		this.streets.add(street);
	}

	
	public void remove(int id) {
		
		for (Iterator<Street> iterator = this.streets.iterator(); iterator.hasNext();) {
			Street street =  iterator.next();
		
			   if(street.getId() == id){
				    this.streets.remove(street);
				   return;
			   }
		}
	}

	
	public Street search(int id) {
		
		
		for (Iterator<Street> iterator = this.streets.iterator(); iterator.hasNext();) {
			Street street =  iterator.next();
		
			   if(street.getId() == id){
				   return street;
			   }
		}
		
		
		throw new RuntimeException("erro");
	}

	
	public void update(Street street) {
		
		this.remove(street.getId());
		this.insert(street);
	}


	
	public Street search(String name) {
		
		for (Iterator<Street> iterator = this.streets.iterator(); iterator.hasNext();) {
			Street street =  iterator.next();
		
			   if(street.getName().equals(name)){
				   return street;
			   }
		}
		
		
		throw new RuntimeException("erro");
	}


	
	public Iterator<Street> iterator() {
		return this.streets.iterator();
	}
	
	 
}
 
