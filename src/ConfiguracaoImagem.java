import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class ConfiguracaoImagem {

	private static final String TAMANHO_IMAGEM = "w500";

	@JsonAlias("secure_base_url")
	private String secureBaseUrl;

	public String criarCaminho(String caminho) {
		return secureBaseUrl + TAMANHO_IMAGEM + caminho;
	}
}
