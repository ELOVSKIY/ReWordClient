package ui.content

import api.async
import api.notifyCorrectAnswer
import kotlinx.html.InputType
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import model.Word
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import react.*
import styled.*

private const val DEFAULT_RETRY_COUNT = 3

class Card(props: CardProps) : RComponent<CardProps, CardState>(props) {

    init {
        state = CardState("", DEFAULT_RETRY_COUNT)
    }

    override fun RBuilder.render() {
        styledDiv {
            css.classes = mutableListOf("row")
            styledDiv {
                css.classes = mutableListOf("col-md-5 mx-auto")
                styledDiv {
                    css.classes = mutableListOf("card")
                    styledDiv {
                        css.classes = mutableListOf("card-body")
                        styledH5 {
                            css.classes = mutableListOf("card-title")
                            +props.word.word
                        }
                        styledH4 {
                            css.classes = mutableListOf("card-text")
                            +props.word.transcription
                        }
                        styledInput {
                            css.classes = mutableListOf("form-control input-block")
                            attrs {
                                type = InputType.text
                                onChangeFunction = {
                                    onAnswerChanged(it)
                                }
                            }
                        }
                        styledButton {
                            css.classes = mutableListOf("btn btn-primary btn-block")
                            attrs {
                                name = "commit"
                                onClickFunction = {
                                    checkAnswer()
                                }
                            }
                            +"Check"
                        }
                    }
                }
            }
        }
    }

    private fun checkAnswer() {
        if (state.answer == props.word.translation) {
            async {
                notifyCorrectAnswer(props.word.id)
            }.catch {
                console.log(it.message)
            }
            props.changeWord()
        } else {
            val count = state.triesCount - 1
            if (count == 0) {
                async {
                    notifyCorrectAnswer(props.word.id)
                }.catch {
                    console.log(it.message)
                }
                props.changeWord()
            } else {
                setState {
                    triesCount = count
                }
            }
        }
    }

    private fun onAnswerChanged(event: Event) {
        val target = (event.target as? HTMLInputElement)
        val value = target?.value ?: ""
        setState {
            answer = value
        }
    }
}

data class CardProps(
    var word: Word,
    var changeWord: () -> Unit
) : RProps

data class CardState(
    var answer: String,
    var triesCount: Int
) : RState

fun RBuilder.card(handler: CardProps.() -> Unit): ReactElement {
    return child(Card::class) {
        this.attrs(handler)
    }
}