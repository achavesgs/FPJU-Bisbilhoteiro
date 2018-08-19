package br.com.fiap.app;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.business.ResumoDiaBusiness;
import br.com.fiap.business.TweetBusiness;
import br.com.fiap.entity.ResumoDia;
import br.com.fiap.entity.Tweet;
import br.com.fiap.util.Autenticacao;
import twitter4j.Twitter;

public class BisbilhoteiroApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Autenticacao aut = new Autenticacao();
		Twitter twitter = aut.retornaTwitter();
		ResumoDiaBusiness resumoBusiness = new ResumoDiaBusiness(twitter);
		TweetBusiness tweetBusiness = new TweetBusiness(twitter);
		List<Tweet> tweets = new ArrayList<Tweet>();
		List<ResumoDia> resumoDias = new ArrayList<ResumoDia>();
		String mensagem;
		String hashtag = "#java";
		String destinatario = "@wkopti";//"@michelpf";

		try {
			tweets = tweetBusiness.buscarTweets(twitter, hashtag);
			
			//apresenta no console todos os tweets encontrados
			System.out.println("=============================================================================================");
			System.out.println("Listando todos os tweets encontrados para a Hashtagh "+hashtag);
			System.out.println("\n==============================================================================================");

			for (Tweet tweet : tweets) {
				System.out.println(tweet.toString());
			}
			
			resumoDias = resumoBusiness.buscarResumoDia(tweets);

			// Envia mensagem com resumo de tweets, retweets e favoritacoes
			mensagem = resumoBusiness.retornaMsgResumo(resumoDias, hashtag);
			resumoBusiness.enviarRelatorio(destinatario, mensagem);
			
			// Envia mensagem com a data mais recente e a data mais antiga, ordenando por data
			mensagem = tweetBusiness.retornaMsgTweetPorData(tweets, hashtag);
			resumoBusiness.enviarRelatorio(destinatario, mensagem);
			
			// Envia mensagem com o primeiro e o ultimo item da lista ordenada por nome
			mensagem = tweetBusiness.retornaMsgTweetPorNome(tweets, hashtag);
			resumoBusiness.enviarRelatorio(destinatario, mensagem);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
