package SimulationModel;

import dattlee.usefuls.Pair;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

/**
 * Created 07/05/2017
 *
 * @author Dattlee
 * @version 1.0
 */
public class RandomTraderTest {

    TradedCompany google,unilever,tata,mtm;
    Portfolio al, bo, cat, doug, el;
    TradingExchange london;

    @Before
    public void setUp() throws Exception {
        google = new TradedCompany("google", ShareType.HITECH, 10000, 100);
        unilever = new TradedCompany("unilever", ShareType.FOOD, 20000, 50);
        tata = new TradedCompany("tataSteel", ShareType.HARD, 10000, 30);
        mtm = new TradedCompany("mtm", ShareType.PROPERTY, 3000, 10);

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

        al = new Portfolio("al", alStock, (double)100000);
        bo = new Portfolio("bo", boStock, (double)100000);
        cat = new Portfolio("cat", catStock, (double)200000);
        doug = new Portfolio("doug", dougStock, (double)100000);
        el = new Portfolio("el", elStock, (double) 100900);

        ArrayList<TradedCompany> all = new ArrayList<>();
        all.add(google);
        all.add(unilever);
        all.add(tata);
        all.add(mtm);

        london = new TradingExchange(all);
        london.newRandTrader(al);
        london.newRandTrader(bo);
        london.newRandTrader(cat);
        london.newRandTrader(doug);

    }

    @Test//(expected = Exception.class)
    public void testAddPortfolio() throws Exception {
        RandomTrader rt = new RandomTrader("123",london);
        rt.addPortfolio(el);
        assertTrue(rt.getAllClients().get(0).equals(el));
        assertTrue(rt.getAllClients().size() == 1);
        try{
            rt.addPortfolio(doug);
        } catch (Exception e) {
            assertTrue(e.getMessage().equals("RandomTrader: There is already a portfolio assigned to this trader."));
        }

    }


    @Test
    public void testRemovePortfolio() throws Exception {
        RandomTrader rt = new RandomTrader("123",london,el);

        // The setup
        assertTrue(rt.getAllClients().get(0).equals(el));
        assertTrue(rt.getAllClients().size() == 1);

        rt.removePortfolio();

        assertTrue(rt.getAllClients().size() == 0);
        //assertTrue(rt.getAllClients().size() == 0);


    }

    @Test
    public void testAct() throws Exception {
    }

    /**
     * Asserts that if there is no client that an empty ArrayList is returned.
     * Otherwise an arraylist of one client is returned.
     *
     * @throws Exception _
     */
    @Test
    public void testGetAllClients() throws Exception {
        RandomTrader rt = new RandomTrader("123",london);
        assertTrue(rt.getAllClients().size() == 0);

        RandomTrader rt2 = new RandomTrader("123",london, el);
        assertTrue(rt2.getAllClients().size() == 1);
    }
}