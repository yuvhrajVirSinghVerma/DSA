package RangeQuery;

import java.util.Arrays;

public class SqrtDecomposition {
    int query(int l,int r,int sqrt[],int arr[],int len){
        int ans=Integer.MAX_VALUE;
        while(l<=r){
            if(l%len==0 && l+len-1<=r){//starting of new block in sqrt
                ans=Math.min(ans,sqrt[l/len]);
                l+=len;
            }else{
                ans=Math.min(ans,arr[l]);
                l++;
            }
        }
        return ans;
    }
    void preProcess(int arr[],int len,int sqrt[]){
        
        //for finding min value in range
        Arrays.fill(sqrt,Integer.MAX_VALUE);
        for(int i=0;i<arr.length;i++){
            int sqrtInd=i/len;
            sqrt[sqrtInd]=Math.min(sqrt[sqrtInd],arr[i]);
        }
    }

    void update(int ind,int val,int sqrt[],int arr[],int len){
        //min or max we need to iterate on that block to find min or max so tc will be sqrt(n)
        //for sum
        arr[ind]+=val;
        sqrt[ind/len]+=val;
    }
    void main(){

        int arr[]={1,2,3,45,6,7};
        int len=(int)Math.ceil(Math.sqrt(arr.length));
        int sqrt[]=new int[len];
        preProcess(arr, len, sqrt);
    }
}
