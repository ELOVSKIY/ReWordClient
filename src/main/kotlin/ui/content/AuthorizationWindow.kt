package ui.content

import kotlinx.html.*
import kotlinx.html.js.onChangeFunction
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import react.*
import styled.*


data class AuthorizationState(
    var username: String,
    var password: String
    ) : RState

class AuthorizationWindow(props: RProps) : RComponent<RProps, AuthorizationState>(props) {

    private fun onUsernameChange(event: Event) {
        val target = (event.target as? HTMLInputElement)
        val value = target?.value ?: ""
        setState {
            username = value
        }
    }

    private fun onPasswordChange(event: Event) {
        val target = (event.target as? HTMLInputElement)
        val value = target?.value ?: ""
        setState {
            password = value
        }
    }

    override fun RBuilder.render() {
        styledDiv {
            css.classes = mutableListOf("container")
            styledDiv {
                css.classes = mutableListOf("row")
                styledDiv {
                    css.classes = mutableListOf("col-md-offset-3 col-md-6")
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
                                attrs.onChangeFunction = { event ->
                                    onUsernameChange(event)
                                }
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
                                attrs.onChangeFunction = { event ->
                                    onPasswordChange(event)
                                }
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