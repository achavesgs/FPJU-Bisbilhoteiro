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
public class ResumoDiaBusiness {
	
	Twitter twitter;
	/**
	 * Metodo construtor com autenticacao automatica
	 */
	public ResumoDiaBusiness(Twitter twitter) {
		this.twitter = twitter;
	}

	/**
	 * Metodo para retornar o resumo das informacoes organizadas por dia
	 * @param tweets - Lista de tweets encontrados para a hashtag
	 * @return resumoDias - Lista de resumo de quantidade de tweets, retweets e favoritacoes por dia na ultima semana
	 */
	public List<ResumoDia> buscarResumoDia(List<Tweet> tweets) {
		List<ResumoDia> resumoDias = new ArrayList<ResumoDia>();
		String dataAnterior = null;
		String dataAtual = null;

		boolean umaVez = true;
		int contadorTW = 0;
		int contadorRT = 0;
		int contadorFV = 0;

		ResumoDia resumoDia = new ResumoDia();

		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

		for (Tweet tweet : tweets) {
			dataAtual = df.format(tweet.getData());

			if (umaVez) {
				// executa uma vez para criarmos o primeiro resumo
				resumoDia.setData(tweet.getData());
				umaVez = false;
				dataAnterior = df.format(tweet.getData());
			}

			// comparamos a data do tweet atual com a data do tweet anterior
			if (dataAtual.equals(dataAnterior)) {
				// se for igual iremos somando o que já temos até o momento com os valores do
				// tweet corrente
				contadorTW += 1;
				contadorRT += tweet.getQtdRetweets();
				contadorFV += tweet.getQtdFavoritos();

			} else {
				// adiciona na lista de resumo diário o que já conseguimos do dia anterior (ja
				// que o dia é diferente)
				resumoDias.add(resumoDia);
				// e criamos uma nova lista de resumo diário para o "novo" dia encontrado
				resumoDia = new ResumoDia();
				// reiniciamos os contadores com os valores do tweet corrente
				contadorTW = 1;
				contadorRT = tweet.getQtdRetweets();
				contadorFV = tweet.getQtdFavoritos();

				dataAnterior = dataAtual;

				// adicionamos os valores ao resumo diário
				resumoDia.setData(tweet.getData());
			}

			// adicionamos os valores atuais ao resumo diario
			resumoDia.setQtdTweet(contadorTW);
			resumoDia.setQtdRetweet(contadorRT);
			resumoDia.setQtdFavorito(contadorFV);
		}

		// adiciona o ultimo resumo à lista
		resumoDias.add(resumoDia);

		return resumoDias;
	}

	/**
	 * Metodo para efetuar o tweet com o relatorio
	 * @param destinatario - usuario para o qual deverá ser direcionado o tweet
	 * @param mensagem - mensagem a ser enviada no tweet
	 * @throws TwitterException Quando ocorre erro no envio do tweet
	 */
	public void enviarRelatorio(String destinatario, String mensagem) throws TwitterException {
		twitter.updateStatus(destinatario + " " + mensagem);
	}

	/**
	 * Metodo para retornar quantidade de tweets, retweets e favoritacoes da hashtag
	 * @param resumoDias - Lista com os tweets armazenados por dia
	 * @param hashtag - hashtag do resumo
	 * @return mensagem contendo o resumo de quantidade de tweets, retweets e favoritacoes por dia na ultima semana
	 */
	public String retornaMsgResumo(List<ResumoDia> resumoDias, String hashtag) {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String mensagem = "";
		for (ResumoDia rd : resumoDias) {
			mensagem += df.format(rd.getData()) + ": " + "TW:" + rd.getQtdTweet() + " RT:" + rd.getQtdRetweet() + " FV:"
					+ rd.getQtdFavorito() + "\n";
		}

		mensagem += "(TW) Tweets - (RT) Retweets - (FV) Favoritacoes da Hashtag "+hashtag;
		return mensagem;
	}

}
