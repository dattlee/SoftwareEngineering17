package SimulationModel;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Dattlee on 30/03/2017.
 */
public class PortfolioTest {

    Portfolio port1;

    @Before
    public void setUp() throws Exception {
        port1 = new Portfolio("1001"); // Should initialize Risk to low by default
    }

    @Test
    public void getId() throws Exception {
        assertEquals(port1.getName(),"1001");
    }

    @Test
    public void setId() throws Exception {
        assertEquals(port1.getName(),"1001");
        port1.setName("2001");
        assertEquals(port1.getName(),"2001");
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
        port1.getTotalSharesValue();
        port1.getShares();

    }

    @Test
    public void getSharesSinglecompany() throws Exception {
        // Need to assert a singe number/ Integer is returned
        assertTrue(1==0);
    }

    @Test
    public void buyShares() throws Exception {
        /*
        Need to assert:
        1. When buying shares you add the number of shares bought to the
        */
        assertTrue(1==0);
    }

    @Test
    public void sellShares() throws Exception {
        /*
        Need to assert:
        1. Can't sell more shares than owned for a company
        2. Can't sell shares for a company not owned
         */
        assertTrue(1==0);
    }

    @Test
    public void getCash() throws Exception {
        /*
        Need to assert:
        1. Can't sell more shares than owned for a company
        2. Can't sell shares for a company not owned
         */
        assertTrue(1==0);
    }

    @Test
    public void addCash() throws Exception {
        /*
        Need to assert:
        1. Cash total increases by exactly amount given
         */
        assertTrue(1==0);
    }

    @Test
    public void removeCash() throws Exception {
        /*
        Need to assert:
        1. Cash total decreases by exactly taken given
         */
        assertTrue(1==0);
    }

    @Test
    public void getValue() throws Exception {
        /*
        Need to assert:
        1. Cash is added to value of portfolio
        2. Shares are added to the value of the portfolio
         */
        assertTrue(1==0);
    }


}