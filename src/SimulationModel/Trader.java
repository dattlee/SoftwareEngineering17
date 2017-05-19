package SimulationModel;

import java.util.ArrayList;

/**
 * This is an Abstract class that defines the key attributes of any Trader in the Wolf and Gecko Simulation. It allows
 * for the expansion of more complex traders, with new behaviours.
 *
 * @version 1.0
 * @author Dattlee ¯\_(ツ)_/¯
 */
public abstract class Trader {

    /* **************************************************
     *
     *                  Fields
     *
     ****************************************************/

    protected String ID;
    protected TradingExchange exchange;

    /* **************************************************
     *
     *                  Constructors
     *
     ****************************************************/

    public Trader(String ID, TradingExchange exchange){
        this.ID = ID;
        this.exchange = exchange;
    }


    /* **************************************************
     *
     *                  Methods
     *
     ****************************************************/

    /**
     * This is the method called by the simulation class at each cycle.
     */
    public abstract void act();

    /**
     * This is a method that is used for returning the portfolio value found within the trader object
     *
     * @return A double representing the total value of the client
     */
    public abstract Double getPortfolioValue();

    /**
     * This is a method that is used for returning the clients name
     *
     * @return A string representing the clients name.
     */
    public abstract String getClientName();

    /**
     * Returns the unique ID assined to the Trader
     * @return a String identification
     */
    public String getID() {
        return ID;
    }

    /**
     * The Trading exchange that the Trader is associated with.
     *
     * @return Trading Exchange object.
     */
    public TradingExchange getExchange() {
        return exchange;
    }

    /**
     * Returns a Collection of all the Portfolios the Trader manages.
     *
     * @return A Collection of all Portfolios
     */
    abstract public ArrayList<Portfolio> getAllClients();
}
