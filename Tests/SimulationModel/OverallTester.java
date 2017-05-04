package SimulationModel;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created 03/05/2017
 *
 * @version 1.0
 * @author Dattlee
 */
public class OverallTester {

    Simulation example;

    @Before
    public void setUp() throws Exception {
        example = new Simulation();
    }

    @Test
    public void importAnd1Daytest() throws Exception {
        System.out.println("importAnd1Daytest");
        System.out.println();

        ArrayList<TradedCompany> all = example.getAllTradedCompanies();
        int allsize = all.size();
        double[] values = new double[allsize];
        String[] names = new String[allsize];
        for(int j = 0; j<allsize; j++) {
            values[j] = all.get(j).getValue();
            names[j] = all.get(j).getName();
        }
        System.out.println();
        for(String name:names){
            System.out.print(name+" ");
        }
        System.out.println();
        for(double value:values) {
            System.out.print(value+" ");
        }

        example.runXSteps(11000);
        System.out.println();
        System.out.println();
        System.out.println();


        for(int j = 0; j<allsize; j++) {
            values[j] = all.get(j).getValue();
            names[j] = all.get(j).getName();
        }

        for(String name:names){
            System.out.print(name+" ");
        }

        System.out.println("what");

        for(double value:values) {
            System.out.print(value+" ");
        }
        System.out.println("again");


    }
}