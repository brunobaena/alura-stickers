import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ExtratorConteudoTmdb implements Extrator<List<Conteudo>> {

	private static final String API_KEY = System.getenv( "API_KEY" );

	private final ConfiguracaoImagem configuracaoImagem;

	public ExtratorConteudoTmdb() throws ExtratorException {
		this.configuracaoImagem = this.getConfiguracao();
	}

	@Override
	public List<Conteudo> extrair(String json) throws ExtratorException {
		try {
			ObjectMapper mapper = new ObjectMapper();
			String jsonFilmes = mapper.readTree( json ).get( "results" ).toString();

			List<Conteudo> filmes = mapper.readValue( jsonFilmes, new TypeReference<List<Conteudo>>() {
			} );
			filmes.forEach( filme -> filme.setConfiguracaoImagem( configuracaoImagem ) );

			return filmes;
		} catch ( JsonProcessingException ex ) {
			throw new ExtratorException( "erro ao converter conteudo", ex );
		}
	}

	private ConfiguracaoImagem getConfiguracao() throws ExtratorException  {
		try {
			ClienteHttp cliente = new ClienteHttp();
			String bodyConfiguracoes = cliente.executarRequisicao( "https://api.themoviedb.org/3/configuration?api_key=" + API_KEY );

			return new ExtratorConfiguracaoTmdb().extrair( bodyConfiguracoes );

		} catch (ClienteHttpException ex) {
			throw new ExtratorException( "Erro ao buscar configuração TMDB", ex );
		}
	}
}
