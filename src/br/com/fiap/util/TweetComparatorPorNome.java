package br.com.fiap.util;

import java.util.Comparator;

import br.com.fiap.entity.Tweet;

/**
 * Classe que implementa a ordena��o dos tweets por nome de usu�rio
 * @author aline
 *
 */
public class TweetComparatorPorNome  implements Comparator<Tweet>{
	
	/**
	 * Metodo responsavel pela ordenacao dos tweets por nome
	 * @param t1 - Tweet a ser comparado com o Tweet t2
	 * @param t2 - Tweet a ser comparado com o Tweet t1
	 * @return valor que representa se nome de usuario do tweet t1 � maior, menor ou igual ao nome de usuario do tweet t2
	 */
	@Override
	public int compare(Tweet t1, Tweet t2) {
		return t1.getNomeUsuario().compareTo(t2.getNomeUsuario());
	}
}