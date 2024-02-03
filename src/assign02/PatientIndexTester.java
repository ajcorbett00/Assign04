package assign02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;
/**
 * This class contains tests for Patient Index.
 *
 * @author Dominik Jamrich and Austin Corbett
 * @version January 24, 2024
 */
public class PatientIndexTester {
    private Patient patient1,patient2,patient2NewName,patient4,patient5,patient5NewID,patient7,patient8;
    private PatientIndex index,largeIndex;
    @BeforeEach
    void setUp() throws Exception{
        patient1 = new Patient("Austin","Corbett", new UHealthID("utah-0001"));
        patient2 = new Patient("Dominik","Jamrich",new UHealthID("utah-0002"));
        patient2NewName = new Patient("Norm","McDonald", new UHealthID("utah-0002"));
        patient4 = new Patient("Ronald","McDonald", new UHealthID("utah-0004"));
        patient5 = new Patient("Mark","Cuban", new UHealthID("utah-0005"));
        patient5NewID = new Patient("Mark","Cuban", new UHealthID("utah-0006"));
        patient7 = new Patient("Bill","Gates", new UHealthID("utah-0007"));
        patient8 = new Patient("Bob","Dillan", new UHealthID("utah-0008"));

        index = new PatientIndex();
        largeIndex = new PatientIndex();
        largeIndex.addPatient(patient1);largeIndex.addPatient(patient2);largeIndex.addPatient(patient2NewName);
        largeIndex.addPatient(patient4);largeIndex.addPatient(patient5);largeIndex.addPatient(patient5NewID);
        largeIndex.addPatient(patient7);largeIndex.addPatient(patient8);


    }

    @Test
    public void testConstructor(){
        new PatientIndex();
    }

    @Test
    public void getNameReturnNullTest(){
        assertNull(index.getName(new UHealthID("utah-0001")));
    }

    @Test
    public void getNameTest(){
        index.addPatient(patient2);
        var name = index.getName(new UHealthID("utah-0002"));
        assertEquals(name,"Dominik Jamrich");
    }

    @Test
    public void addPatientTest(){
        index.addPatient(patient1);
        assertEquals(index.getName(new UHealthID("utah-0001")),"Austin Corbett");
    }
    @Test
    public void addPatientLargeTest(){
        index.addPatient(patient8);index.addPatient(patient2);index.addPatient(patient2NewName);
        index.addPatient(patient5);index.addPatient(patient4);index.addPatient(patient5NewID);
        index.addPatient(patient7);index.addPatient(patient1);


        assertEquals(largeIndex.getName(new UHealthID("utah-0001")),
                index.getName(new UHealthID("utah-0001")));
        assertEquals(largeIndex.getName(new UHealthID("utah-0002")),
                index.getName(new UHealthID("utah-0002")));
        assertEquals(largeIndex.getName(new UHealthID("utah-0004")),
                index.getName(new UHealthID("utah-0004")));
        assertEquals(largeIndex.getName(new UHealthID("utah-0005")),
                index.getName(new UHealthID("utah-0005")));
        assertEquals(largeIndex.getName(new UHealthID("utah-0006")),
                index.getName(new UHealthID("utah-0006")));
        assertEquals(largeIndex.getName(new UHealthID("utah-0007")),
                index.getName(new UHealthID("utah-0007")));
        assertEquals(largeIndex.getName(new UHealthID("utah-0008")),
                index.getName(new UHealthID("utah-0008")));


    }
    @Test
    public void addPatientUpdateInfoTest(){
        index.addPatient(patient2);
        assertEquals(index.getName(new UHealthID("utah-0002")),"Dominik Jamrich");
        index.addPatient(patient2NewName);
        assertEquals(index.getName(new UHealthID("utah-0002")),"Norm McDonald");

    }

    @Test
    public void removePatientTest(){
        largeIndex.removePatient(patient1);
        assertNull(largeIndex.getName(new UHealthID("utah-0001")));

    }
    @Test
    public void removeAndAddPatientTest(){
        largeIndex.removePatient(patient4);
        assertNull(largeIndex.getName(new UHealthID("utah-0004")));
        largeIndex.addPatient(patient4);
        assertEquals(largeIndex.getName(new UHealthID("utah-0004")),"Ronald McDonald");

    }

    @Test
    public void PatientIndexCanHandleNull(){
        //Make Sure No exceptions thrown;
       Patient p = null;
       largeIndex.addPatient(p);
       largeIndex.removePatient(p);
    }

}
