package SimulationModel;

/**
 * Used to mark the state of the market. The state of the market is the general trend fot the last 3 days.
 *
 *      STABLE - the total value of the market has fluctuated.
 *      BULL - the total value of the market has increased.
 *      BEAR - the total value of the market has decreased.
 *
 * @author Dattlee
 * @version 1.0
 */
public enum MarketState {
    STABLE,BULL,BEAR,
}
