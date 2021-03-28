package ui.content

import kotlinx.html.InputType
import kotlinx.html.js.onChangeFunction
import org.w3c.dom.HTMLInputElement
import react.*
import styled.css
import styled.styledDiv
import styled.styledInput

external interface InputFieldProps : RProps {
    var value: String
}

data class InputFieldState(val value: String) : RState

class InputField(props: InputFieldProps) : RComponent<InputFieldProps, InputFieldState>(props) {

    init {
        state = InputFieldState(props.value)
    }

    override fun RBuilder.render() {
        styledDiv {
            styledInput {
                css {
                    classes = mutableListOf("form-control")
                }
                attrs {
                    type = InputType.text
                    value = state.value
                    onChangeFunction = { event ->
                        setState(
                            InputFieldState(value = (event.target as HTMLInputElement).value)
                        )
                    }
                }
            }
        }
    }
}

fun RBuilder.inputField(handler: InputFieldProps.() -> Unit): ReactElement {
    return child(InputField::class) {
        this.attrs(handler)
    }
}