package sport_events.provider

import com.sun.jersey.spi.MessageBodyWorkers
import java.io.OutputStream
import java.lang.reflect.Type
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.MultivaluedMap
import javax.ws.rs.ext.MessageBodyWriter

public abstract class BaseEventsHtmlProvider<T>(val toHtml: T.() -> String): MessageBodyWriter<T> {
    Context
            var workers: MessageBodyWorkers? = null

    public override fun getSize(events: T,
                                clazz: Class<out Any>,
                                genericType: Type,
                                annotations: Array<out Annotation>,
                                mediaType: MediaType): Long = -1.toLong()  // todo компилятор должен сам определить тип

    public override fun writeTo(events: T,
                                clazz: Class<out Any>,
                                genericType: Type,
                                annotations: Array<out Annotation>,
                                mediaType: MediaType,
                                headers: MultivaluedMap<String, Any>,
                                output: OutputStream)
    {
        val writer = workers?.getMessageBodyWriter(javaClass<String>(), javaClass<String>(), annotations, mediaType)
        writer?.writeTo(events.toHtml(), javaClass<String>(), javaClass<String>(), annotations, mediaType, headers, output)
    }
}