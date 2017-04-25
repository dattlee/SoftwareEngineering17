package SimulationModel;

import dattlee.usefuls.Pair;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Dattlee on 22/04/2017.
 */
public class TradingExchange {

    public HashMap<TradedCompany,ArrayList<Pair<Portfolio, Integer>>> clientsBuying;

    public void sellStock(Portfolio client, TradedCompany company, int shares){

    }


    /**
     * Clients must attempt to buy shares at each 15 minute interval. After an interval passes
     * if they have been unsucessful in buying the stock then their
     * @param client
     * @param company
     * @param shares
     * @return
     */
    public double buyShares(Portfolio client, TradedCompany company, Integer shares){

        return 0;
    }

    /**
     * This method is called at each 15 minute cycle to
     */
    public void act(){

        // Last step in each iteration is to reset the clients buying stock to nothing
        clientsBuying = null;

    }

}
