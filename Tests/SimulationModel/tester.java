package SimulationModel;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Dattlee on 26/04/2017.
 */
public class tester {

    @Test
    public void what(){
        TradedCompany a = new TradedCompany("google", ShareType.HITECH,100);
        issueSharess(a);
        assertEquals(a.getSharesIssued(), 110);
    }

    public void issueSharess(TradedCompany a){
        a.issueShares(10);
        System.out.println(a.getSharesIssued());
        ;

    }



}