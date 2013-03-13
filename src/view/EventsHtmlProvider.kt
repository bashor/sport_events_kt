package sport_events.provider

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.ext.Provider
import sport_events.been.EventInfo
import sport_events.view.toHtml

Provider
Produces("text/html")
public class EventsHtmlProvider: BaseEventsHtmlProvider<List<EventInfo>>({ toHtml() }) {
    val listClass = javaClass<List<*>>()
    val eventInfoClass = javaClass<EventInfo>()

    public override fun isWriteable(clazz: Class<out Any>,
                                    genericType: Type,
                                    annotations: Array<out Annotation>,
                                    mediaType: MediaType): Boolean
    {
        return mediaType == MediaType.TEXT_HTML_TYPE &&
        listClass.isAssignableFrom(clazz) &&
        listClass.isAssignableFrom((genericType as ParameterizedType).getRawType()) &&
        eventInfoClass.isAssignableFrom((genericType as ParameterizedType).getActualTypeArguments()!![0] as Class<*>)
    }
}