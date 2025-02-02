package Graph;

public class Dsu{
    int par[];
    int size[];

    public Dsu(int n){
        par=new int[n];
        size=new int[n];

        for(int i=0;i<n;i++){
            par[i]=i;
            size[i]=1;
        }
    }
    public int find(int n){
        if(par[n]==n)return n;

        return par[n]=find(par[n]); //compressing path here eg => 1  =>       1
                                     //                         / | \      / / \ \
                                     //                        2  3  4    2 3   4 5
                                     //                       /
                                    //                      5
    }

   public void union(int a,int b){
        int p1=find(a);
        int p2=find(b);

        if(p1!=p2){
            par[p1]=p2; //without any optimisation

            //++++with size optimisation++++

            // if(size[p1]>size[p2]){
            //     par[p2]=p1;
            // }else{
            //     par[p1]=p2;
            // }

        }
    }
}