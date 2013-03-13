package sport_events.data_providers

import sport_events.been.EventInfo

public class StatsFCEventProvider: EventProvider {
    class object {
        private val KEY = "q_v_AnLifYfknKOzxRKsfQ4k6sy9VtzdFIKjehHk"
        private val URL = "http://api.statsfc.com/premier-league/fixtures.json?key=$KEY"
    }

    override fun all(): List<EventInfo> = forTeam("")

    override fun forTeam(team: String): List<EventInfo> {
        //                val config = DefaultClientConfig()
        //                config.getClasses()?.add(javaClass<JSONRootElementProvider>())
        //                val client = com.sun.jersey.api.client.Client.create(config)
        //                val resource = client.resource(URL + "&team=$team")
        //                val events = ressource.get(javaClass<List<EventInfo>>)
        //
        //                if (response.getStatus() != 200) {
        //                    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        //                }
        //
        //                return events
        throw UnsupportedOperationException()
    }
}