package SimulationModel;

import dattlee.usefuls.Pair;

import java.util.HashMap;

/**
 * This Class holds the information for each client in the simulation. It is managed by traders classes, which may buy
 * and sell stock the their own discression. However there are parameters which may be adapted to control fow of funds
 * and assets. These include 'risk' and 'liquidate'.
 *
 * The client must be given a name upon creation which is interchangeable with a unique ID.
 *
 * Note: The currency of the cash held by a portfolio is not stored.
 *
 * @version 1.0
 * @author Dattlee ¯\_(ツ)_/¯
 */
public class Portfolio {


    /* **************************************************
     *
     *                      Fields
     *
     ****************************************************/

    private String name;
    private RiskLevel risk;
    private double cash;
    private HashMap<TradedCompany,Integer> companyShares;
    private boolean liquidate;


    /* **************************************************
     *
     *                   Constructors
     *
     ****************************************************/

    /**
     * Create a portfolio for a client.
     *
     * By default:
     *  - risk is set to 'LOW'
     *  - no shares are given
     *
     * @param name the name, or unique identification.
     */
    public Portfolio(String name){
        this.name = name;
        risk = RiskLevel.LOW;
        companyShares = new HashMap<>();
        liquidate = false;
    }

    /**
     * Create a portfolio for a client, and give a list of Stocks.
     *
     * By default:
     *  - risk is set to 'LOW'
     *
     * @param name a String representitive of a name or unique identification.
     * @param stocks an array of Pairs, containing Traded companies and an Integer number of shares.
     */
    public Portfolio(String name, Pair<TradedCompany,Integer>[] stocks){
        this.name = name;
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


    /**
     * Create a portfolio for a client, and give a list of Stocks an cash holding value.
     *
     * By default:
     *  - risk is set to 'LOW'
     *
     * @param name a String representitive of a name or unique identification.
     * @param stocks an array of Pairs, containing Traded companies and an Integer number of shares.
     * @param cashHolding a double, representing the amount of cash the portfolio starts with.
     */
    public Portfolio(String name, Pair<TradedCompany,Integer>[] stocks, Double cashHolding){
        this.name = name;
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


     /* **************************************************
     *
     *                  Methods
     *
     ****************************************************/

    /**
     * Change the state of the client, such that a Trader would offer all stock to the market in each cycle, until
     * changed back.
     *
     * @param liquidate a boolean.
     */
    public void setLiquidate(boolean liquidate) {
        this.liquidate = liquidate;
    }

    /**
     * Returns the current state of the client, to be used by the tradi.
     *
     * @return a boolean, true if the client wants to sell all stocks in a cycle.
     */
    public boolean isLiquidate() {
        return liquidate;
    }

    /**
     * Return the protfolio name, used interchangeably with ID.
     *
     * @return the name of the client.
     */
    public String getName() {
        return name;
    }

    /**
     * Change the name of the client.
     *
     * @param name a String, name of the client.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the current risk level of the portfolio.
     *
     * @return RiskLevel enum
     */
    public RiskLevel getRisk(){
        return risk;
    }

    /**
     * Update the current risk associated with the profile.
     *
     * @param risk an enum RiskLevel.
     */
    public void setRisk(RiskLevel risk){
        this.risk = risk;
    }

    /**
     * Returns a {@link HashMap}<{@link TradedCompany},{@link Integer} of all Traded Companies and the number of shares
     * of each.
     */
    public HashMap<TradedCompany,Integer> getShares() {
        return companyShares;
    }

    /**
     * Returns the number of shares of a specified company as an {@link Integer} held by this portfolio.
     *
     * @param company a TradedCompany
     * @return the amount Integer number of shares held by the portfolio for a given company.
     */
    public Integer getShares(TradedCompany company) {
        return (companyShares.containsKey(company)) ? companyShares.get(company) : 0;
    }

    /**
     * Adds a number of shares of the specified company to the portfolio, and charges the account the current value of
     * the shares.
     *
     * This method does not have any error prevention, for the case in which a portfolio doesn't have the funds to
     * purchase stock. So, the cash may fall to a negative value.
     *
     * @param company the company which to add/update in companyShares.
     * @param numOfShares an int number of shares of which to add to the company.
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

    /**
     * Removes a number of shares of the specified company to the portfolio.
     *
     * @param company - the company which to add to the portfolio, or update.
     * @param numOfShares - the number of shares of which to add.
     *
     * @exception IllegalArgumentException if attempting to sell more shares than that held by the portfolio.
     * @exception IllegalArgumentException if attempting to sell shares for a company that is not held by the portfolio.
     */
    public void sellShares(TradedCompany company, int numOfShares) throws IllegalArgumentException {
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
                throw new IllegalArgumentException(String.format("Attempting to sell more Shares than that held of the company %s",company.getName()));
            }

        } else {
            throw new IllegalArgumentException(String.format("No shares held for the company %s",company.getName()));
        }
    }

    /**
     * Returns the amount of cash in the portfolio.
     *
     * @return a double, representing the amount of cash held by portfolio.
     */
    public double getCash() { return cash; }

    /**
     * Adds the given amount of cash to the portfolio.
     *
     * @param cash a double, representing an amount of cash.
     */
    public void addCash(double cash) { this.cash += cash; }

    /**
     * Removes an the given amount of cash from the portfolio's cash.
     *
     * @param cash a double,
     */
    public void removeCash(double cash) {
        // Ensure can't remove more cash than profile holds
        this.cash -= cash;

    }

    /**
     * Returns the total value of the portfolio, as a double.
     *
     * This includes the total amount of cash held in addition to the value of each share at the time the method is
     * called.
     *
     * @return a double.
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
