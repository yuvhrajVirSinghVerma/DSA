package Graph.Mst;

import java.util.List;
import java.util.PriorityQueue;

//spanning tree => a undirected subgraph having e-1 edges and V number of connected vertices 
//select a node and add only visit to adjacent node with min weight edge with help of priority Queue
public class Prims {
    public class pair{
        int n;int d;
        pair(int n,int d){
            this.n=n;
            this.d=d;
        }
    }
    public int spanningTree(int V, int E, List<List<int[]>> adj) {
        // Code Here.
        PriorityQueue<pair>pq=new PriorityQueue<>((a,b)->a.d-b.d);
        pq.add(new pair(0,0));
        
        int vis[]=new int[V];
        int ans=0;
        while(!pq.isEmpty()){
            pair p=pq.poll();
            int d=p.d;
            int n=p.n;
            
            if(vis[n]==1)continue; //if this node is already part of mst ignore since we reached here with min weight before
            
            vis[n]=1;
            ans+=d;
            for(int i[]:adj.get(n)){
                int neigh=i[0];
                int wt=i[1];
                
                if(vis[neigh]==0)pq.add(new pair(neigh,wt));
            }
            
           
        }
        return ans;
    }
}
