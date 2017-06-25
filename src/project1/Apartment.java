package project1;

import java.util.*;

/**
 * Created by liuxx on 2017/6/24.
 */
public class Apartment {
    private int number;
    private ArrayList<Integer> prefList;
    private boolean isFree;
    private Tenant match;

    public int getNumber() {
        return number;
    }

    public boolean isFree() {
        return this.isFree;
    }

    public int getPrefLevel(Tenant tenant){
        int n = tenant.getNumber();
        return this.prefList.get(n);
    }


    public void setMatch(Tenant tenant){
        this.match = tenant;
        this.isFree = false;
    }

    public Tenant getMatch(){
        return this.match;
    }

    public Apartment(int input, Vector<Integer> preference){
        this.number = input;
        this.prefList = new ArrayList<Integer>(preference);
        this.isFree = true;
    }
}
