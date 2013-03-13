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
public class TeamEventsHtmlProvider: BaseEventsHtmlProvider<Pair<String, List<EventInfo>>>({ toHtml() }) {
    val pairClass = javaClass<Pair<*, *>>()
    val eventListClass = javaClass<List<EventInfo>>()

    public override fun isWriteable(clazz: Class<out Any>,
                                    genericType: Type,
                                    annotations: Array<out Annotation>,
                                    mediaType: MediaType): Boolean
    {
        if (genericType !is ParameterizedType)
            return false

        val genericTypeArgs = genericType.getActualTypeArguments()!!

        return mediaType == MediaType.TEXT_HTML_TYPE &&
        pairClass.isAssignableFrom(clazz) &&
        pairClass.isAssignableFrom(genericType.getRawType()) &&
        genericTypeArgs[0] is Class<*> && javaClass<String>().isAssignableFrom(genericTypeArgs[0] as Class<*>) &&
        genericTypeArgs[1] is ParameterizedType && eventListClass.isAssignableFrom((genericTypeArgs[1] as ParameterizedType).getRawType())
    }

}