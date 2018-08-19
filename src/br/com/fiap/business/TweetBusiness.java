package br.com.fiap.business;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.fiap.entity.ResumoDia;
import br.com.fiap.entity.Tweet;
import br.com.fiap.util.Autenticacao;
import br.com.fiap.util.TweetComparatorPorData;
import br.com.fiap.util.TweetComparatorPorNome;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

/**
 * Classe responsável deter todos os métodos responsáveis por gerenciar as regras de negócio da aplicação
 * @author aline
 */
public class TweetBusiness {
	Twitter twitter;
	/**
	 * Metodo construtor
	 */
	public TweetBusiness(Twitter twitter) {
		this.twitter = twitter;
	}

	/**
	 * Metodo responsavel pela busca dos tweets
	 * @param hashtag - hashtag a ser encontradas nos tweets
	 * @return lista de tweets encontrados contendo a hashtag informada
	 * @throws TwitterException Quando ocorre erro na busca dos tweets para a hashtag informada
	 */
	public List<Tweet> buscarTweets(Twitter twitter, String hashtag) throws TwitterException {

		LocalDate agora = LocalDate.now().minusDays(1);
		LocalDate dtDataInicio = agora.minusDays(6);
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		String dataFim = agora.format(formatador);
		String dataInicio = dtDataInicio.format(formatador);

		System.out.println("Data inicio: "+dataInicio+" - Data fim: " + dataFim);

		Query query = new Query(hashtag);
		query.setLang("pt");
		query.setSince(dataInicio);
		query.setUntil(dataFim);
		QueryResult result;
		List<Tweet> tweets = new ArrayList<Tweet>();

		result = twitter.search(query);

		while (result.hasNext()) {
			query = result.nextQuery();

			for (Status status : result.getTweets()) {
				Tweet tweet = new Tweet();
				tweet.setData(status.getCreatedAt());
				tweet.setNomeAutor(status.getUser().getName());
				tweet.setNomeUsuario(status.getUser().getScreenName());
				tweet.setQtdFavoritos(status.getFavoriteCount());
				tweet.setQtdRetweets(status.getRetweetCount());
				tweet.setMensagem(status.getText());
				tweets.add(tweet);

			}

			result = twitter.search(query);

		}

		// acredito que nao precisamos retornar nada, ja que criamos o relatorio
		return tweets;
	}

	/**
	 * Metodo para retornar data mais recente e data mais antiga
	 * @param tweets - Lista de tweets
	 * @param hashtag - hashtag utilizada
	 * @return mensagem contendo a data mais recente e a data mais antiga, após ordenação por data
	 */
	public String retornaMsgTweetPorData(List<Tweet> tweets, String hashtag) {
		String mensagem;
		tweets = ordenarTweetPorData(tweets);
		
		System.out.println("\n==============================================================================================");
		System.out.println("Listando todos os tweets ordenados por data");
		System.out.println("\n==============================================================================================");

		for (Tweet tweet : tweets) {
			System.out.println(tweet.toString());
		}
		mensagem = "Data mais recente: " + tweets.get(0).getData() + "\n" + "Data mais antiga: "
				+ tweets.get(tweets.size() - 1).getData()+" da Hashtag "+hashtag;
		return mensagem;
	}

	/**
	 * Metodo para retornar primeiro e ultimo nomes
	 * @param tweets - Lista de tweets
	 * @param hashtag - hashtag utilizada
	 * @return mensagem contendo primeiro e ultimo nomes, após ordenação por nome do usuario
	 */
	public String retornaMsgTweetPorNome(List<Tweet> tweets, String hashtag) {
		String mensagem;
		tweets = ordenarTweetPorNome(tweets);
		System.out.println("\n==============================================================================================");
		System.out.println("Listando todos os tweets ordenados por nome");
		System.out.println("\n==============================================================================================");
		for (Tweet tweet : tweets) {
			System.out.println(tweet.toString());
		}
		mensagem = "Primeiro nome: " + tweets.get(0).getNomeUsuario() + "\n" + "Ultimo nome: "
				+ tweets.get(tweets.size() - 1).getNomeUsuario()+" da Hashtag "+hashtag;
		return mensagem;
	}

	/**
	 * Metodo de ordenacao de tweets por data
	 * @param tweets - Lista de tweets a ser ordenada por data
	 * @return lista de tweets ordenada por data
	 */
	private List<Tweet> ordenarTweetPorData(List<Tweet> tweets) {
		Collections.sort(tweets, new TweetComparatorPorData());
		return tweets;
	}

	/**
	 * Metodo de ordenacao de tweets por nome
	 * @param tweets - Lista de tweets a ser ordenada por nome
	 * @return lista de tweets ordenada por nome
	 */
	private List<Tweet> ordenarTweetPorNome(List<Tweet> tweets) {
		Collections.sort(tweets, new TweetComparatorPorNome());
		return tweets;
	}

}
