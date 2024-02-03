package assign02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains tests for Facility.
 *
 * @author Eric Heisler and Dominik Jamrich and Austin Corbett
 * @version May 5, 2023
 */
public class FacilityTester {

	private Facility emptyFacility, verySmallFacility, smallFacility, veryLargeFacility;
	private UHealthID uHID1, uHID2, uHID3;
	private GregorianCalendar date1, date2, date3;

	@BeforeEach
	void setUp() throws Exception {

		uHID1 = new UHealthID("AAAA-1111");
		uHID2 = new UHealthID("BCBC-2323");
		uHID3 = new UHealthID("HRHR-7654");

		date1 = new GregorianCalendar(2023, 0, 1);
		date2 = new GregorianCalendar(2023, 3, 17);
		date3 = new GregorianCalendar(2022, 8, 21);

		emptyFacility = new Facility();

		verySmallFacility = new Facility();
		verySmallFacility.addPatient(new CurrentPatient("Jane", "Doe", uHID1, 1010101, date1));
		verySmallFacility.addPatient(new CurrentPatient("Drew", "Hall", uHID2, 3232323, date2));
		verySmallFacility.addPatient(new CurrentPatient("Riley", "Nguyen", uHID3, 9879876, date3));

		smallFacility = new Facility();
		smallFacility.addAll("src/assign02/small_patient_list.txt");

		veryLargeFacility = new Facility();
		int largeCycle = 10000;
		UHealthID[] ids = generateUIDs("UUUU",largeCycle);
		GregorianCalendar[] dates = generateDates(largeCycle);
		for(int i = 0; i < largeCycle; i++){
			String n = "" + (i%500);
			veryLargeFacility.addPatient(new CurrentPatient(n,n+i,ids[i],i%10,dates[i]));
		}

	}
	private UHealthID[] generateUIDs(String prefix, int howMany){
		UHealthID[] ids = new UHealthID[howMany];
		for (int i = 0; i < howMany; i++)
			ids[i] = new UHealthID(prefix + "-" + String.format("%04d", i));
		return ids;
	}

	private GregorianCalendar[] generateDates(int howMany) {
		GregorianCalendar[] dates = new GregorianCalendar[howMany];
		for (int i = 0; i < howMany; i++)
			dates[i] = new GregorianCalendar(2000 + i%22, i%12, i%28);
		return dates;
	}

	// Empty Facility tests --------------------------------------------------------

	@Test
	public void testEmptyLookupUHID() {assertNull(emptyFacility.lookupByUHID(uHID1));
	}

	@Test
	public void addingOne(){assertEquals(true,emptyFacility.addPatient(new CurrentPatient("X","Y",uHID1,1,date1)));}


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

	@Test
	public void testEmptyGetPhysicianList(){
		ArrayList<Integer> phys = emptyFacility.getPhysicianList();
		assertEquals(phys.size(), 0);
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

	//-----Large Facility Tests -----

@Test
public void testLargeLookupByUHID(){
		CurrentPatient testPatient = veryLargeFacility.lookupByUHID(new UHealthID("UUUU-0950"));
		assertEquals(testPatient.getFirstName(), "450");

}
	
@Test
public void testLargeLookupByPhysician(){
		ArrayList<CurrentPatient> testPatients = veryLargeFacility.lookupByPhysician(0);
		assertEquals(testPatients.get(90).getLastName(),"400900");
}

@Test
public void testLargeInactiveList(){
		GregorianCalendar date = new GregorianCalendar(2023,2,1);
		ArrayList<CurrentPatient> inActives = veryLargeFacility.getInactivePatients(date);
		assertEquals(inActives.size(),10000);
}

@Test
public void testLargeGetPhysicianList(){
		ArrayList<Integer> phys = veryLargeFacility.getPhysicianList();
		assertEquals(phys.size(),10);
		assertEquals(phys.get(2),2);
}

	//---- General Facility Tests ----

	@Test
	public void testDuplicates(){
		assertEquals(false,verySmallFacility.addPatient(new CurrentPatient("Jane", "Doe", uHID1, 1010101, date1)));
	}

	@Test
	public void LookupUIDNotFound(){assertEquals(null, verySmallFacility.lookupByUHID(new UHealthID("XXXX-0000")));}

	@Test
	public void setPhysicianWorks(){
		verySmallFacility.setPhysician(uHID1,1);
		assertEquals(1,verySmallFacility.lookupByUHID(uHID1).getPhysician());
	}

	@Test
	public void setLastVisitWorks(){
		verySmallFacility.setLastVisit(uHID1,date2);
		assertEquals(date2,verySmallFacility.lookupByUHID(uHID1).getLastVisit());
	}

}
