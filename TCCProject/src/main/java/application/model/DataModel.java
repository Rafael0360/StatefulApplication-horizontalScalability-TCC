package application.model;

public abstract class DataModel {

    private int id = 0;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }
}
