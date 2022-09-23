package entity;

public class Pharmacist  {
    private String ID;
    private String name;

    //Constructor
    public Pharmacist(){}

    public Pharmacist(String ID, String name) {
        this.ID = ID;
        this.name = name;
    }


    //Setter and getter
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Pharmacist{" +
                "ID='" + ID + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
