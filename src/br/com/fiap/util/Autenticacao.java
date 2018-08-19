package br.com.fiap.util;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class Autenticacao {

	protected static Twitter twitter;
	private final String apiKey = "Jbtx6feRYBVuRF5CvUR80KBYm";
	private static final String apiSecret = "25um8ixOiBKJ9eCcxs6we26ocVe5cmFYuzUWJXmt7zayKfrJm7";
	private static final String token = "1014996375560474625-b2sGj0Oj9SNNrn541PcDkbD0EhkXqO";
	private static final String tokenSecret = "XO3t7x9kuUmbN1rNkYYDDWi7ADz4uFPV5yGLMRPHpPJdQ";

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
