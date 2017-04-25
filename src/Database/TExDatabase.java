package Database;

import SimulationModel.Portfolio;
import SimulationModel.TradedCompany;
import dattlee.usefuls.Pair;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Dattlee on 23/04/2017.
 *
 * A Java class representation of the Trading Exchange Database
 */
public class TExDatabase {

    /*
    whosSellingHowMuch:
     Google  -  (Ally - 10 shares), (Bo - 40 shares), (Charlie - 13 shares), (Demi - 23 shares)
     Microsoft  -  (Bo - 5 shares), (Charlie - 10 shares)
     Facebook  -  (Charlie - 100 shares)
     TelexSteel  -  (Ally - 67 shares), (Demi - 54 shares)
      */
    private HashMap<TradedCompany,ArrayList<Pair<Portfolio, Integer>>> whosSellingHowMuch;
    private HashMap<TradedCompany, Integer> noSharesForSale;

    /**
     * Default database size of 100 companies
     */
    public TExDatabase(){
        whosSellingHowMuch = new HashMap<>(100);
        noSharesForSale = new HashMap<>(100);
    }

    public void sellStock(Portfolio client, TradedCompany company, Integer amount){

    }

}
