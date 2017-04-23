package SimulationModel;

/**
 * Created by Dattlee on 21/04/2017.
 *
 * Traders of this class can only hold 1 portfolio at a time
 */
public class RandomTraderSgl extends Trader {

    /* **************************************************
     *
     *                  Fields
     *
     ****************************************************/

    public TraderState currentState;
    public Portfolio portfolio;

    /* **************************************************
     *
     *                  Constructors
     *
     ****************************************************/

    public RandomTraderSgl(String ID) {
        super(ID);
        currentState = TraderState.BALANCED;
    }

    public RandomTraderSgl(String ID, Portfolio portfolio) {
        super(ID);
        currentState = TraderState.BALANCED;
        try {
            addPortfolio(portfolio);
        } catch (Exception e) {
            System.out.println("Somehow there is already a portfolio associated with this " +
                    "new trader... Fuck knows how that happened ¯\\_(ツ)_/¯");


        }
    }

    /* **************************************************
     *
     *                  Methods
     *
     ****************************************************/

    public void addPortfolio(Portfolio portfolio) throws Exception {
        if(this.portfolio == null){
            this.portfolio = portfolio;
        } else {
            throw new Exception("There is already a portfolio assigned to this trader.");
        }
    }

    public void removePortfolio() throws Exception {
        if(this.portfolio == null){
            throw new Exception("There is no portfolio assigned to this trader.");
        } else {
            this.portfolio = null;
        }
    }

    /**
     * used buy the simulation class of every 15 minute period
     */
    public void act() {

    }

    /**
     * neeed to build trading excahnge to continue
     * @param company
     * @param shares
     */
    @Override
    public void buyStock(TradedCompany company, Integer shares) {

    }

    /**
     * need to build trading exchange to contiue
     * @param company
     * @param shares
     */
    @Override
    public void sellStock(TradedCompany company, Integer shares) {

    }

    /**
     * No idea why this was made... ¯\_(ツ)_/¯
     */
    @Override
    public void removeStockFromMarket() {

    }
}
