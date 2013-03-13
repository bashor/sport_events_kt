package sport_events.resource

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.Context
import javax.ws.rs.core.UriInfo
import sport_events.been.EventInfo

Path("/event")
Produces("application/json", "text/html")
class EventResource {
    val dataProvider = sport_events.data_providers.JStatsFCEventProvider()

    GET
    Path("/all")
    public fun all(): List<EventInfo> {
        return dataProvider.all()
    }

    Context
            var uriInfo: UriInfo? = null

    GET
    Path("/{team}")
    public fun team(PathParam("team") team: String): Pair<String, List<EventInfo>> {
        val team1 = uriInfo?.getPathSegments()!![1].getPath()!! // todo fix this workaround
        return (team1 to dataProvider.forTeam(team1))
    }

}