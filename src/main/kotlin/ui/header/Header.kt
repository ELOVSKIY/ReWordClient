package ui.header

import react.*
import react.dom.header

class Header(props: RProps): RComponent<RProps, RState>(props) {
    override fun RBuilder.render() {
        header {

        }
    }
}

fun RBuilder.header(handler: RProps.() -> Unit): ReactElement {
    return child(Header::class) {
        this.attrs(handler)
    }
}