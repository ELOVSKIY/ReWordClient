package ui.authorization

import api.async
import api.authorization
import kotlinx.html.*
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import model.User
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import react.*
import react.dom.h1
import react.dom.label
import styled.*

data class AuthorizationState(
    var username: String,
    var password: String
) : RState

data class AuthorizationProps(
    var userAssigned: (User) -> Unit = {}
): RProps

class AuthorizationWindow(props: AuthorizationProps) : RComponent<AuthorizationProps, AuthorizationState>(props) {

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

    private fun authorization() {
        async {
            val user = authorization(state.username, state.password)
            props.userAssigned(user)
        }.catch {
//            console.log(it.message)
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
                styledDiv {
                    attrs["acceptCharset"] = "UTF-8"
                    label {
//                        attrs.htmlFor = "login_field"
                        +"Username"
                    }
                    styledInput {
                        css.classes = mutableListOf("form-control input-block")
                        attrs {
                            type = InputType.text
                            onChangeFunction =  {
                                onUsernameChange(it)
                            }
                        }
                    }
                    styledDiv {
                        css.classes = mutableListOf("position-relative")
                        label {
//                            attrs.htmlFor = "password"
                            +"Password"
                        }
                        styledInput {
                            css.classes = mutableListOf("form-control input-block")
                            attrs {
                                type = InputType.password
                                name = "password"
                                id = "password"
                                onChangeFunction = {
                                    onPasswordChange(it)
                                }
                            }
                        }
                        styledButton {
                            css.classes = mutableListOf("btn btn-primary btn-block")
                            attrs {
                                name = "commit"
                                onClickFunction = {
                                    authorization()
                                }
                            }
                            +"Sign in"
                        }
                    }
                }
            }
        }
    }
}

fun RBuilder.authorization(handler: AuthorizationProps.() -> Unit): ReactElement {
    return child(AuthorizationWindow::class) {
        this.attrs(handler)
    }
}