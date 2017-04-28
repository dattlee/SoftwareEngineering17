package SimulationModel;

import dattlee.usefuls.Pair;

import java.util.ArrayList;

/**
 * Created by Dattlee on 26/04/2017.
 */
public class Orders {


    /* **************************************************
     *
     *                  Fields
     *
     ****************************************************/

    private ArrayList<Pair<Portfolio, Integer>> clientsBuying;
    private int demand;

    private ArrayList<Pair<Portfolio, Integer>> clientsSelling;
    private int supply;


    /* **************************************************
     *
     *                  Consturctors
     *
     ****************************************************/

    public Orders(){
        clientsBuying = new ArrayList<>();
        demand = 0;
        clientsSelling = new ArrayList<>();
        supply = 0;
    }


    /* **************************************************
     *
     *                  Methods
     *
     ****************************************************/

    public void buy(Portfolio client, Integer shares){
        clientsBuying.add(new Pair<>(client, shares));
        demand += (int) shares;
    }

    public void sell(Portfolio client, Integer shares){
        clientsSelling.add(new Pair<>(client, shares));
        supply += (int) shares;
    }

    public void reset(){
        clientsBuying.clear();
        demand = 0;
        clientsSelling.clear();
        supply = 0;
    }


    public ArrayList<Pair<Portfolio, Integer>> getClientsBuying() {
        return clientsBuying;
    }

    public int getDemand() {
        return demand;
    }

    public ArrayList<Pair<Portfolio, Integer>> getClientsSelling() {
        return clientsSelling;
    }

    public int getSupply() {
        return supply;
    }

    /**
     * excess demand returns a positive value
     * excess supply returns a negative value
     * @return
     */
    public int excess(){
        return demand - supply;
    }
}
