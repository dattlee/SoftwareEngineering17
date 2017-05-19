package SimulationModel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This Test Class ensures the Clock Class increments in accordance with the StockMarket. Such that all public holidays
 * are acknowledged, etc.
 *
 * @author sdocker
 * @version 1.0
 */
public class ClockTest {
    Clock clock;
    CsvImport import1;
    StockMarket market;

    @Before
    public void setUp() throws Exception {
     import1 = new CsvImport("InitialDataV2.csv", "InitialDataV2portfolio.csv");
     market = new StockMarket(import1.getTradedCompanies(), import1.getPortfolios());
    }

    /**
     * Test 1 - checks time has incremented by 1 minute
     * Test 2 - checks time has incremented by 15 minutes
     * Test 3 - checks time has incremented by 1 hour
     */
    @Test
    public void minuteIncrementTest(){

        // 1 minute increment test
        clock = new Clock();
        clock.minuteIncrement(540);
        clock.minuteIncrement(1);
        assertEquals(clock.hour, 9);
        assertEquals(clock.minute, 1);

        // 15 minute increment test
        clock.minuteIncrement(15);
        assertEquals(clock.hour, 9);
        assertEquals(clock.minute, 16);

        // 60 minute increment test
        clock.minuteIncrement(60);
        assertEquals(clock.hour, 10);
        assertEquals(clock.minute, 16);
    }

    /**
     * Test 1 - checks date formats into Sunday 01/01/2017 00:00
     * Test 2 - increment time by 1 day, check day of the week increases and formats into Monday 02/01/2017 00:00
     * Test 3 - increment time by 1 month, check month increases and formats into Wednesday 01/02/2017 00:00
     * Test 4 - increments time by 1 year, check year increases and formats into Monday 01/01/2018 00:00
     * Test 5 - increment time by 159 minutes and check hour increases and minutes equals 59
     * Test 6 - increment time by 25 hour and check day of the week, day and hour has increased, increment by 47 hours and
     * check hour, day of the week and day has increased
     * Test 7 - increment by 1 year and 5 hours and confirm day, day of the week, month, hour and minute increase, increment
     * by 31 days and check month and day of the week increased
     */
    @Test
    public void getDateAndDaysPassedTest(){
        // Start date test
        clock = new Clock();
        assertEquals(clock.getDate(),"Sunday 01/01/2017 00:00");

        // Day increment test
        clock = new Clock();
        for (int i = 0; i < 96; i++){
            clock.minuteIncrement(15);
        }
        assertEquals(clock.getDate(),"Monday 02/01/2017 00:00");

        // Month increment test
        clock = new Clock();
        for (int i = 0; i < 2976; i++){
            clock.minuteIncrement(15);
        }
        assertEquals(clock.getDate(),"Wednesday 01/02/2017 00:00");

        // Year increment test
        clock = new Clock();
        for (int i = 0; i < 35040; i++){
            clock.minuteIncrement(15);
        }
        assertEquals(clock.getDate(),"Monday 01/01/2018 00:00");

        // minutes mod test
        clock = new Clock();
        clock.minuteIncrement(119);
        assertEquals(clock.hour, 1);
        assertEquals(clock.minute, 59);
        clock.minuteIncrement(121);
        assertEquals(clock.hour, 4);
        assertEquals(clock.minute, 0);

        // hours mod test
        clock = new Clock();
        clock.minuteIncrement(25*60);
        assertEquals(clock.hour, 1);
        assertEquals(clock.currentDay, "Monday");
        assertEquals(clock.dayDate, 2);
        clock.minuteIncrement(47*60);
        assertEquals(clock.hour, 0);
        assertEquals(clock.dayDate, 4);
        assertEquals(clock.currentDay, "Wednesday");

        // Final increments test
        clock = new Clock();
        clock.minuteIncrement(((365*24)*60)+(60*5));
        assertEquals(clock.hour, 5);
        assertEquals(clock.minute, 0);
        assertEquals(clock.currentDay, "Monday");
        assertEquals(clock.currentMonth, "January");
        clock.minuteIncrement(31*(24*60));
        assertEquals(clock.currentMonth,"February");
        assertEquals(clock.currentDay,"Thursday");
    }

