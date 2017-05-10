package SimulationModel;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * This Class that will run the entire simulation of the Stock Market.
 *
 * It runs for a total of 365 days.
 * Trading occurs every 15 minutes between 9am and 5pm. Throughout this package we will refer to every 15 minute
 * interval as a cycle.
 *
 * @version 1.0
 * @author Dattlee ¯\_(ツ)_/¯
 */
public class Simulation {

	/* **************************************************
     *
     *                  Fields
     *
     ****************************************************/
	private StockMarket market;
	protected Clock clock;

	/* **************************************************
     *
     *                  Constructors
     *
     ****************************************************/
	/**
	 * Constructs a news stock market with 1 trading exchange, and sets the starting date to 00:00:00 1/1/2017.
	 */
	public Simulation(List<TradedCompany> companies){
		if(Log.debug){ System.out.printf("Simulation: Creating a new simulation.\n");}

		clock = new Clock();
		market = new StockMarket(companies);
	}

	/**
	 * Constructor that initiates new instance of CsvImport and uses the file path strings provided through the params,
	 * data is then processed by the CsvImport object and passed to a new instance of trading exchange through the params.
	 *
	 * @param tradedCompanyCsvFilePath file path to traded company .csv file
	 * @param portfolioCsvFilePath file path to portfolio .csv file
	 */
	public Simulation(String tradedCompanyCsvFilePath, String portfolioCsvFilePath) throws FileNotFoundException{
		if(Log.debug){System.out.printf("Simulation: Creating a new simulation with imported data.\n");}

		CsvImport import1;

		try {
			import1 = new CsvImport(tradedCompanyCsvFilePath,portfolioCsvFilePath);
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("Files not found");
		}

		clock = new Clock();
		market = new StockMarket(import1.getTradedCompanies(), import1.getPortfolios());

	}

	/* **************************************************
	 *
	 *                  Methods
	 *
	 ****************************************************/

	/**
	 * Returns an ArrayList of all the companies on the market
	 *
	 * @return a List of all the companies on the market
     */
	public ArrayList<TradedCompany> getAllTradedCompanies() {
		if(Log.debug){ System.out.printf("Simulation: Returning all Traded Companies.\n");}

		return market.getAllTradedCompanies();
	}

	/**
	 * Returns a Traded Company object if there is one on the market with the id name.
	 *
	 * @param name the company to search for
	 * @return the company needed
	 * @throws Exception if there is no company with the ID name.
     */
	public TradedCompany getCompany(String name) throws Exception {
		if(Log.debug){ System.out.printf("Simulation: Returning Traded Company %s.\n",name); }

		return market.getCompany(name);
	}


	/**
	 * An ArrayList of all the Traders on the market.
	 * @return
     */
	public ArrayList<Trader> getAllTraders(){
		if(Log.debug){ System.out.printf("Simulation: Returning all Random Traders.\n");}
		return market.getAllTraders();
	}

	//Feel free to give these different names but let me know so I can tweak things

	/**
	 * Method to call the runClock method within clock object which handles all trading.
	 *
	 * @param days amount of days to run simulation for
	 */
	public void runXDays(int days){
		if(Log.debug){ System.out.printf("Simulation: Returning all Traded Companies.\n");}
		clock.runXDays(days, market);
	}

	/**
	 * Method to call the runClock method within clock object which handles all trading.
	 *
	 * @param numOfCycles number of 15 minute intervals to run
	 */
	public void runXCycles(int numOfCycles){
		if(Log.debug){ System.out.printf("Simulation: Returning all Traded Companies.\n");}
		clock.runXCycles(numOfCycles, market);
	}

	/**
	 * Returns the date in the following format:
	 * day of the week day/month/year HH:MM
	 *
	 * @return The current date found in clock object
	 */
	public String getDate(){
		return clock.getDate();
	}

	public ArrayList<Portfolio> getAllPortfolios() {
		return market.getAllPortfolios();
	}
}