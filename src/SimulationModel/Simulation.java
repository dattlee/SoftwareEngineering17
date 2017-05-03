package SimulationModel;

import java.util.ArrayList;

/**
 * This Class that will run the entire simulation of the Stock Market.
 *
 * It runs for a total of 365 days.
 * Trading occurs every 15 minutes between 9am and 5pm. Throughout this package we will refer to every 15 minute
 * interval as a cycle
 *
 * @version 1.0
 * @author Dattlee ¯\_(ツ)_/¯
 */
public class Simulation {

	private TradingExchange exchange;

	//Gets a traded company (necessary to get all traded companies)
	public ArrayList<TradedCompany> getAllTradedCompanies(){
		return exchange.getAllCompanies();
	}


	public TradedCompany getCompany(String name) throws Exception {
		for (TradedCompany company: getAllTradedCompanies()) {
			if (company.getName() == name) {
				return company;
			}
		}
		throw new Exception("No company called "+name);
	}


	//Gets a trader (necessary to get all traders)
	public ArrayList<Trader> getAllTraders(){
		return exchange.getAllTraders();
	}

	//Feel free to give these different names but let me know so I can tweak things


}