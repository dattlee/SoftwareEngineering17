package SimulationModel;

import dattlee.usefuls.Pair;

import java.util.ArrayList;

/**
 * Created by Dattlee on 26/04/2017.
 * ¯\_(ツ)_/¯
 *
 * This is a class to hold all Orders made in a cycle for a company, that is to be reset at the end of each cycle.
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

    /*
     * To make an offer for shares on the market for this cycle.
     * @param client
     * @param shares
     */
    public void buy(Portfolio client, Integer shares){
        clientsBuying.add(new Pair<>(client, shares));
        demand += (int) shares;
    }

    /*
     * To offer shares for this cycle on the market.
     * @param client
     * @param shares
     */
    public void sell(Portfolio client, Integer shares){
        clientsSelling.add(new Pair<>(client, shares));
        supply += (int) shares;
    }

    /**
     * To be called at the end of every cycle, so to set all offers and orders to 0.
     */
    public void reset(){
        clientsBuying.clear();
        demand = 0;
        clientsSelling.clear();
        supply = 0;
    }


    /*
     * Returns an ArrayList, of pairs of Each client buying stock and each selling stock
     * @return
     */
    public ArrayList<Pair<Portfolio, Integer>> getClientsBuying() {
        return clientsBuying;
    }

    /*
     * Returns the current Demand for a stock, as an integer of the number of orders made
     * @return demand
     */
    public int getDemand() {
        return demand;
    }

    public ArrayList<Pair<Portfolio, Integer>> getClientsSelling() {
        return clientsSelling;
    }

    /**
     * Returns the current Supply for a stock, as an integer of the number of offers made
     * @return supply
     */
    public int getSupply() {
        return supply;
    }

    /**
     * Returns the difference in demand and supply as an integer.
     *
     *  excess demand returns a positive value.
     *  excess supply returns a negative value.
     *
     * @return the difference
     */
    public int excess(){
        return demand - supply;
    }
}
