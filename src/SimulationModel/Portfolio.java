package SimulationModel;

import dattlee.usefuls.Pair;

import java.util.HashMap;

/**
 * Created by Dattlee on 30/03/2017.
 * ¯\_(ツ)_/¯
 *
 * NOTE: May need to refactor code such that the company shares field holds a string
 * instead of a Traded do
 *
 * NOTE: Not associating portfolio with trader.
 */
public class Portfolio {


    /* **************************************************
     *
     *                  Fields
     *
     ****************************************************/

    private String id;
    private RiskLevel risk;
    private double cash;
    private HashMap<TradedCompany,Integer> companyShares;
    private boolean liquidate;


    /* **************************************************
     *
     *                  Constructors
     *
     ****************************************************/

    /*
     * Create a portfolio for a client.
     *
     * By default:
     *  - risk is set to 'LOW'
     *  - no shares are given
     *
     * @param id the unique identification number of this portfolio
     */
    public Portfolio(String id){
        this.id = id;
        risk = RiskLevel.LOW;
        companyShares = new HashMap<>();
        liquidate = false;
    }

    /*
     * Create a portfolio for a client, and give a list of Stocks.
     *
     * By default:
     *  - risk is set to 'LOW'
     *
     * @param id the unique identification number of this portfolio
     */
    public Portfolio(String id, Pair<TradedCompany,Integer>[] stocks){
        this.id = id;
        risk = RiskLevel.LOW;
        companyShares = new HashMap<>();
        liquidate = false;

        int size = stocks.length;
        for (int i = 0; i < size; i++){
            Integer shares = stocks[i].getSecond();
            TradedCompany company = stocks[i].getFirst();
            companyShares.put(company, shares);
        }
    }

<<<<<<< HEAD
    /*
=======
    /**
     * Create a portfolio for a client, and give a list of Stocks an cash holding value.
     *
     * By default:
     *  - risk is set to 'LOW'
     *
     * @param id the unique identification number of this portfolio
     */
    public Portfolio(String id, Pair<TradedCompany,Integer>[] stocks, Double cashHolding){
        this.id = id;
        risk = RiskLevel.LOW;
        companyShares = new HashMap<>();
        liquidate = false;
        this.cash = cashHolding;

        int size = stocks.length;
        for (int i = 0; i < size; i++){
            Integer shares = stocks[i].getSecond();
            TradedCompany company = stocks[i].getFirst();
            companyShares.put(company, shares);
        }
    }

    /**
>>>>>>> 7930b09ca3473f5830bd578132660e802e4d4e12
     * Create a portfolio for a client.
     *
     * By default:
     * -    no shares are given
     *
     * @param id the unique identification number of this portfolio
     */
    public Portfolio(String id, RiskLevel risk) {
        this.id = id;
        this.risk = risk;
        companyShares = new HashMap<>();
        liquidate = false;


    }


    /*
     * Constructor to create a portfolio with stocks, and to set the risk Preference.
     * @param id Name of the client
     * @param risk Risk of the portfolio
     * @param stocks Shares to start with
     */
    public Portfolio(String id, RiskLevel risk, Pair<TradedCompany,Integer>[] stocks){
        this.id = id;
        this.risk = risk;
        liquidate = false;


        // upload all initial stocks
        int size = stocks.length;
        for (int i = 0; i < size; i++){
            Integer shares = stocks[i].getSecond();
            TradedCompany company = stocks[i].getFirst();
            companyShares.put(company, shares);
        }
    }

     /* **************************************************
     *
     *                  Methods
     *
     ****************************************************/

    /*
     * Change the state of the client to sell all stocks
     * @param liquidate - Boolean
     */
    public void setLiquidate(boolean liquidate) {
        this.liquidate = liquidate;
    }

    /*
     * @return - True if the client wants to sell all stocks
     */
    public boolean isLiquidate() {
        return liquidate;
    }

