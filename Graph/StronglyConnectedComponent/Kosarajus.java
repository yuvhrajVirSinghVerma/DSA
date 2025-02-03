package Graph.StronglyConnectedComponent;

import java.util.*;

import Graph.TopoSort;

//scc => in direct graph it is a subset of graph from where every vertices are reachable from other vertices in same subset
class Kosarajus{

    // toposort and store in stack 
    // reverse edges of graph
    // do dfs on rev graph

    public void dfs(int n,int vis[],List<List<Integer>>adj){
        vis[n]=1;

        for(int i:adj.get(n)){
            if(vis[i]==0){
                dfs(i,vis,adj);
            }
        }
    }

    void Scc(List<List<Integer>>adj,int V){

        // 1 => toposort 
        Stack<Integer>topoStack=new Stack<>();
        int vis[]=new int[V];

        TopoSort topo=new TopoSort();
        for(int i=0;i<V;i++){
            if(vis[i]==0){
                topo.dfs(i, vis, topoStack, adj);
            }
        }

        // 2 => reverse graph
        List<List<Integer>>rev_adj=new ArrayList<>();
        for(int i=0;i<V;i++){

            for(int j:adj.get(i)){
                rev_adj.get(j).add(i);
            }
        }

        // 3 => count scc on order on reversed graph
        vis=new int[V];
        int scc=0;
        while(!topoStack.isEmpty()){
            int node=topoStack.pop();

            if(vis[node]==0){
                dfs(node,vis,rev_adj);
                scc++;
            }
        }

        System.out.println("number of scc : "+scc);
    }

}