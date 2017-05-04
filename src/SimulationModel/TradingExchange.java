package SimulationModel;

import dattlee.usefuls.Pair;

import java.util.*;

/**
 * This class is used as a platform for all Traders to buy and sell shares for their clients
 *
 * @version 1.0
 * @author Dattlee
 */
public class TradingExchange {

    /* **************************************************
     *
     *                  Fields
     *
     ****************************************************/

    // All of these fields must be reset at the end of each cycle

    private ArrayList<TradedCompany> allCompanies;          // all companies available for purchase
    private HashMap<TradedCompany,Orders> exchange;
    private ArrayList<Trader> allTraders;
    private int traderIds;

    /* **************************************************
     *
     *                  Constructors
     *
     ****************************************************/


    /**
     * Create a trading Exchange with a list of companies.
     *
     * @param companies a List of Traded Company objects
     */
    public TradingExchange(List<TradedCompany> companies){
        System.out.printf("TradingExchange: Constructing TradingExchange with %s companies.\n", companies.size());

        allCompanies = new ArrayList<>();
        exchange = new HashMap<>();
        allTraders = new ArrayList<>();
        traderIds = 0;

        // All companies
        Iterator it = companies.iterator();
        while(it.hasNext()){
            TradedCompany tc = (TradedCompany) it.next();
            exchange.put(tc,new Orders());
            if (tc.getShareValue()>=1){
                allCompanies.add(tc);
            }
        }
    }

    /**
     * Create a trading Exchange with a list of companies and generate Random traders for a list of clinets and add them the the exchane.
     *
     * @param companies A {@link List} of TradedCompany objects
     * @param clients A {@link List} of Portfolio objects
     */
    public TradingExchange(List<TradedCompany> companies, List<Portfolio> clients){
        System.out.printf("TradingExchange: Constructing TradingExchange with %s companies and %s clients.\n", companies.size(), clients.size());

        allCompanies = new ArrayList<>();
        exchange = new HashMap<>();
        allTraders = new ArrayList<>();
        traderIds = 0;

        // Add all Companies to Trading Exchange
        Iterator it = companies.iterator();
        while(it.hasNext()){
            TradedCompany tc = (TradedCompany) it.next();
            exchange.put(tc,new Orders());
            if (tc.getShareValue()>=1){
                allCompanies.add(tc);
            }
        }

        for (TradedCompany company:companies) {
            exchange.put(company,new Orders());
        }

        for (Portfolio client:clients) {
            newRandTrader(client);
        }
    }


    /* **************************************************
     *
     *                  Methods
     *
     ****************************************************/

    /**
     * Create a new RandomTrader to manage the Portfolio of a client.
     *
     * @param client a Portfolio
     */
    public void newRandTrader(Portfolio client){
//        System.out.printf("TradingExchange: Constructing new RandomTrader for Protfolio, named %s.\n", client.getName());
        ++ traderIds;
        String id = "" + traderIds;
        allTraders.add(new RandomTrader(id, this, client));
    }

    public void sellShares(Portfolio client, TradedCompany company, Integer shares){
//        System.out.printf("TradingExchange: Selling %s shares for %s on behalf of %s.\n",shares,company.getName(),client.getName());
        exchange.get(company).sell(client,shares);
    }

    /**
     * Used by Traders to put an order in for a client for a selection of shares.
     *
     * @param client client to buy shares from the market
     * @param sharesOffered a Pair of a TradedCompany and an Integer
     */
    public void sellShares(Portfolio client, ArrayList<Pair<TradedCompany,Integer>> sharesOffered){
//        System.out.printf("TradingExchange: Selling a selection of %s shares on behalf of %s.\n", sharesOffered.size(),client.getName());
        for(Pair<TradedCompany,Integer> order: sharesOffered) {
            exchange.get(order.getFirst()).sell(client, order.getSecond());
        }
    }


    /**
     * Used by Traders to put an order in for a client for shares in a company.
     *
     * @param client client to sell shares for
     * @param company company to sell
     * @param shares number of shares to sell
     */
    public void buyShares(Portfolio client, TradedCompany company, Integer shares){
//        System.out.printf("TradingExchange: Selling %s shares for %s on behalf of %s.\n",shares,company.getName(),client.getName());
        exchange.get(company).buy(client,shares);
    }

