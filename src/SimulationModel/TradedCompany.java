package SimulationModel;

/**
 * Created by Dattlee on 30/03/2017.
 *
 * NOTE: Must refactor the code for database implementation
 */
public class TradedCompany {
    private String name;
    private ShareType shareType;
    private int sharesIssued;
    private double shareValue;

    /**
     * To be completed
     */
    public TradedCompany(String name, ShareType shareType, int sharesIssued, double shareValue){
        this.name = name;
        this.shareType = shareType;
        this.sharesIssued = sharesIssued;
        this.shareValue = shareValue;
    }

    /**
     * To be completed
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * To be completed
     * @return
     */
    public ShareType getShareType() {
        return shareType;
    }

    /**
     * To be completed
     * @return
     */
    public int getSharesIssued() {
        return sharesIssued;
    }

    /**
     * To be completed
     *
     * May need to change from double BigDecimal to return bigger numbers.
     * @return
     */
    public double getShareValue() {
        return shareValue;
    }

    /**
     * To be completed
     *
     * May need to change from double BigDecimal to return bigger numbers.
     * @return
     */
    public double getValue() {
        return getShareValue() * sharesIssued;
    }

    /**
     * To be completed. need trading exchange
     *
     * May need to change from double BigDecimal to return bigger numbers.
     * @return
     */
    public void issueShares(int noShares) {
        sharesIssued += noShares;
    }

    /**
     * returns share value after inflate/deflating
     * @param excessDemand
     * @return
     */
    public double increaseShareValue(int excessDemand) {
        shareValue += ((double)excessDemand/sharesIssued)*shareValue;
        if (shareValue < 1) {
            shareValue = 0;
        }
        return shareValue;
    }
}
