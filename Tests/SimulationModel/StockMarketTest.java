package SimulationModel;

import org.junit.Test;

/**
 * Created 08/05/2017
 *
 * @author Dattlee
 * @version 1.0
 */
public class StockMarketTest {

    @Test
    public void testGetAllTradedCompanies() throws Exception {

    }

    @Test
    public void testGetCompany() throws Exception {

    }

    @Test
    public void testGetAllPortfolios() throws Exception {

    }

    @Test
    public void testGetAllTraders() throws Exception {

    }

    @Test
    public void testGetMarketValue() throws Exception {

    }

    @Test
    public void testAddPortfolioIntelli() throws Exception {

    }

    @Test
    public void testAddPortfolioRand() throws Exception {

    }

    @Test
    public void testAddCompany() throws Exception {

    }

    @Test
    public void testAct() throws Exception {
        String tradedCompanyCsvFilePath = "InitialDataV2.csv";
        String portfolioCsvFilePath = "InitialDataV2portfolio.csv";

        CsvImport importData = new CsvImport(tradedCompanyCsvFilePath,portfolioCsvFilePath);

        StockMarket market = new StockMarket(importData.getTradedCompanies(),importData.getPortfolios());

        int[] tcs = new int[market.getAllTradedCompanies().size()];

        String companies = "";
        String values = "";
        for(TradedCompany company:market.getAllTradedCompanies()) {
            companies += "" + company.getName().substring(0, 6) + " | ";
            values += "  " + company.getShareValue() + " |";
        }
        System.out.println(companies);
        System.out.println(values);



        Clock clock = new Clock(0,0);
        clock.runClock(100,market);



        companies = "";
        values = "";
        for(TradedCompany company:market.getAllTradedCompanies()) {
            companies += "" + company.getName().substring(0, 6) + " | ";
            values += "  " + company.getShareValue() + " |";
        }
        System.out.println(companies);
        System.out.println(values);
    }
}