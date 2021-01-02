class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map=new HashMap<>();
        List<List<String>> answer=new ArrayList<>();
        for(String s:strs){
            char[] arr=s.toCharArray();
            Arrays.sort(arr);
            String key=String.valueOf(arr);
            List<String> list=map.getOrDefault(key,new ArrayList<>());
            list.add(s);
            map.put(key,list);
        }

        for(String s:map.keySet()){
            answer.add(map.get(s));
        }

        return answer;
    }
}