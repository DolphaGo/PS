class Solution {
    public int romanToInt(String s) {
        Map<Character,Integer> map=new HashMap<>();
        map.put('I',1); map.put('V',5); map.put('X',10); map.put('L',50);
        map.put('C',100); map.put('D',500); map.put('M',1000);

        int answer=0;
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            boolean check=false;
            if(i+1<s.length()){
                char next=s.charAt(i+1);
                int number=getNumber(c,next);
                if(number!=0) {
                    check=true;
                    i++;
                }
                answer+=number;
            }
            if(!check) answer+=map.get(c);
        }
        return answer;
    }
    static int getNumber(char c,char next){
        if(c=='I'){
            if(next=='V') return 4;
            if(next=='X') return 9;
        }else if(c=='X'){
            if(next=='L') return 40;
            if(next=='C') return 90;
        }else if(c=='C'){
            if(next=='D') return 400;
            if(next=='M') return 900;
        }
        return 0;
    }
}