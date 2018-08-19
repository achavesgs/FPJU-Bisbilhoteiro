package br.com.fiap.entity;

import java.util.Date;

/**
 * Classe responsável por armazenar as informações de tweets, organizadas por data e contendo o resumo das quantidades de tweets, retweets e favoritações do dia
 * @author aline
 *
 */
public class ResumoDia {
	
	/**
	 * Data do resumo
	 */
	private Date data;
	
	/**
	 * Quantidade de Tweets do dia
	 */
	private int qtdTweet;
	
	/**
	 * Quantidade de Retweets do dia
	 */
	private int qtdRetweet;
	
	/**
	 * Quantidade de favoritações do dia
	 */
	private int qtdFavorito;

	public ResumoDia() {
		this.qtdRetweet = 0;
		this.qtdTweet = 0;
		this.qtdFavorito = 0;
	}

	/**
	 * Metodo para retornar a quantidade de tweets do dia
	 * @return qtdTweet
	 */
	public int getQtdTweet() {
		return qtdTweet;
	}

	/**
	 * Metodo para retornar a quantidade de retweets do dia
	 * @return qtdRetweet
	 */
	public int getQtdRetweet() {
		return qtdRetweet;
	}

	/**
	 * Metodo para retornar a quantidade de favoritacoes do dia
	 * @return qtdFavorito
	 */
	public int getQtdFavorito() {
		return qtdFavorito;
	}


	/**
	 * Adiciona a quantidade de tweets do dia ao resumo
	 * @param qtdTweet
	 */
	public void setQtdTweet(int qtdTweet) {
		this.qtdTweet = qtdTweet;
	}
	
	/**
	 * Adiciona a quantidade de retweets do dia ao resumo
	 * @param qtdRetweet
	 */
	public void setQtdRetweet(int qtdRetweet) {
		this.qtdRetweet = qtdRetweet;
	}

	/**
	 * Adiciona a quantidade de favoritacoes do dia ao resumo
	 * @param qtdFavorito
	 */
	public void setQtdFavorito(int qtdFavorito) {
		this.qtdFavorito = qtdFavorito;
	}

	/**
	 * Metodo para retornar a data do resumo
	 * @return data
	 */
	public Date getData() {
		return data;
	}

	/**
	 * Adiciona a data do resumo
	 * @param data
	 */
	public void setData(Date data) {
		this.data = data;
	}
	
}
