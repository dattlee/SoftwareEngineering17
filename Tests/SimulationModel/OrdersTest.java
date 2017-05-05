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
    Orders one;

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


        one = new Orders();

    }

    @Test
    public void buy() throws Exception {
        // Check that Orders is empty
        assert(one.getClientsBuying().size() == 0);
        assert(one.getClientsSelling().size() == 0);
        assert(one.getDemand() == 0);
        assert(one.getSupply() == 0);

        one.buy(al, 2);

        // checks only buying has incremented
        assert(one.getClientsBuying().size() == 1);
        assert(one.getClientsSelling().size() == 0);
        assert(one.getDemand() == 2);
        assert(one.getSupply() == 0);


    }

    @Test
    public void sell() throws Exception {
        // Check that Orders is empty
        assert(one.getClientsBuying().size() == 0);
        assert(one.getClientsSelling().size() == 0);
        assert(one.getDemand() == 0);
        assert(one.getSupply() == 0);

        one.sell(al, 2);

        // checks only selling has incremented
        assert(one.getClientsBuying().size() == 0);
        assert(one.getClientsSelling().size() == 1);
        assert(one.getDemand() == 0);
        assert(one.getSupply() == 2);

    }

    @Test
    public void testReset() throws Exception {
        one.buy(al, 2);
        one.sell(cat,10);

        // checks only buying has incremented
        assert(one.getClientsBuying().size() == 1);
        assert(one.getClientsSelling().size() == 1);
        assert(one.getDemand() == 2);
        assert(one.getSupply() == 10);

        one.reset();

        // checks only buying has incremented
        assert(one.getClientsBuying().size() == 0);
        assert(one.getClientsSelling().size() == 0);
        assert(one.getDemand() == 0);
        assert(one.getSupply() == 0);


    }

    @Test
    public void testGetClientsBuying() throws Exception {
        // Check that Orders is empty
        assert(one.getClientsBuying().size() == 0);

        one.buy(al, 2);
        one.buy(cat,10);

        // checks only buying has incremented
        assert(one.getClientsBuying().size() == 2);

        Pair<Portfolio,Integer> a  = one.getClientsBuying().get(0);
        Pair<Portfolio,Integer> b  = one.getClientsBuying().get(1);

        assert(a.getFirst().getName().equals("al"));
        assert(a.getSecond().intValue() == 2);
        assert(b.getFirst().equals(cat));
        assert(b.getSecond().intValue() == 10);


    }

    @Test
    public void testGetDemand() throws Exception {
        // Check that Orders is empty
        assert(one.getDemand() == 0);

        one.buy(al, 2);
        one.buy(cat,10);

        // checks only buying has incremented
        assert(one.getDemand() == 12);
    }

    @Test
    public void testGetClientsSelling() throws Exception {
        // Check that Orders is empty
        assert(one.getClientsSelling().size() == 0);

        one.sell(al, 2);
        one.sell(cat,10);

        // checks only buying has incremented
        assert(one.getClientsSelling().size() == 2);

        Pair<Portfolio,Integer> a  = one.getClientsSelling().get(0);
        Pair<Portfolio,Integer> b  = one.getClientsSelling().get(1);

        assert(a.getFirst().getName().equals("al"));
        assert(a.getSecond().intValue() == 2);
        assert(b.getFirst().equals(cat));
        assert(b.getSecond().intValue() == 10);
    }

    @Test
    public void testGetSupply() throws Exception {
        // Check that Orders is empty
        assert(one.getSupply() == 0);

        one.sell(al, 2);
        one.sell(cat,10);

        // checks only buying has incremented
        assert(one.getSupply() == 12);
    }

    @Test
    public void testExcess() throws Exception {
        one.sell(al,30);
        one.buy(cat,40);
        assert(one.excess() == 10);

        one.sell(al,40);
        assert(one.excess() ==-30);

    }
}