    /*
     * Return the protfolio ID, used interchangeably with client Name
     * @return id - String
     */
    public String getId() {
        return id;
    }

    /*
     * Change the Portfolio ID to id
     * @param id name of the client
     */
    public void setId(String id) {
        this.id = id;
    }

    /*
     * Returns the current risk level of the portfolio
     * @return risk - RiskLevel
     */
    public RiskLevel getRisk(){
        return risk;
    }

    /*
     * Change the risk of the profile to risk
     */
    public void setRisk(RiskLevel risk){
        this.risk = risk;
    }

    /*
     * Returns a {@link HashMap}<{@link TradedCompany},{@link Integer} of all Traded Companies and the number of shares of each.
     */
    public HashMap<TradedCompany,Integer> getShares() {
        return companyShares;
    }

    /*
     * Returns the number of shares of a specified company as an {@link Integer} held by this portfolio.
     * @param company
     * @return the amount nnumber of shares held for a company 'company'
     */
    public Integer getShares(TradedCompany company) {
        return (companyShares.containsKey(company)) ? companyShares.get(company) : 0;
    }

    /*
     * Returns a HashMap of all company shares
     * @return All shares held buy the portfolio
     */
    public HashMap<TradedCompany, Integer> getCompanyShares() {
        return companyShares;
    }

    /*
     * Adds a number of shares of the specified company to the portfolio,
     * And charges the account (cash)
     * @param company - the company which to add to the portfolio, or update
     * @param numOfShares - the number of shares of which to add
     *
     * Error Prevention: Stop it from puchacing if there aren't the funds
     */
    public void buyShares(TradedCompany company, int numOfShares) {

        double cost = numOfShares * company.getShareValue();

        if (companyShares.containsKey(company)) {
            int total = companyShares.get(company) + numOfShares;
            cash -= cost;                                                   // Charge account
            companyShares.replace(company,total);                           // Add shares
        } else {
            cash -= cost;                                                   // Charge account
            companyShares.put(company, numOfShares);                        // Add shares
        }
    }

    /*
     * Removes a number of shares of the specified company to the portfolio
     * @param company - the company which to add to the portfolio, or update
     * @param numOfShares - the number of shares of which to add
     */
    public void sellShares(TradedCompany company, int numOfShares) throws Exception {
        // If the company shares exist in the Portfolio
        if (companyShares.containsKey(company)) {

            // If the number of shares you wish to sell are held by the account
            if (companyShares.get(company) <= numOfShares) {

                double credit = company.getShareValue() * numOfShares;
                // subtract the number of shares from the Portfolio
                int total = companyShares.get(company) - numOfShares;
                cash += credit;                                             // Credit Account
                companyShares.replace(company,total);                       // Remove Shares

            } else {
                throw new Exception(String.format("Attempting to sell more Shares than that held of the company %s",company.getName()));
            }

        } else {
            throw new Exception(String.format("No shares held for the company %s",company.getName()));
        }
    }

    /*
     * Returns the amount of cash in the portfolio
     * @return the amount of cash held by portfolio
     */
    public double getCash() { return cash; }

    /*
     * Adds an x amount of cash to the portfolio
     */
    public void addCash(double x) { cash += x; }

    /*
     * Removes an x amount of cash from the portfolio
     */
    public void removeCash(double x) {
        // Ensure can't remove more cash than profile holds
        if (x <= cash ) {
            cash -= x;
        }
    }

    /*
     * Returns the total cash value of the portfolio.
     * This includes the total amount of cash held in addition to
     * the value of each share at the time the method is called.
     * @return the total value of a Portfolio
     */
    public double getValue() {
        // The total amount of money
        double total = 0;

        // for each item multiply the number of shares by their value and add it to the total
        for (HashMap.Entry<TradedCompany, Integer> entry : companyShares.entrySet()) {
            TradedCompany company = entry.getKey();
            Integer shares = entry.getValue();
            total += shares * company.getShareValue();
        }

        // add held cash
        total += getCash();

        return total;
    }

}
