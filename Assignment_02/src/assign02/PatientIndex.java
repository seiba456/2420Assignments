package assign02;

import java.util.Comparator;
import java.util.TreeMap;

public class PatientIndex {

	private TreeMap<UHealthID, String> member;
	private PatientIndex index;
	
	public PatientIndex() {
		//PatientIndex test = new PatientIndex(UHealthID o1, UHealthID 02)
		index = new PatientIndex((UHealthID o1, UHealthID o2) -> o1.toString.compare(o2.toString()));
	}
	
	public PatientIndex(Comparator<? super UHealthID> comparator){
		member = new TreeMap<UHealthID, String>(comparator);
	}
	
	/**
	 * Adds a patient to the map
	 * @param p
	 */
	public void addPatient(Patient p) {
		if (member.containsKey(p.getUHealthID())) {
			member.replace(p.getUHealthID(), p.getFirstName() + " " + p.getLastName());
			return;
		}
		member.put(p.getUHealthID(), p.getFirstName() + " " + p.getLastName());
	}
	
	/**
	 * Removes a patient from the map
	 * @param p
	 */
	public void removePatient(Patient p) {
		if (member.containsKey(p.getUHealthID())) {
			member.remove(p.getUHealthID());
		}
	}
	
	/**
	 * Gets the name of the patient connected to the id
	 * @param id
	 * @return
	 */
	public String getName(UHealthID id) {
		if (member.containsKey(id)) {
			return member.get(id);
		}
		return null;
	}
}
