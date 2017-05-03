package SimulationModel;

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

    private String ID;
    private TradingExchange exchange;

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
}
