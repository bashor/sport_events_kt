package sport_events.HtmlBuilder

import java.util.ArrayList
import java.util.HashMap

trait Element {
    fun render(builder: StringBuilder, indent: String)

    fun toString(): String? {
        val builder = StringBuilder()
        render(builder, "")
        return builder.toString()
    }
}

class TextElement(val text: String): Element {
    override fun render(builder: StringBuilder, indent: String) {
        builder.append("$indent$text\n")
    }
}

abstract class Tag(val name: String): Element {
    val children: ArrayList<Element> = ArrayList<Element>()
    val attributes = HashMap<String, String>()

    protected fun initTag<T: Element>(tag: T, init: T.() -> Unit): T {
        tag.init()
        children.add(tag)
        return tag
    }

    override fun render(builder: StringBuilder, indent: String) {
        builder.append("$indent<$name${renderAttributes()}>\n")
        for (c in children) {
            c.render(builder, indent + "  ")
        }
        builder.append("$indent</$name>\n")
    }

    private fun renderAttributes(): String? {
        val builder = StringBuilder()
        for (a in attributes.keySet()) {
            builder.append(" $a=\"${attributes[a]}\"")
        }
        return builder.toString()
    }
}

abstract class TagWithText(name: String): Tag(name) {
    fun String.plus() {
        children.add(TextElement(this))
    }
}

class HTML(): TagWithText("html") {
    fun head(init: Head.() -> Unit) = initTag(Head(), init)

    fun body(init: Body.() -> Unit) = initTag(Body(), init)
}

class Head(): TagWithText("head") {
    fun title(init: Title.() -> Unit) = initTag(Title(), init)
}

class Title(): TagWithText("title")

abstract class BodyTag(name: String): TagWithText(name) {
    fun b(init: B.() -> Unit) = initTag(B(), init)
    fun h1(init: H1.() -> Unit) = initTag(H1(), init)
    fun a(href: String, init: A.() -> Unit) {
        val a = initTag(A(), init)
        a.href = href
    }
    fun table(init: Table.() -> Unit) = initTag(Table(), init)
    fun img(src: String, height: Int? = null, width: Int? = null) = initTag(Img(src, height, width), { })
}

class Body(): BodyTag("body")

class B(): BodyTag("b")
class H1(): BodyTag("h1")
class A(): BodyTag("a") {
    public var href: String
        get() = attributes["href"]!!
        set(value) {
            attributes["href"] = value
        }
}

class Table(): TagWithText("table") {
    fun tr(init: Tr.() -> Unit) = initTag(Tr(), init)
}

class Tr(): TagWithText("tr") {
    fun td(align: String = "", init: Td.() -> Unit) = initTag(Td(), init).attributes["align"] = align
}

class Td(): BodyTag("td")

class Img(val src: String, val height: Int? = null, val width: Int? = null): Element {
    override fun render(builder: StringBuilder, indent: String) {
        builder.append("$indent<img src='$src' height='${height?:""}' width='${width?:""}'/>\n")
    }
}


fun html(init: HTML.() -> Unit): HTML {
    val html = HTML()
    html.init()
    return html
}