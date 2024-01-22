package assign02;

import java.util.GregorianCalendar;

public class CurrentPatientGeneric<Type> extends Patient {
	

	private String firstName, lastName;
	private Type physician;
	private UHealthID uHealthID;;
	private GregorianCalendar lastVisit;

	public CurrentPatientGeneric(String firstName, String lastName, UHealthID uHealthID, Type physician, GregorianCalendar lastVisit){
		super(firstName, lastName, uHealthID);
		
		this.uHealthID = uHealthID;
		this.physician = physician;
		this.lastVisit = lastVisit;
		
	}
	
	/**
	 *  Getter for the physician of the patient
	 * @return physician number
	 */
	public Type getPhysician() { return this.physician;}
	
	
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
	public void updatePhysician(Type newPhysician) {
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
