package SimulationModel;

/**
 * This class creates a clock object that starts on Monday 1st January 2017
 * @version 1.0
 * @author SDocker
 */

public class Clock {

    /* **************************************************
     *
     *                  Fields
     *
     ****************************************************/
//    protected TradingExchange trading;
    protected int hour;
    protected int minute;
    protected int dayDate;
    protected int monthDate;
    protected int year;
    protected int daysOfTheWeekCounter;
    protected String currentDay;
    protected String currentMonth;
    protected String[] daysOfTheWeek = {"Monday", "Tuesday", "Wednesday","Thursday", "Friday", "Saturday", "Sunday"};
    protected String[] allMonths = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    protected int[] daysPerMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    /* **************************************************
     *
     *                  Constructors
     *
     ****************************************************/
    /**
     * Constructor initiates clock to start from Monday 1st January, sets time based on ints provided in params and
     * initiates all fields needed to track and update days of the week, date and time.
     *
     * set hour and minute to 00:00, set day and month to 1, initiate dayOfTheWeekCounter for tracking
     * day, set current day string equal to daysOfTheWeek String array element [dayOfTheWeekCounter] and set current
     * month String to allMonths String array element [month-1]
     */
    public Clock () {
        this.hour = 0;
        this.minute = 0;
        this.year = 2017;
        dayDate = 1;
        monthDate = 1;
        daysOfTheWeekCounter = 6;
        currentDay = daysOfTheWeek[daysOfTheWeekCounter];
        currentMonth = allMonths[monthDate-1];
    }

    /**
     * Constructor initiates clock to start from Monday 1st January, sets time, day of the month, month and
     *
     * set hour and minute to 00:00, set day and month to 1, initiate dayOfTheWeekCounter for tracking
     * day, set current day string equal to daysOfTheWeek String array element [dayOfTheWeekCounter] and set current
     * month String to allMonths String array element [month-1]
     *
     * @param dayDate day of the month
     * @param monthDate month to start
     * @param daysOfTheWeekCounter choose which day of the week
     */
    public Clock (int dayDate, int monthDate, int daysOfTheWeekCounter) {
//        this.trading = trading;
        this.hour = 0;
        this.minute = 0;
        this.dayDate = dayDate;
        this.monthDate = monthDate;
        this.year = 2017;
        this.daysOfTheWeekCounter = daysOfTheWeekCounter;
        currentDay = daysOfTheWeek[daysOfTheWeekCounter];
        currentMonth = allMonths[monthDate-1];
    }

    /* **************************************************
     *
     *                  Methods
     *
     ****************************************************/
    /**
     * Returns the date in the following format:
     * day of the week day/month/year HH:MM
     *
     * @return The current date
     */
    public String getDate(){
        return(currentDay + " " + formatDate(dayDate) + "/" + formatDate(monthDate) + "/" + year + " " + formatDate(hour) + ":" + formatDate(minute));
    }

    /**
     * Method used to format any single digit integer to be started with a 0, ints are then formatted into a string and returned
     *
     * @param formatInt takes an int as parameter, formats single digits to lead with 0 = and returns the int as a string
     * @return The formatted int as a string
     */
    public String formatDate(int formatInt){
        String formatted = "";
        if (formatInt < 10){
            formatted = String.format("%02d", formatInt);
        } else {
            formatted = Integer.toString(formatInt);
        }
        return formatted;
    }

    /**
     * Increments minutes passed by param, if minutes == 60 then calculate hours past, adds to int hour and
     * resets counter to 0 and increments hour
     * if hour == 24 then resets hour counter to 0 and called dayPassed() method
     *
     * @param incrementInMinutes choose how many minutes to increment by
     */
    public void minuteIncrement(int incrementInMinutes){
        minute += incrementInMinutes;

        if (minute > 59){
            int hourIncrement = (int)Math.floor(minute/60);
            minute = minute % 60;
            hour += hourIncrement;

            if (hour >= 24){
                int totalDays = (int)Math.floor(hour/24);
                for (int i = 0; i < totalDays; i++) {
                    dayPassed();
                }
                hour = hour % 24;
            }
        }
    }

