package models;

import java.util.ArrayList;

public class Travel {
    private String title;
    private String description;
    private static ArrayList<Travel> instances = new ArrayList<>();


    public Travel(String title, String description){
        this.title = title;
        this.description = description;
        instances.add(this);
    }


//GETTERS
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public static ArrayList<Travel> getAll() {
        return instances;
    }


}
