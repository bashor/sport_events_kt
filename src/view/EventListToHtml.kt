package sport_events.view

import sport_events.HtmlBuilder.*
import sport_events.been.EventInfo

fun List<EventInfo>.toHtml(team: String? = null): String {
    val list = this
    val result =
            html {
                body {
                    //                    if (list.isEmpty()) {
                    //                        if (team != null) {
                    //                            h1 { +"$team not found." }
                    ////                            img(src = "http://empty.jpg.to/")
                    //                        }
                    //                        else {
                    //                            h1 { +"Events not found" }
                    //                            img(src = "http://empty.jpg.to/")
                    //                        }
                    //                    }
                    //                    else {
                    if (team != null)
                        img(src = "http://${team.replace(' ', '-')}.jpg.to", width = 300)

                    table {
                        for (event in list) {
                            tr {
                                td { +event.date }
                                td(align = "right") {
                                    if (team == event.home)
                                        b { +event.home }
                                    else
                                        a(href = "/event/${event.home}") { +event.home }
                                }
                                td { +"-" }
                                td {
                                    if (team == event.away)
                                        b { +event.away }
                                    else
                                        a(href = "/event/${event.away}") { +event.away }
                                }
                            }
                        }
                    }
                    //                    }
                }
            }.toString()!!

    return result
}

fun Pair<String, List<EventInfo>>.toHtml(): String = second.toHtml(first)