package ui.content

import kotlinx.html.*
import kotlinx.html.js.onChangeFunction
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import react.*
import react.dom.form
import react.dom.h1
import react.dom.label
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
            css.classes = mutableListOf("auth-form px-3")
            styledDiv {
                css.classes = mutableListOf("auth-form-header p-0")
                h1 {
                    +"Sign in to ReWord"
                }
            }
            styledDiv {
                css.classes = mutableListOf("auth-form-body mt-3")
                form {
                    attrs {
                        action = "/session"
                        method = FormMethod.post
                    }
                    attrs["acceptCharset"] = "UTF-8"
                    label {
                        attrs.htmlFor = "login_field"
                        +"Username"
                    }
                    styledInput {
                        css.classes = mutableListOf("form-control input-block")
                        attrs {
                            type = InputType.text
                        }
                    }
                    styledDiv {
                        css.classes = mutableListOf("position-relative")
                        label {
                            attrs.htmlFor = "password"
                            +"Password"
                        }
                        styledInput {
                            css.classes = mutableListOf("form-control input-block")
                            attrs {
                                type = InputType.password
                                name = "password"
                                id = "password"
                            }
                        }
                        styledInput {
                            css.classes = mutableListOf("btn btn-primary btn-block")
                            attrs {
                                type = InputType.submit
                                name = "commit"
                                value = "Sign in"
                            }
                        }
                    }
                }
            }
            styledP {
                css.classes = mutableListOf("login-callout mt-3")
            }
        }
    }
}

fun RBuilder.authorization(handler: RProps.() -> Unit): ReactElement {
    return child(AuthorizationWindow::class) {
        this.attrs(handler)
    }
}