import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        int[] nums={-1,0,1,2,-1,4};
        System.out.println(Arrays.toString(threeSum(nums).toArray()));
    }
    static public List<List<Integer>> threeSum(int[] nums) {
        int n=nums.length;
        Arrays.sort(nums);
        Set<List<Integer>> set=new HashSet<>();
        List<List<Integer>> answer=new ArrayList<>();
        for(int i=0;i<n-2;i++){
            int a=nums[i];
            for(int j=i+1;j<n-1;j++){
                List<Integer> list=new ArrayList<>();
                int b=nums[j];
                int tmp=a+b;

                int s=j+1;
                int e=n-1;
                while(s<e){
                    int m=(s+e)>>1;
                    if(tmp+nums[m]<0) s=m+1;
                    else e=m;
                }
                if(tmp+nums[e]==0){
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[e]);
                    set.add(list);
                }
            }
        }
        answer.addAll(set);
        return answer;
    }
}