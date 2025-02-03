package Graph.Eulers;
import java.util.*;
//euler path => path that visits all edges of graph exactly once
public class EulerPath {
    void dfs(int i,int vis[],List<List<Integer>>adj){
        vis[i]=1;

        for(int j:adj.get(i)){
            if(vis[j]==0)dfs(j,vis,adj);
        }
    }
    boolean isConnected(List<List<Integer>>adj,int V){
        int node=-1;
        for(int i=0;i<adj.size();i++){
            if(adj.get(i).size()>0){
                node=i;
                break;
            }
        }

        int vis[]=new int[V];
        dfs(node,vis,adj);

        for(int i=0;i<V;i++){
            if(vis[i]==0 && adj.size()>0){ //if graph has multiple components andthat component has edges so its not an euler graph
                return false;
            }
        }
        return true;

    }

    //for undirected graph
    void getEulerPath(List<List<Integer>>adj,int V){

        int even=0,odd=0;
        if(!isConnected(adj, V)){
            System.out.println("not an euler graph");
            return;
        }
        //eulerian path  => exactly 2 nodes have odd degree which is starting and ending node
        //eulerian cycle => all nodes have even degree
        for(List<Integer>l:adj){
            if((l.size()%2)==0){
                even++;
            }else odd++;
        }

        if(odd>2){
            System.out.println("not has euler path");
            return;
        }
        if(odd==0){
            System.out.println("graph contains euler cycle");
        }




    }

    //for directed graph
    void getDirectedEulerPath(List<List<Integer>>adj,int V){
        int in[]=new int[V];
        int out[]=new int[V];

        for(int i=0;i<V;i++){
            out[i]+=adj.get(i).size();

            for(int j:adj.get(i)){
                in[j]+=1;
            }
        }

        int cnt1=0;
        int cnt2=0;
        int cnt3=0;

        int startNode=-1;
        int EndNode=-1;

        for(int i=0;i<V;i++){
            if(in[i]==out[i])cnt3++;

            if(out[i]-in[i]==1){
                startNode=i;
                cnt1++;
            }
            else if(in[i]-out[i]==1){
                EndNode=i;
                cnt2++;
            }
        }

        if(cnt1>1 || cnt2>1){
            System.out.println("Not an Euler Graph");
            return;
        }

        if(cnt3==V)System.out.println("Euler cycle");
    }

}
