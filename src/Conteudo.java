import java.net.MalformedURLException;
import java.net.URL;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class Conteudo {

	@JsonAlias("title")
	private final String titulo;

	@JsonAlias("poster_path")
	private final String url;

	@JsonIgnore
	private ConfiguracaoImagem configuracao;

	private Conteudo() {
		this( null, null );
	}

	public Conteudo(String titulo, String url) {
		this.titulo = titulo;
		this.url = url;
	}

	public String getTitulo() {
		return titulo;
	}

	public URL getUrlImagem() throws ConteudoException {
		try {
			String caminho = configuracao == null
								? url
								: configuracao.criarCaminho( url );

			return new URL( caminho );

		} catch (MalformedURLException ex) {
			throw new ConteudoException( "erro ao criar url", ex );
		}
	}

	public void setConfiguracaoImagem(ConfiguracaoImagem configuracao) {
		this.configuracao = configuracao;
	}
}
