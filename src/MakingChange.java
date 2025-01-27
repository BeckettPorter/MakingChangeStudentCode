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
    /**
     * TODO: Complete this function, countWays(), to return the number of ways to make change
     *  for any given total with any given set of coins.
     */


    public static long countWays(int target, int[] coins)
    {

        // Sort the coins from smallest to largest.
        int[] sortedCoins = sortSmallToLarge(coins);

        int totalWays = 0;


        System.out.println(twoCoinComparison(sortedCoins[0], sortedCoins[1], target));

        for (int smallCoinIndex = 0; smallCoinIndex < sortedCoins.length - 1; smallCoinIndex++)
        {
            for (int largeCoinIndex = smallCoinIndex; largeCoinIndex < sortedCoins.length; largeCoinIndex++)
            {
                totalWays += twoCoinComparison(sortedCoins[smallCoinIndex], sortedCoins[largeCoinIndex], target);
            }
        }



        return totalWays;
    }

    // for small coin, get max num we can fit, then subtract large coin from total, repeat

    private static int twoCoinComparison(int smallCoin, int largeCoin, int target)
    {
        int numCombosFound = 0;

        // Given two coins, this should find the number of combos we can make with them.
        while (target > smallCoin)
        {
            int currentTestAmount = smallCoin;

            // Add as many small coins as we can.
            while (currentTestAmount < target)
            {
                currentTestAmount += smallCoin;
            }

            if (target % currentTestAmount == 0)
            {
                numCombosFound++;
            }

            target -= largeCoin;
        }

        return numCombosFound;
    }

    private static int[] sortSmallToLarge(int[] ar)
    {
        ArrayList<Integer> arrayList = new ArrayList<>();

        for (int num : ar)
        {
            arrayList.add(num);
        }
        arrayList.sort(null);

        int[] sortedList = new int[ar.length];

        for (int i = 0; i < arrayList.size(); i++)
        {
            sortedList[i] = arrayList.get(i);
        }
        return sortedList;
    }
}
