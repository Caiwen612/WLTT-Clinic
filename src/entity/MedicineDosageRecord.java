package entity;

import java.io.Serializable;

public class MedicineDosageRecord implements Serializable {
    private int restockQuantity;
    private int allocateQuantity;

    public MedicineDosageRecord(int restockQuantity, int allocateQuantity) {
        this.restockQuantity = restockQuantity;
        this.allocateQuantity = allocateQuantity;
    }

    public int getRestockQuantity() {
        return restockQuantity;
    }

    public void setRestockQuantity(int restockQuantity) {
        this.restockQuantity = restockQuantity;
    }

    public int getAllocateQuantity() {
        return allocateQuantity;
    }

    public void setAllocateQuantity(int allocateQuantity) {
        this.allocateQuantity = allocateQuantity;
    }

    @Override
    public String toString() {
        return "MedicineDosageRecord{" +
                "restockQuantity=" + restockQuantity +
                ", allocateQuantity=" + allocateQuantity +
                '}';
    }
}
