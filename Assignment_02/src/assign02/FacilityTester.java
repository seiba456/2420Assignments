package assign02;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * This class contains tests for Facility.
 *
 * @author Eric Heisler and ??
 * @version May 5, 2023
 */
public class FacilityTester {

	private Facility emptyFacility, verySmallFacility, smallFacility, largeFacility;
	private UHealthID uHID1, uHID2, uHID3, uHID4, uHID5, uHID6;
	private GregorianCalendar date1, date2, date3, oldDate, youngDate;
	private CurrentPatient randomPatient, oldPatient, youngPatient;

	@BeforeEach
	void setUp() throws Exception {

		uHID1 = new UHealthID("AAAA-1111");
		uHID2 = new UHealthID("BCBC-2323");
		uHID3 = new UHealthID("HRHR-7654");
		uHID5 = new UHealthID("ABAB-1212");
		uHID6 = new UHealthID("CDCD-3434");

		date1 = new GregorianCalendar(2023, 0, 1);
		date2 = new GregorianCalendar(2023, 3, 17);
		date3 = new GregorianCalendar(2022, 8, 21);
		oldDate = new GregorianCalendar(1949, 6, 6);
		youngDate = new GregorianCalendar(2025, 6, 6);

		emptyFacility = new Facility();

		verySmallFacility = new Facility();
		verySmallFacility.addPatient(new CurrentPatient("Jane", "Doe", uHID1, 1010101, date1));
		verySmallFacility.addPatient(new CurrentPatient("Drew", "Hall", uHID2, 3232323, date2));
		verySmallFacility.addPatient(new CurrentPatient("Riley", "Nguyen", uHID3, 9879876, date3));

		smallFacility = new Facility();
		smallFacility.addAll("small_patient_list.txt");
		
		largeFacility = new Facility();
		
		youngPatient = new CurrentPatient("Young", "Patient", uHID5, 1020304, youngDate);
		oldPatient = new CurrentPatient("Old", "Patient", uHID6, 2030406, oldDate);
		
		for (int i = 0; i < 50; i++) {
			Random r = new Random();
			String first = "";
			for (int j = 0; j < 4; j++) {
				first = first + (char)r.nextInt(26);
			}
			
			String last = "";
			for (int j = 0; j < 4; j++) {
				last = last + (char)r.nextInt(26);
			}
			
			String uHID = "";
			for (int j = 0; j < 4; j++) {
				uHID = uHID + (char)r.nextInt(26);
			}
			
			for (int j = 0; j < 4; j++) {
				uHID = uHID + r.nextInt(9);
			}
			
			uHID4 = new UHealthID(uHID);
			
			int physician = r.nextInt(8999999) + 1000000;
			
			int year = r.nextInt(73) + 1950;
			int month = r.nextInt(11) + 1;
			int day = r.nextInt(27) + 1;
			
			GregorianCalendar date = new GregorianCalendar(year, month, day);
			
			CurrentPatient patient = new CurrentPatient(first, last, uHID4, physician, date);
			largeFacility.addPatient(patient);
			if (i == 25) {
				randomPatient = patient;
			}
		}
		
		// FILL IN -- Extend this tester to add more tests for the facilities above,
		// as well as to create and test larger facilities.
		// (HINT: For larger facility, generate random names, UHIDs, physicians, and
		// dates in a loop, instead of typing one at a time.)
	}

	// Empty Facility tests --------------------------------------------------------

	@Test
	public void testEmptyLookupUHID() {
		assertNull(emptyFacility.lookupByUHID(uHID1));
	}

	@Test
	public void testEmptyLookupPhysician() {
		ArrayList<CurrentPatient> patients = emptyFacility.lookupByPhysician(1010101);
		assertEquals(0, patients.size());
	}

	@Test
	public void testEmptySetVisit() {
		// ensure no exceptions thrown
		emptyFacility.setLastVisit(uHID2, date3);
	}

	@Test
	public void testEmptySetPhysician() {
		// ensure no exceptions thrown
		emptyFacility.setPhysician(uHID2, 1010101);
	}

	@Test
	public void testEmptyGetInactivePatients() {
		ArrayList<CurrentPatient> patients = emptyFacility.getInactivePatients(date3);
		assertEquals(0, patients.size());
	}

	// Very small facility tests ---------------------------------------------------

