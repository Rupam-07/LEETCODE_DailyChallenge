class Player implements Comparable<Player>{
    int score;
    int age;
    Player(int s, int a) {
        score = s;
        age = a;
    }
    
    public int compareTo(Player other) {
        return this.age == other.age ? this.score - other.score : this.age - other.age;
    }
}
class Solution {
    public int bestTeamScore(int[] scores, int[] ages) {
        Player arr[] = new Player[scores.length];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = new Player(scores[i], ages[i]);
        }
        Arrays.sort(arr);
        int sequence[] = new int[arr.length];
        for(int i = 0; i < arr.length; i++) {
            sequence[i] = arr[i].score;
        }
        return LISS(sequence);
    }
    private int LISS(int sequence[]) {
        int dp[] = new int[sequence.length];
        for(int i = 0; i < dp.length; i++) {
            dp[i] = sequence[i];
        }
        int max = -1;
        for(int i = 0 ; i < dp.length; i++) {
            for(int j = 0; j < i; j++) {
                if(sequence[j] <= sequence[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + sequence[i]);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}