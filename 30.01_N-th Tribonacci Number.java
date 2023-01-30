class Solution {
    public int tribonacci(int n) {
        if(n == 0){
            return 0;
        }
        else if(n < 3){
            return 1;
        }
        else{
            return getTribonacci(n);
        }        
    }
    public int getTribonacci(int n){
        int t0 = 0;
        int t1 = 1;
        int t2 = 1;
        for(int i = 3 ; i <= n ; i++){
            int temp = t0+t1+t2;
            t0=t1;
            t1=t2;
            t2=temp;
        }
        return t2;
    }
}