    /**
     * Increments the date, updates the day of the week and checks whether a month has passed and whether a year has
     * passed and updates the appropriate fields.
     * Used by incrementMinutes method when hour equals 24.
     */
    public void dayPassed(){
        dayDate++;
        if (daysOfTheWeekCounter < 6){
            daysOfTheWeekCounter++;
        } else {
            daysOfTheWeekCounter = 0;
        }
        currentDay = daysOfTheWeek[daysOfTheWeekCounter];

        if (dayDate > daysPerMonth[monthDate-1]){
            monthDate++;
            dayDate = 1;

            if (monthDate > 12){
                monthDate = 1;
                year++;
            }
            currentMonth = allMonths[monthDate-1];
        }
    }

    /**
     * Returns whether the current day falls between Monday and Friday
     *
     * @return a boolean, true if the current day is Monday to Friday
     */
    public boolean isWeekday(){
        if (daysOfTheWeekCounter >= 0 && daysOfTheWeekCounter < 5) {
            return true;
        }
        return false;
    }

    /**
     * Returns whether the current minute falls within the 15 minute interval for initiating trade
     *
     * @return a boolean, true if the current minute falls on a 15 minute interval for trading
     */
    public boolean fifteenMinuteInterval(){
        if (minute == 0){
            return true;
        } else if (minute == 15){
            return true;
        } else if (minute == 30){
            return true;
        } else if (minute == 45){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns whether the current date falls on 2017 good Friday/Easter Monday or Christmas/Boxing day
     *
     * @return a boolean, true if the date is good friday/easter monday or Christmas/Boxing day
     */
    public boolean isPublicHoliday(){
        if (monthDate == 4){
            if (dayDate == 14 || dayDate == 17){
                return true;
            }
        } else if (monthDate == 12){
            if (dayDate == 25 || dayDate == 26){
                return true;
            }
        }
        return false;
    }

    /**
     * Returns whether the current date falls on 2017 good Friday/Easter Monday
     *
     * @return a boolean, true if the date is good friday/easter monday
     */
    public boolean isTradingHours(){
        if (hour >= 9 && hour < 16) {
            return true;
        }
        return false;
    }

    /**
     * Returns whether time is within the trading hours of 9:00 and 16:00 on Monday to Friday and whether the date
     * is a non holiday weekday
     *
     * @return a boolean, true if current time falls within 15 minute interval for trading and is within the trading
     * hours of 9am and 4pm on Monday to Friday and it is a non holiday weekday
     */
    public boolean canTrade(){
        if (isWeekday()){
            if (isTradingHours()) {
                if (!isPublicHoliday()) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * for loop that runs for x amount of days and calls the act method on trading
     * exchange when it is a valid training day
     *
     * @param numberOfDays int for inputting number of days to run clock
     * @param market the Stock Market object to commit act method on
     */
    public void runXDays(int numberOfDays, StockMarket market){
        int incrementMinutes = 15;
        int numberOfRuns = numberOfDays*24*4;

        for (int i = 0; i < numberOfRuns; i++){
            if (canTrade()){
                market.act();
            }
            minuteIncrement(incrementMinutes);
        }
    }

    /**
     * for loop that runs for x amount of cycles and calls the act method on trading
     * exchange when it is a valid training day
     *
     * @param numOfCycles int for inputting number of 15 minute intervals to run clock
     * @param market the Stock Market object to commit act method on
     */
    public void runXCycles(int numOfCycles, StockMarket market){
        int incrementMinutes = 15;

        for (int i = 0; i < numOfCycles; i++){
            if (canTrade()){
                market.act();
            }
            minuteIncrement(incrementMinutes);
        }
    }
}