	@Test
	public void testVerySmallLookupUHID() {
		Patient expected = new Patient("Drew", "Hall", new UHealthID("BCBC-2323"));
		CurrentPatient actual = verySmallFacility.lookupByUHID(new UHealthID("BCBC-2323"));
		assertEquals(expected, actual);
	}

	@Test
	public void testVerySmallLookupPhysicianCount() {
		ArrayList<CurrentPatient> actualPatients = verySmallFacility.lookupByPhysician(9879876);
		assertEquals(1, actualPatients.size());
	}

	@Test
	public void testVerySmallLookupPhysicianPatient() {
		Patient expectedPatient = new Patient("Riley", "Nguyen", new UHealthID("HRHR-7654"));
		ArrayList<CurrentPatient> actualPatients = verySmallFacility.lookupByPhysician(9879876);
		assertEquals(expectedPatient, actualPatients.get(0));
	}

	@Test
	public void testVerySmallAddNewPatient() {
		assertTrue(verySmallFacility.addPatient(new CurrentPatient("Jane", "Doe", new UHealthID("BBBB-2222"), 1010101, date1)));
	}

	@Test
	public void testVerySmallUpdatePhysician() {
		verySmallFacility.lookupByUHID(uHID1).updatePhysician(9090909);
		CurrentPatient patient = verySmallFacility.lookupByUHID(uHID1);
		assertEquals(9090909, patient.getPhysician());
	}

	// Small facility tests -------------------------------------------------------------------------

	@Test
	public void testSmallLookupPhysicianCount() {
		ArrayList<CurrentPatient> actualPatients = smallFacility.lookupByPhysician(8888888);
		assertEquals(2, actualPatients.size());
	}

	@Test
	public void testSmallLookupPhysicianPatient() {
		Patient expectedPatient1 = new Patient("Kennedy", "Miller", new UHealthID("QRST-3456"));
		Patient expectedPatient2 = new Patient("Taylor", "Miller", new UHealthID("UVWX-7890"));

		ArrayList<CurrentPatient> actualPatients = smallFacility.lookupByPhysician(8888888);
		assertTrue(actualPatients.contains(expectedPatient1) && actualPatients.contains(expectedPatient2));
	}

	@Test
	public void testSmallGetInactivePatients() {
		ArrayList<CurrentPatient> actual = smallFacility.getInactivePatients(new GregorianCalendar(2020, 0, 0));
		assertEquals(9, actual.size());
	}

	@Test
	public void testSmallGetPhysicianList() {
		ArrayList<Integer> actual = smallFacility.getPhysicianList();
		assertEquals(7, actual.size());
	}
	
	// Large facility tests
	
	@Test
	public void testLargeLookupPhysicianCount() {
		ArrayList<CurrentPatient> actualPatients = largeFacility.lookupByPhysician(randomPatient.getPhysician());
		assertEquals(1, actualPatients.size());
	}

	@Test
	public void testLargeLookupPhysicianPatient() {
		ArrayList<CurrentPatient> actualPatients = largeFacility.lookupByPhysician(randomPatient.getPhysician());
		assertTrue(actualPatients.contains(randomPatient));
	}
	
	@Test
	public void testLargeGetInactivePatientsBefore2025() {
		ArrayList<CurrentPatient> actual = largeFacility.getInactivePatients(new GregorianCalendar(2025, 0, 0));
		assertEquals(50, actual.size());
		largeFacility.addPatient(youngPatient);
		ArrayList<CurrentPatient> actual2 = largeFacility.getInactivePatients(new GregorianCalendar(2025, 0, 0));
		assertEquals(50, actual2.size());
	}
	
	@Test
	public void testLargeGetInactivePatientsBefore1950() {
		ArrayList<CurrentPatient> actual = largeFacility.getInactivePatients(new GregorianCalendar(1950, 0, 0));
		assertEquals(0, actual.size());
		largeFacility.addPatient(oldPatient);
		ArrayList<CurrentPatient> actual2 = largeFacility.getInactivePatients(new GregorianCalendar(1950, 0, 0));
		assertEquals(1, actual2.size());
	}
	
	@Test
	public void testLargeGetPhysicianList() {
		ArrayList<Integer> actual = largeFacility.getPhysicianList();
		assertEquals(50, actual.size());
	}
}
