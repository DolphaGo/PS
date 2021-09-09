class Solution {
    public int solution(int[] numbers) {
        int sum = 45;
        for(int num:numbers){
            sum-=num;
        }
        return sum;
    }
}
