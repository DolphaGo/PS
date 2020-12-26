class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> answer=new ArrayList<>();
        boolean[] check=new boolean[nums.length];
        int[] data=new int[nums.length];
        go(answer, data, nums, check,0);
        return answer;
    }

    public void go(List<List<Integer>> answer, int[] data,int[] nums,boolean[] check,int count){
        if(count==nums.length){
            List<Integer> list=Arrays.stream(data)
                                     .boxed()
                                     .collect(Collectors.toList());
            answer.add(list);
            return;
        }

        for(int i=0;i<nums.length;i++){
            if(check[i]) continue;
            check[i]=true;
            data[count++]=nums[i];
            go(answer, data,nums,check,count);
            count--;
            check[i]=false;
        }
    }
}