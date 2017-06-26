package project1;

import java.io.File;
import java.util.Vector;
import java.util.Scanner;

public class Driver {
    public static String filename;
    public static boolean testBruteForce;
    public static boolean testGS;
    
    public static void main(String[] args) throws Exception {
        parseArgs(args);
        
        Matching problem = parseMatchingProblem(filename);
        testRun(problem);
    }

    private static void usage() {
        System.err.println("usage: java Driver [-g] [-b] <filename>");
        System.err.println("\t-b\tTest Brute Force implementation");
        System.err.println("\t-g\tTest Gale-Shapley implementation");
        System.exit(1);
    }
    
    public static void parseArgs(String[] args) {
        if (args.length == 0) {
            usage();
        }
        
        filename = "";
        testBruteForce = false;
        testGS = false;
        boolean flagsPresent = false;
        
        for (String s : args) {
            if(s.equals("-g")) {
                flagsPresent = true;
                testGS = true;
            } else if(s.equals("-b")) {
                flagsPresent = true;
                testBruteForce = true;
            } else if(!s.startsWith("-")) {
                filename = s;
            } else {
                System.err.printf("Unknown option: %s\n", s);
                usage();
            }
        }
        
        if(!flagsPresent) {
            testBruteForce = true;
            testGS = true;
        }
    }

    public static Matching parseMatchingProblem(String inputFile)
            throws Exception {
        int l = 0;
        int n = 0;
        Vector<Vector<Integer>> landlordOwn;
        Vector<Vector<Integer>> landlordPrefs, tenantPrefs;

        Scanner sc = new Scanner(new File(inputFile));
        String[] inputSizes = sc.nextLine().split(" ");

        l = Integer.parseInt(inputSizes[0]);
        n = Integer.parseInt(inputSizes[1]);
        
        landlordOwn = readPreferenceLists(sc, l);
        landlordPrefs = readPreferenceLists(sc, l);
        tenantPrefs = readPreferenceLists(sc, n);

        Matching problem = new Matching(l, n, landlordOwn, landlordPrefs,
        		tenantPrefs);
                

        return problem;
    }

    
    private static Vector<Vector<Integer>> readPreferenceLists(
            Scanner sc, int m) {
        Vector<Vector<Integer>> preferenceLists;
        preferenceLists = new Vector<Vector<Integer>>(0);

        for (int i = 0; i < m; i++) {
            String line = sc.nextLine();
            String[] preferences = line.split(" ");
            Vector<Integer> preferenceList = new Vector<Integer>(0);
            for (Integer j = 0; j < preferences.length; j++) {
                preferenceList.add(Integer.parseInt(preferences[j]));
            }
            preferenceLists.add(preferenceList);
        }

        return preferenceLists;
    }

    public static void testRun(Matching problem) {
        Program1 program = new Program1();
        boolean isStable;

        if (testGS) {
            long startTime = System.nanoTime();
            Matching GSMatching = program.stableMatchingGaleShapley(problem);
            long estimatedTime = (System.nanoTime() - startTime);
            System.out.println(GSMatching);
            System.out.println(estimatedTime);
            isStable = program.isStableMatching(GSMatching);
            System.out.printf("%s: stable? %s\n", "Gale-Shapley", isStable);
            System.out.println();
        }

        if (testBruteForce) {
            long startTime = System.nanoTime();
            Matching BFMatching = program.stableMatchingBruteForce(problem);
            long estimatedTime = (System.nanoTime() - startTime);
            System.out.println(BFMatching);
            System.out.println(estimatedTime);
            isStable = program.isStableMatching(BFMatching);
            System.out.printf("%s: stable? %s\n", "Brute Force", isStable);
            System.out.println();
        }
    }
}
