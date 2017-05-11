package SimulationModel;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Steven on 29/04/2017.
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

    @Test
    public void tradedCompanyExistsTest() throws IOException {
        assertTrue(import1.tradedCompanyExists("Pear Computing"));
        assertTrue(import1.tradedCompanyExists("Gardens 'R' Us"));
        assertTrue(import1.tradedCompanyExists("Sussex Flats"));
        assertTrue(import1.tradedCompanyExists("Skinners"));
        assertFalse(import1.tradedCompanyExists("Always True"));
    }

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