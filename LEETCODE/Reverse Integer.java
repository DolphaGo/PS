class Solution {
    public int reverse(int x) {
        StringBuilder sb=new StringBuilder();
        if(x<0){
            String s=String.valueOf(x).substring(1);
            return -Integer.parseInt(sb.append(s).reverse().toString());
        }else{
            String s=String.valueOf(x);
            return Integer.parseInt(sb.append(s).reverse().toString());
        }
    }
}