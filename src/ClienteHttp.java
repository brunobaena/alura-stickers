import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;

public class ClienteHttp {

	public String executarRequisicao(String url) throws ClienteHttpException {
		try {
			URI endereco = URI.create( url );

			var client = HttpClient.newHttpClient();
			var request = HttpRequest.newBuilder( endereco ).build();

			return client.send( request, BodyHandlers.ofString() ).body();

		} catch ( IOException | InterruptedException ex ) {
			throw new ClienteHttpException( "erro ao executar requisicao: " + url, ex );
		}
	}
}
