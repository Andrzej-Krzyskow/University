class Solution2 {

    public int solution(int[] days) {
        int[] dp = new int[days.length];
        int[] periods = {1, 7, 30};
        int[] costs = {2, 7, 25};

        for (int currentDayPointer = days.length - 1; currentDayPointer >= 0; currentDayPointer--) {
            dp[currentDayPointer] = Integer.MAX_VALUE;

            for (int p = 0; p < 3; p++) {

                int nextDayPointer = currentDayPointer;
                while (nextDayPointer < days.length && days[currentDayPointer] + periods[p] > days[nextDayPointer]) {
                    nextDayPointer++;
                }

                if (nextDayPointer < days.length) {
                    dp[currentDayPointer] = Math.min(dp[currentDayPointer], costs[p] + dp[nextDayPointer]);
                } else {
                    dp[currentDayPointer] = Math.min(dp[currentDayPointer], costs[p]);
                }
            }
        }

        return dp[0];
    }


    public static void main(String[] args) {
        Solution2 solution = new Solution2();

        System.out.println(solution.solution(new int[]{1, 2, 4, 5, 7, 29, 30}));

    }

}