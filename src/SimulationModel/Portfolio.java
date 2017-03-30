package SimulationModel;

import java.util.HashMap;

/**
 * Created by Dattlee on 30/03/2017.
 */
public class Portfolio {

    private String id;
    private RiskLevel risk;
//    private Trader trader:
    private double cash;
    private HashMap<TradedCompany,Integer> companyShares;

    /**
     * Create a portfolio object.
     *
     * By default:
     * -    risk is set to 'LOW'
     * -    no trader is assigned
     * -    no shares are given
     *
     * @param id the unique identification number of this portfolio
     */
    public Portfolio(String id){
        this.id = id;
        risk = RiskLevel.LOW;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RiskLevel getRisk(){
        return risk;
    }

    public void setRisk(RiskLevel risk){
        this.risk = risk;
    }


}
