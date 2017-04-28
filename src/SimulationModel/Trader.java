package SimulationModel;

/**
 * Created by Dattlee on 21/04/2017.
 * ¯\_(ツ)_/¯
 *
 * Every trader can only care for one portfolio. However, this is subject to change.
 */
public abstract class Trader {

    /* **************************************************
     *
     *                  Fields
     *
     ****************************************************/

    // THIS WILL BE REMOVED OF TRADERS CAN HAVE MORE THAN ONE CLIENT
    //public Portfolio client;
    public String ID;
    public TradingExchange exchange;

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
     * This is the method called by the simulation class. At each epoch (time step)
     */
      public abstract void act();

}
