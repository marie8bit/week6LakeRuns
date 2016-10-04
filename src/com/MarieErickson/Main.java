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
import java.util.Scanner;
import java.util.HashMap;
public class Main {
    static Scanner numberScanner = new Scanner(System.in);
    static Scanner stringScanner = new Scanner(System.in);
    public static void main(String[] args) {
        //initiates value for variable that allows user to enter multiple entries
        String question= "y";
        System.out.println("Enter data for each training run");
        //initialze 3 hashmaps to track number of runs,
        // sum of times and shortest run times
        HashMap<String, Double> times = new HashMap<>();
        HashMap<String, Integer> lakes = new HashMap<>();
        HashMap<String, Double> fast = new HashMap<>();
        while (question.equalsIgnoreCase("y"))
        {
            System.out.println("Enter the name of the lake");
            String lake = stringScanner.nextLine();
            System.out.println("Enter the run time");
            double time = numberScanner.nextDouble();
            //process user data to add it to appropriate hashmaps
            if (!times.isEmpty()){
                //manages entering data for duplicate keys
                if (times.containsKey(lake)){
                    //identifies fastest time per lake
                    if (fast.get(lake)>time){
                        //rewrites the entry for the lake the user entered
                        fast.put(lake, time);
                    }
                    //sum times per lake for average calculation
                    time= time+times.get(lake);
                    //count entries for each lake for average calulation
                    int count = lakes.get(lake)+1;
                    lakes.put(lake, count);
                }
                else
                {
                    //initiates the count for lakes integer value
                    lakes.put(lake, 1);
                    //adds the key/value pair in the fast hashmap/first lake entry
                    fast.put(lake, time);
                }
            }
            else{
                //initiates the count for lakes integer value
                lakes.put(lake, 1);
                //first user entry
                fast.put(lake, time);
            }
            //adds keyvalue pair
            times.put(lake, time);
            System.out.println("Would you like to add another run? Y for yes");
            //sets value to loop through user entry
            question = stringScanner.nextLine();
        }
        //calls method to calculate the average for each lake
        HashMap avg = getAverage(lakes, times);
        //displays data to user
        System.out.println("Average times for each lake");
        for(Object ob : avg.keySet()){
            System.out.println(ob +" "+ avg.get(ob));
        }
        System.out.println("Fastest times for each lake");
        for(Object ob : fast.keySet()) {
            System.out.println(ob + " " + fast.get(ob));
        }
    }
    //method to calculate average run time for each lake
    private static HashMap<String, Double> getAverage(HashMap<String, Integer> lakes, HashMap<String, Double> times) {
        HashMap avHash = new HashMap();
        for (String avLake :times.keySet()){
            double average = times.get(avLake)/lakes.get(avLake);
            avHash.put(avLake, average);
        }

        return avHash;
    }
}
