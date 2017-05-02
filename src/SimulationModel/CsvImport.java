package SimulationModel;

import dattlee.usefuls.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Steven on 30/04/2017.
 */
public class CsvImport {

    BufferedReader tradedCompanyBR;
    String[] tradedCompanyEntries;
    ArrayList<TradedCompany> allCompanies = new ArrayList<>();
    HashMap<String, TradedCompany> tradedCompanies = new HashMap();

    BufferedReader portfolioBR;
    String[] portfolioEntries;
    String line = "";
    ArrayList<String[]> portfolios = new ArrayList<>();
    String[] companyNames;
    ArrayList<Portfolio> allPortfolios = new ArrayList<>();

    /* **************************************************
     *
     *                  Constructors
     *
     ****************************************************/
    public CsvImport(String companyCSV, String portfolioCSV) throws FileNotFoundException {
        this.tradedCompanyBR = new BufferedReader(new FileReader(new File(companyCSV)));
        this.portfolioBR = new BufferedReader(new FileReader(new File(portfolioCSV)));

        try {
            readDataTradedCompanies(tradedCompanyBR);
        } catch (IOException e) {
            System.out.println("Failed to read file");
        }

        // For each entry in tradedCompanies HashMap, add TradedCompany value to allCompanies ArrayList
        for (HashMap.Entry <String, TradedCompany> entry : tradedCompanies.entrySet()){
            allCompanies.add(entry.getValue());
        }

        try {
            readDataPortfolio(portfolioBR);
        } catch (IOException e) {
            System.out.println("Failed to read file");
        }
    }

    public void readDataTradedCompanies(BufferedReader companies) throws IOException {
        while ((line = companies.readLine()) != null) {
            String[] tradedCompanyEntries = line.split(",");
            tradedCompanies.put(tradedCompanyEntries[0], new TradedCompany(tradedCompanyEntries[0], ShareType.valueOf(tradedCompanyEntries[1].toUpperCase()), Integer.parseInt(tradedCompanyEntries[2]), Double.parseDouble(tradedCompanyEntries[3])));
        }
    }

    public boolean tradedCompanyExists(String key){
        return tradedCompanies.containsKey(key);
    }

    public TradedCompany getTradedCompany(String key){
        if (tradedCompanyExists(key)){
            return tradedCompanies.get(key);
        }
        return null;
    }

    public void readDataPortfolio(BufferedReader portfolios) throws IOException {
        while ((line = portfolios.readLine()) != null) {
            String[] portfolioEntries = line.split(",");
            this.portfolios.add(portfolioEntries);
        }

        this.companyNames = this.portfolios.get(0); // get string array of company names from portfolio ArrayList
        String clientName;
        Double cashHoldings;

        for (int i = 1; i < this.portfolios.size(); i++) { // from 1 to number of clients
            Pair<TradedCompany, Integer>[] clientPairs = new Pair[companyNames.length - 2];
            String[] clientPortfolio = this.portfolios.get(i); // get string array found in row(i) from portfolio arraylist
            clientName = clientPortfolio[0]; // client name equals element 0 in array clientPortfolio
            //System.out.println(clientName);
            cashHoldings = Double.parseDouble(clientPortfolio[1]); // cash holdings equals element 1 in array clientPortfolio
            //System.out.println(cashHoldings);
            for (int j = 2; j < clientPortfolio.length; j++) { // from element 2 (start of company names) to size of string array
                //System.out.println(getTradedCompany(companyNames[j].replaceAll("\\s", "")));
                //System.out.println(Integer.parseInt(clientPortfolio[j]));
                clientPairs[j - 2] = new Pair(getTradedCompany(this.companyNames[j]), Integer.parseInt(clientPortfolio[j]));
            }
            allPortfolios.add(new Portfolio(clientName, clientPairs, cashHoldings));
        }
    }
}
