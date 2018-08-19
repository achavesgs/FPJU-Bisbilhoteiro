package br.com.fiap.util;

import java.util.Comparator;

import br.com.fiap.entity.Tweet;
import twitter4j.TwitterException;

/**
 * Classe que implementa a ordenação dos tweets por data
 * @author aline
 *
 */
public class TweetComparatorPorData  implements Comparator<Tweet>{
	
	/**
	 * Metodo responsavel pela ordenacao dos tweets por data
	 * @param t1 - Tweet a ser comparado com o Tweet t2
	 * @param t2 - Tweet a ser comparado com o Tweet t1
	 * @return valor que representa se a data do tweet t1 é maior, menor ou igual à data do tweet t2
	 */
	@Override
	public int compare(Tweet t1, Tweet t2) {
		return t1.getData().compareTo(t2.getData());
	}
}