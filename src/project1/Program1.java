/**EE 360 Project 1
 * Xiangxing Liu
 * xl5587
 */
package project1;

import java.util.*;

/**
 * Your solution goes in this class.
 * 
 * Please do not modify the other files we have provided for you, as we will use
 * our own versions of those files when grading your project. You are
 * responsible for ensuring that your solution works with the original version
 * of all the other files we have provided for you.
 * 
 * That said, please feel free to add additional files and classes to your
 * solution, as you see fit. We will use ALL of your additional files when
 * grading your solution.
 */
public class Program1 extends AbstractProgram1 {
    /**
     * Determines whether a candidate Matching represents a solution to the
     * Stable Matching problem. Study the description of a Matching in the
     * project documentation to help you with this.
     */
    public boolean isStableMatching(Matching given_matching){
        if(given_matching.getTenantCount() != given_matching.getTenantMatching().size()){
            return false;
        }

        for(int i = 0; i < given_matching.getLandlordCount(); i++){

        }

        for(int i = 0; i < given_matching.getTenantCount(); i++){

        }

        return false; /* TODO remove this line */
    }

    /**
     * Determines a solution to the Stable Matching problem from the given input
     * set. Study the project description to understand the variables which
     * represent the input to your solution.
     * 
     * @return A stable Matching.
     */
    public Matching stableMatchingGaleShapley(Matching match) {
        //Lists initialization
        ArrayList<Tenant> TenantList = new ArrayList<Tenant>();
        ArrayList<Apartment> ApartmemtList = new ArrayList<Apartment>();
        Queue<Tenant> FreeTenant = new LinkedList<Tenant>();

        //create all lists and set all free
        createLists(FreeTenant, TenantList, ApartmemtList, match);
        HashMap<Tenant, Apartment> MatchMap = new HashMap<Tenant, Apartment>();
        //HashMap<Apartment, Tenant> ApartmentMap = new HashMap<Apartment, Tenant>();

        int index = 0;

        //while there is a free tenant
        while(!FreeTenant.isEmpty()){
            Tenant tempTanent = FreeTenant.poll();
            int nextChoice = tempTanent.getPreference().poll();
            Apartment tempApartment = findFree(nextChoice, ApartmemtList);

            //check if the apartment is free
            if(tempApartment.isFree()){
                //if free then assign A to this tempTanent
                MatchMap.put(tempTanent, tempApartment);
                tempTanent.setMatch(tempApartment);
                tempApartment.setMatch(tempTanent);
            }

            else{
                //if not free, check the match
                Tenant checkTanent = tempApartment.getMatch();

                if(tempApartment.getPrefLevel(checkTanent) > tempApartment.getPrefLevel(tempTanent)){
                    //if the landlord of this apartment prefers this tempTenant
                    MatchMap.remove(checkTanent);
                    FreeTenant.offer(checkTanent);
                    tempApartment.setMatch(tempTanent);
                    tempTanent.setMatch(tempApartment);
                }
                else{
                    //if the landlord does not prefer
                    //remain the same
                    FreeTenant.add(tempTanent);
                }

            }


        }

        Vector<Integer> newList = new Vector<Integer>();

        for (int i = 0; i < match.getTenantCount(); i++) {

            newList.add(TenantList.get(i).getMatch().getNumber());
        }

        Matching newMatch = new Matching(match, newList);

        return newMatch;
    }

    private static boolean isPreferedTenant(Tenant tenant, Apartment apartment){


        return false;
    }





    private static Apartment findFree(int number, ArrayList<Apartment> ApartmentList){
        for(int i = 0; i < ApartmentList.size(); i++){
            if(ApartmentList.get(i).getNumber() == number){
                return ApartmentList.get(i);
            }
        }

        return null;
    }


    private static void createLists(Queue<Tenant> FreeTenants, ArrayList<Tenant> Tenants, ArrayList<Apartment> ApartmentList, Matching match){
        for(int i = 0; i < match.getTenantCount(); i++) {
            Tenant tenant = new Tenant(i, match.getTenantPref().get(i));
            Tenants.add(tenant);
            FreeTenants.offer(tenant);
        }

        for(int i = 0; i < match.getTenantPref().get(0).size(); i++){
            for(int j = 0; j < match.getLandlordOwners().size(); j++){
                for(int k = 0; k < match.getLandlordOwners().get(j).size(); k++){
                    int temp = match.getLandlordOwners().get(j).get(k);
                    if(i == temp){
                        Apartment apartment = new Apartment(i, match.getLandlordPref().get(j));
                        ApartmentList.add(apartment);
                    }
                }
            }
        }
        //finish creating the lists, return


    }
}
