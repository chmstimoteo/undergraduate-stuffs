package sicv.business.occurrence;

import java.util.PriorityQueue;

import sicv.model.occurrence.Occurrence;


public class OccurrenceController {
 
	private OccurenceRecord occurrenceRecord;
	
	private PriorityQueue<Occurrence> inMemoryOccurrences;
	
	public OccurrenceController() {
		this.inMemoryOccurrences = new PriorityQueue<Occurrence>();
	}
}
 
