package Bits;

public class bits {
    void RemoveLastSetBit(int n){
        int n1=n-(n&-n);//same as querying in fenwick tree

        int n2=n&n-1;
    }
    void powerOfTwo(int n){
        boolean f=(n&n-1)==0; //power of two contains only one set bit
        System.out.println(f);
    }

    void unsetBit(int n,int ind){
        int n1=n&(~(1<<ind)); // making mask of 1 and then flipping bits using not operator

       
    }

    void setbit(int n,int ind){
        int n1=n|(1<<ind);
    }

    boolean is_ith_Set(int n ,int ind){
         //check if ith bit is unset
         int n1=n&(1<<ind);
         return n1!=0; // if zero then ith bit is not set
    }
}
