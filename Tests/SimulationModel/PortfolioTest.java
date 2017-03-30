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
        assertEquals(port1.getId(),"1001");
    }

    @Test
    public void setId() throws Exception {
        assertEquals(port1.getId(),"1001");
        port1.setId("2001");
        assertEquals(port1.getId(),"2001");
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

    @Test
    public void getCash() throws Exception {
        assertTrue(1==0);
    }

    @Test
    public void buyShares() throws Exception {
        assertTrue(1==0);
    }

    @Test
    public void sellShares() throws Exception {
        assertTrue(1==0);
    }


}