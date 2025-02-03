package Hashing;
import java.util.*;
public class DoubleHashing {
    //other hash functions 
    // mod =>  k%size
    // midsquare => square number and tak mid ele as index if it exceed size mod it  => 11 => 11*11 => 121 => index = 2
    //folding => add group of digits to get index if it exceed fold it again or mod it   eg=123342 =>12+33+42
    class pair{
        int k,v;
        pair(int k,int v){
            this.k=k;
            this.v=v;
        }
    }
    int SIZE=20;
    int primaryHash(int k){
        return 8-(k%8);
    }
    int secondHash(int k){
        return k%10;
    }

    int doubleHash(pair h[],int k){
        int i=0;
        int ind=primaryHash(k);
        while(h[(primaryHash(k)+i*secondHash(k))%SIZE]!=null){
            i++;
        }
        return (ind+i*primaryHash(k))%SIZE;
    }
    void insert(pair h[],int k,int v){
        int ind=primaryHash(k);

        while(h[ind]!=null){
            ind=doubleHash(h, k);
        }

        h[ind]=new pair(k,v);
    }

    int search(pair h[],int k){
        int ind=primaryHash(k);

        while(h[ind]!=null && h[ind].k!=k){
            ind=doubleHash(h, k);
        }
        return h[ind]==null?-1:h[ind].v;
    }

    void remove(pair h[],int k){
        int ind=primaryHash(k);

        while(h[ind]!=null && h[ind].k!=k){
            ind=doubleHash(h, k);
        }
        h[ind]=null;
    }
}
