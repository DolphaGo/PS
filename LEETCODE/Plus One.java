class Solution {
    public int[] plusOne(int[] digits) {
        int n=digits.length;
        digits[n-1]+=1;
        for(int i=n-1;i>=1;i--){
            if(digits[i]==10){
                digits[i]=0;
                digits[i-1]+=1;
            }else break;
        }

        if(digits[0]==10){
            int[] newDigits=new int[n+1];
            newDigits[0]=1;
            return newDigits;
        }else return digits;
    }
}