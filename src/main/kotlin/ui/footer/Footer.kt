package ui.footer

import react.*
import react.dom.footer

class Footer(props: RProps): RComponent<RProps, RState>(props) {
    override fun RBuilder.render() {
        footer {

        }
    }
}

fun RBuilder.footer(handler: RProps.() -> Unit): ReactElement {
    return child(Footer::class) {
        this.attrs(handler)
    }
}