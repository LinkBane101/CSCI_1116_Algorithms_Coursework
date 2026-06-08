import java.util.*;

import javafx.beans.value.WeakChangeListener;

public class CountOccurrenceOfWords {
	public static void main(String[] args) {
		// Set text in a string
		String text = "Good morning. Have a good class. " +
			"Have a good visit. Have fun!";

		// Create a TreeMap to hold words as key and count as value
		Map<String, Integer> map = new HashMap<>();

		String[] words = text.split("[\\s+\\p{P}]");
		for (int i = 0; i < words.length; i++) {
			String key = words[i].toLowerCase();
			
			if (key.length() > 0) {
				if (!map.containsKey(key)) {
					map.put(key, 1);
				}
				else {
					int value = map.get(key);
					value++;
					map.put(key, value);
				}
			}
		}
		// Create ArrayList to store the objects of WordOccurrence
		ArrayList<WordOccurrence> list = new ArrayList<>();

		// Make each map entry into a WordOccurrence object
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			list.add(new WordOccurrence(entry.getKey(), entry.getValue()));
		}
		// Sorting ArrayList by Occurence
		Collections.sort(list);
		
		//Display words in ascending order of occurence counts
		for (WordOccurrence wordOccurrence : list) {
			System.out.println(wordOccurrence.word + "\t" + wordOccurrence);
		}
	}

	static class WordOccurrence implements Comparable<WordOccurrence> {
		String word;
		int count;

		public WordOccurrence(String word, int count) {
			this.word = word;
			this.count = count;
		}

		@Override
		public int compareTo(WordOccurrence other) {
			if(this.count > other.count) {
				return 1;
			}
		else if (this.count < other.count) {
			return -1;
		}
		else {
			return this.word.compareTo(other.word);
			}
		}
	}
}