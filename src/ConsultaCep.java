import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

    public class ConsultaCep {

        private final String URL_BASE = "https://viacep.com.br/ws/";

        public Endereco buscar(String cep) throws Exception {

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL_BASE + cep + "/json/"))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new IllegalArgumentException("Serviço indisponível. Tente novamente mais tarde.");
            }

            if (response.body().contains("\"erro\"")) {
                throw new IllegalArgumentException("CEP não encontrado.");
            }

            Gson gson = new Gson();
            return gson.fromJson(response.body(), Endereco.class);
        }
    }
