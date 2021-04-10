package ui.header

import kotlinx.html.js.onClickFunction
import react.*
import styled.*

val navigations = mutableListOf(
    NavigationType.LEARN to "LEARN",
    NavigationType.CATEGORIES to "CATEGORIES",
    NavigationType.STATISTICS to "STATISTICS",
    NavigationType.SETTINGS to "SETTINGS"
)

data class HeaderState(var navigationType: NavigationType) : RState

data class HeaderProps(var navigationItemSelected: (NavigationType) -> Unit) : RProps

class Header(props: HeaderProps) : RComponent<HeaderProps, HeaderState>(props) {

    init {
         setState {
             navigationType = NavigationType.LEARN
         }
    }

    private fun onSelectNavItem(navigation: NavigationType) {
        setState {
            navigationType = navigation
        }
        props.navigationItemSelected(navigation)
    }

    override fun RBuilder.render() {
        styledHeader {
            css.classes = mutableListOf("d-flex flex-column h-100")
            styledNav {
                css.classes = mutableListOf("navbar navbar-expand-md navbar-dark fixed-top bg-dark")
                styledDiv {
                    css.classes = mutableListOf("container-fluid")
                    styledSpan {
                        css.classes = mutableListOf("navbar-brand")
                        +"ReWord"
                    }
                    styledDiv {
                        css.classes = mutableListOf("collapse navbar-collapse")
                        styledUl {
                            css.classes = mutableListOf("navbar-nav me-auto mb-2 mb-md-0")
                            for (navigation in navigations) {
                                styledLi {
                                    css.classes = mutableListOf("nav-item")
                                    styledA {
                                        val classes = mutableListOf("nav-link")
                                        if (navigation.first == state.navigationType) {
                                            classes += "active"
                                        }
                                        css.classes = classes
                                        attrs.onClickFunction = {
                                            onSelectNavItem(navigation.first)
                                        }
                                        +navigation.second
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun RBuilder.header(handler: HeaderProps.() -> Unit): ReactElement {
    return child(Header::class) {
        this.attrs(handler)
    }
}