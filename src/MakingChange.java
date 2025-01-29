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

        ArrayList<Integer> ar = new ArrayList<>();

        for (int coin : coins)
        {
            ar.add(coin);
        }




        return search(target, ar);
    }

    private static int search(int target, ArrayList<Integer> coins)
    {
        if (target <= 0)
        {
            return 0;
        }

        if (coins.size() == 1)
        {
            return target % coins.getFirst();
        }

        // Include
        int include = search(target - coins.getLast(), coins);

        // Exclude
        coins.remove(coins.getLast());
        int exclude = search(target, coins);

        return include + exclude;
    }
}
