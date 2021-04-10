package ui

import model.User
import react.*
import ui.content.authorization
import ui.content.content
import ui.footer.footer
import ui.header.NavigationType
import ui.header.header

data class AppState(
    var currentUser: User?,
    var navigation: NavigationType
) : RState

class App(props: RProps) : RComponent<RProps, AppState>(props) {
    override fun RBuilder.render() {
        //TODO commnet for debug
//        if (state.currentUser != null) {
            header {
                navigationItemSelected = this@App::navigationItemSelected
            }
            content {

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
