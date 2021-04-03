package ui.footer

import react.*
import react.dom.footer
import styled.styledH1

class Footer(props: RProps): RComponent<RProps, RState>(props) {
    override fun RBuilder.render() {
    }
}

fun RBuilder.footer(handler: RProps.() -> Unit): ReactElement {
    return child(Footer::class) {
        this.attrs(handler)
    }
}