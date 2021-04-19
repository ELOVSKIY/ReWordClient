package ui.content

import model.User
import react.*

data class SettingsProps(var user: User) : RProps

class Settings(props: SettingsProps) : RComponent<SettingsProps, RState>(props) {
    override fun RBuilder.render() {

    }
}

fun RBuilder.settings(handler: SettingsProps.() -> Unit): ReactElement {
    return child(Settings::class) {
        this.attrs(handler)
    }
}