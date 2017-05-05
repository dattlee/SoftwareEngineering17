package SimulationModel;

import dattlee.usefuls.Pair;

import java.util.ArrayList;

/**
 * This is a class to hold all Orders made in a cycle for a company. During each cycle traders submit "requests" to buy
 * or sell shares in a company.
 *
 * This class then stores:
 *  - the total supply of shares
 *  - the a portfolios and number of shares each portfolio has offered to sell
 *  - the total demand for shares
 *  - the a portfolios and number of shares each portfolio has offered to buy
 *
 * At the end of each cycle, all sales and purchases that be fulfilled by the supply/demand
 * are credited/charged to the appropriate portfolio accounts.
 *
 * @version 1.0
 * @author Dattlee ¯\_(ツ)_/¯
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
     *                  Constructors
     *
     ****************************************************/

    /**
     * Creates an empty order object to be stored in a Traded company object,
     * The initial demand and supply is set to 0.
     */
    public Orders(){
        if(Log.debug){System.out.println("Orders: Creating a new Orders object.");}
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

    /**
     * Used by traders make an request to buy a number for shares for a company.
     *
     * @param client a portfolio to charge the account with if shares can be bought at the end of the cycle.
     * @param shares the number of shares a client wishes to buy.
     */
    public void buy(Portfolio client, Integer shares){
        if(Log.debug){System.out.printf("Orders: Making an offer to buy %s shares on behalf of %s.\n", shares, client.getName());}
        clientsBuying.add(new Pair<>(client, shares));
        demand += (int) shares;
    }

    /**
     * Used by traders make an request to sell a number for shares for a company.
     *
     * @param client a portfolio to credit the account with if shares can be sold at the end of a cycle.
     * @param shares the number of shares a client wishes to sell.
     */
    public void sell(Portfolio client, Integer shares){
        if(Log.debug){System.out.printf("Orders: Making an offer to sell %s shares on behalf of %s.\n", shares, client.getName());}
        clientsSelling.add(new Pair<>(client, shares));
        supply += (int) shares;
    }

    /**
     * To be called at the end of every cycle. It sets all of the orders to 0 and resets the supply and demand.
     */
    public void reset(){
        if(Log.debug){System.out.println("Orders: Resetting all orders to 0.");}
        clientsBuying.clear();
        demand = 0;
        clientsSelling.clear();
        supply = 0;
    }


    /**
     * Returns an ArrayList of Pairs, containing each client buying shares and the number of shares they wish to buy.
     *
     * @return ArrayList of Pairs.
     */
    public ArrayList<Pair<Portfolio, Integer>> getClientsBuying() {
        if(Log.debug){System.out.println("Orders: Returning list of clients buying stocks.");}
        return clientsBuying;
    }

    /**
     * Returns the current demand for a stock, as an integer of the number of orders made.
     * @return the number of shares requested as an int.
     */
    public int getDemand() {
        if(Log.debug){System.out.println("Orders: Returning demand for stock");}
        return demand;
    }


    public ArrayList<Pair<Portfolio, Integer>> getClientsSelling() {
        if(Log.debug){System.out.println("Orders: Returning list of clients selling stocks.");}
        return clientsSelling;
    }

    /**
     * Returns the current supply for a stock, as an integer of the number of offers made.
     * @return the number of shares offered as an int.
     */
    public int getSupply() {
        if(Log.debug){System.out.println("Orders: Returning supply of stocks.");}
        return supply;
    }

    /**
     * Returns the difference in demand and supply as an integer.
     *
     *  excess demand returns a positive value.
     *  excess supply returns a negative value.
     *
     * @return the difference as an int.
     */
    public int excess(){
        if(Log.debug){System.out.printf("Orders: Returning exess as %s.\n",demand-supply);}
        return demand - supply;
    }
}
