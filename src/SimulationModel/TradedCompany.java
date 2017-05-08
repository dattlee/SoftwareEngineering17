package SimulationModel;

/**
 * A Traded Company is an object that stores the details of a company to be traded on the stock market.
 *
 * @version 1.0
 * @author Dattlee ¯\_(ツ)_/¯
 */
public class TradedCompany {


    /* **************************************************
     *
     *                      Fields
     *
     ****************************************************/

    private String name;
    private ShareType shareType;
    private int sharesIssued;
    private double shareValue;


    /* **************************************************
     *
     *                    Constructors
     *
     ****************************************************/

    public TradedCompany(String name, ShareType shareType, int sharesIssued, double shareValue){
        if(Log.debug){System.out.printf("TradedCompany: Creating a new company %s, with a share value of %s.\n",name,shareValue);}
        this.name = name;
        this.shareType = shareType;
        this.sharesIssued = sharesIssued;
        this.shareValue = shareValue;
    }


    /**
     * Returns the name of the company.
     *
     * @return A String of the company's name.
     */
    public String getName() {
        if(Log.debug){System.out.printf("TradedCompany: Returning the company name %s\n",name);}
        return name;
    }

    /**
     * Returns the ShareType
     *
     * @return ShareType
     */
    public ShareType getShareType() {
        if(Log.debug){System.out.printf("TradedCompany: Returning the ShareType of %s\n",name);}
        return shareType;
    }

    /**
     * Returns number of Shares issued
     * @return shares issued
     */
    public int getSharesIssued() {
        if(Log.debug){System.out.printf("TradedCompany: Returning %s shares issued by %s\n",name);}
        return sharesIssued;
    }

    /**
     * Returns the value of a single share
     *
     * @return share value
     */
    public double getShareValue() {
        if(Log.debug){System.out.printf("TradedCompany: Returning the company name %s\n",name);}
        return shareValue;
    }

    /**
     * Returns the Total value of a company.
     *
     * @return value of sum of all shares issued
     */
    public double getValue() {
        if(Log.debug){System.out.printf("TradedCompany: Returning the company name %s\n",name);}

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
        shareValue += (((double)excessDemand/sharesIssued)*shareValue);
        System.out.printf("TradedCompany: Increasing the share value of %s by %s\n",name, (int)(((double)excessDemand/sharesIssued)*shareValue));
        if (shareValue < 1) {
            shareValue = 0;
        }
        return shareValue;
    }
}
