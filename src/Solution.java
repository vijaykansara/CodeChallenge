/******************************************************************************************************************************
 PROBLEM STATEMENT:

 Write a method that modifies a string using the following rules:

 1. Each word in the input string is replaced with the following: the first letter of the word, the count of distinct letters
 between the first and last letter, and the last letter of the word. For example, "Automotive" would be replaced by "A6e".

 2. A "word" is defined as a sequence of alphabetic characters, delimited by any non-alphabetic characters.

 3. Any non-alphabetic character in the input string should appear in the output string in its original relative location.

 ******************************************************************************************************************************/
import java.util.HashSet;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args){

        //Instantiating an object to access the class method
        Solution s = new Solution();

        //Taking user input for the String
        // We can add validation steps here as per requirement.
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your string for manipulation: ");
        String str = scan.nextLine();

        // print the modified String with expected requirements.
        System.out.println(s.modifyStr(str));
    }

    // Counting unique characters from a String
    public int countDistinctChar(String str){

        // Creating HashSet of character
        HashSet<Character> hash = new HashSet<Character>();
        //str = str.toLowerCase();

        //iterating through String, extracting all distinct characters and adding them in HashSet.
        //return the size of HashSet, hence count is obtained.
        for(int i = 0; i < str.length(); i++){
            hash.add(str.charAt(i));
        }
        return hash.size();
    }

    // Returns the distinct character count of a given String except first and last characters.
    public int countUniqueInternalChar(String str){

        // stored length of the string in a variable strLength
        int strLength = str.length();

        // Checking if length of the string is of two or less characters, if so return the string as is. No need of operation.
        if(strLength <= 2){
            return 0;
        }
        //return distinct internal characters
        return countDistinctChar(str.substring(1, strLength - 1));
    }

    //modifies the String/Word by replacing it with first character + distinct count of internal characters + last character
    public String modifyWord(String str){

        // Checking if length of the string is of two or less characters, if so return the string as is. No need of operation.
        if(str.length() <= 2){
            return str;
        }

        // operation stored in modifiedWord
        StringBuilder modifiedWord = new StringBuilder();

        //counting distinct characters in between first and last characters
        int count = countUniqueInternalChar(str);

        //append first character
        modifiedWord.append(str.substring(0,1));
        //then append count after first character
        modifiedWord.append(count);
        // then append last character after first and count
        modifiedWord.append(str.substring(str.length() - 1));

        return modifiedWord.toString();
    }

    // Method modifies alphabetic characters in string using modifyWord() method
    // also it keeps the non alphabetic characters to their original relative location
    public String modifyStr(String str){

        // convert string to character array
        char[] chars = str.toCharArray();

        // for holding the word, modified string
        StringBuilder word = new StringBuilder();
        StringBuilder modifiedStr = new StringBuilder();

        // Iterate through char array from start to end
        // If alphabetic characters found, append character to word,
        // If non alphabetic characters found, along with word before it then append it to modifiedStr
        for(char ch : chars){
            if(Character.isLetter(ch)){
                word.append(ch);
            }else if(word.length() > 0){
                modifiedStr.append(modifyWord(word.toString()));
                word.delete(0, word.length());
                modifiedStr.append(ch);
            }else{
                modifiedStr.append(ch);
            }

        }
        // if there is still a word after parsing the String, modify it.
        if(word.length() > 0){
            modifiedStr.append(modifyWord(word.toString()));
        }

        // convert it back to String and return it.
        return modifiedStr.toString();
    }
}