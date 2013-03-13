package sport_events.data_providers;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import sport_events.been.EventInfo;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.util.ArrayList;
import java.util.List;

public class JStatsFCEventProvider implements EventProvider {

    private static final String KEY_VALUE = "q_v_AnLifYfknKOzxRKsfQ4k6sy9VtzdFIKjehHk";
    private static final String BASE_URL = "http://api.statsfc.com/premier-league/fixtures.json";

    public List<EventInfo> all() {
        return forTeam("");
    }

    public List<EventInfo> forTeam(String team) {
        Client client = Client.create();
        WebResource resource = client.resource(BASE_URL);

        MultivaluedMap<String, String> params = new MultivaluedMapImpl();
        params.add("team", team.replace(' ', '-'));
        params.add("key", KEY_VALUE);


        JSONArray jsonArray =
                resource.queryParams(params)
                        .accept(MediaType.APPLICATION_JSON_TYPE)
                        .get(JSONArray.class);

        List<EventInfo> events = new ArrayList<EventInfo>();

        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject json = (JSONObject) jsonArray.get(i);
                events.add(new EventInfo(
                        json.getString("id"),
                        json.getString("date"),
                        json.getString("home_id"),
                        json.getString("home"),
                        json.getString("away_id"),
                        json.getString("away"),
                        json.getString("status")));
            } catch (JSONException e) {
                // continue;
            }
        }

        return events;
    }
}
