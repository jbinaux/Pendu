package myPackage;

import java.util.ArrayList;
import java.util.Scanner;

public class LePendu {

	public static boolean checkWord(String word)
	{
		for(int i = 0; i < word.length(); i++)
		{
			if(word.charAt(i) < 'a' || word.charAt(i) > 'z')
			{
				return false;
			}
		}
		return true;
	}
	
	public static String enterWord()
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Entrez un mot");
		String word = scan.next();
		while(!checkWord(word))
		{
			System.out.println("Entrez un mot en minuscule, compose de lettres");
			word = scan.next();
		}
		for(int i = 0; i <= 1000; i++)
		{
			System.out.println("\n");
		}
		return word;
	}
	
	public static String createADeviner(String word)
	{
		char[] truc = new char[word.length()];
		for(int i = 0; i < word.length(); i++)
		{
			truc[i] = '_';
		}
		String aDeviner = new String(truc);
		return aDeviner;
	}
	
	public static char enterLetter()
	{
		Scanner scan = new Scanner(System.in);
		String quelqueChose = scan.next();
		char c;
		while(quelqueChose.length() > 1 || (quelqueChose.charAt(0) < 'a' && quelqueChose.charAt(0) < 'z'))
		{
			if(quelqueChose.length() > 1)
			{
				System.out.println("N'entrez qu'une lettre !");
			}
			else
			{
				System.out.println("Entrez une lettre minuscule !");
			}
			quelqueChose = scan.next();
		}
		c = quelqueChose.charAt(0);
		return c;
	}
	
	public static boolean isIncomplete(String aDeviner)
	{
		for(int i = 0; i < aDeviner.length(); i++)
		{
			if(aDeviner.charAt(i) == '_')
			{
				return true;
			}
		}
		return false;
	}
	
	public static boolean isGood(char letter, String word)
	{
		for(int i = 0; i < word.length(); i++)
		{
			if(word.charAt(i) == letter)
			{
				return true;
			}
		}
		return false;
	}

	public static String completeWord(char letter, String aDeviner, String word)
	{
		char[] aDevinerChar = aDeviner.toCharArray();
		for(int i = 0; i < word.length(); i++)
		{
			if(word.charAt(i) == letter)
			{
				aDevinerChar[i] = letter;
			}
		}
		aDeviner = String.valueOf(aDevinerChar);
		return aDeviner;
	}
	
	public static void displayUsedChar(ArrayList<Character> usedChar)
	{
		for(int i = 0; i < usedChar.size(); i++)
		{
			System.out.print("\u001b[31m" + usedChar.get(i) + "\u001b[0m ");
		}
		System.out.print("\n");
	}
	
	public static boolean isUsed(char letter, ArrayList<Character> usedChar)
	{
		for(int i = 0; i < usedChar.size(); i++)
		{
			if(usedChar.get(i) == letter)
			{
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		String word = enterWord();
		String aDeviner = createADeviner(word);
		int essais = 10;
		char letter;
		ArrayList<Character> usedChar= new ArrayList<Character>();
		
		System.out.println(aDeviner);
		while(essais > 0 && isIncomplete(aDeviner))
		{
			letter = enterLetter();
			if(!isUsed(letter, usedChar))
			{
				usedChar.add(letter);
				if(isGood(letter, word))
				{
					aDeviner = completeWord(letter, aDeviner, word);
					System.out.println(aDeviner);
					displayUsedChar(usedChar);
				}
				else
				{
					essais--;
					System.out.println("essais restant : " + essais);
					System.out.println(aDeviner);
					displayUsedChar(usedChar);
				}
			}
			else
			{
				System.out.println("Vous avez deja essaye cette lettre.");
				System.out.println(aDeviner);
				displayUsedChar(usedChar);
			}
		}
		if(essais == 0)
		{
			System.out.println("Game Over");
			System.out.println("Le mot etait : " + word);
		}
		else
		{
			System.out.println("Success !");
		}
	}

}
