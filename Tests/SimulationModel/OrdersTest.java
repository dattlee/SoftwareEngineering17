package SimulationModel;

import dattlee.usefuls.Pair;
import org.junit.Before;
import org.junit.Test;

/**
 * This Test Class ensures that all sale and purchasing offers are recorded correctly in a Trading Exchange during each
 * cycle.
 *
 * Test requirements:
 *  - TradedCompany
 *  - Portfolio
 *  - Pair
 *
 * @author Dattlee
 * @version 1.0
 */
public class OrdersTest {

    Portfolio al, bo, cat;
    Orders one;

    @Before
    public void setUp() throws Exception {

        // Set up stocks for clients
        Pair<TradedCompany, Integer>[] alStock = new Pair[0];
        Pair<TradedCompany, Integer>[] boStock = new Pair[0];
        Pair<TradedCompany, Integer>[] catStock = new Pair[0];

        // create clients
        al = new Portfolio("al", alStock);
        bo = new Portfolio("bo", boStock);
        cat = new Portfolio("cat", catStock);

        // Orders object
        one = new Orders();

    }

    /**
     * Checks that when a client offers to buy a number of stocks that they are added to the list of clients buying.
     * Checks made:
     *  - The number of clients buying increases
     *  - The demand increases with each client added
     *  - The client object is added with the number of shares
     *  - The client object is added
     *  - No Selling or Supply increases
     *
     *
     * Test requirements:
     *  - Orders.getClientsSelling() working correctly
     *  - Orders.getClientsBuying() working correctly
     *
     * @throws Exception Standard Exceptions
     */
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
        assert(one.getClientsBuying().get(0).getFirst().equals(al) && one.getClientsBuying().get(0).getSecond().equals(2));
        assert(one.getDemand() == 2);
        assert(one.getSupply() == 0);

        one.buy(bo, 8);

        // checks only buying has incremented
        assert(one.getClientsBuying().size() == 2);
        assert(one.getClientsSelling().size() == 0);
        assert(one.getClientsBuying().get(1).getFirst().equals(bo) && one.getClientsBuying().get(1).getSecond().equals(8));
        assert(one.getDemand() == 10);
        assert(one.getSupply() == 0);

    }

    /**
     * Checks that when a client offers to buy a number of stocks that they are added to the list of clients buying.
     * Checks made:
     *  - The number of clients buying increases
     *  - The demand increases with each client added
     *  - The client object is added with the number of shares
     *  - The client object is added
     *  - No Selling or Supply increases
     *
     *
     * Test requirements:
     *  - Orders.getClientsBuying()
     *  - Orders.getClientsSelling()
     *
     * @throws Exception Standard Exceptions
     */
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
        assert(one.getClientsSelling().get(0).getFirst().equals(al) && one.getClientsSelling().get(0).getSecond().equals(2));
        assert(one.getDemand() == 0);
        assert(one.getSupply() == 2);

    }

    /**
     * Checks that when called all fields are set back to zero.
     * Checks made:
     *  - Demand set to 0
     *  - supply set to 0
     *  - Clients buying set to 0
     *  - clients selling set to 0
     *
     * Test requirements:
     *  - Orders.getClientsBuying()
     *  - Orders.getClientsSelling()
     *
     * @throws Exception Standard Exceptions
     */
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

    /**
     * Checks That the excess changes accordingly when clients buy and sell.
     * Checks made:
     *  - excess is positive when there are more buyers
     *  - excess is negative when there is more sellers
     *
     * Test requirements:
     *  - Orders.buy()
     *  - Orders.sell()
     *
     * @throws Exception Standard Exceptions
     */
    @Test
    public void testExcess() throws Exception {
        one.sell(al,30);
        one.buy(cat,40);
        assert(one.excess() == 10);

        one.sell(al,40);
        assert(one.excess() ==-30);

    }
}