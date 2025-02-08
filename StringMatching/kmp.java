package StringMatching;

public class kmp {
    
    void lps(int lps[],String s,int pat){
        int len=0;
        int i=1;

        String str=pat+"#"+s; //kmp if lps[i]==pat.length then i-pat.length+1 is starting of ocuurence of pat in s
        while(i<str.length()){

            if(str.charAt(i)==str.charAt(len)){
                lps[i]=len+1;
                i++;
                len++;
            }else{
                if(i>0){
                    len=lps[len-1];
                }else{
                    lps[i]=0;
                    i++;
                }
               
            }
        }
    }
}
