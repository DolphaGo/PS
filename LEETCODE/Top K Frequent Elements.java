class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> Integer.compare(o2[1], o1[1]));
        for(int key:map.keySet()){
            q.add(new int[]{key,map.get(key)});
        }

        int[] answer=new int[k];
        for(int i=0;i<k;i++){
            answer[i]=q.poll()[0];
        }
        return answer;
    }
}