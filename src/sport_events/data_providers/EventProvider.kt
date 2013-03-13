package sport_events.data_providers

import sport_events.been.EventInfo

trait EventProvider {
    fun all(): List<EventInfo>
    fun forTeam(team: String): List<EventInfo>
}