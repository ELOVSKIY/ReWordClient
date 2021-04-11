package ui.content

import react.*
import styled.styledDiv
import styled.styledH1
import ui.header.NavigationType

data class ContentProps(var activeNavigationType: NavigationType) : RProps

class Content(props: ContentProps) : RComponent<ContentProps, RState>(props) {
    override fun RBuilder.render() {
        styledDiv {
            styledH1 {
                css.classes = mutableListOf("mt-5")
                when (props.activeNavigationType) {
                    NavigationType.LEARN -> {
                        +"LEARN"
                    }
                    NavigationType.CATEGORIES -> {
                        +"CATEGORIES"
                    }
                    NavigationType.STATISTICS -> {
                        +"STATISTICS"
                    }
                    NavigationType.SETTINGS -> {
                        +"SETTINGS"
                    }
                }

            }
        }
    }
}

fun RBuilder.content(handler: ContentProps.() -> Unit): ReactElement {
    return child(Content::class) {
        this.attrs(handler)
    }
}