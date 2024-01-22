package assign02;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * This class represents a record of patients that have visited a UHealth
 * facility.
 *
 * @author Eric Heisler and Arath Alatorre Muniz && Mattes Clarke
 * @version May 5, 2023
 */
public class FacilityGeneric<Type> {

	private ArrayList<CurrentPatientGeneric> patientList;

	/**
	 * Creates an empty facility record.
	 */
	public FacilityGeneric() {
		this.patientList= new ArrayList<CurrentPatientGeneric>();
	}

	/**
	 * Adds the given patient to the list of patients, avoiding duplicates.
	 *
	 * @param patient - patient to be added to this record
	 * @return true if the patient was added,
	 *         false if the patient was not added because they already exist in the record
	 */
	public boolean addPatient(CurrentPatientGeneric patient) {
		
		if(this.patientList.isEmpty()) {
			patientList.add(patient);
			return true;
		}
		
		// || will change if the contaings does not uuse .equals of patient||
		if(patientList.contains(patient))
			return false;
			
		return false;
	}

	/**
	 * Retrieves the patient with the given UHealthID.
	 *
	 * @param UHealthID of patient to be retrieved
	 * @return the patient with the given ID, or null if no such patient
	 * 			exists in the record
	 */
	public CurrentPatientGeneric lookupByUHID(UHealthID patientID) {
		
		//checks if patient list is empty
		if(this.patientList.isEmpty())
			return null;
		
		for(int i = 0; i < patientList.size(); i++) {
			if(patientList.get(i).getUHealthID().equals(patientID))
				return patientList.get(i);
		}
		
		return null;
		
	}

	/**
	 * Retrieves the patient(s) with the given physician.
	 *
	 * @param physician - physician of patient(s) to be retrieved
	 * @return a list of patient(s) with the given physician (in any order),
	 * 	     or an empty list if no such patients exist in the record
	 */
	public ArrayList<CurrentPatientGeneric> lookupByPhysician(Type physician) {
		
		ArrayList<CurrentPatientGeneric> patientsAssigned = new ArrayList<CurrentPatientGeneric>();
		
		if(!this.patientList.isEmpty()) {
			
			for(int i = 0; i < patientList.size(); i++) {
				
				if(patientList.get(i).getPhysician().equals(physician)) {
					patientsAssigned.add(patientList.get(i));
				}
			}
		}
		
		
		// FILL IN -- do not return null
		return patientsAssigned;
	}

	/**
	 * Retrieves the patient(s) with last visits older than a given date.
	 *
	 * NOTE: If the last visit date equals this date, do not add the patient.
	 *
	 * @param date - cutoff date later than visit date of all returned patients.
	 * @return a list of patient(s) with last visit date before cutoff (in any order),
	 * 	     or an empty list if no such patients exist in the record
	 */
	public ArrayList<CurrentPatientGeneric> getInactivePatients(GregorianCalendar date) {

		ArrayList<CurrentPatientGeneric> olderPatients = new ArrayList<CurrentPatientGeneric>();
		
		if(!patientList.isEmpty()) {
				for(int i = 0; i < patientList.size(); i++) {
				
				if(date.before(patientList.get(i).getLastVisit())){
					olderPatients.add(patientList.get(i));
				}
			}
			
		}
		return olderPatients;
	}

	/**
	 * Retrieves a list of physicians assigned to patients at this facility.
	 *
	 * * NOTE: Do not put duplicates in the list. Make sure each physician
	 *       is only added once.
	 *
	 * @return a list of physician(s) assigned to current patients,
	 * 	     or an empty list if no patients exist in the record
	 */
	public ArrayList<Type> getPhysicianList() {
		
		ArrayList<Type> physicians = new ArrayList<Type>();
		
		if(!patientList.isEmpty()) {
			
			for(int i = 0; i < patientList.size(); i++) {
				
				Type currentPhysician = (Type) patientList.get(i).getPhysician();
				if(!physicians.contains(currentPhysician))
					physicians.add(currentPhysician);
			}
		}
			
		return physicians;
	}

	/**
	 * Sets the physician of a patient with the given UHealthID.
	 *
	 * NOTE: If no patient with the ID exists in the collection, then this
	 * 		method has no effect.
	 *
	 * @param patientID - UHealthID of patient to modify
	 * @param physician - identifier of patient's new physician
	 */
	public void setPhysician(UHealthID patientID, int physician) {
		
		
		boolean patientFound = true;
		int indexOfPatient = 0;
		for(int i = 0; i < patientList.size(); i++) {
			
			if(patientList.get(i).getUHealthID().equals(patientID)) {
				patientFound = true;
				indexOfPatient = i;
				break;
			}
		}
		
		if(patientFound)
			patientList.get(indexOfPatient).updatePhysician(physician);
	}

	/**
	 * Sets the last visit date of a patient with the given UHealthID.
	 *
	 * NOTE: If no patient with the ID exists in the collection, then this
	 * 		method has no effect.
	 *
	 * @param patientID - UHealthID of patient to modify
	 * @param date - new date of last visit
	 */
	public void setLastVisit(UHealthID patientID, GregorianCalendar date) {
		
		boolean patientFound = true;
		int indexOfPatient = 0;
		for(int i = 0; i < patientList.size(); i++) {
			
			if(patientList.get(i).getUHealthID().equals(patientID)) {
				patientFound = true;
				indexOfPatient = i;
				break;
			}
		}
		
		

		if(patientFound)
			patientList.get(indexOfPatient).updateLastVisit(date);
		
	
	}
}
