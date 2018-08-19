package br.com.fiap.entity;

import java.util.Date;

/**
 * Classe responsável por armazenar as informações de cada tweet encontrado
 * @author aline
 *
 */
public class Tweet {
	
	/**
	 * Data do tweet
	 */
	private Date data;
	
	/**
	 * Quantidade de retweets do tweet
	 */
	private int qtdRetweets;
	
	/**
	 * Quantidade de favoritações do tweet
	 */
	private int qtdFavoritos;
	
	/**
	 * Nome do autor do tweet
	 */
	private String nomeAutor;
	
	/**
	 * Nome de usuario do autor do tweet
	 */
	private String nomeUsuario;
	
	/**
	 * Mensagem tweetada
	 */
	private String mensagem;
	
	/**
	 * Metodo para obter a data do Tweet
	 * @return
	 */
	public Date getData() {
		return data;
	}
	
	/**
	 * Metodo para armazenar a data do Tweet
	 * @param data
	 */
	public void setData(Date data) {
		this.data = data;
	}
	
	/**
	 * Metodo para obter a quantidade de retweets do Tweet
	 * @return
	 */
	public int getQtdRetweets() {
		return qtdRetweets;
	}
	
	/**
	 * Metodo para armazenar a quantidade de retweets do Tweet
	 * @param qtdRetweets
	 */
	public void setQtdRetweets(int qtdRetweets) {
		this.qtdRetweets = qtdRetweets;
	}
	
	/**
	 * Metodo para obter a quantidade de favoritacoes do Tweet
	 * @return
	 */
	public int getQtdFavoritos() {
		return qtdFavoritos;
	}
	
	/**
	 * Metodo para armazenar a quantidade de favoritacoes do Tweet
	 * @param qtdFavoritos
	 */
	public void setQtdFavoritos(int qtdFavoritos) {
		this.qtdFavoritos = qtdFavoritos;
	}
	
	/**
	 * Metodo para obter o nome do autor do Tweet
	 * @return
	 */
	public String getNomeAutor() {
		return nomeAutor;
	}
	
	/**
	 * Metodo para armazenar o nome do autor do Tweet
	 * @param nomeAutor
	 */
	public void setNomeAutor(String nomeAutor) {
		this.nomeAutor = nomeAutor;
	}
	
	/**
	 * Metodo para obter o nome do usuario do Tweet
	 * @return nomeUsuario
	 */
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	
	/**
	 * Metodo para armazenar o nome do usuario do Tweet
	 * @param nomeUsuario
	 */
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	
	/**
	 * Metodo para obter a mengsagem do Tweet
	 * @return mensagem
	 */
	public String getMensagem() {
		return mensagem;
	}

	/**
	 * Metodo para armazenar a mensagem do Tweet
	 * @param mensagem
	 */
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	@Override
	public String toString() {
		return "Tweet [data=" + data + ", favoritado=" + qtdFavoritos + ", usuario="
				+ nomeUsuario + ", nome=" + nomeAutor + ", mensagem=" + mensagem
				+ ", retweet=" + qtdRetweets + "]";
	}
}
