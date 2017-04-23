package SimulationModel;

/**
 * Created by Dattlee on 21/04/2017.
 *  A Random Trader my have 1 of 3 states in any 15 minute period:
 *
 *  BALANCED
 *      Buys stocks using between 0% - 1% of it's available cash assets
 *      Sells stocks valued between 0% - 1% of it's total portfolio value
 *
 *  AGGSELLER (Aggressive Seller)
 *      Buys stocks using between 0% - 0.5% of it's available cash assets
 *      Sells stocks valued between 0% - 2% of it's total portfolio value
 *
 *  AGGBUYER (Aggressive Buyer)
 *      Buys stocks using between 0% - 2% of it's available cash assets
 *      Sells stocks valued between 0% - 0.5% of it's total portfolio value
 *
 */
public enum TraderState {
    BALANCED ( new double[]{0,1}, new double[]{0,1} ),
    AGGBUYER ( new double[]{0,2}, new double[]{0,0.5} ),
    AGGSELLER ( new double[]{0,0.5}, new double[]{0,2} );

    private double[] buyProb, sellProb;

    TraderState (double[] buyProb, double[] sellProb) {
        this.buyProb = buyProb;
        this.sellProb = sellProb;
    }

    public double[] getBuyProb() {
        return buyProb;
    }

    public double[] getSellProb() {
        return sellProb;
    }
}
