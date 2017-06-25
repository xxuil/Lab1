package project1;

import java.lang.annotation.Target;
import java.util.*;

/**
 * Created by liuxx on 2017/6/24.
 */
public class Tenant {
    private int number;
    private ArrayList<Integer> prefList;
    private Queue<Integer> preference;
    private boolean isFree;
    private Apartment match;



    public Tenant(int input, Vector<Integer> preference){
        this.number = input;
        this.prefList = new ArrayList<Integer>(preference);
        this.preference = new LinkedList<Integer>();

        /*
        for(int i = 0; i < preference.size(); i++){
            this.prefList.add(preference.get(i));
        }
        */

        setPref(preference);
        this.isFree = true;
    }

    /**
     * getPrefLevel
     * This method determines the tenant's preference level of input apartment
     * @param input number of apartment
     * @return  tenant's preference level of input apartment
     */
    private Integer getPrefLevel(int input) {
        return prefList.get(input);
    }

    /**
     * setPref
     * @param preference
     */
    private void setPref(Vector<Integer> preference){
        ArrayList<Integer> aList = new ArrayList<>();

        for(int i = 0; i < preference.size(); i++){
            aList.add(i);
        }

        aList.sort((a1, a2) -> (getPrefLevel(a1)).compareTo(getPrefLevel(a2)));

        for(int i = 0; i < preference.size(); i++){
            this.preference.offer(i);
        }
    }


    public boolean isEqual(Tenant that){
        if(this.number == that.number)
            return true;
        else
            return false;
    }

    public void setMatch(Apartment apartment){
        this.match = apartment;
        this.isFree = false;
    }

    public Apartment getMatch(){
        return this.match;
    }

    public int getNumber() {
        return number;
    }

    public Queue<Integer> getPreference() {
        return preference;
    }
    public boolean isFree() {
        return isFree;
    }
}
