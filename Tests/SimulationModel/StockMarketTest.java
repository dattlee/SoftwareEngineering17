package SimulationModel;

import org.junit.Before;
import org.junit.Test;

/**
 * Test the overall
 *
 * @author Dattlee
 * @version 1.0
 */
public class StockMarketTest {

    StockMarket market;


    @Before
    public void setUp() throws Exception {

        String tradedCompanyCsvFilePath = "InitialDataV2.csv";
        String portfolioCsvFilePath = "InitialDataV2portfolio.csv";

        CsvImport importData = new CsvImport(tradedCompanyCsvFilePath,portfolioCsvFilePath);

        market = new StockMarket(importData.getTradedCompanies(),importData.getPortfolios());
    }

    /**
     * Prints the value of each companies stocks at midnight each day for 365 days (all of 2017)
     *
     * @throws Exception Standard Exception
     */
    @Test
    public void testFluctuationInCompanyStocks() throws Exception {

        Clock clock = new Clock();
        int string_length = 15;
        int days = 365;             // number of days to run the simulation for


        System.out.printf("The starting price, in pence, of all the stocks on \n\n",clock.getDate());
        String companies = "|";
        String values = "|";

        for(TradedCompany company:market.getAllTradedCompanies()) {

            companies += " " + clipString(company.getName(),string_length) + " |";

            values += " " +  number2string((int)company.getShareValue(),string_length) + " |";
        }
        System.out.println(companies);
        System.out.println(values);
        System.out.println();





        for (int i = 0; i < days; i++){
            clock.runXDays(1,market);
            System.out.printf("%s closing prices (pence).\n\n",clock.getDate());
            companies = "|";
            values = "|";

            for(TradedCompany company:market.getAllTradedCompanies()) {

                companies += " " + clipString(company.getName(), string_length) + " |";

                values += " " +  number2string((int)company.getShareValue(),string_length) + " |";
            }
            System.out.println(companies);
            System.out.println(values);
            System.out.println();
            System.out.println();
        }
    }



    private String number2string(int number, int len){
        String value = "" + (int)number;

        int spaces = len - value.length();
        String fullString = "";

        for(int i = 0;i<spaces;i++){
            fullString += " ";
        }

        return fullString += value;
    }

    private String clipString(String name, int len){

        if (name.length() > len) {
            return name.substring(0, len);
        }else{

            int spaces = len - name.length();
            String fullString = "";

            for(int i = 0;i<spaces;i++){
                fullString += " ";
            }

            return fullString += name;
        }
    }

    /**
     * Prints the value of each clients portfolio at midnight each day for 365 days (all of 2017)
     *
     * @throws Exception Standard Exception
     */
    @Test
    public void testClientValues() throws Exception {

        Clock clock = new Clock();
        int string_length = 15;


        System.out.printf("The starting client values, in pence, on \n\n",clock.getDate());
        String companies = "|";
        String values = "|";

        for(Portfolio company:market.getAllPortfolios()) {

            companies += " " + clipString(company.getName(),string_length) + " |";
            values += " " +  number2string((int)company.getValue(),string_length) + " |";

        }
        System.out.println(companies);
        System.out.println(values);
        System.out.println();




        // Run for a number of days
        int days = 365;

        for (int i = 0; i < days; i++) {
            clock.runXDays(1, market);
            System.out.printf("%s closing values (pence) of clients.\n\n", clock.getDate());
            companies = "|";
            values = "|";

            for (Portfolio company : market.getAllPortfolios()) {

                companies += " " + clipString(company.getName(),string_length) + " |";

                values += " " + number2string((int) company.getValue(), string_length) + " |";
            }
            System.out.println(companies);
            System.out.println(values);
            System.out.println();
            System.out.println();
        }

    }

    /**
     * Prints the value of the market every day at midnight, for 365 days (all of 2017)
     *
     * @throws Exception Standard Exception
     */
    @Test
    public void testGetTotalValue() throws Exception{
        Clock clock = new Clock();
        int string_length = 15;
        int days = 365;


        System.out.printf("Market valued at    £%s    on    %s\n\n",market.getMarketValue(),clock.getDate());



        // Run for a number of days

        for (int i = 0; i < days; i++) {
            clock.runXDays(1, market);
            System.out.printf("Market valued at    £%s    on    %s\n\n",market.getMarketValue(),clock.getDate());
        }
    }
}