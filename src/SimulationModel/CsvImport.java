package SimulationModel;

import dattlee.usefuls.Pair;

import javax.sound.sampled.Port;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class takes two filename.csv Strings as parameters and processes the information found within them.
 * one .csv file containing the traded companies, their stock type, the number of shares issued and the value of the
 * stock, the other .csv file contains the names of each client, their cash holdings, and the shares they hold in each
 * company.
 *
 * It then processes this information using BufferedReaders to create two ArrayLists containing the TradedCompany and
 * Portfolio objects to be passed to the TradingExchange.
 * @version 1.0
 * @author SDocker
 */
public class CsvImport {

    /* **************************************************
     *
     *                  Fields
     *
     ****************************************************/
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
    /**
     * Set tradedCompanyBR and portfolioBR Buffer readers to the .csv string path provided in the constructor param,
     * run the corresponding methods for processing the .csvs
     *
     * @param companyCSV String of the Traded Company .csv file path, portfolioCSV String of the Portfolio .csv file path
     * @Exception e throw exception is the .csv file cannot be read
     */
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

    /* **************************************************
     *
     *                  Methods
     *
     ****************************************************/
    /**
     * For each line within the BufferedReader, split the line by ',' into tradedCompanyEntries String array,
     * Use string elements within String array to create new TradedCompany and put into tradedCompanies HashMap
     *
     * @param companies BufferedReader object pointing to Traded Companies .csv file
     */
    public void readDataTradedCompanies(BufferedReader companies) throws IOException {
        while ((line = companies.readLine()) != null) {
            String[] tradedCompanyEntries = line.split(",");
            tradedCompanies.put(tradedCompanyEntries[0], new TradedCompany(tradedCompanyEntries[0], ShareType.valueOf(tradedCompanyEntries[1].toUpperCase()), Integer.parseInt(tradedCompanyEntries[2]), Double.parseDouble(tradedCompanyEntries[3])));
        }
    }

    /**
     * Returns whether the traded company key exists within tradedCompanies HashMap
     *
     * @return a boolean, true if the key exists within tradedCompanies HashMap
     */
    public boolean tradedCompanyExists(String key){
        return tradedCompanies.containsKey(key);
    }

    /**
     * Return the traded company object matching the String key within HashMap
     *
     * @return the HashMap value corresponding to company name or null if key does not exist
     */
    public TradedCompany getTradedCompany(String key){
        if (tradedCompanyExists(key)){
            return tradedCompanies.get(key);
        }
        return null;
    }

    /**
     * For each line within the BufferedReader, split the line by ',' into portfolioEntries String array and add to
     * ArrayList of strings.
     * Get first element of ArrayList containing all company names and iterate through remaining elements to obtain
     * portfolio information and create/add Portfolio objects to Portfolio HashMap
     *
     * @param portfolios BufferedReader object pointing to Portfolio .csv file
     */
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

    /**
     * Returns the arraylist containing all traded companies
     * @return arraylist of all traded companies
     */
    public ArrayList<TradedCompany> getTradedCompanies(){
        return allCompanies;
    }

    /**
     * Returns the arraylist containing all portfolios
     * @return arraylist of all portfolios
     */
    public ArrayList<Portfolio> getPortfolios(){
        return allPortfolios;
    }
}
