package assign02;

import java.util.GregorianCalendar;

/**
 * This class sets up a Generic type current patient object and has
 * getter and setter methods to help.
 *
 * @author Dominik Jamrich, Ausitn Corbett
 * @version January 21, 2024
 */
public class CurrentPatientGeneric<Type> extends Patient{
    private Type physician;
    private GregorianCalendar lastVisit;

    /**
     * Constructs a current patient object of their information.
     * Note: the physician can be any type.
     *
     * @param firstName of patient
     * @param lastName of patient
     * @param uHealthID of patient
     * @param physician current physician of patient
     * @param lastVisit date of last visit
     */
    public CurrentPatientGeneric(String firstName, String lastName,
                                 UHealthID uHealthID, Type physician,
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
    public Type getPhysician(){
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
    public void updatePhysician(Type newPhysician){
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
