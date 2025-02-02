package Graph.Mst;
import Graph.*;
import java.util.*;
//consoder each vertex as disjoin and sort weights in ascending order so that we can join first with min weights
public class Kruskals {
    public class pair{
        int u, v, d;
        pair(int u,int v,int d){
            this.u=u;
            this.d=d;
            this.v=v;
        }
    }
    public void mst(int V,List<List<int[]>>adj){
        // [
        //     0 => [n1,wt2],
        //     1 => [n2,wt2]
        // ]
        List<pair>l=new ArrayList<pair>();

        Dsu dsu=new Dsu(V);   
        for(int i=0;i<V;i++){
            for(int neigh[]:adj.get(i)){
                int n=neigh[0];
                int wt=neigh[1];
                l.add(new pair(i, n, wt));
            }
        }
        Collections.sort(l,(a,b)->a.d-b.d); //sorting on basis of weights

        int ans=0;
        for(pair p:l){
            int node=p.u;
            int neigh=p.v;
            int wt=p.d;

            int p1=dsu.find(node);
            int p2=dsu.find(neigh);

            if(p1!=p2){
                ans+=wt;
                dsu.union(node,neigh);
            }
        }
        System.out.println(ans);
        
    }
}