    /**
     * Used by Traders to put an order in for a client for a selection of shares.
     *
     * @param client client to sell shares for
     * @param sharesWanted a Pair of a TradedCompany and an Integer
     */
    public void buyShares(Portfolio client, ArrayList<Pair<TradedCompany,Integer>> sharesWanted){
//        System.out.printf("TradingExchange: Buying a selection of %s shares on behalf of %s.\n", sharesWanted.size(),client.getName());
        for(Pair<TradedCompany,Integer> order: sharesWanted) {
            exchange.get(order.getFirst()).buy(client, order.getSecond());
        }
    }

    /**
     * Used to resets all of the orders each cycle.
     */
    private void reset(){
//        System.out.println("TradingExchange: Resetting all Orders to 0");
        for (HashMap.Entry<TradedCompany, Orders> entry : exchange.entrySet())
        {
            entry.getValue().reset();
        }
    }


    public ArrayList<TradedCompany> getAllAvailableCompanies() {
//        System.out.printf("TradingExchange: Returning %s available companies for sale.\n",allCompanies.size());
        return allCompanies;
    }

    /**
     * This method is called at each 15 minute cycle. It causes all traders to make their orders on behalf of their clients
     * and then completes all possible transactions.
     */
    public void act(){
        System.out.println("TradingExchange: Running a cycle.");
        //_________________________
        //   traders make orders
        //_________________________

        for(Trader t : allTraders){
            t.act();
        }

        //_________________________
        //      complete orders
        //_________________________

        // For Each Traded Company
        for (HashMap.Entry<TradedCompany,Orders> entry : exchange.entrySet()){

            TradedCompany company = entry.getKey();
            Orders orders = entry.getValue();
            int excess = orders.excess();


            // if there is no excess demand/supply, make all purchases. and don't edit share price
            if (excess == 0){
                equaldemand(company,orders);
            }else if(excess > 0){
                overdemand(company,orders);
            }else{
                oversupply(company,orders);
            }

            // update value of shares
            double val = company.increaseShareValue(excess);
            if (val == 0){
                allCompanies.remove(company);
            }


            // reset all orders
            orders.reset();
        }

    }


    /**
     * If the demand is equal in a cycle complete all orders and requests for stocks.
     *
     * @param company The TradedCompany to complete orders for.
     * @param orders The Orders to complete.
     */
    private void equaldemand(TradedCompany company, Orders orders){
//        System.out.printf("TradingExchange: Completing all orders for %s as demand = supply.\n",company.getName());
        // Complete all Sales
        for(Pair<Portfolio,Integer> order : orders.getClientsBuying()){
            order.getFirst().buyShares(company,order.getSecond());
        }
        // Complete all Purchases
        for(Pair<Portfolio,Integer> order : orders.getClientsSelling()){
            try{
                order.getFirst().sellShares(company,order.getSecond());
            }catch (Exception e){
                System.out.printf("Exception caught: %s\n\tFound in:TradingExchange.equaldemand(TradedCompany company, Orders orders)\n",e.getMessage());
                       // this error stems from; Portfolio.sellShares(TradedCompany company, Integer shares)");
            }
        }
    }

