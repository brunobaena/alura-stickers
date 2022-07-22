import java.util.List;

public class App {

	private static final String API_KEY = System.getenv( "API_KEY" );

	public static void main(String[] args) {
		try {
			ClienteHttp cliente = new ClienteHttp();
			String bodyFilmes = cliente.executarRequisicao( "https://api.themoviedb.org/3/trending/movie/week?language=pt-BR&api_key=" + API_KEY );

			Extrator<List<Conteudo>> extrator = new ExtratorConteudoTmdb();
			List<Conteudo> listaConteudo = extrator.extrair( bodyFilmes );

			var gerador = new GeradorSticker();

			for ( Conteudo conteudo : listaConteudo ) {
				String nomeArquivo = conteudo.getTitulo().replace( ' ', '_' ).replaceAll( "\\W", "" );

				gerador.criar( conteudo.getUrlImagem().openStream(), "saida/" + nomeArquivo + ".png" );
			}

		} catch ( Exception ex ) {
			throw new RuntimeException( ex );
		}
	}
}
