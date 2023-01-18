class Solution {
    public int maxSubarraySumCircular(int[] arr) {
        int sum = arr[0];
		int min_csum = arr[0];
		int min_osum = arr[0];
		int max_csum = arr[0];
		int max_osum = arr[0];

		for(int i = 1; i < arr.length; i++){
			sum += arr[i];

			if(max_csum > 0){
				max_csum += arr[i];
			} else { 
				max_csum = arr[i];
			}

			if(min_csum < 0){
				min_csum += arr[i];
			} else {
				min_csum = arr[i];
			}

			max_osum = Math.max(max_osum , max_csum);
			min_osum = Math.min(min_osum , min_csum);
		}

		return (sum == min_osum) ? max_osum : Math.max(max_osum, sum - min_osum);
    }
    

}