import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // Montar requisição para a nossa API e salvar a resposta em String
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI adress = URI.create(url);
        HttpClient myClient = HttpClient.newHttpClient();
        HttpRequest myRequest = HttpRequest.newBuilder(adress).GET().build();
        HttpResponse<String> myResponse = myClient.send(myRequest, BodyHandlers.ofString());
        String myBody = myResponse.body();

        // Utilizar a classe JsonParser para transformar a string que retornou da requisição em uma lista
        JsonParser parser = new JsonParser();
        List<Map<String,String>> movies = parser.parse(myBody);
        
        // Exibir e manipular os dados na nossa aplicação
        for (Map<String,String> movie : movies) {
            System.out.println("Title: " + movie.get("title"));
            System.out.println("Year: " + movie.get("year"));
            System.out.println("Rating: " + movie.get("imDbRating"));
        }
    }
}
