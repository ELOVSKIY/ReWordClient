package ui.header

import react.*
import react.dom.header
import styled.styledDiv
import styled.styledHeader
import styled.styledNav
import styled.styledSpan

val navigations = hashMapOf(
    NavigationType.LEARN to "LEARN",
    NavigationType.CATEGORIES to "CATEGORIES",
    NavigationType.STATISTICS to "STATISTICS",
    NavigationType.SETTINGS to "SETTINGS"
)

class Header(props: RProps) : RComponent<RProps, RState>(props) {
    override fun RBuilder.render() {
        styledHeader {
            css.classes =
                mutableListOf("Header Details px-3 px-md-4 px-lg-5 flex-wrap flex-md-nowrap")
            styledDiv {
                css.classes = mutableListOf("Header-item Header-item--full flex-justify-center d-md-none position-relative")
                styledNav {
                    css.classes = mutableListOf("d-flex flex-column flex-md-row flex-self-stretch flex-md-self-auto")
                    for (items in navigations) {
                        styledSpan {
                            css.classes = mutableListOf("js-selected-navigation-item Header-link mt-md-n3 mb-md-n3 py-2" +
                                    " py-md-3 mr-0 mr-md-3 border-top border-md-top-0 border-white-fade-15")
                            +"Settings"
                        }
                    }
                }
            }
        }
    }
}

fun RBuilder.header(handler: RProps.() -> Unit): ReactElement {
    return child(Header::class) {
        this.attrs(handler)
    }
}