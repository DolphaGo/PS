import java.util.Arrays;

class Solution {
    int[] src;
    int[] data;
    public Solution(int[] nums) {
        src =nums.clone();
        data =nums.clone();
    }

    public int[] reset() {
        return src;
    }

    public int[] shuffle() {
        int n=data.length;
        int i=n-1;
        while(i-1>=0&&data[i-1]>data[i]) --i;
        if(i==0) {
            data=src.clone();
            return shuffle();
        }
        for(int j=n-1;j>=i;j--) {
            if(data[j]>data[i-1]) {
                int temp=data[j];
                data[j]=data[i-1];
                data[i-1]=temp;

                int[] tempArr=new int[n-i];
                for(int x=n-1;x>=i;x--) tempArr[n-1-x]=data[x];
                for(int x=i;x<=n-1;x++) data[x]=tempArr[x-i];

                break;
            }
        }
        return data;
    }

    public void swap(int x,int y){
        int temp=data[x];
        data[x]=data[y];
        data[y]=temp;
    }

    public static void main(String[] args) {
        int[] arr={1,2,3,4,5};
        Solution solution=new Solution(arr);
        for(int i=0;i<50;i++){
            System.out.println(Arrays.toString(solution.shuffle()));
        }
    }
}