package SimulationModel;

import dattlee.usefuls.Pair;

import java.util.HashMap;

/**
 * Created by Dattlee on 30/03/2017.
 *
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

    /**
     * Create a portfolio object.
     *
     * By default:
     * -    risk is set to 'LOW'
     * -    no trader is assigned
     * -    no shares are given
     *
     * @param id the unique identification number of this portfolio
     */
    public Portfolio(String id){
        this.id = id;
        risk = RiskLevel.LOW;
        companyShares = new HashMap<>();
        liquidate = false;
    }

    public Portfolio(String id, RiskLevel risk) {
        this.id = id;
        this.risk = risk;
        companyShares = new HashMap<>();
        liquidate = false;
    }

    public void setLiquidate(boolean liquidate) {
        this.liquidate = liquidate;
    }

    public boolean isLiquidate() {
        return liquidate;
    }

    /**
     * Constructor to create a portfolio with stocks.
     * @param id
     * @param risk
     * @param stocks
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RiskLevel getRisk(){
        return risk;
    }

    public void setRisk(RiskLevel risk){
        this.risk = risk;
    }

    /**
     * Returns a {@link HashMap}<{@link TradedCompany},{@link Integer} of all Traded Companies and the number of shares of each.
     */
    public HashMap<TradedCompany,Integer> getShares() {
        return companyShares;
    }

    /**
     * Returns the number of shares of a specified company as an {@link Integer}.
     * @param company
     * @return
     */
    public Integer getShares(TradedCompany company) {
        return (companyShares.containsKey(company)) ? companyShares.get(company) : 0;
    }

    /**
     * Returns a HashMap of all company shares
     * @return
     */
    public HashMap<TradedCompany, Integer> getCompanyShares() {
        return companyShares;
    }

    /**
     * Adds a number of shares of the specified company to the portfolio
     * @param company - the company which to add to the portfolio, or update
     * @param numOfShares - the number of shares of which to add
     */
    public void buyShares(TradedCompany company, int numOfShares) {
        if (companyShares.containsKey(company)) {
            int total = companyShares.get(company) + numOfShares;
            companyShares.replace(company,total);
        } else {
            companyShares.put(company, (numOfShares));
        }
    }

    /**
     * Removes a number of shares of the specified company to the portfolio
     * @param company - the company which to add to the portfolio, or update
     * @param numOfShares - the number of shares of which to add
     */
    public void sellShares(TradedCompany company, int numOfShares) throws Exception {
        // If the company shares exist in the Portfolio
        if (companyShares.containsKey(company)) {

            // If the number of shares you wish to sell are held by the account
            if (companyShares.get(company) <= numOfShares) {

                // subtract the number of shares from the Portfolio
                int total = companyShares.get(company) - numOfShares;
                Integer integer = total > 0 ? companyShares.replace(company, total) : companyShares.remove(company);

            } else {
                throw new Exception(String.format("Attempting to sell more Shares than that held of the company %s",company.getName()));
            }

        } else {
            throw new Exception(String.format("No shares held for the company %s",company.getName()));
        }
    }

    /**
     * Returns the amount of cash in the portfolio
     */
    public double getCash() { return cash; }

    /**
     * Adds an x amount of cash to the portfolio
     */
    public void addCash(double x) { cash += x; }

    /**
     * Removes an x amount of cash from the portfolio
     */
    public void removeCash(double x) {
        // Ensure can't remove more cash than profile holds
        if (x <= cash ) {
            cash -= x;
        }
    }

    /* ---------------------------------------------------
    /* ---------------------------------------------------

     */
    /**
     * Returns the total cash value of the portfolio.
     * This includes the total amount of cash held in addition to
     * the value of each share at the time the method is called.
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
