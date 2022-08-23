package entity;

public class Doctor {
    private String ID;
    private String name;

    //Constructor
    public Doctor(){
        this("","");
    }
    public Doctor(String id, String name){
        this.ID = id;
        this.name = name;
    }

    //Getter
    public String getID() {
        return this.ID;
    }

    public String getName() {
        return this.name;
    }

}
