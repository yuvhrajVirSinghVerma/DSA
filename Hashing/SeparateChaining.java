package Hashing;

public class SeparateChaining {
    
    class Node{
        int val;
        Node next=null;
        Node(int val){
            this.val=val;
        }
    }
    void SortedInsert(Node root,int val){
        Node node=new Node(val);
        if(root==null || root.val>=val){
            node.next=root;
            root=node;
            return;
        }

        Node temp=root;
        while(temp.next!=null && temp.next.val<val){
            temp=temp.next;
        }
        node.next=temp.next;
        temp.next=node;
    }

    int hashFunction(int k){
        return k%10;
    }
    void Insert(Node hashTable[],int key){
        int index=hashFunction(key);
        SortedInsert(hashTable[index],key);
    }

    Node Search(Node node,int key){
        Node temp=node;

        while(temp!=null){
            if(temp.val==key)return temp;
            temp=temp.next;
        }
        return null;
    }

void delete(Node node,int k){
    //same code for deleting a node in linkedlist
}
    void main(){
        Node hashTable[]=new Node[10];
        Insert(hashTable,10);
        Insert(hashTable,12);
        Insert(hashTable,14);
        Insert(hashTable,15);

        System.out.println(Search(hashTable[14], 14));

    }
}
