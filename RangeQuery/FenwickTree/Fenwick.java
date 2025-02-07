package RangeQuery.FenwickTree;

//only useful for mutable range sum query it takes less space than segment tree
// (n&-n) gives last set bit
public class Fenwick {
    int BIT[];
    int arr[];
    Fenwick(int arr[]){
        this.arr=arr;
        BIT=new int[arr.length+1];
    }
    //tc O(nlogn)
    void update(int index,int val){
        for(int i=index;i<=arr.length;i+=(i&-i)){
            BIT[i]+=val;
        }
    }

    //tc O(n)
    void optimisedUpdate(){
        int preS[]=new int[arr.length];
        for(int i=1;i<preS.length;i++){
            preS[i]=preS[i-1]+arr[i];
        }

        for(int i=1;i<preS.length;i++){
            int idash=i-(i&-i);
            BIT[i]=preS[i]-preS[idash];
        }


    }
    int query(int index){
        int ans=0;
        for(int i=index;i>=0;i-=(i&-i)){
            ans+=BIT[i];
        }
        return ans;
    }

    void main(){
        int arr[]={1,4,5,6,7,8};
        int query[][]={{0,2},{3,5}};

        int preArr[]=new int[arr.length+1];

        Fenwick obj=new Fenwick(preArr);
        for(int i=0;i<arr.length;i++){
            preArr[i+1]=arr[i];
        }

        for(int i=1;i<preArr.length;i++){
            obj.update(i, preArr[i]);
        }

        for(int i[]:query){
            int ans=obj.query(i[1])-obj.query(i[0]);
            System.out.println(ans);
        }
    }

}
