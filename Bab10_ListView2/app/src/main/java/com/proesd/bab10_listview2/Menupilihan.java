package com.proesd.bab10_listview2;

/**
 * Created by erick on 12/04/18.
 */

public class Menupilihan {

    String id = null; // variable id dengan tipe string diberi nilai null
    String name = null; // variable name dengan tipe string diberi nilai null
    boolean selected = false; // variable selected dengan tipe boolean diberi nilai falsel

    public Menupilihan(String id, String name, boolean selected) {
        super();
        this.id = id;
        this.name = name;
        this.selected = selected;
    }

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

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
