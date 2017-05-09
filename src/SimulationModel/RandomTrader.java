package SimulationModel;

import dattlee.usefuls.Pair;

import java.util.*;

/**
 * This Class represents a Random Trader on the stock market that manages a Portfolio for a client. Each
 * random trader created in this class is only capable of managing the affairs of one client.
 *
 * The manner in which a Random Trader manages a Portfolio differs depending on the current state. The state of a
 * RandomTrader is updated with each cycle, see {@link SimulationModel.TraderState}.
 *
 * @version 1.0
 * @author Dattlee ¯\_(ツ)_/¯
 */
public class RandomTrader extends Trader {

    /* **************************************************
     *
     *                  Fields
     *
     ****************************************************/

    private TraderState currentState;
    private Portfolio client;

    /* **************************************************
     *
     *                  Constructors
     *
     ****************************************************/

    /**
     * Create a trader and add it to the Trading Exchange.
     *
     * By default the state of the trader is set to {@link TraderState}.BALANCED.
     *
     * @param ID A unique ID assigned to a trader when they join a Trading Exchange
     * @param exchange - A reference to the trading exchange that the Trader has joined
     */
    public RandomTrader(String ID, TradingExchange exchange) {
        super(ID, exchange);
        if(Log.debug){System.out.printf("RandomTrader: Creating a new Random Trader with ID %s.\n",ID);}
        currentState = TraderState.BALANCED;
    }


    /**
     * Create a trader and add it to the Trading Exchange.
     *
     * By default the state of the trader is set to {@link TraderState}.BALANCED.
     *
     * @param ID A unique ID assigned to a trader when they join a Trading Exchange.
     * @param exchange A reference to the trading exchange that the Trader has joined
     * @param portfolio - The portfolio that the trader is responsible for.
     */
    public RandomTrader(String ID, TradingExchange exchange, Portfolio portfolio) {
        super(ID, exchange);
        if(Log.debug){System.out.printf("RandomTrader: Creating a new Random Trader with ID %s, assigned to %s.\n",ID,portfolio.getName());}
        currentState = TraderState.BALANCED;
        client = portfolio;
    }

    /* **************************************************
     *
     *                  Methods
     *
     ****************************************************/

    /**
     * To give a trader a new Portfolio to manage.
     *
     * @param portfolio the portfolio to add.
     *
     * @exception Exception if a Trader is already responsible for a portfolio.
     */
    public void addPortfolio(Portfolio portfolio) throws Exception {
        if(Log.debug){System.out.printf("RandomTrader: Adding the portfolio of %s for Trader (ID %s) to manage.\n",portfolio.getName(),ID);}
        if(this.client == null){
            this.client = portfolio;
        } else {
            throw new Exception("RandomTrader: There is already a portfolio assigned to this trader.");
        }
    }

    /**
     * Removes the portfolio currently managed by the Random Trader.
     *
     * @exception Exception if the trader is not currently managing a portfolio.
     */
    public void removePortfolio() throws Exception {
        if(Log.debug){System.out.printf("RandomTrader: Removing the portfolio of %s from Trader (ID %s)'s management.\n",client.getName(),ID);}
        if(this.client == null){
            throw new Exception("There is no portfolio assigned to this trader.");
        } else {
            this.client = null;
        }
    }

    /**
     * This method is called during every cycle. It follows this process:
     *      - If the client wants to liquidate all of their stock, offer all stock on the market
     *      - Else
     *          - Buy all stock from 1 company based on the state of the trader
     *          - Then, Sell stock based on the state of the trader
     */
    @Override
    public void act() {
        if(Log.debug){ System.out.printf("RandomTrader: Trader (ID %s) starting to make purchases and sales.\n",ID);}

        if(client.isLiquidate()) {
            sellAllStock();
        }else {

            // buy something
            HashMap<String,TradedCompany> dontSell = new HashMap<>();
            for (Pair<TradedCompany,Integer> p:buyStock()) {
                dontSell.put(p.getFirst().getName(),p.getFirst());
            }

            // sell something, remember you cant sell something your buying. that's just pointless.
            sellStock(dontSell);

        }

        //potentially change state
        double changeState = Math.random();
        if (changeState <= currentState.getSwitchBuy()) {
            currentState = TraderState.AGGBUYER;
        } else if (changeState <= (currentState.getSwitchBuy() + currentState.getSwitchBalance())) {
            currentState = TraderState.BALANCED;
        } else {
            currentState = TraderState.AGGSELLER;
        }
    }


