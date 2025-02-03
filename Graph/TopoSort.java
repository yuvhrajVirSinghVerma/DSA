package Graph;
import java.util.*;
public class TopoSort {
    //using dfs
    public void dfs(int n,int vis[],Stack<Integer>st,List<List<Integer>>adj){
        vis[n]=1;

        for(int i:adj.get(n)){
            if(vis[i]==0){
                dfs(i,vis,st,adj);
            }
        }
        st.add(n);
    }
}
