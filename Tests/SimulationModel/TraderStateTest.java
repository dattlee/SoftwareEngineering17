package SimulationModel;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * Created by Dattlee on 22/04/2017.
 */
public class TraderStateTest {

    /**
     * This test Checks to see if the created enum contain the correct probablities.
     * @throws Exception
     */
    @Test
    public void general() throws Exception {
        TraderState a = TraderState.BALANCED;
        TraderState b = TraderState.AGGBUYER;
        TraderState c = TraderState.AGGSELLER;

        assertArrayEquals(a.getBuyPerc(), new double[]{0,1}, 0);
        assertArrayEquals(a.getSellPerc(), new double[]{0,1}, 0);

        assertArrayEquals(b.getBuyPerc(), new double[]{0,2}, 0);
        assertArrayEquals(b.getSellPerc(), new double[]{0,0.5}, 0);

//        assertArrayEquals(c.getBuyPerc(), new double[]{0,0.5}, 0);
//        assertArrayEquals(c.getSellPerc(), new double[]{0,2}, 0);
    }


}