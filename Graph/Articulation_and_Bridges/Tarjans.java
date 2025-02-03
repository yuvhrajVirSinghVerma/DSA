package Graph.Articulation_and_Bridges;

import java.util.*;

public class Tarjans {
    int timer=0;
    int bridges=0;
    List<int[]>bridgesPair;
    void dfs(int i,int par,int nodeDiscoveryTime[],int nodeMinTime[],int vis[],List<List<Integer>>adj){
        vis[i]=1;
        nodeDiscoveryTime[i]=timer;
        nodeMinTime[i]=timer;
        for(int j:adj.get(i)){
            if(j==par)continue;
            if(vis[j]==0){
                timer++;
                dfs(j,i,nodeDiscoveryTime,nodeMinTime,vis,adj);
                nodeMinTime[i]=Math.min(nodeMinTime[i],nodeMinTime[j]);

                if(nodeMinTime[j]>nodeDiscoveryTime[i]){
                    bridges++;
                    bridgesPair.add(new int[]{i,j});
                }
            }else{
                nodeMinTime[i]=Math.min(nodeMinTime[i],nodeMinTime[j]);
            }
        }
    }

    void dfs2(int i,int par,int nodeDiscoveryTime[],int nodeMinTime[],int vis[],List<List<Integer>>adj,int mark[]){
        vis[i]=1;
        nodeDiscoveryTime[i]=timer;
        nodeMinTime[i]=timer;
        int child=0;
        for(int j:adj.get(i)){
            if(j==par)continue;
            if(vis[j]==0){
                timer++;
                dfs(j,i,nodeDiscoveryTime,nodeMinTime,vis,adj);
                nodeMinTime[i]=Math.min(nodeMinTime[i],nodeMinTime[j]);

                if(nodeMinTime[j]>=nodeDiscoveryTime[i] && par!=-1){
                    mark[i]=1;
                }
                child++;
            }else{
                //if node is visited update min of curr to discovery of visited node if it is min
                // if this node is removed and and i can reach to this node at minimum as to find more minimu node i have to go via this node but it is supposed to be removed 
                nodeMinTime[i]=Math.min(nodeMinTime[i],nodeDiscoveryTime[j]);
            }
        }
        if(child >1 && par==-1){ //for root node eg=>   0
                                 //                   / | \
                                 //                  1  2  3
            mark[i]=1;
        }
    }
    //bridge => any edge if removed then graphs will broken to multiple components
    void Bridges(int V,List<List<Integer>>adj){
        int nodeDiscoveryTime[]=new int[V];
        int nodeMinTime[]=new int[V]; //it helps to identify if any adjacent node of current node is able to return to a node previous to current node or not without passing through its parent node i.e (adjNode != parent && nodeMinTime[adjNode] <= nodeDiscoveryTime[curr]) => adj is reachable tp prev node
        int vis[]=new int[V];
        bridgesPair=new ArrayList<>();
        dfs(0,-1,nodeDiscoveryTime,nodeMinTime,vis,adj);

        System.out.println("number of bridges "+bridges);
        System.out.println("pair of bridges "+bridgesPair);
    }

    // ArticulationPoint => node on whose removal graph breaks into multiple components
    void Articulation(int V,List<List<Integer>>adj){

        int nodeDiscoveryTime[]=new int[V];
        int nodeMinTime[]=new int[V]; // here it will store min time to react a node through any other node if we chose curr node as an articulation point 
        int vis[]=new int[V];
        int mark[]=new int[V];

        dfs2(0,-1,nodeDiscoveryTime,nodeMinTime,vis,adj,mark);
        List<Integer>articulationPoints=new ArrayList<>();
        for(int i=0;i<V;i++){
            if(mark[i]==1){
                articulationPoints.add(i);
            }
        }
        System.out.println("Articulation Points are : "+articulationPoints);
    }
}
