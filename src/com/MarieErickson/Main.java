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
Lab 6 problem 4
Start with the lakes running program from Lab 4, Essentials Problem 4.
Modify this program to use objects to store the data. What class(es)
will you create?  Your classes and objects should improve your program's
organization, conciseness, clarity and structure.
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
        //allow for multiple entries using a boolean variable
        while (question.equalsIgnoreCase("y")) {
            System.out.println("Enter the name of the lake");
            String lake = stringScanner.nextLine();
            System.out.println("Enter the run time");
            double time = numberScanner.nextDouble();
            //code to add the first object of the LakeRuns class
            if (lakeRunsList.isEmpty()) {
                //use constructor to set name property and initialize an arrayList property
                LakeRuns first = new LakeRuns(lake);
                //add run time to arrayList property
                first.addRunTime(time);
                //add object to an ArrayList that contains LakeRun objects
                lakeRunsList.add(first);
            }
            //identify if there is a new object to be made
            // or if run time needs to be added to an object
            else {
                //iterate through a copy of a list to avoid ConcurrentModificationException
                ArrayList<LakeRuns> copy = new ArrayList<>(lakeRunsList);
                //boolean value to avoid duplicating objects
                boolean inList = false;
                while (!inList) {
                    for (int x = 0; x < copy.size(); x++) {
                        //intelliJ offered equals() method for my ==
                        if (Objects.equals(copy.get(x).getName(), lake)) {
                            //exit while loop/skip second if statement
                            inList = true;
                            //add runtime to object arrayList runTimes using the get function
                            //to access the objects method
                            lakeRunsList.get(x).addRunTime(time);
                        }
                        //no else necessary
                    }
                    //adds new object to an arrayList that does not allready contain an object
                    // with the same name/boolean value did not get flipped in previous for loop
                    if (!inList) {
                        LakeRuns lake1 = new LakeRuns(lake);
                        lake1.addRunTime(time);
                        lakeRunsList.add(lake1);
                        //breaks out of while list once object has been initilzed and added to collection
                        inList = true;
                        }
                }
                //clear copy list to avoid chance of duplication
                copy.clear();
            }
            //sets boolena value for current while loop/breaks while loop
            System.out.println("Would you like to add another run? Y for yes");
            //sets value to loop through user entry
            question = stringScanner.nextLine();
        }
        //loop through objects
        for (LakeRuns lake : lakeRunsList) {
            //calls method to calculate the average for each lake
            //displays data to user using class methods
            System.out.println("The fastest run time for " + lake.getName() + " was " + lake.getShortest());
        }
        //close resources
        numberScanner.close();
        stringScanner.close();
    }
}
