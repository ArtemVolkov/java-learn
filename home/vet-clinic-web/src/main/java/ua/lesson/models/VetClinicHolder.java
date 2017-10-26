package ua.lesson.models;
import ua.lesson.lessons.*;
/**
 * Class singleton for VetClinic
 */
public class VetClinicHolder {
    private static VetClinic vet=new VetClinic();

    /**
     * Method returns instance of vetclinic
     * @return
     */
    public static VetClinic getInstance() {
        return vet;
    }

    /**
     * private constuctor
     */
    private VetClinicHolder(){}
}
