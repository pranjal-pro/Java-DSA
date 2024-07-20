import java.util.*;

public class AlphaGreedyAlgo {
    public static void main(String args[]) {
        int start[] = { 1, 3, 0, 5, 8, 5 };
        int end[] = { 2, 4, 6, 7, 9, 9 };
        System.out.println(activitySelectionSorted(start, end));
        System.out.println(activitySelection(start, end));

        int value[] = { 60, 100, 120 };
        int weight[] = { 10, 20, 30 };
        System.out.println(knapsack(weight, value, 50));

        int a[] = { 1, 2, 3 };
        int b[] = { 2, 3, 1 };
        System.out.println(minAbsoluteDiff(a, b));

        int pair[][] = { { 5, 24 }, { 39, 60 }, { 5, 28 }, { 27, 40 }, { 50, 90 } };
        System.out.println(maxChainPair(pair));

        System.out.println(indianCoin(243));

        int deadline[] = { 4, 1, 1, 1 };
        int profit[] = { 20, 10, 40, 30 };
        System.out.println(jobSequence(deadline, profit));

        Integer width[] = { 2, 1, 3, 1, 4 };
        Integer lenght[] = { 4, 1, 2 };
        System.out.println(chocolaProblem(lenght, width));

        System.out.println(maxBalanceStringPartitions("LRRRLRRLLRLRLL"));
        System.out.println(kLargestOddNumber(0, 7, 5));
        System.out.println(smallestSubString(4, 30));
        int prices[] = { 1, 2, 3, 4, 5 };
        System.out.println(maxProfit(prices));
        int array[] = { 1, 2, 3, 4 };
        maxOfMinSubArray(array, 2, 0, 0);
        System.out.println(ans);
    }

