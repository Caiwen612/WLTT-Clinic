package entity;

import adt.ListInterface;


public class Medicine implements Cloneable, Comparable  {
    private String id;
    private String name;
    private ListInterface<Dosage> dosage;
    private String description;

    public Medicine(){}

    public Medicine(String id, String name, ListInterface<Dosage> dosage, String description) {
        this.id = id;
        this.name = name;
        this.dosage = dosage;
        this.description = description;
    }



    //Setter and getter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ListInterface<Dosage> getDosage() {
        return dosage;
    }

    public void setDosage(ListInterface<Dosage>  dosage) {
        this.dosage = dosage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "Medicine{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", dosage=" + dosage +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public Medicine clone() {
        try {
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return (Medicine) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }



    @Override
    public int compareTo(Object o) {
        return toString().compareTo(o.toString());
    }

    public int compareID(Object o){
        if (o instanceof Medicine)
            return this.getId().compareTo(((Medicine) o).getId());
        else return 0;
    }

    public int compareName(Object o){
        if (o instanceof Medicine)
            return this.getName().compareTo(((Medicine) o).getName());
        else return 0;
    }
}
