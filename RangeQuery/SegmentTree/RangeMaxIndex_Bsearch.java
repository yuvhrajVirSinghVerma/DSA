package RangeQuery.SegmentTree;

import java.util.Arrays;

public class RangeMaxIndex_Bsearch {
    int segmentT[];
    int arr[];
    RangeMaxIndex_Bsearch(int arr[],int n){
        segmentT=new int[4*n];
        this.arr=arr;
    }
    void buildTree(int currI,int l,int r){
        if(l==r){
            segmentT[currI]=l;
            return;
        }
        int mid=(l+r)/2;
        buildTree(2*currI+1, l, mid);
        buildTree(2*currI+2, mid+1, r);

        int l_ind=segmentT[2*currI+1];
        int r_ind=segmentT[2*currI+2];

        if(arr[l_ind]>=arr[r_ind]){
                segmentT[currI]=l_ind;
        }else{
                segmentT[currI]=r_ind;
        }

    }
    int rmiq(int ql,int qr,int currInd,int l, int r){
        if(r<ql || l>qr)return -1; 
        if(l>=ql && r<=qr)return segmentT[currInd];
        int mid=(l+r)/2;
        
        int left=rmiq(ql, qr, 2*currInd+1, l, mid) ;
        int right=rmiq(ql, qr, 2*currInd+2, mid+1, r); 

        if(left==-1)return right;
        if(right==-1)return left;

         if(arr[left]>=arr[right]){
                return left;
        }
            return right;
    }

    void main(){
        int arr[]={1,4,5,6,8,2,3,5};
        int queries[][]={{0,1},{0,2},{3,4},{5,6}};

        //find leftmost index whose val is greater thant both q1 and q2  => queries[i] =[q1,q2]
        RangeMaxIndex_Bsearch t=new RangeMaxIndex_Bsearch(arr,arr.length);
        t.buildTree(0, 0, arr.length-1);
        int ans[]=new int[arr.length-1];
        int in=0;
        // 1,  4,  5,  6,  8,  2,  3,  5
        // q1      q2
        //binar search s       mid      e    => if we get max at any index before mid we can store it as posiible answer as it leftmost and try to search for e=mid-1
        for(int i[]:queries){
            int s=i[1];
            int e=arr.length-1;

            while(s<=e){
                int mid=(s+e)/2;
                int ind=t.rmiq(s, mid, 0, 0,arr.length-1);

                if(arr[ind]>i[0] && arr[ind]>i[1]){
                    ans[in]=ind;
                    e=mid-1;
                }else{
                    s=mid+1;
                }
            }
            in++;
        }

        System.out.println("ans++++ "+Arrays.toString(ans));
    }
}
