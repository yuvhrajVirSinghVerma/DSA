package StringMatching;

public class Manachers {
    
    void longestPalindrome(String str){
        StringBuilder s = new StringBuilder("#");
        for (char c : str.toCharArray()) {
            s.append(c).append("#");
        }

        int lps[]=new int[s.length()];

        int center=0,right=0;

        for(int i=0;i<s.length();i++){
            if(i<right){
                int mirrorInd=center-(i-center);
                lps[i]=Math.min(lps[mirrorInd],right-i);
            }

            while(lps[i]+i+1 <s.length() && i-lps[i]-1>=0 &&s.charAt(lps[i]+i+1)==s.charAt(i-lps[i]-1)){
                lps[i]++;
            }

            if(i+lps[i]>right){
                right=lps[i]+i;
                center=i;
            }
        }
    }
}
