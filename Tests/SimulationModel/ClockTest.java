package SimulationModel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static org.junit.Assert.*;

/**
 * Created by Steven on 03/05/2017.
 */
public class ClockTest {
    Clock clock;
    CsvImport import1;
    TradingExchange trading;

    @Before
    public void setUp() throws Exception {
     import1 = new CsvImport("InitialDataV2.csv", "InitialDataV2portfolio.csv");
     trading = new TradingExchange(import1.getTradedCompanies(), import1.getPortfolios());
    }

    @Test
    public void minuteIncrementTest(){

        // 1 minute increment test
        clock = new Clock (9, 0, trading);
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

    @Test
    public void getDateAndDaysPassedTest(){
        // Start date test
        clock = new Clock(0,0, trading);
        assertEquals(clock.getDate(),"Sunday 01/01/2017 00:00");

        // Day increment test
        clock = new Clock(0,0, trading);
        for (int i = 0; i < 96; i++){
            clock.minuteIncrement(15);
        }
        assertEquals(clock.getDate(),"Monday 02/01/2017 00:00");

        // Month increment test
        clock = new Clock(0,0, trading);
        for (int i = 0; i < 2976; i++){
            clock.minuteIncrement(15);
        }
        assertEquals(clock.getDate(),"Wednesday 01/02/2017 00:00");

        // Year increment test
        clock = new Clock(0,0, trading);
        for (int i = 0; i < 35040; i++){
            clock.minuteIncrement(15);
        }
        assertEquals(clock.getDate(),"Monday 01/01/2018 00:00");

        // minutes mod test
        clock = new Clock(0,0, trading);
        clock.minuteIncrement(119);
        assertEquals(clock.hour, 1);
        assertEquals(clock.minute, 59);
        clock.minuteIncrement(121);
        assertEquals(clock.hour, 4);
        assertEquals(clock.minute, 0);

        // hours mod test
        clock = new Clock(0,0, trading);
        clock.minuteIncrement(25*60);
        assertEquals(clock.hour, 1);
        assertEquals(clock.currentDay, "Monday");
        assertEquals(clock.dayDate, 2);
        clock.minuteIncrement(47*60);
        assertEquals(clock.hour, 0);
        assertEquals(clock.dayDate, 4);
        assertEquals(clock.currentDay, "Wednesday");

        // Final increments test
        clock = new Clock(0,0, trading);
        clock.minuteIncrement(((365*24)*60)+(60*5));
        assertEquals(clock.hour, 5);
        assertEquals(clock.minute, 0);
        assertEquals(clock.currentDay, "Monday");
        assertEquals(clock.currentMonth, "January");
        clock.minuteIncrement(31*(24*60));
        assertEquals(clock.currentMonth,"February");
        assertEquals(clock.currentDay,"Thursday");
    }

    @Test
    public void isWeekdayTest(){

        // Sunday Test
        clock = new Clock (9, 0, trading);
        assertEquals(clock.isWeekday(), false);
        // Monday Test

        clock.minuteIncrement(24*60);
        assertEquals(clock.isWeekday(), true);
    }

    @Test
    public void fifteenMinuteIntervalTest(){
        clock = new Clock (9,6, trading);
        assertEquals(clock.fifteenMinuteInterval(), false);
        clock.minuteIncrement(9);
        assertEquals(clock.fifteenMinuteInterval(), true);
    }

    @Test
    public void isPublicHolidayTest(){
        // Good Friday Test
        clock = new Clock(9,0,14, 4, 4, trading);
        assertEquals(clock.isPublicHoliday(), true);
        clock.minuteIncrement(24*60);
        assertEquals(clock.isPublicHoliday(), false);

        // Easter Monday Test
        clock = new Clock(9,0,17,4,0, trading);
        assertEquals(clock.isPublicHoliday(), true);
        clock.minuteIncrement(24*60);
        assertEquals(clock.isPublicHoliday(), false);

        //Christmas and Boxing Day Test
        clock = new Clock(9,0,25,12,0, trading);
        assertEquals(clock.isPublicHoliday(),true);
        clock.minuteIncrement(24*60);
        assertEquals(clock.isPublicHoliday(), true);
        clock.minuteIncrement(24*60);
        assertEquals(clock.isPublicHoliday(), false);
    }

    @Test
    public void isTradingHoursTest(){
        clock = new Clock(8,0, trading);
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

    @Test
    public void canTradeTest(){
        // Sunday
        clock = new Clock(9,0, trading);
        assertEquals(clock.canTrade(), false);
        // 1 minute before 9am Monday
        clock.minuteIncrement((24*60)-1);
        assertEquals(clock.canTrade(), false);
        //9am Monday
        clock.minuteIncrement(1);
        assertEquals(clock.canTrade(), true);
    }

    @Test
    public void runClockTest(){
        clock = new Clock(0, 0, trading);
        clock.runClock(2, trading);
    }
}