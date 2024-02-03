package assign02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains tests for FacilityGeneric.
 *
 * @author Eric Heisler and Dominik Jamrich and Austin Corbett
 * @version May 5, 2023
 */
public class FacilityGenericTester {
	// Generic Facility
	private FacilityGeneric<Integer> uNIDFacility, emptyFacility, phase3Facility;
	private FacilityGeneric<UHealthID> uHIDFacility;
	private FacilityGeneric<String> nameFacility;
	// IDs, dates, names
	private FacilityGeneric<String> veryLargeFacilityString;
	private FacilityGeneric<UHealthID> veryLargeFacilityU;
	private UHealthID[] uHIDs1, uHIDs2;
	private GregorianCalendar[] dates;
	private String[] firstNames, lastNames, physicianNames;
	// For phase 3
	private UHealthID p3id1, p3id2, p3id3, p3id4;
	private GregorianCalendar p3date1, p3date2, p3date3, p3date4;
	private String[] largeNames;
	private GregorianCalendar[] datesLarge;

	private String[] largePhysNames;

	private FacilityGeneric<Integer> smallerFac;


	// Don't change these numbers. It will affect some tests.
	int nPatients = 20;
	int nPhysicians = 8;

	// A private helper to generate UHIDs
	private UHealthID[] generateUHIDs(String prefix, int howMany) {
		UHealthID[] ids = new UHealthID[howMany];
		for (int i = 0; i < howMany; i++)
			ids[i] = new UHealthID(prefix + "-" + String.format("%04d", i));
		return ids;
	}

	// A private helper to generate dates
	private GregorianCalendar[] generateDates(int howMany) {
		GregorianCalendar[] dates = new GregorianCalendar[howMany];
		for (int i = 0; i < howMany; i++)
			dates[i] = new GregorianCalendar(2000 + i%22, i%12, i%28);
		return dates;
	}

	// A private helper to generate names
	private String[] generateNames(int howMany, int a, int b) {
		String[] names = new String[howMany];
		for (int i = 0; i < howMany; i++)
			names[i] = (char)('A' + (i+a) % 26) + "" + (char)('a' + (b*i) % 26);
		return names;
	}

	@BeforeEach
	void setUp() throws Exception {

		uHIDs1 = generateUHIDs("PATS", nPatients); // for patients
		uHIDs2 = generateUHIDs("DOCS", nPhysicians); // for physicians

		dates = generateDates(nPatients);

		firstNames = generateNames(nPatients, 1, 2);
		lastNames = generateNames(nPatients, 5, 3);
		physicianNames = generateNames(nPhysicians, 10, 4);

		uNIDFacility = new FacilityGeneric<Integer>();
		uHIDFacility = new FacilityGeneric<UHealthID>();
		nameFacility = new FacilityGeneric<String>();
		emptyFacility = new FacilityGeneric<Integer>();
		phase3Facility = new FacilityGeneric<Integer>();

		for (int i = 0; i < nPatients; i++) {
			uNIDFacility.addPatient(new CurrentPatientGeneric<Integer>(
										firstNames[i], lastNames[i],
										uHIDs1[i], 1234567 + i%nPhysicians, dates[i]));
			uHIDFacility.addPatient(new CurrentPatientGeneric<UHealthID>(
										firstNames[i], lastNames[i],
										uHIDs1[i], uHIDs2[i%nPhysicians], dates[i]));
			nameFacility.addPatient(new CurrentPatientGeneric<String>(
										firstNames[i], lastNames[i],
										uHIDs1[i], physicianNames[i%nPhysicians], dates[i]));
		}

		p3id1 = new UHealthID("XXXX-1111");
 		p3id2 = new UHealthID("BBBB-1111");
 		p3id3 = new UHealthID("FFFF-1111");
 		p3id4 = new UHealthID("BBBB-2222");
 		p3date1 = new GregorianCalendar(2019, 1, 5);
 		p3date2 = new GregorianCalendar(2019, 1, 4);
 		p3date3 = new GregorianCalendar(2019, 1, 3);
 		p3date4 = new GregorianCalendar(2019, 1, 2);

		phase3Facility.addPatient(new CurrentPatientGeneric<Integer>("A", "B", new UHealthID("XXXX-1111"), 7, new GregorianCalendar(2019, 1, 5)));
		phase3Facility.addPatient(new CurrentPatientGeneric<Integer>("A", "B", new UHealthID("BBBB-1111"), 7, new GregorianCalendar(2019, 1, 4)));
		phase3Facility.addPatient(new CurrentPatientGeneric<Integer>("A", "C", new UHealthID("FFFF-1111"), 7, new GregorianCalendar(2019, 1, 3)));
		phase3Facility.addPatient(new CurrentPatientGeneric<Integer>("R", "T", new UHealthID("BBBB-2222"), 7, new GregorianCalendar(2019, 1, 2)));

		//Large Facility Initializations--------------
		int largeFacilitySize = 10000;
		veryLargeFacilityString = new FacilityGeneric<String>();
		veryLargeFacilityU = new FacilityGeneric<UHealthID>();
		largeNames = generateNames(largeFacilitySize, 0 , 0);
		largePhysNames = generateNames(largeFacilitySize,1,1);
		UHealthID[] physList = generateUHIDs("PHYS",largeFacilitySize);
		UHealthID[] patientList = generateUHIDs("PATS",largeFacilitySize);
		datesLarge = generateDates(largeFacilitySize);
		for(int i = 0; i < largeFacilitySize; i++){
			veryLargeFacilityString.addPatient(new CurrentPatientGeneric<String>(largeNames[i],
					largeNames[i],patientList[i],largePhysNames[i],datesLarge[i]));
			veryLargeFacilityU.addPatient(new CurrentPatientGeneric<UHealthID>(largeNames[i],
					largeNames[i], patientList[i], physList[i%1000], datesLarge[i]));
		}
///-----For Phase 3 ---
		smallerFac = new FacilityGeneric<Integer>();
		smallerFac.addPatient(new CurrentPatientGeneric<Integer>("A","B",new UHealthID("AAAA-1111"),1,dates[0]));
		smallerFac.addPatient(new CurrentPatientGeneric<Integer>("A","B", new UHealthID("AAAA-1112"),1,dates[0]));
		smallerFac.addPatient(new CurrentPatientGeneric<Integer>("C","C", new UHealthID("AAAA-0000"),1,dates[0]));

	}

