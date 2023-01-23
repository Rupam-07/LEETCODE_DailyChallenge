class Solution {
    public int findJudge(int n, int[][] trust) {

        int count[] = new int[n+1];

        for(int i=0;i<trust.length;i++){
            count[trust[i][0]] = Integer.MIN_VALUE;
            count[trust[i][1]]++;
        }

        int judge = -1;

        for(int i=1;i<n+1;i++){
            if(count[i] == n-1){
                judge = i;
                break;
            }
        }

        return judge;
        
    }
}