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
 *
 *  Furthermore, at the end of each 15 minute period there is a chance that the state of a trader
 *  will swap from one to another, this is determined by the current state based on these probabilities:
 *
 *  BALANCED
 *      seller(40%), balanced(60%), buyer(0%)
 *
 *  AGGSELLER
 *      seller(10%), balanced(80%), buyer(10%)
 *
 *  AGGBUYER
 *      seller(0%), balanced(70%), buyer(30%)
 *
 */
public enum TraderState {
    AGGSELLER ( new double[]{0,0.5}, new double[]{0,2}, 0.4, 0.6, 0 ),
    BALANCED ( new double[]{0,1}, new double[]{0,1}, 0.1, 0.8, 0.1  ),
    AGGBUYER ( new double[]{0,2}, new double[]{0,0.5}, 0, 0.7, 0.3 );

    private final double[] buyPerc, sellPerc;
    private final double switchSell, switchBalance, switchBuy;

    TraderState(double[] buyPerc, double[] sellPerc, double switchSell, double switchBalance, double switchBuy) {
        this.buyPerc = buyPerc;
        this.sellPerc = sellPerc;
        this.switchSell = switchSell;
        this.switchBalance = switchBalance;
        this.switchBuy = switchBuy;
    }

    public double[] getBuyPerc() {
        return buyPerc;
    }

    public double[] getSellPerc() {
        return sellPerc;
    }

    public double getSwitchSell() {
        return switchSell;
    }

    public double getSwitchBalance() {
        return switchBalance;
    }

    public double getSwitchBuy() {
        return switchBuy;
    }
}
