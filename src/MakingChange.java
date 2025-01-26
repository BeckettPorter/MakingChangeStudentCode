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

    // Index corresponds to the coin, array at that index is the already tried number of those coins.
    private static ArrayList<Integer>[] coinCombos;

    public static long countWays(int target, int[] coins)
    {
        int numWaysFound = 0;

        // Need to find the TOTAL number of ways to make up the target given an array of ints (coins).
        int highestCoin = 0;
        for (int c : coins)
        {
            highestCoin = Math.max(c, highestCoin);
        }
        coinCombos = new ArrayList[highestCoin + 1];

        for (int i = 0; i < coinCombos.length; i++)
        {
            coinCombos[i] = new ArrayList<>();
        }

        for (int i = 0; i < coins.length; i++)
        {
            long numCanUseCoin = 0;
            while (numCanUseCoin != -1)
            {
                numCanUseCoin = useCoinUntilCant(coins[i], target);
            }
        }

        for (int coin1 : coins)
        {
            for (int coin2 : coins)
            {
                if (coin1 != coin2)
                {
                    for (int mult1 : coinCombos[coin1])
                    {
                        for (int mult2 : coinCombos[coin2])
                        {
                            if (coin1 * mult1 + coin2 * mult2 == target)
                            {
                                numWaysFound++;
                            }
                        }
                    }
                }
            }
        }







        return numWaysFound;
    }

    private static long useCoinUntilCant(int coin, int target)
    {
        int calculatedAmount = 0;
        int numThisCoin = 0;

        // All possible coin counts for this coin have already been calculated.
        if (coinCombos[coin].contains(1))
        {
            return -1;
        }

        while (calculatedAmount + coin <= target && !coinCombos[coin].contains(numThisCoin + 1))
        {
            calculatedAmount += coin;
            numThisCoin++;
        }
        coinCombos[coin].add(numThisCoin);

        return numThisCoin;
    }
}
