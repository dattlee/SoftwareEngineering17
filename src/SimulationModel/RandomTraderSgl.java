package SimulationModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Dattlee on 21/04/2017.
 *
 * Traders of this class can only hold 1 portfolio at a time
 */
public class RandomTraderSgl extends Trader {

    /* **************************************************
     *
     *                  Fields
     *
     ****************************************************/

    public TraderState currentState;
    public Portfolio client;

    /* **************************************************
     *
     *                  Constructors
     *
     ****************************************************/

    public RandomTraderSgl(String ID, TradingExchange exchange) {
        super(ID, exchange);
        currentState = TraderState.BALANCED;
    }

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

    public void addPortfolio(Portfolio portfolio) throws Exception {
        if(this.client == null){
            this.client = portfolio;
        } else {
            throw new Exception("There is already a portfolio assigned to this trader.");
        }
    }

    public void removePortfolio() throws Exception {
        if(this.client == null){
            throw new Exception("There is no portfolio assigned to this trader.");
        } else {
            this.client = null;
        }
    }

    /**
     * used buy the simulation class of every 15 minute period
     */
    @Override
    public void act() {

        if(client.isLiquidate()) {
            sellAllStock();
        }else {

            // either buy and/or sell stock, can not buy same stock that it is selling

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
     * can be improved.
     */
    private void sellAllStock(){

        for (HashMap.Entry<TradedCompany, Integer> entry : client.getCompanyShares().entrySet())
        {
            exchange.sellShares(client, entry.getKey(),entry.getValue());
        }
    }

    /**
     * neeed to build trading excahnge to continue
     * Need to stop from buying the same stock that it is selling
     */
    public TradedCompany buyStock() {
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
     * need to build trading exchange to continue
     */
    public void sellStock(TradedCompany dontSell) {
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

    /**
     * No idea why this was made... ¯\_(ツ)_/¯
     */
    @Override
    public void removeStockFromMarket() {

    }
}
