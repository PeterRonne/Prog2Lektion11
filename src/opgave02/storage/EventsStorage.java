package opgave02.storage;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import opgave02.models.Event;
import opgave02.models.Tag;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventsStorage implements Storage {
    @Override
    public List<Tag> getTags() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://api.detskeriaarhus.dk/api/tags"))
                    .header("accept", "application/json")
                    .GET()
                    .build();
            HttpResponse<String> response = HttpClient.newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());
            String res = response.body();
            Gson gson = new Gson();
            ArrayList<Tag> retur = gson.fromJson(res, ArrayList.class);
            System.out.println(retur);
        } catch (URISyntaxException exception) {
            System.out.println(exception.getMessage());
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        } catch (InterruptedException exception) {
            System.out.println(exception.getMessage());
        }
        return new ArrayList<Tag>();
    }

    @Override
    public List<Event> getEvents(String tag) {
        List<Event> events = null;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://api.detskeriaarhus.dk/api/events?tags=" + tag))
                    .header("accept", "application/json")
                    .GET()
                    .build();
            HttpResponse<String> response = HttpClient.newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());
            String res = response.body();
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
                        @Override
                        public LocalDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                            return LocalDateTime.parse(jsonElement.getAsJsonPrimitive().getAsString().substring(0, 19));
                        }
                    })
                    .create();
            events = gson.fromJson(response.body(), new TypeToken<ArrayList<Event>>(){}.getType());

        } catch (
                Exception exception) {
            System.out.println(exception.getMessage());

        }
        return events;
    }
}
