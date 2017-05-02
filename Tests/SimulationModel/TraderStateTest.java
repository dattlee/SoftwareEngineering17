package SimulationModel;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Dattlee on 22/04/2017.
 */
public class TraderStateTest {

    /*
     * This test Checks to see if the created enum contain the correct probablities.
     */
    @Test
    public void general() throws Exception {
        TraderState a = TraderState.BALANCED;
        TraderState b = TraderState.AGGBUYER;
        TraderState c = TraderState.AGGSELLER;

        assertEquals(a.getBuyPerc(),1);
        assertEquals(a.getSellPerc(), 1);

        assertEquals(b.getBuyPerc(), 2);
        assertEquals(b.getSellPerc(), 0.5);

//        assertArrayEquals(c.getBuyPerc(), new double[]{0,0.5}, 0);
//        assertArrayEquals(c.getSellPerc(), new double[]{0,2}, 0);
    }


}