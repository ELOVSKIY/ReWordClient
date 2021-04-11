package ui.header

import kotlinx.css.CSSBuilder
import kotlinx.css.RuleSet
import kotlinx.html.ButtonType
import kotlinx.html.id
import kotlinx.html.js.onClickFunction
import react.*
import styled.*

val navigations = mutableListOf(
    NavigationType.LEARN to "LEARN",
    NavigationType.CATEGORIES to "CATEGORIES",
    NavigationType.STATISTICS to "STATISTICS",
    NavigationType.SETTINGS to "SETTINGS"
)

data class HeaderProps(
    var activeNavigationType: NavigationType,
    var navigationItemSelected: (NavigationType) -> Unit
) : RProps

class Header(props: HeaderProps) : RComponent<HeaderProps, RState>(props) {

    private fun onSelectNavItem(navigation: NavigationType) {
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
                    styledButton {
                        css.classes = mutableListOf("navbar-toggler")
                        attrs {
                            type = ButtonType.button
                        }
                        attrs["data-bs-toggle"] = "collapse"
                        attrs["data-bs-target"] = "#navbarCollapse"
                        attrs["aria-controls"] = "navbarCollapse"
                        attrs["aria-expanded"] = "true"
                        attrs["aria-label"] = "Toggle navigation"
                        styledSpan {
                            css.classes = mutableListOf("navbar-toggler-icon")
                        }
                    }
                    styledDiv {
                        css.classes = mutableListOf("collapse navbar-collapse")
                        attrs.id = "navbarCollapse"
                        
                        styledUl {
                            css.classes = mutableListOf("navbar-nav me-auto mb-2 mb-md-0")
                            for (navigation in navigations) {
                                styledLi {
                                    css.classes = mutableListOf("nav-item")
                                    styledA {
                                        val classes = mutableListOf("nav-link")
                                        if (navigation.first == props.activeNavigationType) {
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