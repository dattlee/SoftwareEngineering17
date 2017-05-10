package SimulationModel;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

/**
 * Created 08/05/2017
 *
 * @author Dattlee
 * @version 1.0
 */
public class SimulationTest {
    Simulation sim;

    @Before
    public void setUp() throws Exception {
        sim = new Simulation("InitialDataV2.csv", "InitialDataV2portfolio.csv");
    }

    @Test
    public void testGetAllTradedCompanies() throws Exception {
    }

    @Test
    public void testGetCompany() throws Exception {

    }

    @Test
    public void testGetAllTraders() throws Exception {

    }

    @Test
    public void testRunXSteps() throws Exception {

    }

    @Test
    public void testGetDate() throws Exception {

    }

    @Test
    public void testGetAllPortfolios() throws Exception {

    }

    /* Method moved to GUI
    @Test
    public void traderTableSetUpTest() throws Exception {
        String[][] traderTableTest = sim.traderTableSetUp();
        assertEquals(traderTableTest[0][0], "1");
        assertEquals(traderTableTest[1][0], "Norbert DaVinci");
        assertEquals(traderTableTest[0][9], "10");
        assertEquals(traderTableTest[1][9], "Xi Xian");
    }**/
}