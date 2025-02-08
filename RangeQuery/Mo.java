package RangeQuery;

import java.util.*;

public class Mo {
    int freq[]=new int[(int)1e5];
    class Query{
        int l,r,i;
        Query(int l,int r,int i){
            this.l=l;
            this.r=r;
            this.i=i;
        }
    }
    void process(int queries[][],int arr[]){
        ArrayList<Query>q=new ArrayList<>();
        int ind=0;
        for(int i[]:queries){
            q.add(new Query(i[0], i[1], ind));
            ind++;
        }
        int len=(int)Math.ceil(Math.sqrt(arr.length));
        Collections.sort(q,(a,b)->a.l/len!=b.l/len?a.l-b.l:a.r-b.r);

        int l=0;int r=-1;

       for(Query qu:q){
        int ql=qu.l;
        int qr=qu.r;

        //increasing range my l is greater than ql or my ris less than qr add that index freq
        while(l>ql)operate(arr,--l, 1); //increase freq by one
        while(r<qr)operate(arr,++r, 1);

        //decreasing range
        while(l<ql)operate(arr,++l, -1); //dcrease freq by one
        while(r>qr)operate(arr,--r, -1);
// similarly we can do for sum of range
       }


    }
void operate(int arr[],int ind,int op){
    int ele=arr[ind];
    freq[ele]+=op;
}
   
}
