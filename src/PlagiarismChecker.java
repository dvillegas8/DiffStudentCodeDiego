/**
 * Plagiarism Checker
 * A tool for finding the longest shared substring between two documents.
 *
 * @author Zach Blick
 * @author YOUR NAME HERE
 */
public class PlagiarismChecker {

    /**
     * This method finds the longest sequence of characters that appear in both texts in the same order,
     * although not necessarily contiguously.
     * @param doc1 the first document
     * @param doc2 the second
     * @return The length of the longest shared substring.
     */
    public static int longestSharedSubstring(String doc1, String doc2) {

        // TODO Complete this function to return the length of the longest shared substring.
        int[][] table = new int[doc1.length() + 1][doc2.length() + 1];
        int longest = largestCommonSubsequenceTabulation(doc1, doc2, table);

        return longest;
    }
    public static int largestCommonSubsequenceMemoization(String doc1, String doc2, int[][]table, int i, int j){
        // Base cases
        if(table[i][j] != 0){
            return table[i][j];
        }
        if(i == 0){
            return 0;
        }
        if(j == 0){
            return 0;
        }
        // Recursive step: If letters match
        if(doc1.charAt(i - 1) == doc2.charAt(j - 1)){
            table[i][j] = largestCommonSubsequenceMemoization(doc1, doc2, table, i - 1, j - 1) + 1;
        }
        // Recursive step: Check top or left
        else{
            table[i][j] = Math.max(largestCommonSubsequenceMemoization(doc1, doc2, table, i - 1, j), largestCommonSubsequenceMemoization(doc1, doc2, table, i, j - 1));
        }
        return table[i][j];
    }
    public static int largestCommonSubsequenceTabulation(String doc1, String doc2, int[][]table) {
        for(int i = 1; i < table.length; i++){
            for(int j = 1; j < table[0].length; j++){
                // Check if letters match
                if(doc1.charAt(i - 1) == doc2.charAt(j - 1)){
                    table[i][j] = table[i - 1][j-1] + 1;
                }
                else{
                    // Check left and top, grab the bigger answer
                    table[i][j] = Math.max(table[i][j - 1], table[i - 1][j]);
                }
            }
        }
        return table[doc1.length()][doc2.length()];
    }
}
