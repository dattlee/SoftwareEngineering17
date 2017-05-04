package SimulationModel;

import dattlee.usefuls.Pair;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created 04/05/2017
 *
 * @author Dattlee
 * @version 1.0
 */
public class OrdersTest {

    TradingExchange london;
    TradedCompany google, unilever, tata, mtm;
    Portfolio al, bo, cat, doug, el;

    @Before
    public void setUp() throws Exception {

        // Create Companies
        google = new TradedCompany("google", ShareType.HITECH, 10000, 100);
        unilever = new TradedCompany("unilever", ShareType.FOOD, 20000, 50);
        tata = new TradedCompany("tataSteel", ShareType.HARD, 10000, 30);
        mtm = new TradedCompany("mtm", ShareType.PROPERTY, 3000, 10);

        // Set up stocks for clients
        Pair<TradedCompany, Integer>[] alStock = new Pair[2];
        alStock[0] = new Pair(google, 10000);
        alStock[1] = new Pair(unilever, 10000);
        Pair<TradedCompany, Integer>[] boStock = new Pair[1];
        boStock[0] = new Pair(unilever, 10000);
        Pair<TradedCompany, Integer>[] catStock = new Pair[2];
        catStock[0] = new Pair(tata, 5000);
        catStock[1] = new Pair(mtm, 1000);
        Pair<TradedCompany, Integer>[] dougStock = new Pair[1];
        dougStock[0] = new Pair(mtm, 1000);
        Pair<TradedCompany, Integer>[] elStock = new Pair[2];
        elStock[0] = new Pair(tata, 5000);
        elStock[1] = new Pair(mtm, 1000);

        // create clients
        al = new Portfolio("al", alStock);
        bo = new Portfolio("bo", boStock);
        cat = new Portfolio("cat", catStock);
        doug = new Portfolio("doug", dougStock);
        el = new Portfolio("el", elStock);

        // Add companies to array for TradingExchange
        ArrayList<TradedCompany> all = new ArrayList<>();
        all.add(google);
        all.add(unilever);
        all.add(tata);
        all.add(mtm);

        // Add
        TradingExchange london = new TradingExchange(all);
        london.newRandTrader(al);
        london.newRandTrader(bo);
        london.newRandTrader(cat);
        london.newRandTrader(doug);
        london.newRandTrader(el);

    }

    @Test
    public void buy() throws Exception {

    }

    @Test
    public void sell() throws Exception {

    }

    @Test
    public void testReset() throws Exception {

    }

    @Test
    public void testGetClientsBuying() throws Exception {

    }

    @Test
    public void testGetDemand() throws Exception {

    }

    @Test
    public void testGetClientsSelling() throws Exception {

    }

    @Test
    public void testGetSupply() throws Exception {

    }

    @Test
    public void testExcess() throws Exception {

    }
}