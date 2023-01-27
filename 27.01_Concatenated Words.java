import java.util.*;
class Solution {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> ans= new ArrayList<>();
        Set<String> set= new HashSet<>();

        for(String s : words){
            set.add(s);
        }

        for(String s : words){
            set.remove(s);

            if(concatenated(s, set)){
                ans.add(s);
            }

            set.add(s);
        }

        return ans;
    }

    public boolean concatenated (String s, Set<String> set){
        if(s.length() == 0) return false;
        if(set.size() == 0) return false;
        int lt= s.length();
        boolean[] arr= new boolean[lt+1];
        arr[0]= true;

        for(int i=0;i<=lt;i++){
            for(int j=0;j<i;j++){
                if(arr[j]== false) continue;
                else{
                    if(set.contains(s.substring(j,i))){
                        arr[i] = true;
                        break;
                    }
                }
            }
        }
        return arr[lt];
    }
}