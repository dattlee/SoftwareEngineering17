package SimulationModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created 08/05/2017
 *
 * @author Dattlee
 * @version 1.0
 */
public class StockMarket {


    /* **************************************************
     *
     *                  Fields
     *
     ****************************************************/
    private TradingExchange exchange;
    private HashMap<String,TradedCompany> allCompanies;
    private HashMap<String,Portfolio> allClients;
    private MarketState marketState;



	/* **************************************************
     *
     *                  Constructors
     *
     ****************************************************/
    /**
     * Constructor  that initiates new instance of CsvImport using file paths to data already existent within the
     * project, data is then processed by the CsvImport object and passed to a new instance of trading exchange through
     * its params.
     *
     * @param companies a list of companies to initialise the market with.
     */
    public StockMarket(List<TradedCompany> companies){
        if(Log.debug){System.out.printf("StockMarket: Creating a new StockMarket with %s companies.\n",companies.size());}
        exchange = new TradingExchange(companies);

        allCompanies = new HashMap<>(companies.size()*2);
        for(TradedCompany company:companies){
            allCompanies.put(company.getName(),company);
        }

        allClients = new HashMap<>();

        marketState = MarketState.STABLE;
    }


    /**
     * Constructor  that initiates new instance of CsvImport using file paths to data already existent within the
     * project, data is then processed by the CsvImport object and passed to a new instance of trading exchange through
     * its params.
     *
     * @param companies a list of companies to initialise the market with.
     * @param portfolios a list of clients to initialise the market with.
     */
    public StockMarket(List<TradedCompany> companies, List<Portfolio> portfolios){
        if(Log.debug){System.out.printf("StockMarket: Creating a new StockMarket with %s companies & %s clients.\n",companies.size(), portfolios.size());}
        exchange = new TradingExchange(companies,portfolios);

        // Used to track alll
        allCompanies = new HashMap<>(companies.size()*2);
        for(TradedCompany company:companies){
            allCompanies.put(company.getName(),company);
        }

        allClients = new HashMap<>(portfolios.size()*2);
        for(Portfolio port:portfolios){
            allClients.put(port.getName(),port);
        }

        marketState = MarketState.STABLE;
    }


    /* **************************************************
     *
     *                  Methods
     *
     ****************************************************/


    public ArrayList<TradedCompany> getAllTradedCompanies(){
        ArrayList<TradedCompany> tcs = new ArrayList<>();
        for (HashMap.Entry<String,TradedCompany> company:allCompanies.entrySet()){
            tcs.add(company.getValue());
        }
        return tcs;
    }

    public TradedCompany getCompany(String name) throws Exception {
        for (TradedCompany company: getAllTradedCompanies()) {
            if (company.getName() == name) {
                return company;
            }
        }
        throw new Exception("StockMarket: No company called "+name+".");
    }


    public ArrayList<Portfolio> getAllPortfolios(){
        ArrayList<Portfolio> ps = new ArrayList<>();
        for (HashMap.Entry<String,Portfolio> client:allClients.entrySet()){
            ps.add(client.getValue());
        }
        return ps;
    }

    //Gets a trader (necessary to get all traders)
    public ArrayList<Trader> getAllTraders(){
        return exchange.getAllTraders();
    }

    public double getMarketValue(){
        double total = 0;
        for(TradedCompany company:getAllTradedCompanies()){
            total += company.getValue();
        }
        return total;
    }

    public void addPortfolioIntelli(Portfolio client) {
        exchange.newIntelTrader(client);

    }

    public void addPortfolioRand(Portfolio client) {
        exchange.newRandTrader(client);

    }


    public void addCompany(TradedCompany company) {

    }

    /**
     * Returns the trading exchange object within stock market
     *
     * @return the trading exchange object
     */
    public TradingExchange getExchange(){
        return exchange;
    }

    public void act() {
        exchange.act();
    }
}
