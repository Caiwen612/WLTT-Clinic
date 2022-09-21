package entity;

import adt.ArrayList;
import adt.ListInterface;

import java.util.Date;

public class MedicalRecord {
    private String medicalProblems;
    private String medicalSymptoms;
    private String medicalDiagnosis;
    private ListInterface<Medicine> medicineCart;
    private final Date date;

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



    public String getMedicalProblems() {
        return this.medicalProblems;
    }

    public void setMedicalProblems(String medicalProblems) {
        this.medicalProblems = medicalProblems;
    }

    public String getMedicalSymptoms() {
        return this.medicalSymptoms;
    }

    public void setMedicalSymptoms(String medicalSymptoms) {
        this.medicalSymptoms = medicalSymptoms;
    }

    public String getMedicalDiagnosis() {
        return this.medicalDiagnosis;
    }

    public void setMedicalDiagnosis(String medicalDiagnosis) {
        this.medicalDiagnosis = medicalDiagnosis;
    }

    public Date getDate() {
        return this.date;
    }

    public ListInterface<Medicine> getMedicineCart() {
        return medicineCart;
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

//    public void printMedicineCart(){
//        System.out.println("caiwen");
//        if(medicineCart != null){
//            System.out.println("ID" + "Name " + "Dosage Form" + "Dose" + "Quantity");
//            for(int i =1;i <= medicineCart.getNumberOfEntries();i++){
//                Medicine medicine = medicineCart.getEntry(i);
//                System.out.println(medicine.getId() + medicine.getName() + medicine.getDosage());
//            }
//        }
//    }
}
