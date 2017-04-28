package SimulationModel;

import dattlee.usefuls.Pair;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Dattlee on 26/04/2017.
 * ¯\_(ツ)_/¯
 *
 */
public class tester {
//
//    @Test
//    public void what(){
//        TradedCompany a = new TradedCompany("google", ShareType.HITECH,100);
//        issueSharess(a);
//        assertEquals(a.getSharesIssued(), 110);
//    }
//
//    public void issueSharess(TradedCompany a){
//        a.issueShares(10);
//        System.out.println(a.getSharesIssued());
//
//
//    }
//
//    @Test
//    public void hash(){
//        HashMap<String, Integer>  hasher = new HashMap<>();
//        hasher.put("a",1);
//        hasher.put("b",2);
//        hasher.put("c",3);
//        hasher.put("d",4);
//        hasher.put("e",5);
//        hasher.put("f",6);
//        hasher.put("g",99);
//        hasher.put("h",7);
//        hasher.put("i",8);
//        hasher.put("j",9);
//        hasher.put("k",10);
//        hasher.put("l",11);
//
//        for(HashMap.Entry<String,Integer> entry: hasher.entrySet()){
//            System.out.println(entry.getValue());
//        }
//    }
//
//    @Test
//    public void mult() {
//        double a = 100;
//        int b = 5;
//        int c = 100;
//        int d = 10;
//        a += (double)((b/c)/ d);
//        System.out.println(a);
//        System.out.println(b/c);
//    }
//
//    @Test
//    public void another(){
//
////        int demand = 71;
////        int supply = 140;
////        double distribution = (double)supply/demand;
////        System.out.println(distribution);
////        int dis = (int)8.99999;
////        System.out.println((int)distribution);
//        double dub = 0.4;
//        int a = (int)(new Integer(4) * dub);
//        int b = 23;
//        b -= 9;
//        System.out.println(Math.random());
//    }
//
//    @Test
//    public void randsearch() throws NoSuchFieldException {
//        int i = new Random().nextInt(2);
//        System.out.println(i);
//
//        String a = "" + 1;
//        System.out.println(a);
//    }
//

    @Test
    public void theWholeFuckingThing() {
        TradedCompany google = new TradedCompany("google", ShareType.HITECH, 10000, 100);
        TradedCompany unilever = new TradedCompany("unilever", ShareType.FOOD, 20000, 50);
        TradedCompany tata = new TradedCompany("tataSteel", ShareType.HDCMOD, 10000, 30);
        TradedCompany mtm = new TradedCompany("mtm", ShareType.PROPERTY, 3000, 10);

        Pair<TradedCompany, Integer>[] alStock = new Pair[2];
        alStock[0] = new Pair(google, 10000);
        alStock[1] = new Pair(unilever, 10000);
        Pair<TradedCompany, Integer>[] boStock = new Pair[1];
        boStock[0] = new Pair(unilever, 10000);
        Pair<TradedCompany, Integer>[] catStock = new Pair[2];
        catStock[0] = new Pair(tata, 5000);
        catStock[1] = new Pair(mtm, 1000);
        Pair<TradedCompany, Integer>[] dougStock = new Pair[1];
        dougStock[0] = new Pair(mtm, 1000);
        Pair<TradedCompany, Integer>[] elStock = new Pair[2];
        elStock[0] = new Pair(tata, 5000);
        elStock[1] = new Pair(mtm, 1000);

        Portfolio al = new Portfolio("al", alStock);
        Portfolio bo = new Portfolio("bo", boStock);
        Portfolio cat = new Portfolio("cat", catStock);
        Portfolio doug = new Portfolio("doug", dougStock);
        Portfolio el = new Portfolio("el", elStock);

        ArrayList<TradedCompany> all = new ArrayList<>();
        all.add(google);
        all.add(unilever);
        all.add(tata);
        all.add(mtm);

        TradingExchange london = new TradingExchange(all);
        london.newRandTrader(al);
        london.newRandTrader(bo);
        london.newRandTrader(cat);
        london.newRandTrader(doug);
        london.newRandTrader(el);





        System.out.println();
        System.out.println();
        System.out.println();
        for (double i : london.allStockValues()) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println();





        london.act();
        double[] a = london.allStockValues();

        System.out.println();
        System.out.println();
        System.out.println();
        for (double i : a) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println();






        for(int j = 0; j<10000; j++) {
            london.act();
            a = london.allStockValues();

            System.out.println();
            System.out.println();
            System.out.println();
            for (double i : a) {
                System.out.print(i + " ");
            }
            System.out.println();
            System.out.println();
            System.out.println();
        }

    }
}