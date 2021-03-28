package ui.content

import kotlinx.html.*
import org.w3c.dom.events.Event
import react.*
import styled.*


data class AuthorizationState(val registration: Boolean) : RState

class AuthorizationWindow(props: RProps) : RComponent<RProps, AuthorizationState>(props) {

    init {
        state = AuthorizationState(false)
    }

    fun handleSubmit(event: Event) {

    }

    override fun RBuilder.render() {
        styledDiv {
            css.classes = mutableListOf("container")
            styledDiv {
                css.classes = mutableListOf("row")
                styledDiv {
                    css.classes = mutableListOf("col-md-offset-3 col-md-6 row-md-offset-3 row-md-3")
                    styledForm {
                        css.classes = mutableListOf("form-horizontal")
                        styledSpan {
                            css.classes = mutableListOf("heading")
                            +"Авторизация"
                        }
                        styledDiv {
                            css.classes = mutableListOf("form-group")
                            styledInput(type = InputType.text) {
                                css.classes = mutableListOf("form-control")
                                attrs.id = "inputUsername"
                                attrs.placeholder = "Username"
                            }
                            styledI {
                                css.classes = mutableListOf("fa fa-user")
                            }
                        }
                        styledDiv {
                            css.classes = mutableListOf("form-group help")
                            styledInput(type = InputType.password) {
                                css.classes = mutableListOf("form-control")
                                attrs.id = "inputPassword"
                                attrs.placeholder = "Password"
                            }
                            styledI {
                                css.classes = mutableListOf("fa fa-lock")
                            }
                        }
                        styledDiv {
                            css.classes = mutableListOf("form-group")
                            styledButton(type = ButtonType.submit) {
                                css.classes = mutableListOf("btn btn-default")
                                +"Войти"
                            }
                        }
                    }
                }
            }
        }
    }
}

fun RBuilder.authorization(handler: RProps.() -> Unit): ReactElement {
    return child(AuthorizationWindow::class) {
        this.attrs(handler)
    }
}