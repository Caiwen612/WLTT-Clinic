package entity;

import adt.ArrayList;
import adt.ListInterface;

import java.util.Date;

/** Appointment - An entity class of medical record.
 *  @author Tay Chai Boon
 */

public class MedicalRecord {
    private String medicalProblems;
    private String medicalSymptoms;
    private String medicalDiagnosis;
    private ListInterface<Medicine> medicineCart;
    private final Date date;

    //Constructor
    public MedicalRecord(){
        this("","","",null);
    }


    public MedicalRecord(String medicalProblems, String medicalSymptoms, String medicalDiagnosis,ListInterface<Medicine> medicineCart){
        this.date = new Date();
        this.medicalProblems = medicalProblems;
        this.medicalSymptoms = medicalSymptoms;
        this.medicalDiagnosis = medicalDiagnosis;
        this.medicineCart = medicineCart;
    }

    public MedicalRecord(String medicalProblems, String medicalSymptoms, String medicalDiagnosis){
        this.date = new Date();
        this.medicalProblems = medicalProblems;
        this.medicalSymptoms = medicalSymptoms;
        this.medicalDiagnosis = medicalDiagnosis;
        this.medicineCart = new ArrayList<Medicine>();
    }

    //Getter
    public String getMedicalProblems() {
        return this.medicalProblems;
    }

    public String getMedicalSymptoms() {
        return this.medicalSymptoms;
    }

    public String getMedicalDiagnosis() {
        return this.medicalDiagnosis;
    }

    public ListInterface<Medicine> getMedicineCart() {
        return medicineCart;
    }

    public Date getDate() {
        return this.date;
    }

    //Setter
    public void setMedicalProblems(String medicalProblems) {
        this.medicalProblems = medicalProblems;
    }

    public void setMedicalSymptoms(String medicalSymptoms) {
        this.medicalSymptoms = medicalSymptoms;
    }

    public void setMedicalDiagnosis(String medicalDiagnosis) {
        this.medicalDiagnosis = medicalDiagnosis;
    }

    public void setMedicineCart(ListInterface<Medicine> medicineCart) {
        this.medicineCart = medicineCart;
    }

    @Override
    public String toString(){
        String output = "";
        output += "\tMedical Problems: " + this.medicalProblems
                + "\n\tSymptoms: " + this.medicalSymptoms
                + "\n\tMedical Diagnosis: " + this.medicalDiagnosis
                + "\n\tDate: " + this.date;
        return output;
    }
}
