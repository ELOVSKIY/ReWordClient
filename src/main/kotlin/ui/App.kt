package ui

import model.User
import react.*
import ui.authorization.authorization
import ui.content.content
import ui.footer.footer
import ui.header.NavigationType
import ui.header.header

data class AppState(
    var currentUser: User?,
    var navigation: NavigationType
) : RState

class App(props: RProps) : RComponent<RProps, AppState>(props) {

    init {
        state = AppState(null, NavigationType.LEARN)
    }
    override fun RBuilder.render() {
        //TODO commnet for debug
//        if (state.currentUser != null) {
            header {
                activeNavigationType = state.navigation
                navigationItemSelected = this@App::navigationItemSelected
            }
            content {
                activeNavigationType = state.navigation
            }
            footer {

            }
//        } else {
//            authorization {
//                userAssigned = this@App::userAssigned
//            }
//        }
    }

    private fun navigationItemSelected(selectedNavigationType: NavigationType) {
        setState {
            navigation = selectedNavigationType
        }
    }

    private fun userAssigned(user: User) {
        setState {
            currentUser = user
        }
    }
}
