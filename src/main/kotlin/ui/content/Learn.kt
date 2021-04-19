package ui.content

import model.Word
import react.*
import styled.styledDiv

data class LearnProps(
    var words: List<Word>,
    var onAnswer: () -> Unit
) : RProps

class Learn(props: LearnProps) : RComponent<LearnProps, RState>(props) {

    override fun RBuilder.render() {
        styledDiv {
            if (props.words.isNotEmpty()) {
                card {
                    word = props.words[0]
                    changeWord = {
                        learnNextWord()
                    }
                }
            }
        }
    }

    private fun learnNextWord() {
        props.onAnswer()
    }
}

fun RBuilder.learn(handler: LearnProps.() -> Unit): ReactElement {
    return child(Learn::class) {
        this.attrs(handler)
    }
}