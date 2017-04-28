package SimulationModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Dattlee on 21/04/2017.
 * ¯\_(ツ)_/¯
 *
 * Traders of this class can only hold and manage 1 portfolio at a time.
 */
public class RandomTraderSgl extends Trader {

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
     * Create a trader and add it to the Trading Exchange
     *
     * @param ID - A unique ID assigned to a trader when they join a Trading Exchange
     * @param exchange - A reference to the trading exchange that the Trader has joined
     */
    public RandomTraderSgl(String ID, TradingExchange exchange) {
        super(ID, exchange);
        currentState = TraderState.BALANCED;
    }


    /**
     * Create a trader and add it to the Trading Exchange
     *
     * @param ID - A unique ID assigned to a trader when they join a Trading Exchange
     * @param exchange - A reference to the trading exchange that the Trader has joined
     * @param portfolio - The portfolio that the trader is responsible for.
     */
    public RandomTraderSgl(String ID, TradingExchange exchange, Portfolio portfolio) {
        super(ID, exchange);
        currentState = TraderState.BALANCED;
        try {
            addPortfolio(portfolio);
        } catch (Exception e) {
            System.out.println("Somehow there is already a portfolio associated with this " +
                    "new trader... Fuck knows how that happened ¯\\_(ツ)_/¯");


        }
    }

    /* **************************************************
     *
     *                  Methods
     *
     ****************************************************/

    /**
     *
     * @param portfolio
     * @throws Exception - When a Trader is already responsible for a portfolio
     */
    public void addPortfolio(Portfolio portfolio) throws Exception {
        if(this.client == null){
            this.client = portfolio;
        } else {
            throw new Exception("There is already a portfolio assigned to this trader.");
        }
    }

//    /**
//     *
//     * @throws Exception
//     */
//    public void removePortfolio() throws Exception {
//        if(this.client == null){
//            throw new Exception("There is no portfolio assigned to this trader.");
//        } else {
//            this.client = null;
//        }
//    }

    /**
     * This method is called during every cycle. It follows this process:
     *      - If the client wants to liquidate all of their stock, offer all stock on the market
     *      - Else
     *          > Buy all stock based on the state of the trader
     *          > Then, Sell stock based on the state of the trader
     */
    @Override
    public void act() {

        if(client.isLiquidate()) {
            sellAllStock();
        }else {

            // buy something
            TradedCompany dontSell = buyStock();

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
     * Method used by Trader to sell of a clients stock when they have asked for stocks to be liquidated.
     */
    private void sellAllStock(){

        for (HashMap.Entry<TradedCompany, Integer> entry : client.getCompanyShares().entrySet())
        {
            exchange.sellShares(client, entry.getKey(),entry.getValue());
        }
    }

    /**
     * Used buy the act method. It follows this process:
     *      - Searches from all the available company stocks
     *      - Purchase the Maximum amount of stock from one company that a portfolio can afford.
     */
    private TradedCompany buyStock() {
        // choose a company stock
        ArrayList<TradedCompany> allCompanies = exchange.getAllCompanies();
        int picked = new Random().nextInt(allCompanies.size());
        TradedCompany chosen = allCompanies.get(picked);

        // choose number of shares
        double buyingPercentage = currentState.getBuyPerc() * Math.random();
        double maxPurchPrice = client.getValue() * buyingPercentage;
        if(maxPurchPrice>client.getCash()){
            maxPurchPrice = client.getCash();
        }
        int shares2buy = (int)(maxPurchPrice/chosen.getShareValue());                 // maximum number of shares the client can afford, int wrapping rounds down

        // make the offer to the market the stock
        exchange.buyShares(client,chosen,shares2buy);
        return chosen;

    }

    /**
     * Sell as much stock up to a maximum from company that is available on the stockmarket.
     * EXCEPT for a given company 'dontSell'.
     */
    private void sellStock(TradedCompany dontSell) {
        // choose a company stock
        ArrayList<TradedCompany> allCompanies = new ArrayList<>();                                  // choose from share the client has

        for(Map.Entry<TradedCompany,Integer> comp : client.getCompanyShares().entrySet()){
            if (comp.getKey().equals(dontSell)){
                System.out.println("already buying this item");
            } else {
                allCompanies.add(comp.getKey());
            }
        }


        if(allCompanies.size()>0) { // prevent trying to buy when there is no stock

            int picked = new Random().nextInt(allCompanies.size());
            TradedCompany chosen = allCompanies.get(picked);

            // choose number of shares
            double sellPercentage = currentState.getSellPerc() * Math.random();
            double maxSellPrice = client.getValue() * sellPercentage;

            if (maxSellPrice >= chosen.getShareValue()) {
                int numberSelling = (int) (maxSellPrice / chosen.getShareValue());
                exchange.sellShares(client, chosen, numberSelling);                                   // offer shares to market
            } else {
                System.out.println("cant afford to by stock this time round");
            }
        } else {
            System.out.println("no companies to buy shares in");
        }

    }


}
