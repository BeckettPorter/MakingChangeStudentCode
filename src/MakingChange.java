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

    private static int[][] memoization;

    public static long countWays(int target, int[] coins)
    {
        int[] sortedCoins = sortSmallToLarge(coins);

        // X is up to target and y is the coins.
        memoization = new int[target][coins.length];

        for (int i = 0; i < coins.length; i++)
        {
            memoization[0][i] = 1;
        }

        return count(target, sortedCoins, 0);
    }

    private static int count(int target, int[] coins, int index)
    {
        if (target == 0)
        {
            return 1;
        }
        if (target < 0)
        {
            return 0;
        }

        // If we have no more coins in the array.
        if (index == coins.length)
        {
            return 0;
        }

        int nInclude = 0;
        if (target - coins[index] >= 0)
        {
            nInclude = memoization[target - coins[index]][index];
        }

        // Include
        if (nInclude != 0)
        {
            return nInclude;
        }
        int include = count(target - coins[index], coins, index);

        if (target - coins[index] >= 0)
        {
            memoization[target - coins[index]][index] = include;
        }


        // Exclude
        int exclude = count(target, coins, index + 1);

        memoization[target][index] = exclude;

        return include + exclude;
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

        for (int i = 0; i < ar.length; i++)
        {
            sortedList[i] = arrayList.get(i);
        }
        return sortedList;
    }
}