    /**
     * Test 1 - check boolean to confirm its Saturday-Sunday
     * Test 2 - check boolean to confirm if its Monday-Friday
     */
    @Test
    public void isWeekdayTest(){

        // Sunday Test
        clock = new Clock ();
        clock.minuteIncrement(540);
        assertEquals(clock.isWeekday(), false);
        // Monday Test

        clock.minuteIncrement(24*60);
        assertEquals(clock.isWeekday(), true);
    }

    /**
     * Test 1 - check that time is not within 15 minute intervals 00/15/30/45 and boolean returns false
     * Test 2 - increase time to 15 minute interval and test boolean returns true
     */
    @Test
    public void fifteenMinuteIntervalTest(){
        clock = new Clock ();
        clock.minuteIncrement(546);
        assertEquals(clock.fifteenMinuteInterval(), false);
        clock.minuteIncrement(9);
        assertEquals(clock.fifteenMinuteInterval(), true);
    }

    /**
     * Test 1 - set date to good Friday (2017) and check if boolean returns true for public holiday, increment by 24
     * hours and check boolean returns false
     * Test 2 - set date to easter monday and check if boolean returns true, increment by 24 hours and check boolean
     * returns false
     * Test 3 - Set date to christmas and check boolean returns true, increment by 24 hours to boxing day and check
     * boolean returns true, increment by 24 hours and check boolean returns false for 27/12/2017
     */
    @Test
    public void isPublicHolidayTest(){
        // Good Friday Test
        clock = new Clock(14, 4, 4);
        clock.minuteIncrement(540);
        assertEquals(clock.isPublicHoliday(), true);
        clock.minuteIncrement(24*60);
        assertEquals(clock.isPublicHoliday(), false);

        // Easter Monday Test
        clock = new Clock(17,4,0);
        clock.minuteIncrement(540);
        assertEquals(clock.isPublicHoliday(), true);
        clock.minuteIncrement(24*60);
        assertEquals(clock.isPublicHoliday(), false);

        //Christmas and Boxing Day Test
        clock = new Clock(25,12,0);
        clock.minuteIncrement(540);
        assertEquals(clock.isPublicHoliday(),true);
        clock.minuteIncrement(24*60);
        assertEquals(clock.isPublicHoliday(), true);
        clock.minuteIncrement(24*60);
        assertEquals(clock.isPublicHoliday(), false);
    }

    /**
     * Test 1 - set time to 08:00 and check boolean returns false for time being 09:00-16:00, increment to 08:59 and
     * check boolean still returns false, increment by 1 minute to 09:00 and check boolean returns true, increment by
     *  6:59 to 15:59 and check boolean still returns true, increment by 1 minute and confirm boolean returns false
     */
    @Test
    public void isTradingHoursTest(){
        clock = new Clock();
        clock.minuteIncrement(480);
        assertEquals(clock.isTradingHours(), false);
        clock.minuteIncrement(59);
        assertEquals(clock.isTradingHours(), false);
        clock.minuteIncrement(1);
        assertEquals(clock.isTradingHours(),true);
        clock.minuteIncrement((60*7)-1);
        assertEquals(clock.isTradingHours(), true);
        clock.minuteIncrement(1);
        assertEquals(clock.isTradingHours(), false);
    }

    /**
     * Confirm method that uses all previous booleans can recognise weekends and working hours
     * Test 1 - check boolean returns false for being able to trade as day is Sunday, increment by 23:59 hours and
     * confirm boolean returns false for being able to trade as outside of working hours, increment by 1 minute and
     * confirm being able to trade at 09:00 on a non holiday Monday
     */
    @Test
    public void canTradeTest(){
        // Sunday
        clock = new Clock();
        clock.minuteIncrement(540);
        assertEquals(clock.canTrade(), false);
        // 1 minute before 9am Monday
        clock.minuteIncrement((24*60)-1);
        assertEquals(clock.canTrade(), false);
        //9am Monday
        clock.minuteIncrement(1);
        assertEquals(clock.canTrade(), true);
    }

}