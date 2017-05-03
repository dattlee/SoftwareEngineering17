package SimulationModel;

import dattlee.usefuls.Pair;

import java.util.*;

/**
 * Created by Dattlee on 22/04/2017.
 * ¯\_(ツ)_/¯
 *
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

    public TradingExchange(List<TradedCompany> companies){
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


    /* **************************************************
     *
     *                  Methods
     *
     ****************************************************/

    public void newRandTrader(Portfolio client){
        ++ traderIds;
        String id = "" + traderIds;
        allTraders.add(new RandomTrader(id, this, client));
    }

    public void sellShares(Portfolio client, TradedCompany company, Integer shares){
        exchange.get(company).sell(client,shares);
    }


    /**
     * Clients must attempt to buy shares at each 15 minute interval. After an interval passes
     * if they have been unsucessful in buying the stock then their
     * @param client client to sell shares for
     * @param company company to sell
     * @param shares number of shares to sell
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


    public ArrayList<TradedCompany> getAllAvailableCompanies() {
        return allCompanies;
    }

    /**
     * This method is called at each 15 minute cycle to
     */
    public void act(){

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

        //reset();
    }


    private void equaldemand(TradedCompany company, Orders orders){
        for(Pair<Portfolio,Integer> order : orders.getClientsBuying()){
            order.getFirst().buyShares(company,order.getSecond());
        }
        for(Pair<Portfolio,Integer> order : orders.getClientsSelling()){
            try{
                order.getFirst().sellShares(company,order.getSecond());
            }catch (Exception e){
                System.out.println("You haven't fixed the error of selling shares in the portfolio class.");
            }
        }
    }

    private void overdemand(TradedCompany company, Orders orders) {
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
                    System.out.println("Portfolio selling method not working");
                }
            }else{                                                              // if the amount ordereded is greater or equal to that sold
                try {
                    order.getFirst().sellShares(company, sold);                 // sell all the remaining shares from this account
                    sold -= sold;                                               // set sold to 0, as all have been sold.
                }catch (Exception e){
                    System.out.println("Portfolio selling method not working");
                }
            }
        }
    }

    private void oversupply(TradedCompany company, Orders orders) {
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
                System.out.println("still haven't fixed that error");
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

    public double[] allStockValues(){
        Set<Map.Entry<TradedCompany,Orders>> all = exchange.entrySet();
        double[] values = new double[all.size()];
        int i = 0;
        for(Map.Entry<TradedCompany,Orders> tc : all) {
            values[i] = tc.getKey().getShareValue();
            i++;
        }
        return values;
    }


    public ArrayList<Trader> getAllTraders() {
        return allTraders;
    }

    public ArrayList<TradedCompany> getAllCompanies() {
        ArrayList<TradedCompany> allComps = new ArrayList<>();
        for (HashMap.Entry<TradedCompany,Orders> company:exchange.entrySet()) {
            allComps.add(company.getKey());
        }

        return allCompanies;
    }
}
