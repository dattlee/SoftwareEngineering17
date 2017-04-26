package SimulationModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Dattlee on 22/04/2017.
 */
public class TradingExchange {

    /* **************************************************
     *
     *                  Fields
     *
     ****************************************************/

    // All of these fields must be reset at the end of each cycle

    private ArrayList<TradedCompany> allCompanies;

    private HashMap<TradedCompany,Orders> exchange;

    /* **************************************************
     *
     *                  Constructors
     *
     ****************************************************/

    public TradingExchange(ArrayList<TradedCompany> companies){

        allCompanies = companies;

        // All companies
        Iterator it = companies.iterator();
        while(it.hasNext()){
            TradedCompany tc = (TradedCompany) it.next();
            exchange.put(tc,new Orders());
        }
    }


    /* **************************************************
     *
     *                  Methods
     *
     ****************************************************/

    /**
     *
     * @param client
     * @param company
     * @param shares
     */
    public void sellStock(Portfolio client, TradedCompany company, Integer shares){
        exchange.get(company).sell(client,shares);
    }


    /**
     * Clients must attempt to buy shares at each 15 minute interval. After an interval passes
     * if they have been unsucessful in buying the stock then their
     * @param client
     * @param company
     * @param shares
     */
    public void buyShares(Portfolio client, TradedCompany company, Integer shares){
        exchange.get(company).buy(client,shares);
    }

    /**
     * Used to resets all of the orders each cycle.
     */
    private void reset(){
        for (HashMap.Entry<TradedCompany, Orders> entry : exchange.entrySet())
        {
            entry.getValue().reset();
        }
    }


    /**
     * This method is called at each 15 minute cycle to
     */
    public void act(){
        // Reset
        
        // Update value of stocks - supply demand equation
        
        
        // Update


        // Reset all orders

    }

}
