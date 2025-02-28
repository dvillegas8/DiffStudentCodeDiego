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
        int[][] table = new int[doc1.length()][doc2.length()];
        int longest = returnLongest(doc1, doc2, 0, table, 0);

        return longest;
    }
    public static int returnLongest(String doc1, String doc2, int counter, int[][] table, int indexOfFirstChar){
        // Base cases
        int index = doc2.indexOf(doc1.charAt(0));
        if(index != -1){
            if(table[indexOfFirstChar][index] != 0){
                return table[indexOfFirstChar][index];
            }
        }
        if(doc1.length() == 1){
            // If the last char is present in doc 2
            if(doc2.indexOf(doc1.charAt(0)) != -1){
                return counter + 1;
            }
            else{
                return counter;
            }
        }
        if(doc2.length() == 1) {
            // If the last char is present in doc 1
            if (doc1.indexOf(doc2.charAt(0)) != -1) {
                return counter + 1;
            } else {
                return counter;
            }
        }
        if(doc1.isEmpty() || doc2.isEmpty()){
            return counter;
        }
        int caseOne = 0;
        int caseTwo = 0;
        int caseThree = 0;
        int caseFour = 0;
        // recursive steps
        // Include
        if(index != -1){
            caseOne = returnLongest(doc1.substring(1), doc2.substring(index + 1), counter + 1, table,indexOfFirstChar + 1);
        }
        // Exclude
        caseTwo = returnLongest(doc1.substring(1), doc2, counter, table, indexOfFirstChar + 1);
        /*
        // Get the first instance of the first char in doc 2 in doc 1
        int indexTwo = doc1.indexOf(doc2.charAt(0));
        if(index != -1){
            caseThree = returnLongest(doc1.substring(indexTwo + 1), doc2.substring(1), counter + 1);
        }

         */
        // caseFour = returnLongest(doc1, doc2.substring(1), counter);
        int bigCaseOne = Math.max(caseOne, caseTwo);
        int bigCaseTwo = Math.max(caseThree, caseFour);
        int longest = Math.max(bigCaseOne, bigCaseTwo);
        if(index != -1){
            table[indexOfFirstChar][index] = longest;
        }
        // Returns the biggest number
        return longest;
    }
}
