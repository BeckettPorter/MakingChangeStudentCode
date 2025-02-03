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
    private static long[][] memoization;

    public static long countWays(int target, int[] coins)
    {
        // Sort our coins from smallest to largest (because they can start unordered).
        int[] sortedCoins = sortSmallToLarge(coins);

        // Initialize the memoization array.
        memoization = new long[target + 1][coins.length];

        // Call the initial recursive call and return the result of that recursive stack.
        return count(target, sortedCoins, 0);
    }

    // Recursive method that counts the number of ways to make up target int with an int array of numbers (aka coins).
    private static long count(int target, int[] coins, int index)
    {
        // If target is 0, we have successfully reached a combo of coins that works, so return 1.
        if (target == 0)
        {
            return 1;
        }

        // If target is negative, or we have no more coins to use, return 0.
        if (target < 0 || index == coins.length)
        {
            return 0;
        }

        // Include, we set target equal to target - the current coin. (i.e. including this coin)
        long include = 0;
        int checkTarget = target - coins[index];

        // If our target we are checking is not a negative number.
        if (checkTarget >= 0)
        {
            // Then check if we have already calculated a value at this point in the array.
            if (memoization[checkTarget][index] != 0)
            {
                // If we have, use that value.
                include = memoization[checkTarget][index];
            }
            // If not, then calculate it for the first time.
            else
            {
                include = count(checkTarget, coins, index);
                // Then set that point in the array to that new calculated value.
                memoization[checkTarget][index] = include;
            }
        }

        // Exclude, we keep target the same but go to the next coin (i.e. excluding this coin)
        long exclude = 0;
        int checkIndex = index + 1;

        // If our check index is a valid index
        if (checkIndex < coins.length)
        {
            // Then check if we have already calculated a value at this point in the array.
            if (memoization[target][checkIndex] != 0)
            {
                // If we have, use that value.
                exclude = memoization[target][checkIndex];
            }
            // If not, then calculate it for the first time.
            else
            {
                exclude = count(target, coins, checkIndex);
                // Then set that point in the array to that new calculated value.
                memoization[target][checkIndex] = exclude;
            }
        }

        // Add together the included and excluded values and return that.
        return include + exclude;
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