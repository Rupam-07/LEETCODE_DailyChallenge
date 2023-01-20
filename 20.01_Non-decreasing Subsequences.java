class Solution {
    int[] nums;
    Set<List<Integer>> set = new HashSet<>();
    void backtrack(int i, List<Integer> li) {
        if(i == nums.length) return;

        if(li.size() == 0) {
            li.add(nums[i]);
            backtrack(i+1, li);
            li.remove(li.size()-1);
        }else if(nums[i] >= li.get(li.size()-1)) {
            li.add(nums[i]);
            set.add(new ArrayList<>(li));
            backtrack(i+1, li);
            li.remove(li.size()-1);
        }

        backtrack(i+1, li);
    }
    public List<List<Integer>> findSubsequences(int[] nums) {
        this.nums = nums;
        backtrack(0, new ArrayList<>());
        return new ArrayList<>(set);
    }
}