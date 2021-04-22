package ui.content

import api.async
import api.fetchWordToLearn
import model.Word
import react.*
import styled.styledDiv
import styled.styledH1
import ui.header.NavigationType

private const val MIN_WORDS_COUNT_TO_LOAD = 3

data class ContentProps(var activeNavigationType: NavigationType) : RProps

data class ContentState(var words: MutableList<Word>) : RState

class Content(props: ContentProps) : RComponent<ContentProps, ContentState>(props) {

    init {
        state = ContentState(mutableListOf())
//        loadWords()
    }

    override fun RBuilder.render() {
        styledDiv {
            css.classes = mutableListOf("mt-5")
            when (props.activeNavigationType) {
                NavigationType.LEARN -> {
                    learn {
                        words = state.words
                        onAnswer = {
                            onLearnAnswer()
                        }
                    }
                }
                NavigationType.CATEGORIES -> {
                    categories {

                    }
                }
                NavigationType.STATISTICS -> {
                    styledH1 {
                        +"STATISTICS"
                    }
                }
                NavigationType.SETTINGS -> {
                    styledH1 {
                        +"SETTINGS"
                    }
                }
            }
        }
    }

    private fun onLearnAnswer() {
        val stateWords = state.words
        stateWords.removeAt(0)
        setState {
            words = stateWords
        }
        if (stateWords.size <= MIN_WORDS_COUNT_TO_LOAD) {
//            loadWords()
        }
    }

    private fun loadWords() {
        async {
            val fetchedWords = fetchWordToLearn()
            val stateWords = state.words
            stateWords.addAll(fetchedWords)
            setState {
                words = stateWords
            }
        }.catch {
            console.log(it)
        }
    }
}

fun RBuilder.content(handler: ContentProps.() -> Unit): ReactElement {
    return child(Content::class) {
        this.attrs(handler)
    }
}