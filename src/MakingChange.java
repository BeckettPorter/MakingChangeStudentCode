
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
        int totalWays = 0;


        for (int testCoin : coins)
        {
            int testTarget = target;

            for (int comparisonCoin : coins)
            {
                if (testCoin != comparisonCoin)
                {
                    while (testTarget > 0)
                    {
                        if (canMakeTarget(testTarget, testCoin))
                        {
                            totalWays++;
                        }
                        testTarget -= comparisonCoin;
                    }
                }
            }

        }

        return totalWays;
    }

    private static boolean canMakeTarget(int target, int coin)
    {
        return (target % coin == 0);
    }
}
