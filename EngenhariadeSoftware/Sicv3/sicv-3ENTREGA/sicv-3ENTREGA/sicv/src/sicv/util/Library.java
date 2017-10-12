package sicv.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Library {

	public static String getFile(String file) throws FileNotFoundException {
		return readFile(file);
	}

	public static String getFileReplace(String keyWord, String newWord, String file)
			throws FileNotFoundException {
		String text;

		text = readFile(file);
		return getWordReplace(keyWord, newWord, text);
	}

	public static String getFileListReplace(String[] keywords, String[] newWords, String file)
			throws FileNotFoundException {
		String text;

		text = readFile(file);

		return getWordListReplace(keywords, newWords, text);
	}

	private static String readFile(String file) throws FileNotFoundException {
		String context;
		String aux = null;
		FileReader fr = null;
		BufferedReader bf = null;

		fr = new FileReader(file);
		bf = new BufferedReader(fr);
		context = new String();

		try {
			aux = bf.readLine();

			while (aux != null) {
				aux += "\n";
				context += aux;
				aux = bf.readLine();
			}

			fr.close();
			bf.close();
		} catch (IOException i) {
			i.printStackTrace();
		}

		return context;
	}

	private static String getWordListReplace(String[] keywords, String[] newWords, String text) {
		int small = 0;
		String text2 = new String(text);

		small = keywords.length;

		if (small > newWords.length) {
			small = newWords.length;
		}

		for (int i = 0; i < small; i++) {
			text2 = getWordReplace(keywords[i], newWords[i], text2);
		}

		return text2;
	}

	private static String getWordReplace(String keyword, String newWord, String texto) {
		String newText;
		int index;
		String texto2 = new String(texto);

		newText = new String();
		index = texto2.indexOf(keyword);

		while (index != -1) {
			newText += texto2.substring(0, index) + newWord;
			texto2 = texto2.substring(index + keyword.length());
			index = texto2.indexOf(keyword);
		}

		return newText + texto2;
	}

	/**
	 * Recebe um string e substitui os carriage return e/ou
	 * line feed por um \\n, o qual sera impresso no codigo
	 * javascript como valor para um input (upload de valores)
	 */
	public static String adjustString(String aux) {
		StringTokenizer stoken;

		stoken = new StringTokenizer(aux, (char) 10 + "" + (char) 13);
		aux = "";

		while (stoken.hasMoreTokens()) {
			aux = aux + stoken.nextToken() + "\\n";
		}

		if (aux.length() < 3) {
			return aux;
		}

		return aux.substring(0, (aux.length() - 2));
	}

}
