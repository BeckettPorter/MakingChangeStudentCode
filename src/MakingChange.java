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
    // 1st [] is target, 2nd [] is coin index.
    private static long[][] memoization;

    public static long countWays(int target, int[] coins)
    {
        int[] sortedCoins = sortSmallToLarge(coins);

        // X is up to target and y is the coins.
        memoization = new long[target + 1][coins.length];

        for (int i = 0; i < coins.length; i++)
        {
            memoization[0][i] = 1;
        }

        return count(target, sortedCoins, 0);
    }

    private static long count(int target, int[] coins, int index)
    {
        // If target is 0, we have successfully reached a combo of coins that works.
        if (target == 0)
        {
            return 1;
        }

        // If target is negative, or we have no more coins to use.
        if (target < 0 || index == coins.length)
        {
            return 0;
        }


        // Include, we set target equal to target - the current coin. (including this coin)
        long include = 0;
        int checkTarget = target - coins[index];
        // If we have already calculated this include, use that. Otherwise, calculate it.

        if (checkTarget >= 0)
        {
            if (memoization[checkTarget][index] != 0)
            {
                include = memoization[checkTarget][index];
            }
            else
            {
                include = count(checkTarget, coins, index);
                memoization[checkTarget][index] = include;
            }
        }
        // Exclude, we keep target the same but go to the next coin (excluding this coin)
        long exclude = 0;
        int checkIndex = index + 1;

        if (checkIndex < coins.length)
        {
            if (memoization[target][checkIndex] != 0)
            {
                exclude = memoization[target][checkIndex];
            }
            else
            {
                exclude = count(target, coins, checkIndex);
                memoization[target][checkIndex] = exclude;
            }
        }


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
