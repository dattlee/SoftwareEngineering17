package SimulationModel;


import dattlee.usefuls.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This Test Class ensures that all sale and purchasing offers are recorded correctly in a Trading Exchange during each
 * cycle.
 *
 * Test requirements:
 *  - TradedCompany
 *
 * @author Dattlee
 * @version 1.0
 */
public class PortfolioTest {

    Portfolio port1,port2;
    TradedCompany google,micro;

    @Before
    public void setUp() throws Exception {
        port1 = new Portfolio("Megan",100000.00); // Should initialize Risk to low by default
        google = new TradedCompany("google",ShareType.HITECH,24494,50);
        micro = new TradedCompany("micro",ShareType.HITECH,24494,50);

        Pair<TradedCompany,Integer>[] shares = new Pair[]{new Pair<>(google,14),new Pair<>(micro,20)};
        port2 = new Portfolio("Charles",shares,100000.00);             // £1000
    }

    @After
    public void tearDown() throws Exception {
        port1 = null;
        google = null;
        micro = null;
        port2 = null;
    }


    /**
     * Asserts that when stocks are purchased that the Client is charged the value of the stocks.
     *
     * Checks made:
     *  - New stocks added to Holdings
     *  - When stocks already owned for a company, they are added to the holdings
     *  - the account is charged the value of the stock
     *
     * Test requirements:
     *  - Portfolio.getShares() returns a Hashmap of TradedCompany's and Integers
     *  - Portfolio.getCash()
     *
     * @throws Exception Standard Exceptions
     */
    @Test
    public void buyShares() throws Exception {

        double startingCash = port1.getCash();
        // Checks empty, with cash held
        assertEquals(port1.getShares().entrySet().size(),0);
        System.out.println(port1.getCash() == 100000.0);
        assertTrue(port1.getCash() == 100000.0);

        // Number of stocks buying in google
        int numberShares = 12;
        port1.buyShares(google,numberShares);

        // Stocks bought and charged
        assertEquals(port1.getShares().entrySet().size(),1);
        assertTrue(port1.getShares(google).equals(numberShares));
        assertTrue(port1.getCash() == (startingCash - numberShares*google.getShareValue()));

        // Buying more stocks
        int numberShares2 = 10;
        port1.buyShares(google,numberShares2);
        assertEquals(port1.getShares().entrySet().size(),1);
        assertTrue(port1.getShares(google).equals(numberShares+numberShares2));
        assertTrue(port1.getCash() == startingCash - (numberShares2+numberShares)*google.getShareValue());


        // Buying more stocks in another company
        double currentCash = startingCash - (numberShares2+numberShares)*google.getShareValue();
        int numberShares3 = 10;
        port1.buyShares(micro,numberShares3);
        assertEquals(port1.getShares().entrySet().size(),2);
        assertTrue(port1.getShares(micro).equals(numberShares3));
        assertTrue(port1.getCash() == currentCash - numberShares3*micro.getShareValue());
    }

    /**
     * Asserts that when stocks are sold that the Client is credited the value of the stocks.
     *
     * Checks made:
     *  - Stocks removed from holdings
     *  - When stocks already owned for a company, they are subtracted to the holdings
     *  - the account is credited the value of the stocks
     *  - Company NOT removed when all stocks sold
     *
     * Test requirements:
     *  - Portfolio.getShares() returns a Hashmap of TradedCompany's and Integers
     *  - Portfolio.getCash()
     *
     * @throws Exception Standard Exceptions
     */
    @Test
    public void sellShares() throws Exception {


        double startingCash = port2.getCash();
        // Checks 2 stocks held, with cash held
        assertEquals(port2.getShares().entrySet().size(),2);
        assertTrue(port2.getCash() == 100000.0);

        // Number of stocks selling in google
        int numberShares = 14;
        port2.sellShares(google,numberShares);

        // Stocks bought and charged
        assertEquals(port2.getShares().entrySet().size(),2);
        assertTrue(port2.getShares(google).equals(0));
        assertTrue(port2.getCash() == (startingCash + numberShares*google.getShareValue()));

        // Sell more stocks
        startingCash += numberShares*google.getShareValue();
        int numberShares2 = 10;
        port2.sellShares(micro,numberShares2);
        assertEquals(port2.getShares().entrySet().size(),2);
        assertTrue(port2.getShares(micro).equals(numberShares2));
        assertTrue(port2.getCash() == startingCash + numberShares2*micro.getShareValue());


        // Selling more stocks in same company
        int numberShares3 = 10;
        port2.sellShares(micro,numberShares3);
        assertEquals(port2.getShares().entrySet().size(),2);
        assertTrue(port2.getShares(micro).equals(0));
        assertTrue(port2.getCash() == startingCash + (numberShares3+numberShares2)*micro.getShareValue());
    }

    /**
     * Asserts that right amount of money is added.
     *
     * Checks made:
     *  - Cash is added to the account
     *
     * Test requirements:
     *  - Portfolio.getCash()
     *
     * @throws Exception Standard Exceptions
     */
    @Test
    public void addCash() throws Exception {
        assertTrue(port2.getCash() == 100000.00);
        port2.addCash(1000.1);
        assertTrue(port2.getCash() == 101000.1);
    }

    /**
     * Asserts that right amount of money is removed.
     *
     * Checks made:
     *  - Cash is removed from the account
     *  - That cash held can be negative (if a client in debt)
     *
     * Test requirements:
     *  - Portfolio.getCash()
     *
     * @throws Exception Standard Exceptions
     */
    @Test
    public void removeCash() throws Exception {
        assertTrue(port2.getCash() == 100000.00);
        port2.removeCash(100000.0);
        assertTrue(port2.getCash() == 0.0);
        port2.removeCash(100000.0);
        assertTrue(port2.getCash() == (-100000.0));
    }

}