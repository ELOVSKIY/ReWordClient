import react.dom.render
import kotlinx.browser.document
import kotlinx.browser.window
import ui.App
import ui.content.AuthorizationWindow

fun main() {
    window.onload = {
        render(document.getElementById("root")) {
            child(App::class) {
                attrs {
                }
            }
        }
    }
}
