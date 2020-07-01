class Solution {
    public boolean solution(int[] arrA, int[] arrB) {
        //길이 검사
		if(arrA.length!=arrB.length) return false;
		int len=arrA.length;
		for(int i=0;i<len;i++) {
			if(check(arrA,arrB)) return true;
			move(arrA);
		}
		return false;
    }
    static void move(int[] a) {
		int len=a.length;
		int tmp=a[len-1];
		for(int i=len-1;i>0;i--) a[i]=a[i-1];
		a[0]=tmp;
	}
	static boolean check(int[] a,int[] b) {
		int len=a.length;
		for(int i=0;i<len;i++) {
			if(a[i]!=b[i]) return false;
		}
		return true;
	}
    
}