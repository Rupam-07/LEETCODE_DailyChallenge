class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int[][] new1=new int[intervals.length+1][2];
        int i=0;
        for(i=0;i<intervals.length;i++){
            new1[i]=intervals[i];
        }
        new1[i]=newInterval;
        Arrays.sort(new1,(a,b)->a[0]-b[0]);
        ArrayList<int[]> ans=new ArrayList<>();
        int s=new1[0][0];
        int e=new1[0][1];
        for(int[] k:new1){
            if(e>=k[0]){
                e=Math.max(e,k[1]);
            }else{
                ans.add(new int[]{s,e});
                s=k[0];
                e=k[1];
            }
        }
        ans.add(new int[]{s,e});
        int[][] ans1=new int[ans.size()][2];
        for(int k=0;k<ans.size();k++){
            ans1[k]=ans.get(k);
        }
        return ans1;
    }
}