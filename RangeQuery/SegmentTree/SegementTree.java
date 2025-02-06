package RangeQuery.SegmentTree;

public class SegementTree {
    int segmentT[];
    int arr[];
    int lazy[];
    SegementTree(int n,int arr[]){
        //leaf nodes equals to size of array
        segmentT=new int[4*n];// n+ n/2 + n/4....1 => gp(a/1-r) =>(n/(1-(1/2)))=>approx 2*n nodes , if n is not power of 2 we need to add dummy nodes for pairing so its approx to 4n nodes
        this.arr=arr;
        lazy=new int[4*n];
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

    int query(int ql,int qr,int currInd,int l, int r){
        if(r<ql || l>qr)return 0; //out of bound range
        if(l>=ql && r<=qr)return segmentT[currInd];//query range is withing range of node [l,r]
        int mid=(l+r)/2;
        return query(ql, qr, 2*currInd+1, l, mid) + query(ql, qr, 2*currInd+2, mid+1, r); //overlapping
    }

    //updating a range with a given value
    void lazyPropagation(int ul,int ur,int val,int currInd,int l,int r){
        if(lazy[currInd]!=0){
            segmentT[currInd]+=(r-l+1)*lazy[currInd];
            if(l!=r){ //if not leaf node update its both child nodes
                lazy[2*currInd+1]+=lazy[currInd];
                lazy[2*currInd+2]+=lazy[currInd];
            }
            lazy[currInd]=0;
        }
        if(r<ul || l>ur)return ; //out of bound range

        if(l>=ul && r<=ur){
            int childrens=r-l+1;
            
            segmentT[currInd]+=val*childrens;

            if(l!=r){ //if not leaf node update its both child nodes
                lazy[2*currInd+1]+=val;
                lazy[2*currInd+2]+=val;
            }
            
            return;
        }

        int mid=(l+r)/2;
        lazyPropagation(ul, ur, val, 2*currInd+1, l, mid);
        lazyPropagation(ul, ur, val, 2*currInd+2, mid+1, r);

        segmentT[currInd]=segmentT[2*currInd+1]+segmentT[currInd+2];

    }
}
