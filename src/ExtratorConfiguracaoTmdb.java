import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ExtratorConfiguracaoTmdb implements Extrator<ConfiguracaoImagem> {

	@Override
	public ConfiguracaoImagem extrair(String json) throws ExtratorException {
		try {
			ObjectMapper mapper = new ObjectMapper();
			String jsonImages = mapper.readTree( json ).get( "images" ).toString();

			return mapper.readValue( jsonImages, ConfiguracaoImagem.class );

		} catch ( JsonProcessingException ex ) {
			throw new ExtratorException( "erro processar configuração", ex );
		}
	}
}
