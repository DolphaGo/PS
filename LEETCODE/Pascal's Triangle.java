class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> answer=new ArrayList<>();
        for(int i=0;i<numRows;i++){
            List<Integer> list=new ArrayList<>();
            for(int j=0;j<=i;j++){
                if(j==0 || j==i) list.add(1);
                else {
                    List<Integer> temp=answer.get(i-1);
                    int num1=temp.get(j-1);
                    int num2=temp.get(j);
                    list.add(num1+num2);
                }
            }
            answer.add(list);
        }
        return answer;
    }
}