    /**
     * Method used by a Trader to sell of a clients stock when they have asked for stocks to be liquidated.
     */
    private void sellAllStock(){
        if(Log.debug){ System.out.printf("RandomTrader: Trader (ID %s) selling all stock for %s.\n",ID,client.getName());}
        for (HashMap.Entry<TradedCompany, Integer> entry : client.getShares().entrySet())
        {
            super.getExchange().sellShares(client, entry.getKey(),entry.getValue());
        }
    }

    /**
     * Used buy the act method. It follows this process:
     *      - Searches from all the available company stocks
     *      - Purchase the Maximum amount of stock from one company that a portfolio can afford.
     */
    private ArrayList<Pair<TradedCompany,Integer>> buyStock() {
        if(Log.debug){ System.out.printf("RandomTrader: Trader (ID %s), offering to buy stock on TradingExchange for %s.\n",ID,client.getName());}

        // Get Available company stocks
        ArrayList<TradedCompany> allCompanies = exchange.getAllAvailableCompanies();
        Collections.shuffle(allCompanies);

        // Get the max expenditure
        double buyingPercentage = currentState.getBuyPerc() * Math.random();
        double maxPurchPrice = client.getCash() * buyingPercentage;
//        if(maxPurchPrice>client.getCash()){
//            maxPurchPrice = client.getCash();
//        }

        // List of Shares to buy
        ArrayList<Pair<TradedCompany,Integer>> buying = new ArrayList<>();

        for(TradedCompany company:allCompanies){                    // for each available company
            double shareVal = company.getShareValue();                  // get the share value
            if(shareVal < maxPurchPrice){                               // if they have enough money
                int noShares = (int)(maxPurchPrice/shareVal);               // bid for x shares
                maxPurchPrice -= noShares*shareVal;                         // subtract the cost from money availble for this round
                buying.add(new Pair<>(company,noShares));                   // add shares to the buying list
            }
        }

        // Make orders to exchange
        exchange.buyShares(client,buying);

        return buying;

    }

    /**
     * Sell as much stock up to a maximum from company that is available on the stockmarket.
     *
     * EXCEPT for a companies already purchasing stock for.
     */
    private void sellStock(HashMap<String,TradedCompany> dontSell) {
        if(Log.debug){System.out.printf("RandomTrader: Trader (ID %s), offering to sell stock on TradingExchange for %s.\n",ID,client.getName());}

        // The full list of Company stocks availabile
        ArrayList<TradedCompany> allCompanies = new ArrayList<>();                                  // choose from share the client has
        for(Map.Entry<TradedCompany,Integer> comp : client.getShares().entrySet()){
            TradedCompany company = comp.getKey();
            if (!company.equals(dontSell.get(company.getName()))){
                allCompanies.add(comp.getKey());
            }
        }

        Collections.shuffle(allCompanies);

        double sellPercentage = currentState.getSellPerc() * Math.random();
        double maxSellPrice = client.getTotalSharesValue() * sellPercentage;

        // List of Shares to buy
        ArrayList<Pair<TradedCompany,Integer>> selling = new ArrayList<>();

        for(TradedCompany company:allCompanies){                    // for each available company
            double shareVal = company.getShareValue();                  // get the share value
            if(shareVal < maxSellPrice){                               // if they have enough money
                int noShares = (int)(maxSellPrice/shareVal);               // bid for x shares
                if (noShares>client.getShares(company)){
                    noShares = client.getShares(company);
                }
                maxSellPrice -= noShares*shareVal;                         // subtract the cost from money availble for this round
                selling.add(new Pair<>(company,noShares));                   // add shares to the buying list
            }
        }

        // Make orders to exchange
        exchange.sellShares(client,selling);

    }

    @Override
    public ArrayList<Portfolio> getAllClients() {
        ArrayList<Portfolio> a = new ArrayList<>();
        if(!(client == null)){a.add(client);}
        return a;
    }



}
