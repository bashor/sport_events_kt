package sport_events.been

import javax.xml.bind.annotation.XmlRootElement

XmlRootElement
public class EventInfo(
        val id: String,
        val date: String,
        val home_id: String,
        val home: String,
        val away_id: String,
        val away: String,
        val status: String)