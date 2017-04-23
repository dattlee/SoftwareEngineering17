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

        assertArrayEquals(a.getBuyProb(), new double[]{0,1}, 0);
        assertArrayEquals(a.getSellProb(), new double[]{0,1}, 0);

        assertArrayEquals(b.getBuyProb(), new double[]{0,2}, 0);
        assertArrayEquals(b.getSellProb(), new double[]{0,0.5}, 0);

//        assertArrayEquals(c.getBuyProb(), new double[]{0,0.5}, 0);
//        assertArrayEquals(c.getSellProb(), new double[]{0,2}, 0);
    }


}