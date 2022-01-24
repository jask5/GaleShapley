import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

//code to implement GaleShapley Algorithm to match pets with their owners.

public class GaleShapley {

    public static void main(String[] args) throws IOException {

        FileReader reader = new FileReader("program1data.txt");
        BufferedReader br = new BufferedReader(reader);
        String line="";

        int total =Integer.parseInt(br.readLine());
        int lineCount = 0;
        String[] person =new String[total];
        while(lineCount<total) {
            person[lineCount] = br.readLine();
            lineCount++;
        }
        int[][] personP = new int[total][total];
        lineCount=0;
        while(lineCount<total) {

            String[] splittedArray = br.readLine().split(" ");
            for (int i = 0; i < splittedArray.length; i++) {
                personP[lineCount][i]  = Integer.parseInt(splittedArray[i]);
            }

            lineCount++;

        }
        lineCount = 0;
        String[] pet =new String[total];
        while(lineCount<total) {
            pet[lineCount] = br.readLine();
            lineCount++;
        }

        int[][] petsP = new int[total][total];
        lineCount=0;
        while(lineCount<total) {

            String[] splittedArray = br.readLine().split(" ");
            for (int i = 0; i < splittedArray.length; i++) {
                petsP[lineCount][i]  = Integer.parseInt(splittedArray[i]);
            }
            lineCount++;
        }

        String[][] prp = new String[total][total];
        String[][] ptp = new String[total][total];

        for (int i = 0; i < total; i++) {
            for (int j = 0; j < total; j++) {
                prp[i][j] = pet[personP[i][j]-1];
                ptp[i][j] = person[petsP[i][j]-1];
            }
        }

        GaleShapley gs = new GaleShapley(person, pet, prp, ptp);

    }

    private int N, matchCount;
    private String[][] personPref;
    private String[][] petPref;
    private String[] persons;
    private String[] pets;
    private String[] petMatch;
    private boolean[] personMatched;

    /** Constructor **/
    public GaleShapley(String[] person, String[] pet, String[][] prp, String[][] ptp)
    {
        N = prp.length;
        matchCount = 0;
        persons = person;
        pets = pet;
        personPref = prp;
        petPref = ptp;
        personMatched = new boolean[N];
        petMatch = new String[N];
        calcMatches();
    }
    /** function to calculate all matches **/
    private void calcMatches()
    {
        while (matchCount < N)
        {
            int free;
            for (free = 0; free < N; free++)
                if (!personMatched[free])
                    break;

            for (int i = 0; i < N && !personMatched[free]; i++)
            {
                int index = petIndex(personPref[free][i]);
                if (petMatch[index] == null)
                {
                    petMatch[index] = persons[free];
                    personMatched[free] = true;
                    matchCount++;
                }
                else
                {
                    String currentPartner = petMatch[index];
                    if (morePreference(currentPartner, persons[free], index))
                    {
                        petMatch[index] = persons[free];
                        personMatched[free] = true;
                        personMatched[personIndex(currentPartner)] = false;
                    }
                }
            }
        }
        printCouples();
    }
    /** function to check if pet prefers new partner over old assigned partner **/
    private boolean morePreference(String curPartner, String newPartner, int index)
    {
        for (int i = 0; i < N; i++)
        {
            if (petPref[index][i].equals(newPartner))
                return true;
            if (petPref[index][i].equals(curPartner))
                return false;
        }
        return false;
    }

    private int personIndex(String str)
    {
        for (int i = 0; i < N; i++)
            if (persons[i].equals(str))
                return i;
        return -1;
    }

    private int petIndex(String str)
    {
        for (int i = 0; i < N; i++)
            if (pets[i].equals(str))
                return i;
        return -1;
    }

    public void printCouples()
    {
        Map<String,String> map = new HashMap<>();
        for (int i = 0; i < N; i++)
        {
            map.put(petMatch[i] ,pets[i]);
        }

        for (int i = 0; i < N; i++) {
            String pet = map.get(persons[i]);
            System.out.println(persons[i]+ " " +  "/" + " "+pet);
        }
    }

}