    public static Stack<Integer> activitySelectionSorted(int start[], int end[]) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for (int i = 0; i < start.length; i++) {
            if (end[stack.peek()] <= start[i])
                stack.push(i);
        }
        return stack;
    }

    public static Stack<Integer> activitySelection(int start[], int end[]) {
        int sort[][] = new int[start.length][3];
        for (int i = 0; i < start.length; i++) {
            sort[i][0] = i;
            sort[i][1] = start[i];
            sort[i][2] = end[i];
        }
        Arrays.sort(sort, Comparator.comparingDouble(o -> o[2]));
        Stack<Integer> stack = new Stack<>();
        stack.push(sort[0][0]);
        for (int i = 1; i < start.length; i++) {
            if (end[stack.peek()] <= sort[i][1])
                stack.push(sort[i][0]);
        }
        return stack;
    }

    public static double knapsack(int weight[], int value[], int maxWeight) {
        double maxValue = 0;
        double ratio[][] = new double[weight.length][2];
        for (int i = 0; i < weight.length; i++) {
            ratio[i][0] = i;
            ratio[i][1] = value[i] / weight[i];
        }
        Arrays.sort(ratio, Comparator.comparingDouble(o -> o[1]));
        for (int i = weight.length - 1; i >= 0 && maxWeight > 0; i--) {
            if (maxWeight > weight[(int) ratio[i][0]]) {
                maxWeight -= weight[(int) ratio[i][0]];
                maxValue += value[(int) ratio[i][0]];
            } else {
                maxValue += value[(int) ratio[i][0]] / ((double) weight[(int) ratio[i][0]] / (maxWeight));
                maxWeight = 0;
            }
        }
        return maxValue;
    }

    public static int minAbsoluteDiff(int a[], int b[]) {
        int diff = 0;
        Arrays.sort(a);
        Arrays.sort(b);
        for (int i = 0; i < a.length; i++) {
            diff += a[i] > b[i] ? a[i] - b[i] : b[i] - a[i];
        }
        return diff;
    }

    public static int maxChainPair(int pair[][]) {
        int max = 1;
        Arrays.sort(pair, Comparator.comparingDouble(o -> o[1]));
        int end = pair[0][1];
        for (int i = 1; i < pair.length; i++)
            if (pair[i][0] >= end) {
                end = pair[i][1];
                max++;
            }
        return max;
    }

    public static int indianCoin(int value) {
        int currencyReq = 0;
        int currency[] = { 2000, 500, 100, 50, 20, 10, 5, 2, 1 };
        for (int i = 0; i < currency.length && value != 0; i++) {
            if (value / currency[i] != 0) {
                currencyReq += value / currency[i];
                value = value % currency[i];
            }
        }
        return currencyReq;
    }

    public static Stack<Integer> jobSequence(int deadline[], int profit[]) {
        int sort[][] = new int[deadline.length][3];
        for (int i = 0; i < deadline.length; i++) {
            sort[i][0] = i;
            sort[i][1] = deadline[i];
            sort[i][2] = profit[i];
        }
        Arrays.sort(sort, Comparator.comparingDouble(o -> o[1]));
        Stack<Integer> queue = new Stack<>();
        queue.push(sort[0][0]);
        for (int i = 1; i < deadline.length; i++) {
            if (deadline[queue.peek()] == sort[i][1]) {
                if (profit[queue.peek()] < sort[i][2]) {
                    queue.pop();
                    queue.push(sort[i][0]);
                }
            } else {
                queue.push(sort[i][0]);
            }
        }
        return queue;
    }

    public static int chocolaProblem(Integer lenght[], Integer width[]) {
        int trackL = 0, trackW = 0;
        int pieceL = 1, pieceW = 1;
        int value = 0;
        Arrays.sort(lenght, Collections.reverseOrder());
        Arrays.sort(width, Collections.reverseOrder());
        while (trackL != lenght.length && trackW != width.length) {
            if (lenght[trackL] > width[trackW]) {
                value += pieceW * lenght[trackL];
                pieceL++;
                trackL++;
            } else {
                value += pieceL * width[trackW];
                pieceW++;
                trackW++;
            }
        }
        while (trackL != lenght.length) {
            value += pieceW * lenght[trackL];
            pieceL++;
            trackL++;
        }
        while (trackW != width.length) {
            value += pieceL * lenght[trackW];
            pieceW++;
            trackW++;
        }
        return value;
    }

    public static int maxBalanceStringPartitions(String str) {
        int partition = 0, l = 0, r = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == 'L')
                l++;
            else
                r++;
            if (l == r)
                partition++;
        }
        return partition;
    }

    public static int kLargestOddNumber(int start, int end, int k) {
        k--;
        if (k > (end - start) / 2)
            return 0;
        end = end % 2 == 0 ? end-- : end;
        return end - k * 2;
    }

    public static String smallestSubString(int lenght, int sum) {
        String str = "";
        if (sum >= lenght && lenght * 26 >= sum) {
            while (sum >= 25 + lenght) {
                str = 'z' + str;
                sum -= 26;
                lenght--;
            }
            while (sum < 25 + lenght && sum != lenght && lenght > 0) {
                str = (char) ((char) (sum - lenght) + 'a') + str;
                sum = --lenght;
            }
            while (lenght > 0) {
                str = 'a' + str;
                sum = --lenght;
            }
        }
        return str;
    }

    public static int maxProfit(int prices[]) {
        int buy = prices[0], profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (buy > prices[i])
                buy = prices[i];
            if (profit < prices[i] - buy)
                profit = prices[i] - buy;
        }
        return profit;
    }

    public static int ans = 1000;

    public static void maxOfMinSubArray(int array[], int k, int start, int maxSum) {
        int sum = 0;
        if (k == 1) {
            for (int i = start; i < array.length; i++)
                sum += array[i];
            maxSum = maxSum > sum ? maxSum : sum;
            ans = Math.min(ans, maxSum);
        }
        for (int i = start; i < array.length; i++) {
            sum += array[i];
            maxSum = Math.max(maxSum, sum);
            maxOfMinSubArray(array, k - 1, i + 1, maxSum);
        }
    }
}