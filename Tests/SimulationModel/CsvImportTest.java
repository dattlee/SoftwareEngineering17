package SimulationModel;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * This Test Class ensures that all 3 CSV's can be imported and are transfered to the correct data types.
 * The 3 CSV's being:
 *  - Companies
 *  - Clients
 *  - Events
 *
 * @author sdocker
 * @version 1.0
 */

public class CsvImportTest {
    CsvImport import1;
    ArrayList<Portfolio> ports;
    TradedCompany company;
    ArrayList<String[]> events;

    @Before
    public void setUp() throws Exception {
        import1 = new CsvImport("InitialDataV2.csv", "InitialDataV2portfolio.csv", "ExternalEventData.csv");
    }

    /**
     * Test 1 - check that all 4 traded companies within the InitialDataV2.csv have imported correctly and been
     * added to the HashMap and that the key exists equal to the company names, check that company that has not been
     * imported returns false
     *
     * @throws IOException when no CSV is detected
     */
    @Test
    public void tradedCompanyExistsTest() throws IOException {
        assertTrue(import1.tradedCompanyExists("Pear Computing"));
        assertTrue(import1.tradedCompanyExists("Gardens 'R' Us"));
        assertTrue(import1.tradedCompanyExists("Sussex Flats"));
        assertTrue(import1.tradedCompanyExists("Skinners"));
        assertFalse(import1.tradedCompanyExists("Always True"));
    }

    /**
     * get traded company and check that all Traded Company information has been corrected imported and added
     * to the TradedCompany object, completed on two separate TradedCompany objects. Information check is name ID,
     * share type, shares issued and share value
     */
    @Test
    public void tradedCompanySetUpTest(){
        company = import1.getTradedCompany("Pear Computing");
        assertEquals(company.getName(), "Pear Computing");
        assertEquals(company.getShareType(), ShareType.HITECH);
        assertNotEquals(company.getShareType(), ShareType.HARD);
        assertEquals(company.getSharesIssued(), 50000);
        assertNotEquals(company.getSharesIssued(), 25000);
        assertEquals(company.getShareValue(), 650, 0.00001);
        assertNotEquals(company.getShareValue(), 300, 0.00001);

        company = import1.getTradedCompany("Whizzer and Chipps");
        assertEquals(company.getName(), "Whizzer and Chipps");
        assertEquals(company.getShareType(), ShareType.FOOD);
        assertNotEquals(company.getShareType(), ShareType.HITECH);
        assertEquals(company.getSharesIssued(), 31000);
        assertNotEquals(company.getSharesIssued(), 20000);
        assertEquals(company.getShareValue(), 210, 0.000000001);
        assertNotEquals(company.getShareValue(), 200, 0.000000001);
    }

    /**
     * Check that all portfolios within the .csv have been imported and check TradedCompany are importing correctly
     *
     * Test 1 - check Norbert DaVinci exists as element0, Sir Melvin Codd exists as element3, Xi Xian as element9 and
     * confirm Sir Melvin Codd does not exist in element3, confirm that portfolio element0 contains 10000000 and element5
     * contains 5000000 and confirm element5 returns false on a different value
     *
     * Test 2 - check Portfolio Norbert DaVinci contains 1505 shares in TradedCompany Pear Computing, check portfolio Xi Xian
     * contains 8284 shares in TradedCompany Pear Computing and confirm incorrect share number returns false
     */
    @Test
    public void portfolioSetUpTest(){
        ports = import1.allPortfolios;
        for (int i = 0; i < ports.size(); i++) {
            System.out.println(ports.get(i).getName());
        }

        assertEquals(ports.get(0).getName(),"Norbert DaVinci");
        assertEquals(ports.get(3).getName(),"Sir Melvin Codd");
        assertEquals(ports.get(9).getName(),"Xi Xian");
        assertNotEquals(ports.get(3).getName(),"Norbert DaVinci");

        assertEquals(ports.get(0).getCash(), 10000000, 0.000000001);
        assertEquals(ports.get(5).getCash(), 5000000, 0.000000001);
        assertNotEquals(ports.get(5).getCash(), 5100000, 0.000000001);

        TradedCompany company = import1.getTradedCompany("Pear Computing");
        assertTrue(ports.get(0).getShares(company)== 1505);
        assertTrue(ports.get(9).getShares(company)== 8284);
        assertFalse(ports.get(9).getShares(company)== 1);
    }

    @Test
    public void readDataExternalEventsTest(){
        events = import1.allExternalEvents;
        for (int i = 0; i < events.size(); i++) {
            String[] test = events.get(i);
            System.out.println(test[0]);
            System.out.println(test[1]);
            System.out.println(test[2]);
            System.out.println(test[3]);
            System.out.println(test[4]);
        }
        String[] test = events.get(0);
        assertEquals(test[0], "08.02.2017 09:00");
        assertEquals(test[1], "2");
        assertEquals(test[2], "TradedCompany");
        assertEquals(test[3], "Q1Q tech");
        assertEquals(test[4], "buy");

        String[] test2 = events.get(6);
        assertEquals(test2[0], "04.10.2017 09:00");
        assertEquals(test2[1], "4");
        assertEquals(test2[2], "ShareType");
        assertEquals(test2[3], "PROPERTY");
        assertEquals(test2[4], "buy");
    }
}