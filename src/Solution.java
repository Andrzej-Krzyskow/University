class Solution {

    Integer[] minimumCosts;
    int[] costs = {2, 7, 25};   //day, week, month ticket price

    public int solution(int[] A) {

        minimumCosts = new Integer[A.length];

        return calculateCost(A, 0);
    }

    public int calculateCost(int[] days, int dayPointer) {

        if (dayPointer >= days.length) {
            return 0;
        }

        if (minimumCosts[dayPointer] != null) {
            return minimumCosts[dayPointer];
        }

        int oneDayPass = calculateCost(days, dayPointer + 1) + costs[0];
        int i;

        for (i = dayPointer; i < days.length; i++) {
            if (days[i] >= days[dayPointer] + 7) {
                break;
            }
        }

        int sevenDayPass = calculateCost(days, i) + costs[1];
        for (i = dayPointer; i < days.length; i++) {
            if (days[i] >= days[dayPointer] + 30) {
                break;
            }
        }

        int thirtyDayPass = calculateCost(days, i) + costs[2];

        return minimumCosts[dayPointer] = Math.min(oneDayPass, Math.min(sevenDayPass, thirtyDayPass));
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.solution(new int[]{1, 2, 4, 5, 7, 29, 30}));

    }
}