	// empty Facility tests --------------------------------------------------------

	@Test
	public void testEmptyLookupUHID() {
		assertNull(emptyFacility.lookupByUHID(uHIDs1[0]));
	}

	@Test
	public void testEmptyLookupPhysician() {
		ArrayList<CurrentPatientGeneric<Integer>> patients = emptyFacility.lookupByPhysician(1010101);
		assertEquals(0, patients.size());
	}

	@Test
	public void testEmptySetVisit() {
		// ensure no exceptions thrown
		emptyFacility.setLastVisit(uHIDs1[0], dates[3]);
	}

	@Test
	public void testEmptySetPhysician() {
		// ensure no exceptions thrown
		emptyFacility.setPhysician(uHIDs1[0], 1010101);
	}

	@Test
	public void testEmptyGetInactivePatients() {
		ArrayList<CurrentPatientGeneric<Integer>> patients = emptyFacility.getInactivePatients(dates[4]);
		assertEquals(0, patients.size());
	}

	// uNID Facility tests --------------------------------------------------------

	@Test
	public void testSetPhysicianAllClasses(){
		FacilityGeneric<Integer> blankFacility = new FacilityGeneric<Integer>();
		blankFacility.addPatient(new CurrentPatientGeneric<Integer>("","", uHIDs1[0],0,p3date1));
		blankFacility.setPhysician(uHIDs1[0],2);
		assertEquals(blankFacility.lookupByPhysician(2).size(),1);
	}

	@Test
	public void testUNIDLookupPhysicianCount() {
		ArrayList<CurrentPatientGeneric<Integer>> actualPatients = uNIDFacility.lookupByPhysician(1234568);
		assertEquals(3, actualPatients.size());
	}

	@Test
	public void testUNIDLookupPhysicianPatient() {
		Patient expectedPatient = new Patient(firstNames[1], lastNames[1], new UHealthID(uHIDs1[1].toString()));
		ArrayList<CurrentPatientGeneric<Integer>> actualPatients = uNIDFacility.lookupByPhysician(1234568);
		assertEquals(expectedPatient, actualPatients.get(0));
	}



	// UHealthID facility tests ---------------------------------------------------

	@Test
	public void testUHIDLookupUHID() {
		Patient expected = new Patient(firstNames[0], lastNames[0], new UHealthID(uHIDs1[0].toString()));
		CurrentPatientGeneric<UHealthID> actual = uHIDFacility.lookupByUHID(new UHealthID(uHIDs1[0].toString()));
		assertEquals(expected, actual);
	}

