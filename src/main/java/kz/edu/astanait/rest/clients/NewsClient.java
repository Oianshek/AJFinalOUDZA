package kz.edu.astanait.rest.clients;

import com.google.gson.Gson;
import kz.edu.astanait.models.News;
import kz.edu.astanait.rest.clients.interfaces.IClient;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class NewsClient implements IClient<News> {
    private String baseURI = "http://localhost:8080/AJ_Final_OUDZA_war_exploded/api/news/";

    public WebTarget getWebTarget() {
        ClientConfig config = new ClientConfig();
        javax.ws.rs.client.Client client = ClientBuilder.newClient(config);
        return client.target(baseURI);
    }

    @Override
    public void add(News entity) {
        getWebTarget().request()
                .post(Entity.entity(entity, MediaType.APPLICATION_JSON), Response.class);
    }

    @Override
    public void update(News entity) {
        getWebTarget().request().put(Entity.entity(entity, MediaType.APPLICATION_JSON), Response.class);
    }

    @Override
    public void delete(int entity) {
        getWebTarget().path(String.valueOf(entity)).request().delete();
    }

    @Override
    public List<News> getAll() {
        WebTarget target = getWebTarget();
        String json = target.path("/getAll").request(MediaType.APPLICATION_JSON).get(String.class);

        Gson gson = new Gson();
        News[] entity = gson.fromJson(json, News[].class);

        return new LinkedList<>(Arrays.asList(entity));
    }
}
