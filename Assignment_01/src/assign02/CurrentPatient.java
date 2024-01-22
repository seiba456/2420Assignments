package assign02;

import java.util.GregorianCalendar;

public class CurrentPatient extends Patient {
	
	private String firstName, lastName;
	private int physician;
	private UHealthID uHealthID;;
	private GregorianCalendar lastVisit;

	public CurrentPatient(String firstName, String lastName, UHealthID uHealthID, int physician, GregorianCalendar lastVisit){
		super(firstName, lastName, uHealthID);
		
		this.uHealthID = uHealthID;
		this.physician = physician;
		this.lastVisit = lastVisit;
		
	}
	
	/**
	 *  Getter for the physician of the patient
	 * @return physician number
	 */
	public int getPhysician() { return this.physician;}
	
	
	/**
	 *  Getter for the last visited date of the patientk.
	 * @return gregorian calendar of the last visited date.
	 */
	public GregorianCalendar getLastVisit() {
		
		return this.lastVisit;
	}
	
	
	/**
	 * Changes the physician the patient is assigned to. Updates whenever called. 
	 * 
	 * @param newPhysician -- physician to become the new physician of patient
	 */
	public void updatePhysician(int newPhysician) {
		this.physician = newPhysician;
	}
	
	/**
	 * updates the date last visited to the newly assigned date. 
	 * @param date -- new visited date for patient
	 */
	public void updateLastVisit(GregorianCalendar date) {
		this.lastVisit = date;
		
	}
	
}