	@Test
	public void testUHIDLookupPhysicianCount() {
		Patient expectedPatient = new Patient(firstNames[1], lastNames[1], new UHealthID(uHIDs1[1].toString()));
		ArrayList<CurrentPatientGeneric<UHealthID>> actualPatients = uHIDFacility.lookupByPhysician(uHIDs2[1]);
		assertEquals(3, actualPatients.size());
	}

	@Test
	public void testUHIDAddDuplicatePatient() {
		assertFalse(uHIDFacility.addPatient(new CurrentPatientGeneric<UHealthID>(firstNames[1], lastNames[1], new UHealthID(uHIDs1[1].toString()), uHIDs2[1], dates[1])));
	}

	@Test
	public void testUHIDAddNewPatient() {
		assertTrue(uHIDFacility.addPatient(new CurrentPatientGeneric<UHealthID>(firstNames[1], lastNames[1], new UHealthID("ZZZZ-9999"), uHIDs2[1], dates[1])));
	}

	@Test
	public void testUHIDUpdatePhysician() {
		uHIDFacility.lookupByUHID(uHIDs1[2]).updatePhysician(uHIDs2[0]);
		CurrentPatientGeneric<UHealthID> patient = uHIDFacility.lookupByUHID(uHIDs1[2]);
		assertEquals(uHIDs2[0], patient.getPhysician());
	}

	@Test
	public void largeFacilityPhysicianList(){
		ArrayList<UHealthID> physList = veryLargeFacilityU.getPhysicianList();
		assertEquals(physList.get(99),new UHealthID("PHYS-0099"));
		assertEquals(physList.size(),1000);
	}

	@Test
	public void largeFacilitygetInactivePatients(){
		ArrayList<CurrentPatientGeneric<UHealthID>> inactives1 =
				veryLargeFacilityU.getInactivePatients(new GregorianCalendar(1999,1,2));
		ArrayList<CurrentPatientGeneric<UHealthID>> inactives2 =
				veryLargeFacilityU.getInactivePatients(new GregorianCalendar(2014,12,31));
		assertEquals(inactives1.size(),0);
		assertEquals(inactives2.size(),10000-(454*7));
	}

	@Test
	public void largeFacilityPhysicianLookUp(){
		ArrayList<CurrentPatientGeneric<UHealthID>> physPat = veryLargeFacilityU.lookupByPhysician(new
				UHealthID("PHYS-0555"));
		FacilityGeneric<UHealthID> specificPhys = new FacilityGeneric<UHealthID>();
		for(CurrentPatientGeneric<UHealthID> pat : physPat){
			specificPhys.addPatient(pat);
		}
		assertEquals(physPat.size(),10);
		assertEquals(specificPhys.lookupByUHID(new UHealthID("PATS-3555")), new
				CurrentPatientGeneric<UHealthID>(largeNames[3555],largeNames[3555],new
				UHealthID("PATS-3555"),new UHealthID("PHYS-0555"),
				datesLarge[3555]));
	}

	// name facility tests -------------------------------------------------------------------------

	@Test
	public void testNameLookupPhysician() {
		Patient expectedPatient1 = new Patient(firstNames[1], lastNames[1], new UHealthID(uHIDs1[1].toString()));
		Patient expectedPatient2 = new Patient(firstNames[9], lastNames[9], new UHealthID(uHIDs1[9].toString()));

		ArrayList<CurrentPatientGeneric<String>> actualPatients = nameFacility.lookupByPhysician(physicianNames[1]);
		assertTrue(actualPatients.contains(expectedPatient1) && actualPatients.contains(expectedPatient2));
	}

	@Test
	public void testNameGetInactivePatients() {
		ArrayList<CurrentPatientGeneric<String>> actual = nameFacility.getInactivePatients(new GregorianCalendar(2010, 0, 0));
		assertEquals(10, actual.size());
	}

	@Test
	public void testNameGetPhysicianList() {
		ArrayList<String> actual = nameFacility.getPhysicianList();
		assertEquals(8, actual.size());
	}

	@Test
	public void testSetPhysicianString(){
veryLargeFacilityString.setPhysician(new UHealthID("PATS-0025"),"Patty");
assertEquals("Patty",veryLargeFacilityString.lookupByUHID(new UHealthID("PATS-0025")).getPhysician());
	}

