package text.analyser;

import java.util.Scanner;

/**
 * The program analyses user's inputed text. It counts all characters,
 * characters without spaces, words, vowel and consonant letters and digits.
 */
public class TextAnalyser_main {

	/**
	 * The program entry point. Coordinates the execution of all text analysis
	 * methods.
	 */
	public static void main(String[] args) {
		boolean restart;
		Scanner scanner = new Scanner(System.in);
		
		do {
			String text = userInput(scanner);
			int countCharacter = charactersCounter(text);
			int countCharactersWithoutSpaces = charactersWithoutSpacesCounter(text);
			int countWords = wordsCounter(text);
			int vowelsCount = vowelsCounter(text);
			int consonantsCount = consonantsCounter(text);
			int digitsCount = digitsCounter(text);
			String longestWord = getLongestWord(text);
			char mostFrequentLetter = getMostFrequentLetter(text);
			double averageWord = getAverageWord(text, countWords);
			output(countCharacter, countCharactersWithoutSpaces, countWords, vowelsCount, consonantsCount, digitsCount,
					longestWord, mostFrequentLetter, averageWord);
			restart = askToRestart(scanner);
		} while (restart);
		
		scanner.close();
	}

	/**
	 * Prompts the user to enter a non-empty text. Keep asking until valid input is
	 * provided.
	 * 
	 * @param scanner - the Scanner used to read the user input
	 * @return the inputed text
	 */
	public static String userInput(Scanner scanner) {
		System.out.println("----------------------------------------------");
		System.out.println("                Text Analyser                 ");
		System.out.println("----------------------------------------------");
		while (true) {
			System.out.println("\nEnter a text: ");
			String text = scanner.nextLine();
			if (text.trim().isEmpty()) {
				System.out.println("You entered no symbol! Write again!");
				continue;
			} else {
				return text;
			}
		}
	}

	/**
	 * Counts the total number of characters the text
	 * 
	 * @param text - user's inputed text
	 * @return total number of characters
	 */
	public static int charactersCounter(String text) {
		return text.length();
	}

	/**
	 * Counts number of non-whitespace characters in the text
	 * 
	 * @param text - user's inputed text
	 * @return number of characters excluding whitespace
	 */
	public static int charactersWithoutSpacesCounter(String text) {
		int countCharactersWithoutSpaces = 0;
		for (int i = 0; i < text.length(); i++) {
			char currentCharacter = text.charAt(i);
			if (!Character.isWhitespace(currentCharacter)) {
				countCharactersWithoutSpaces++;
			}
		}
		return countCharactersWithoutSpaces;
	}

	/**
	 * Counts the number of words in the text. A word is defined as a sequence of
	 * non-whiterspace characters.
	 * 
	 * @param text - the input text
	 * @return number of words
	 */
	public static int wordsCounter(String text) {
		int wordsCount = 0;
		for (int i = 0; i < text.length(); i++) {
			char currentCharacter = text.charAt(i);
			if (Character.isLetter(currentCharacter)) {
				if (i == 0) {
					wordsCount++;
				} else if (!Character.isLetter(text.charAt(i - 1)) && text.charAt(i - 1) != '\''
						&& text.charAt(i - 1) != '-') {
					wordsCount++;
				}
			}

		}
		return wordsCount;
	}

	/**
	 * Counts vowel letters in the text
	 * 
	 * @param text - the input text
	 * @return number of vowels
	 */
	public static int vowelsCounter(String text) {
		int vowelsCount = 0;
		for (int i = 0; i < text.length(); i++) {
			char currentCharacter = text.charAt(i);
			if (isVowel(currentCharacter)) {
				vowelsCount++;
			}
		}
		return vowelsCount;
	}

	/**
	 * Counts consonant letters in the text
	 * 
	 * @param text - user's inputed text
	 * @return - the count of consonants
	 */
	public static int consonantsCounter(String text) {
		int consonantsCount = 0;
		for (int i = 0; i < text.length(); i++) {
			char currentCharacter = text.charAt(i);
			if (isConsonant(currentCharacter)) {
				consonantsCount++;
			}
		}
		return consonantsCount;
	}

	/**
	 * The array of vowel letters
	 */
	private static final char[] VOWELS = { 'a', 'o', 'u', 'e', 'i' };

