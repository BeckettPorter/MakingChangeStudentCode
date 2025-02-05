import java.util.ArrayList;

/**
 * The class Making Change solves a classic problem:
 * given a set of coins, how many ways can you make change for a target amount?
 *
 * @author Zach Blick
 * @author Beckett Porter
 */

public class MakingChange
{
    // Memoization array of longs to prevent integer overflow.
    private static long[][] tabulationTable;

    public static long countWays(int target, int[] coins)
    {
        // Sort our coins from smallest to largest (because they can start unordered).
        int[] sortedCoins = sortSmallToLarge(coins);

        // Initialize the memoization array.
        tabulationTable = new long[target + 1][coins.length];


        for (int checkTarget = 0; checkTarget < tabulationTable.length; checkTarget++)
        {
            for (int coin : coins)
            {

                include(checkTarget - coin, coin);

                exclude(checkTarget, coin);
            }
        }


        return count(target, sortedCoins, 0);


    }

    private static int include(int target, int coin)
    {

    }

    private static int exclude(int target, int coin)
    {

    }


    // Sort a given int array from smallest to largest.
    private static int[] sortSmallToLarge(int[] ar)
    {
        // Make a fresh arrayList.
        ArrayList<Integer> arrayList = new ArrayList<>();

        // Add all the numbers from the original int array to the new arrayList.
        for (int num : ar)
        {
            arrayList.add(num);
        }

        // Sort this new arrayList.
        arrayList.sort(null);

        // Make a new int array that we will copy the sorted values from the arrayList into.
        int[] sortedList = new int[ar.length];

        for (int i = 0; i < ar.length; i++)
        {
            sortedList[i] = arrayList.get(i);
        }

        // Return this new sorted int array.
        return sortedList;
    }
}