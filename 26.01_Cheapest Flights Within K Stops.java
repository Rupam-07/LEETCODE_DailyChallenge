class pair{
	int dest;
	int distance;

	public pair(int adest, int adistance){
		this.dest=adest;
		this.distance= adistance;
	}
}


class Solution {
	public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
		Queue<int[]> q = new ArrayDeque<>();
		int[] distance = new int[n];
		Arrays.fill(distance,Integer.MAX_VALUE);

		ArrayList<List<pair>> adj = new ArrayList<>();
		for(int i=0;i<n;i++)adj.add(new ArrayList<>());
		for(int[] arr:flights){
			int source = arr[0];
			int destination = arr[1];
			int price= arr[2];
			adj.get(source).add(new pair(destination,price));
		}

		q.offer(new int[]{src,0,0});
		distance[src]=0;
		while(!q.isEmpty()){
			int[] arr = q.poll();
			if(arr[2]>k)continue;
			int prevPrice = arr[1];
			int oldK = arr[2];
			for(pair p:adj.get(arr[0])){
				int node = p.dest;
				int newdistance = p.distance;

				if(oldK<=k && prevPrice+newdistance<distance[node]){
					distance[node]=prevPrice+newdistance;
					q.offer(new int[]{node,distance[node],oldK+1});
				}
			}
		}

		return distance[dst]==Integer.MAX_VALUE?-1:distance[dst];

	}
}