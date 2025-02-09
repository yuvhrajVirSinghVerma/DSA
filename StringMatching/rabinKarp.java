package StringMatching;

public class rabinKarp {
    int MOD=(int)1e+7;
    int RADIX=26;
    int hash(String s,int m){
        int factor=1;
        int ans=0;
        for(int i=m-1;i>=0;i--){
            ans+=((s.charAt(i)-'a')*factor)%MOD;
            factor=(factor*RADIX)%MOD;
        }
        return ans;
    }

    void match(String pat,String str){

        int max=1;
        int m=pat.length();
        for(int i=0;i<pat.length();i++){
            max=(max*RADIX)%MOD;
        }

        int patHash=hash(pat, m);
        int currWindowHash=0;
        for(int i=0;i<str.length();i++){
            if(i==0){
                currWindowHash=hash(str, m);
            }else{
                currWindowHash=((currWindowHash*RADIX)%MOD - ((str.charAt(i-1)-'a')*max)%MOD + str.charAt(i+m-1)+MOD)%MOD;
            }

            if(patHash==currWindowHash){
                System.out.println("pat found at "+i);
            }
        }
    }
}