	/**
	 * Determines whether a current character is a vowel
	 * 
	 * @param currentCharacter - the character to check
	 * @return true if the character is a vowel, false otherwise
	 */
	public static boolean isVowel(char currentCharacter) {
		currentCharacter = Character.toLowerCase(currentCharacter);
		for (char vowel : VOWELS) {
			if (vowel == currentCharacter) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Determines whether current character is a consonant
	 * 
	 * @param currentCharacter - the character to check
	 * @return true if the character is consonant, false otherwise
	 */
	public static boolean isConsonant(char currentCharacter) {
		return Character.isLetter(currentCharacter) && !isVowel(currentCharacter);
	}

	/**
	 * Counts the digit characters in the text
	 * 
	 * @param text - user's inputed text
	 * @return count of digits
	 */
	public static int digitsCounter(String text) {
		int digitsCount = 0;
		for (int i = 0; i < text.length(); i++) {
			char currentCharacter = text.charAt(i);
			if (Character.isDigit(currentCharacter)) {
				digitsCount++;
			}
		}
		return digitsCount;
	}

	/**
	 * Finds the longest word in the given text. Words are considered as sequences
	 * of letters only; punctuation and whitespace separate words.
	 * 
	 * @param text the input text to search
	 * @return the longest word found in the text; if multiple words have the same
	 *         length, returns the first one
	 */
	public static String getLongestWord(String text) {
		String currentWord = "";
		String longestWord = "";

		for (int i = 0; i < text.length(); i++) {

			char currentCharacter = text.charAt(i);

			if (!Character.isWhitespace(currentCharacter) && Character.isLetter(currentCharacter)) {

				currentWord += currentCharacter;

			} else if (!currentWord.isEmpty()) {
				if (longestWord.length() < currentWord.length()) {
					longestWord = currentWord;
				}
				currentWord = "";
			}

			if (longestWord.length() < currentWord.length()) {
				longestWord = currentWord;
			}

		}

		return longestWord;
	}

	/**
	 * Determines the most frequent letter
	 * 
	 * @param text - user's inputed text
	 * @return the most frequent letter
	 */
	public static char getMostFrequentLetter(String text) {
		int[] counts = new int[26];

		for (int i = 0; i < text.length(); i++) {
			char currentCharacter = Character.toLowerCase(text.charAt(i));
			if (Character.isLetter(currentCharacter)) {
				counts[currentCharacter - 'a']++;
			}
		}

		// comparison
		int maxIndex = 0;
		int maxCount = 0;
		for (int i = 0; i < counts.length; i++) {
			if (counts[i] > maxCount) {
				maxCount = counts[i];
				maxIndex = i;
			}
		}
		if (maxCount == 0) {
			return '\0';
		}
		return (char) (maxIndex + 'a');
	}

	/**
	 * Determines the avarage length of all words in the given text
	 * 
	 * @param text       - user's inputed text
	 * @param countWords - count of words
	 * @return avarage length of all words
	 */
	public static double getAverageWord(String text, int countWords) {

		int lettersCount = 0;
		for (int i = 0; i < text.length(); i++) {
			char currentCharacter = text.charAt(i);
			if (Character.isLetter(currentCharacter)) {
				lettersCount++;
			}
		}
		if (countWords > 0) {
			return (double) (lettersCount / countWords);

		}
		return 0;
	}

	/**
	 * Ask the user to restart the program. Validate the input
	 * 
	 * @param scanner - the scanner used for user's input
	 * @return the true if the user choose restart, false otherwise
	 */
	public static boolean askToRestart(Scanner scanner) {

		int x;
		System.out.println("\nDo you want to restart? \n\n yes - 1  |  no - 0");
		while (true) {
			if (!scanner.hasNextInt()) {
				System.out.println("\nPlease choose one of the options given");
				scanner.next();
				continue;
			} else {
				x = scanner.nextInt();

				if (x == 0 || x == 1) {
					break;
				} else {
					System.out.println("\nPlease choose one of the options given");
					scanner.next();
					continue;
				}

			}
		}

		return (x == 1) ? true : false;
	}

	/**
	 * Puts out the analysis results to the console
	 * 
	 * @param countCharacter               - count of all characters
	 * @param countCharactersWithoutSpaces - count of characters without spaces
	 * @param countWords                   - count of words
	 * @param vowelsCount                  - count of vowel letters
	 * @param consonantsCount              - count of consonant letters
	 * @param digitsCount                  - count of digits
	 * @param longestWord                  - longest word
	 * @param mostFrequentLetter           - most frequent letter
	 * @param avarageWord                  - avarage word
	 */
	public static void output(int countCharacter, int countCharactersWithoutSpaces, int countWords, int vowelsCount,
			int consonantsCount, int digitsCount, String longestWord, char mostFrequentLetter, double avarageWord) {
		System.out.println("\nCharacters:  " + countCharacter);
		System.out.println("Characters without spaces:  " + countCharactersWithoutSpaces);
		System.out.println("Words:  " + countWords);
		System.out.println("Vowels:  " + vowelsCount);
		System.out.println("Consonants:  " + consonantsCount);
		System.out.println("Digits:  " + digitsCount);
		System.out.println("Longest word:  " + longestWord);
		System.out.println("Most frequent letter:  " + mostFrequentLetter);
		System.out.println("Avarage word:  " + avarageWord);
	}
}