    /**
     * If there is overdemand in a cycle complete all orders and distribute the requests.
     *
     * @param company The TradedCompany to complete orders for.
     * @param orders The Orders to complete.
     */
    private void overdemand(TradedCompany company, Orders orders) {
//        System.out.printf("TradingExchange: Completing some orders for %s as demand > supply.\n",company.getName());
        int demand = orders.getDemand();
        int supply = orders.getSupply();
        double distribution = (double) supply/demand;                       // this should always be a number between 0 and 1
        // multiply distribution buy each stock
        int sold = 0;                                                       // Total amount actually sold

        // the buyers first
        for(Pair<Portfolio,Integer> order : orders.getClientsBuying()){
            int bought = (int) (order.getSecond() * distribution);              // The number of shares one will actually buy
            sold += bought;                                                     // add to the total actually sold
            order.getFirst().buyShares(company,bought);                         // charge client and add to to portfolio
        }
        // the sellers second
        for(Pair<Portfolio,Integer> order : orders.getClientsSelling()){
            if(order.getSecond()<sold){                                         // if the amount ordered is less than that sold
                try {
                    int selling = order.getSecond();                            //
                    sold -= selling;                                            // subtract ordered amount from sold
                    order.getFirst().sellShares(company, selling);              // sell the shares to the client
                }catch (Exception e){
                    System.out.printf("Exception caught: %s\n\tFound in:TradingExchange.overdemand(TradedCompany company, Orders orders)\n",e.getMessage());
                    // this error stems from; Portfolio.sellShares(TradedCompany company, Integer shares);
                }
            }else{                                                              // if the amount ordereded is greater or equal to that sold
                try {
                    order.getFirst().sellShares(company, sold);                 // sell all the remaining shares from this account
                    sold -= sold;                                               // set sold to 0, as all have been sold.
                }catch (Exception e){
                    System.out.printf("Exception caught: %s\n\tFound in:TradingExchange.overdemand(TradedCompany company, Orders orders)\n",e.getMessage());
                    // this error stems from; Portfolio.sellShares(TradedCompany company, Integer shares);
                }
            }
        }
    }

    /**
     * If there is over supply in a cycle complete all the requests and distribute the orders.
     *
     * @param company The TradedCompany to complete orders for.
     * @param orders The Orders to complete.
     */
    private void oversupply(TradedCompany company, Orders orders) {
//        System.out.printf("TradingExchange: Completing some orders for %s as demand < supply.\n",company.getName());

        int demand = orders.getDemand();
        int supply = orders.getSupply();
        double distribution = (double) demand/supply;                       // this should always be a number between 0 and 1
        // multiply distribution buy each stock
        int bought = 0;                                                       // Total amount actually sold

        // the sellers First
        for(Pair<Portfolio,Integer> order : orders.getClientsSelling()){
            int buying = (int) (order.getSecond() * distribution);              // The number of shares one will actually buy
            bought += buying;                                                     // add to the total actually sold
            try{
                order.getFirst().sellShares(company,buying);                         // charge client and add to to portfolio
            }catch (Exception e) {
                System.out.printf("Exception caught: %s\n\tFound in:TradingExchange.oversupply(TradedCompany company, Orders orders)\n",e.getMessage());
                // this error stems from; Portfolio.sellShares(TradedCompany company, Integer shares);
            }
        }

        // the buyers first
        for(Pair<Portfolio,Integer> order : orders.getClientsBuying()){
            if (order.getSecond()<bought){
                bought -= order.getSecond();
                order.getFirst().buyShares(company,order.getSecond());
            }else{
                order.getFirst().buyShares(company,bought);
                bought -= bought;
            }
        }
    }

    /**
     * Used to quickly assess market in testing.
     *
     * @return double array of the values of each company in no particular order.
     */
    public double[] allStockValues(){
//        System.out.println("TradingExchange: Returning the values of all the shares on the Trading Exchange.");
        Set<Map.Entry<TradedCompany,Orders>> all = exchange.entrySet();
        double[] values = new double[all.size()];
        int i = 0;
        for(Map.Entry<TradedCompany,Orders> tc : all) {
            values[i] = tc.getKey().getShareValue();
            i++;
        }
        return values;
    }

    /**
     * Returns traded a list of all Traders on the Tradingxchange
     *
     * @return an ArrayList of Traders
     */
    public ArrayList<Trader> getAllTraders() {
//        System.out.println("TradingExchange: Returning all Traders on the Trading Exchange");
        return allTraders;
    }

    /**
     * Returns an ArrayList of all the companies on the market.
     *
     * @return ArrayList of Traded companies
     */
    public ArrayList<TradedCompany> getAllCompanies() {
//        System.out.println("TradingExchange: Returning all Companies on the Trading Exchange.");
        ArrayList<TradedCompany> allComps = new ArrayList<>();
        for (HashMap.Entry<TradedCompany,Orders> company:exchange.entrySet()) {
            allComps.add(company.getKey());
        }

        return allCompanies;
    }
}
