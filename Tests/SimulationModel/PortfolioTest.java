package SimulationModel;


import dattlee.usefuls.Pair;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Dattlee on 30/03/2017.
 */
public class PortfolioTest {

    Portfolio port1,port2;
    TradedCompany google;

    @Before
    public void setUp() throws Exception {
        port1 = new Portfolio("Megan"); // Should initialize Risk to low by default
        google = new TradedCompany("google",ShareType.HITECH,24494,50);
        Pair<TradedCompany,Integer>[] shares = new Pair[]{new Pair<>(google,14)};
        port2 = new Portfolio("temp",shares,1234.56);
    }

    @Test
    public void getId() throws Exception {
        assertEquals(port1.getName(),"Megan");
    }

    @Test
    public void setId() throws Exception {
        assertEquals(port1.getName(),"Megan");
        port1.setName("Charlie");
        assertEquals(port1.getName(),"Charlie");
    }

    @Test
    public void getRisk() throws Exception {
        // Risk should have been initialized to LOW by default
        assertEquals(port1.getRisk(),RiskLevel.LOW);
    }

    @Test
    public void setRisk() throws Exception {
        assertEquals(port1.getRisk(),RiskLevel.LOW);
        port1.setRisk(RiskLevel.HIGH);
        assertEquals(port1.getRisk(),RiskLevel.HIGH);
    }

    // No test method for any getShares methods

    @Test
    public void getSharesHashMap() throws Exception {
        // need to assert that the hashmap returned contains all companies and shares added to it


        assertEquals(port1.getShares().entrySet().size(),0);
        port1.buyShares(google,12);

        assertEquals(port1.getShares().entrySet().size(),1);
        for(HashMap.Entry<TradedCompany,Integer> ent :port1.getShares().entrySet()) {
            assertTrue(ent.getKey().equals(google));
        }
    }

    @Test
    public void getSharesSinglecompany() throws Exception {
        // Need to assert a singe number/ Integer is returned

        assertTrue(port2.getShares(google).equals(14));

    }

    /**
     * Need to assert:
     * 1. When buying shares you add the number of shares bought to the
     */
    @Test
    public void buyShares() throws Exception {

        assertEquals(port1.getShares().entrySet().size(),0);
        port1.buyShares(google,12);
        assertTrue(port1.getShares(google).equals(12));
    }

    /**
     * Asserts
     * 1. Can't sell more shares than owned for a company
     * 2. Can't sell shares for a company not owned
     */
    @Test
    public void sellShares() throws Exception {

        assertEquals(port2.getShares().entrySet().size(),1);
        port2.sellShares(google,14);
        assertTrue(port2.getShares(google).equals(0));
        assertTrue(port1.getShares(google).equals(0));
    }

    /**
     * Need to assert:
     * 1. Can't sell more shares than owned for a company
     * 2. Can't sell shares for a company not owned
     */
    @Test
    public void getCash() throws Exception {

        assertTrue(port2.getCash() == 1234.56);
    }

    /**
     * Asserts:
     * 1. Cash total increases by exactly amount given
     */
    @Test
    public void addCash() throws Exception {
        assertTrue(port2.getCash() == 1234.56);
        port2.addCash(1000);
        assertTrue(port2.getCash() == 2234.56);

    }

    /**
     * Need to assert:
     * 1. Cash total decreases by exactly taken given
     */
    @Test
    public void removeCash() throws Exception {
        assertTrue(port2.getCash() == 1234.56);
        port2.removeCash(1);
        assertTrue(port2.getCash() == 1233.56);
    }

    /**
     * Assert:
     * 1. Cash is added to value of portfolio
     * 2. Shares are added to the value of the portfolio
     */
    @Test
    public void getValue() throws Exception {
        System.out.println(port2.getValue());
        assertTrue(port1.getValue() == 0);
        assertTrue(port2.getValue() == 1934.56);
    }


}