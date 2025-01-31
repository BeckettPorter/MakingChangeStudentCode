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
    public static long countWays(int target, int[] coins)
    {
        int[] sortedCoins = sortSmallToLarge(coins);

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
        if (index == coins.length - 1)
        {
            return 0;
        }

        // Include
        int include = count(target - coins[index], coins, index);

        // Exclude
        int exclude = count(target, coins, index + 1);

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

        for (int i = arrayList.size(); i < arrayList.size(); i++)
        {
            sortedList[i] = arrayList.get(i);
        }
        return sortedList;
    }
}
