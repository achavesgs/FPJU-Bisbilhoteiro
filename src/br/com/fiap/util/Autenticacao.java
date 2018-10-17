package br.com.fiap.util;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class Autenticacao {

	protected static Twitter twitter;
	private final String apiKey = "";
	private static final String apiSecret = "";
	private static final String token = "";
	private static final String tokenSecret = "";

	/**
	 * Metodo construtor com autenticacao automatica
	 */
	public Autenticacao() {
		this.autenticar();
	}

	/**
	 * Metodo para autenticacao
	 */
	private void autenticar() {
		AccessToken accessToken = loadAccessToken();
		TwitterFactory factory = new TwitterFactory();
		twitter = factory.getSingleton();
		twitter.setOAuthConsumer(apiKey, apiSecret);
		twitter.setOAuthAccessToken(accessToken);
	}

	/**
	 * Metodo para realizar o acesso ao Twitter
	 * @return AccessToken - token para acesso
	 */
	private static AccessToken loadAccessToken() {
		return new AccessToken(token, tokenSecret);
	}
	
	public Twitter retornaTwitter() {
		return this.twitter;
	}


}