	@Test
	public void testLargeStringInactive(){
		ArrayList<CurrentPatientGeneric<String>> inactives1 =
				veryLargeFacilityString.getInactivePatients(new GregorianCalendar(1999,1,2));
		ArrayList<CurrentPatientGeneric<String>> inactives2 =
				veryLargeFacilityString.getInactivePatients(new GregorianCalendar(2014,12,31));
		assertEquals(inactives1.size(),0);
		assertEquals(inactives2.size(),10000-(454*7));
	}

	@Test
	public void testLargeStringPhysName(){
		ArrayList<CurrentPatientGeneric<String>> physPat = veryLargeFacilityString.lookupByPhysician(
				"Patty");
		assertEquals(0,physPat.size());
		veryLargeFacilityString.addPatient(new CurrentPatientGeneric<String>("","",
				new UHealthID("AAAA-2020"),"Paul",datesLarge[0]));
		assertEquals(veryLargeFacilityString.lookupByPhysician("Paul").getFirst().getUHealthID(),
				veryLargeFacilityString.lookupByUHID(new UHealthID("AAAA-2020")).getUHealthID());
	}


//-----Phase 3 Tests ---
	@Test
	public void testOrderedByUHIDCount() {
 		ArrayList<CurrentPatientGeneric<Integer>> actual = phase3Facility.getOrderedByUHealthID();
 		assertEquals(4, actual.size());
 	}

 	@Test
 	public void testOrderedByUHIDOrder() {
 		ArrayList<CurrentPatientGeneric<Integer>> actual = phase3Facility.getOrderedByUHealthID();
 		assertTrue(actual.get(3).equals(new CurrentPatientGeneric<Integer>("A", "B", p3id1, 7, p3date1)) &&
 				actual.get(0).equals(new CurrentPatientGeneric<Integer>("A", "B", p3id2, 7, p3date2)) &&
 				actual.get(2).equals(new CurrentPatientGeneric<Integer>("A", "C", p3id3, 7, p3date3)) &&
 				actual.get(1).equals(new CurrentPatientGeneric<Integer>("R", "T", p3id4, 7, p3date4)));
 	}

	 @Test
	 public void testLargeOrderedByUHID(){
		ArrayList<CurrentPatientGeneric<String>> patsString =
				veryLargeFacilityString.getOrderedByUHealthID();
		ArrayList<CurrentPatientGeneric<UHealthID>> patsUhealth =
				 veryLargeFacilityU.getOrderedByUHealthID();
		assertEquals(patsString.get(55).getUHealthID(), new UHealthID("PATS-0055"));
		assertEquals(patsUhealth.get(555).getUHealthID(), new UHealthID("PATS-0555"));

	 }

	 @Test
	 public void testSmallerOrderedByUHealth(){
		assertEquals(smallerFac.getOrderedByUHealthID().getFirst().getUHealthID().toString(),"AAAA-0000");
	 }

	 @Test
	 public void testGetRecentPatientsList(){
		ArrayList<CurrentPatientGeneric<Integer>> noneRemoved = phase3Facility.getRecentPatients(new GregorianCalendar(2019, 1, 1));
		assertEquals(noneRemoved.size(),4);
		ArrayList<CurrentPatientGeneric<Integer>> twoRemoved = phase3Facility.getRecentPatients(
				new GregorianCalendar(2019,1,3));
		assertEquals(twoRemoved.size(),2);
		assertEquals(noneRemoved.get(1), new CurrentPatientGeneric<Integer>
				("A", "B", new UHealthID("XXXX-1111"),
						7, new GregorianCalendar(2019, 1, 5)));

	 }

	 @Test
	 public void testSmallerGetRecentPatientsList(){
		assertEquals(smallerFac.getRecentPatients(
				new GregorianCalendar(1999,1,1)).get(1).getUHealthID().toString(),
				"AAAA-1112");
	 }

	 @Test
	 public void emptyFacility(){
		assertEquals(emptyFacility.getOrderedByUHealthID().size(),0);
		assertEquals(emptyFacility.getRecentPatients(dates[0]).size(),0);
	 }


//---Misc. Testing---
	@Test
	public void isItReallyGeneric(){
		FacilityGeneric<Point> weirdFacility = new FacilityGeneric<Point>();
		weirdFacility.addPatient(new CurrentPatientGeneric<Point>("x","y",uHIDs1[0],new Point(1,2),p3date1));
		assertEquals(weirdFacility.getPhysicianList().get(0),new Point(1,2));
	}



}
