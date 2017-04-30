package SimulationModel;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.util.ArrayList;

/**
 * Created by Steven on 29/04/2017.
 */

public class CsvImportTest {
    CsvImport import1;
    ArrayList<Portfolio> ports;

    @Before
    public void setUp() throws Exception {
        import1 = new CsvImport("InitialDataV2.csv", "InitialDataV2portfolio.csv");
    }

    @Test
    public void tradedCompanyExistsTest() throws IOException {
        assertTrue(import1.tradedCompanyExists("Pear Computing"));
        assertTrue(import1.tradedCompanyExists("Gardens 'R' Us"));
        assertTrue(import1.tradedCompanyExists("Sussex Flats"));
        assertTrue(import1.tradedCompanyExists("Skinners"));
        assertFalse(import1.tradedCompanyExists("Always True"));
    }

    @Test
    public void portfolioSetUpTest(){
        /*ports = import1.allPortfolios;
        for (int i = 0; i < ports.size(); i++) {
            System.out.println(ports.get(i).getId());
        }**/

        assertEquals(ports.get(0).getId(),"Norbert DaVinci");
        assertEquals(ports.get(3).getId(),"Sir Melvin Codd");
        assertNotEquals(ports.get(3).getId(),"Norbert DaVinci");

        assertEquals(ports.get(0).getCash(), 100000, 0.000000001);
        assertEquals(ports.get(5).getCash(), 50000, 0.000000001);
        assertNotEquals(ports.get(5).getCash(), 51000, 0.000000001);

        TradedCompany company = import1.getTradedCompany("Pear Computing");
        assertTrue(ports.get(0).getShares(company)== 1505);
        assertTrue(ports.get(9).getShares(company)== 8284);
        assertFalse(ports.get(9).getShares(company)== 1);
    }
}