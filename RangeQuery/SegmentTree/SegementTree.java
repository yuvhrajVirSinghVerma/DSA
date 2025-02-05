package RangeQuery.SegmentTree;

public class SegementTree {
    int segmentT[];
    int arr[];
    SegementTree(int n,int arr[]){
        //leaf nodes equals to size of array
        segmentT=new int[2*n];// n+ n/2 + n/4....1 => gp(a/1-r) =>(n/(1-(1/2)))=>approx 2*n nodes
        this.arr=arr;
        buildTree(0,0,n);
    }

    void buildTree(int currI,int l,int r){
        if(l==r){
            segmentT[currI]=arr[l];
            return;
        }
        int mid=(l+r)/2;
        buildTree(2*currI+1, l, mid);
        buildTree(2*currI+2, mid+1, r);

        //perform any operation here
        segmentT[currI]=segmentT[2*currI+1]+segmentT[2*currI+2];

    }

    void update(int currI,int updatInd,int val,int l,int r){
        if(l==r){
            segmentT[currI]=val;
            return;
        }

        int mid=(l+r)/2;
        if(updatInd<=mid){
            update(2*currI+1, updatInd, val,l, mid);
        }else{
            update(2*currI+2, updatInd,val, mid+1, r);
        }
        segmentT[currI]=segmentT[2*currI+1]+segmentT[2*currI+2];
    }
}
