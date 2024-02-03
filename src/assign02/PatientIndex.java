package assign02;

import java.util.Comparator;
import java.util.TreeMap;

/**
 * This class represents an index of all the patients
 *
 * @author Austinn Corbett, Dominik Jamrich
 * @version January 21, 2024
 */
public class PatientIndex {
    private TreeMap<UHealthID, String> index;

    /**
     *Constructs a Patient index and orders them based off UHealthID.
     *
     */
    public PatientIndex(){
        index = new TreeMap<>((UHealthID u1, UHealthID u2) ->
                u1.toString().compareTo(u2.toString()));
    }

    /**
     * Adds or updates a patient to the patient index.
     *
     * @param p the patient ot be added
     */
    public void addPatient(Patient p){
        if(p!=null) {
            if (index.containsKey(p.getUHealthID())) {

                index.replace(p.getUHealthID(), "" + p.getFirstName() + " " + p.getLastName()
                );
            } else {
                {
                    index.put(p.getUHealthID(), "" + p.getFirstName() + " " + p.getLastName());
                }
            }
        }
    }

    /**
     *Removes a given patient from the index
     *
     * @param p patient to be removed
     */
    public void removePatient(Patient p){
       if(p != null) {
           if (index.containsKey(p.getUHealthID())) {

               index.remove(p.getUHealthID());
           }
       }
    }

    /**
     * Gets the name of the patient from the index.
     *
     * @param id UHealthID
     * @return Name of patient
     */
    public String getName(UHealthID id){
        return index.get(id);

    }
}
