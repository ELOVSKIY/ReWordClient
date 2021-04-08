package ui

import model.User
import react.*
import ui.content.authorization
import ui.content.content
import ui.footer.footer
import ui.header.header

data class AppState(
    var currentUser: User?
) : RState

class App(props: RProps) : RComponent<RProps, AppState>(props) {
    override fun RBuilder.render() {
        if (state.currentUser != null) {
            header {

            }
            content {

            }
            footer {

            }
        } else {
            authorization {
                userAssigned = this@App::userAssigned
            }
        }
    }

    private fun userAssigned(user: User) {
        setState {
            currentUser = user
        }
    }
}
