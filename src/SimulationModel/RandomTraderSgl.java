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
    public Portfolio client;

    /* **************************************************
     *
     *                  Constructors
     *
     ****************************************************/

    public RandomTraderSgl(String ID, TradingExchange exchange) {
        super(ID, exchange);
        currentState = TraderState.BALANCED;
    }

    public RandomTraderSgl(String ID, TradingExchange exchange, Portfolio portfolio) {
        super(ID, exchange);
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
        if(this.client == null){
            this.client = portfolio;
        } else {
            throw new Exception("There is already a portfolio assigned to this trader.");
        }
    }

    public void removePortfolio() throws Exception {
        if(this.client == null){
            throw new Exception("There is no portfolio assigned to this trader.");
        } else {
            this.client = null;
        }
    }

    /**
     * used buy the simulation class of every 15 minute period
     */
    @Override
    public void act() {
        // either buy and/or sell stock, can not buy same stock that it is selling

        // buy something
        double buySomething = Math.random();
        currentState.getBuyPerc();
        //choose randome stuff to buy
        //1st assess total wealth
        // opt to puc

        // sell something, remember you cant sell something your buying. that's just pointless.
        double sellSomething = Math.random();
        currentState.getSellPerc();
        // choose random stuff to sell


        //potentially change state
        double changeState = Math.random();
        if( changeState <= currentState.getSwitchBuy()){
            currentState = TraderState.AGGBUYER;
        }else if( changeState <= (currentState.getSwitchBuy()+currentState.getSwitchBalance())){
            currentState = TraderState.BALANCED;
        }else{
            currentState = TraderState.AGGSELLER;
        }

    }

    /**
     * neeed to build trading excahnge to continue
     * @param company
     * @param shares
     */
    @Override
    public void buyStock(TradedCompany company, Integer shares) {
        exchange.buyShares(client,company,shares);
    }

    /**
     * need to build trading exchange to continue
     * @param company
     * @param shares
     */
    @Override
    public void sellStock(TradedCompany company, Integer shares) {
        exchange.sellStock(client,company,shares);
    }

    /**
     * No idea why this was made... ¯\_(ツ)_/¯
     */
    @Override
    public void removeStockFromMarket() {

    }
}
