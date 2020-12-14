class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> fizzBuzz=new ArrayList<>();
        StringBuilder sb=new StringBuilder();
        for(int i=1;i<=n;i++){
            boolean three=i%3==0?true:false;
            boolean five=i%5==0?true:false;
            if(three) sb.append("Fizz");
            if(five) sb.append("Buzz");
            if(!three&&!five) sb.append(i);
            fizzBuzz.add(sb.toString());
            sb.setLength(0);
        }
        return fizzBuzz;
    }
}