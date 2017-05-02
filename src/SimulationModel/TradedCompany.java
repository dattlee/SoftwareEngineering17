package SimulationModel;

/**
 * Created by Dattlee on 30/03/2017.
 * ¯\_(ツ)_/¯
 *
 * NOTE: Must refactor the code for database implementation
 */
public class TradedCompany {
    private String name;
    private ShareType shareType;
    private int sharesIssued;
    private double shareValue;

    /*
     * Create a company:
     *  define the name
     *  define the shareType
     *  set the number of shares initially issued
     *  set the initial share value
     *  @
     */
    public TradedCompany(String name, ShareType shareType, int sharesIssued, double shareValue){
        this.name = name;
        this.shareType = shareType;
        this.sharesIssued = sharesIssued;
        this.shareValue = shareValue;
    }

    /**
     * Returns the name of the company
     * @return Company Name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the ShareType
     * @return ShareType
     */
    public ShareType getShareType() {
        return shareType;
    }

    /**
     * Returns number of Shares issued
     * @return shares issued
     */
    public int getSharesIssued() {
        return sharesIssued;
    }

    /**
     * Returns the value of a single share
     *
     * @return share value
     */
    public double getShareValue() {
        return shareValue;
    }

    /**
     * Returns the Total value of a company.
     * @return value of sum of all shares issued
     */
    public double getValue() {
        return getShareValue() * sharesIssued;
    }

    /**
     * Adapt the share value based on demand, buy inputing the demand for a cycle
     * Returns share value after inflate/deflating
     *
     * @param excessDemand the demand for the shares
     * @return the share value after determining new value
     */
    public double increaseShareValue(int excessDemand) {
        shareValue += ((double)excessDemand/sharesIssued)*shareValue;
        if (shareValue < 1) {
            shareValue = 0;
        }
        return shareValue;
    }
}
