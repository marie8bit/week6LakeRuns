package com.MarieErickson;
/*
You are a runner, and you are in training for a race. You'd like to keep
track of all of your times for your training runs. You only like to run
around lakes. Here's some example data,
Cedar, 45.15
Cedar, 43.32
Harriet, 49.34
Harriet, 44.43
Harriet, 46.22
Como, 32.11
Como, 28.14
Please write a program that  enables you to enter the names of lakes and
times, and store it all of this data in data structure(s). Don't store it
in individual variables. Your program should still work if you started
running around another lake too (e.g. Calhoun or Phalen).
Your program should be able to analyze the data that you have stored, and
print your fastest time for each lake you ran around. So, for this data,
your program will display
Cedar, 43.32
Harriet, 44.43
Como, 32.11
 */
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    static Scanner numberScanner = new Scanner(System.in);
    static Scanner stringScanner = new Scanner(System.in);

    public static void main(String[] args) {
        //initiates value for variable that allows user to enter multiple entries
        String question = "y";
        System.out.println("Enter data for each training run");
        //initialize an ArrayList or lakerun objects
        ArrayList<LakeRuns> lakeRunsList = new ArrayList<>();

        while (question.equalsIgnoreCase("y")) {
            System.out.println("Enter the name of the lake");
            String lake = stringScanner.nextLine();
            System.out.println("Enter the run time");
            double time = numberScanner.nextDouble();
            if (lakeRunsList.isEmpty()) {
                LakeRuns first = new LakeRuns(lake);
                first.addRunTime(time);
                lakeRunsList.add(first);
            }
            else {
                ArrayList<LakeRuns> copy = new ArrayList<>(lakeRunsList);
                boolean inList = false;
                while (!inList) {
                    for (int x = 0; x < copy.size(); x++) {
                        if (Objects.equals(copy.get(x).getName(), lake)) {
                            inList = true;
                            lakeRunsList.get(x).addRunTime(time);
                        }
                    }
                    if (!inList) {
                        LakeRuns lake1 = new LakeRuns(lake);
                        lake1.addRunTime(time);
                        lakeRunsList.add(lake1);
                        inList = true;
                        }
                }
                copy.clear();
            }
            System.out.println("Would you like to add another run? Y for yes");
            //sets value to loop through user entry
            question = stringScanner.nextLine();
        }
        for (LakeRuns lake : lakeRunsList) {
            //calls method to calculate the average for each lake
            //displays data to user
            System.out.println("The fastest run time for " + lake.getName() + " was " + lake.getShortest());
        }
        numberScanner.close();
        stringScanner.close();
    }
}
