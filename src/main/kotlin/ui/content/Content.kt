package ui.content

import react.*
import styled.styledDiv
import ui.header.NavigationType

data class ContentState(var navigationType: NavigationType) : RState

class Content(props: RProps): RComponent<RProps, ContentState>(props) {
    override fun RBuilder.render() {
        styledDiv {
//        when(state.navigationType) {
////            NavigationType.LEARN -> {
////                +"LEARN"
////            }
//            NavigationType.CATEGORIES -> {
////                +"CATEGORIES"
//            }
//            NavigationType.STATISTICS -> {
////                +"STATISTICS"
//            }
//            NavigationType.SETTINGS -> {
////                +"SETTINGS"
//            }
//        }
        }
    }
}

fun RBuilder.content(handler: RProps.() -> Unit): ReactElement {
    return child(Content::class) {
        this.attrs(handler)
    }
}