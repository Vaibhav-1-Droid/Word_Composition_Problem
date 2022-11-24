import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Wordcomposition {

	public static void main(String[] args) throws FileNotFoundException {
		
		File file = new File("Input_02.txt");

		Trie trie = new Trie();
		LinkedList<Pair<String>> queue = new LinkedList<Pair<String>>();
		
		HashSet<String> compoundWords = new HashSet<String>();
		@SuppressWarnings("resource")
		Scanner s = new Scanner(file);

		String word;				
		List<Integer> sufIndices;	
		while (s.hasNext()) {
			word = s.next();		
			sufIndices = trie.getSuffixesStartIndices(word);
		
			for (int i : sufIndices) {
				if (i >= word.length())		
					break;					
				queue.add(new Pair<String>(word, word.substring(i)));
			}
	
			trie.insert(word);
		}
		
		Pair<String> p;				
		int maxLength = 0;			
		String longest = "";		
		String sec_longest = "";	
		while (!queue.isEmpty()) {
			p = queue.removeFirst();
			word = p.second();
			
			sufIndices = trie.getSuffixesStartIndices(word);
			if (sufIndices.isEmpty()) {
				continue;
			}
			for (int i : sufIndices) {
				if (i > word.length()) {  
					break;
				}
				
				if (i == word.length()) { 
					if (p.first().length() > maxLength) {
						sec_longest = longest;
						maxLength = p.first().length();
						longest = p.first();
					}
			
					compoundWords.add(p.first());	
					
				} else {
					queue.add(new Pair<String>(p.first(), word.substring(i)));
				}
			}
		}
		System.out.println("Longest Compounded Word: " + longest);
		System.out.println("Second Longest Compounded Word: " + sec_longest);
		System.out.println("Time taken to process the input file: " + compoundWords.size());
	}
}

