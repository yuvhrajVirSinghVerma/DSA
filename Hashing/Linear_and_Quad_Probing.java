package Hashing;

import java.util.Arrays;

public class Linear_and_Quad_Probing {
    int hashTableKeys[]=new int[10];
    int hashTableVals[]=new int[10];
    
    Linear_and_Quad_Probing(){
        Arrays.fill(hashTableKeys, -1);
        Arrays.fill(hashTableVals, -1);

    }

    int hashFun(int k){
        return k%10;
    }
    
    void add(int key,int val){
        int ind=hashFun(key);
        int startpt=ind;
        int i=1;
        do{
            if(hashTableKeys[ind]==-1){
                hashTableKeys[ind]=key;
                hashTableVals[ind]=val;
                return ;
            }
            //update value for exisiting key
            if(hashTableKeys[ind]==key){
                hashTableVals[ind]=val;
                return;
            }
            ind=(ind+i)%10;
            //for quad  
            // ind=(ind+i*i)%10; i++;
        }
        while(ind!=startpt); //till we reach starting point again
        
    }

    int search(int key){
        int ind=hashFun(key);
        int i=1;

        while(hashTableKeys[ind]!=-1){
            if(hashTableKeys[ind]==key)return hashTableVals[ind];
            ind=(ind+i)%10;
            //for quad  
            // ind=(ind+i*i)%10; i++;
        }
        return -1;

    }

    void delete(int key){

        //since deletion requires rehashing so its not recommended , for optimisation we can keep flag to check if key is deleted or not
        int ind=hashFun(key);
        int i=1;

        while(hashTableKeys[ind]!=key){
            ind=(ind+i)%10;
            //for quad  
            // ind=(ind+i*i)%10; i++;
        }
        hashTableKeys[ind]=-1;
        hashTableVals[ind]=-1;

        //rehashing 
        ind=(ind+i)%10; //since we have delete prev key so we ar incrementing ind here
        //for quad  
        // ind=(ind+i*i)%10; i++;
        while(hashTableKeys[ind]!=-1){
            int k=hashTableKeys[ind];
            int v=hashTableVals[ind];

            hashTableKeys[ind]=-1;
            hashTableVals[ind]=-1;
            add(k,v);
            ind=(ind+i)%10;
            //for quad  
            // ind=(ind+i*i)%10; i++;
        }

    }
}
