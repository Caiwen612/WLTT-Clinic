package entity;

import adt.ArrayList;
import adt.ListInterface;

public class TestingPrescription {

    private int medicineNo;
    private int quantity;

    public TestingPrescription(int medicineNo, int quantity) {
        this.medicineNo = medicineNo;
        this.quantity = quantity;
    }

    public int getMedicineNo() {
        return medicineNo;
    }

    public void setMedicineNo(int medicineNo) {
        this.medicineNo = medicineNo;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
