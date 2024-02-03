package assign02;

import java.util.GregorianCalendar;
/**
 * This class sets up a current patient object and has
 * getter and setter methods to help.
 *
 * @author Dominik Jamrich, Austin Corbett
 * @version January 21, 2024
 */
public class CurrentPatient extends Patient{
    private int physician;
    private GregorianCalendar lastVisit;

    /**
     * Constructs a current patient object with their information.
     *
     * @param firstName of patient
     * @param lastName of patient
     * @param uHealthID of patient
     * @param physician current physician of patient
     * @param lastVisit date of last visit
     */
    public CurrentPatient(String firstName, String lastName,
                          UHealthID uHealthID, int physician,
                          GregorianCalendar lastVisit){
        super(firstName,lastName,uHealthID);
        this.physician = physician;
        this.lastVisit = lastVisit;
    }

    /**
     * Gets the physician of the patient
     *
     * @return the physician
     */
    public int getPhysician(){
        return this.physician;
    }

    /**
     * Gets the date of the last visit of patient
     *
     * @return the date of last visit
     */
    public GregorianCalendar getLastVisit(){
        return this.lastVisit;
    }

    /**
     * Updates the patients physician
     *
     * @param newPhysician the patient's new physician
     */
    public void updatePhysician(int newPhysician){
        this.physician = newPhysician;
    }

    /**
     * Updates the date of the patient's last visit.
     *
     * @param date of last patient
     */
    public void updateLastVisit(GregorianCalendar date){
        this.lastVisit = date;
    }

}
