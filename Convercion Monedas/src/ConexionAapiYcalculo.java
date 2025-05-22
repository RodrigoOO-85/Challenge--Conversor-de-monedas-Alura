import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConexionAapiYcalculo {

    private final String moneda1;
    private final String moneda2;
    private final double cantidad;

    public ConexionAapiYcalculo(String moneda1, String moneda2 , Double cantidad){
     this.moneda1 = moneda1;
     this.moneda2 = moneda2;
     this.cantidad = cantidad;

    }

    public DatosExchangeApi conexion(){
        String url = "https://v6.exchangerate-api.com/v6/c033248198011700dc5719ac/pair/" +
                moneda1 + "/" + moneda2 + "/" + cantidad;
        URI enlace = URI.create(url);
        HttpClient client =  HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(enlace).build();
        try {
            HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(),DatosExchangeApi.class);
        }catch (IOException | InterruptedException e){
            System.out.println("Error al conectar con la Api de Exchange Rate" +e.getMessage());
            return null;
        }
    }

}
