package SimulationModel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;


/**
 * Created by Dattlee on 30/03/2017.
 */
public class TradedCompanyTest {

    TradedCompany google;

    @Before
    public void setUp() throws Exception {
        google = new TradedCompany("google", ShareType.HITECH, 10000);
    }

    @Test
    public void getName() throws Exception {
        assertEquals(google.getName(),"google");
        assertNotEquals(google.getName(),"microsoft");

    }

    @Test
    public void getShareType() throws Exception {
        assertEquals(google.getShareType(), ShareType.HITECH);
        assertNotEquals(google.getShareType(), ShareType.FOOD);

    }

    @Test
    public void getSharesIssued() throws Exception {
        assertEquals(google.getSharesIssued(),10000);
        assertNotEquals(google.getSharesIssued(),0);
    }

    @Test
    public void getShareValue() throws Exception {
        assertTrue(google.getShareValue() == 1234567891);
        assertTrue(google.getShareValue() == 0);
    }

    @Test
    public void getValue() throws Exception {
        double total = google.getSharesIssued() * google.getShareValue();
        assertTrue(google.getValue()==total);
//        assertTrue(google.getValue() != 0); // To ensure that the class method isn't just returning 0 all the time
    }

    @Test
    public void issueShares() throws Exception {
        // Checks to make:
        // 1. 1000 shares are added to the shares issued count
        // 2. 1000 shares are added to the trading exchange and up for sale

        int issuedTotal = google.getSharesIssued();
        issuedTotal += 1000;
//        int exchangeTotal = tradingExchange.getShares("google");
//        exchangeTotal += 1000;

        // 1. Issued Count
        google.issueShares(1000);
        assertTrue(google.getSharesIssued() == issuedTotal);

        // 2. Exchange Count
        assertTrue(1==0);
    }
}