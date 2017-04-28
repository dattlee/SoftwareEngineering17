package SimulationModel;

/**
 * Created by Dattlee on 21/04/2017.
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
//
//    public abstract void buyStock(TradedCompany company, Integer shares);
//
//    public abstract void sellStock(TradedCompany company, Integer shares);

    /**
     * No idea why this was made... ¯\_(ツ)_/¯
     */
    public abstract void removeStockFromMarket();

}
