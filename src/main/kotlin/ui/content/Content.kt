package ui.content

import react.*

class Content(props: RProps): RComponent<RProps, RState>(props) {
    override fun RBuilder.render() {
        authorization {
        }
    }
}

fun RBuilder.content(handler: RProps.() -> Unit): ReactElement {
    return child(Content::class) {
        this.attrs(handler)
    }
}