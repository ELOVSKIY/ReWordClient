package ui.content

import api.async
import api.fetchCategories
import model.Category
import react.*
import styled.styledDiv
import styled.styledH5
import styled.styledImg

private const val COLUMNS_COUNT = 3

data class CategoriesState(var categories: MutableList<Category>) : RState

class Categories(props: RProps) : RComponent<RProps, CategoriesState>(props) {

    init {
        state = CategoriesState(mutableListOf())
    }

    override fun RBuilder.render() {
        updateCategories()
        styledDiv {
            css.classes = mutableListOf("container px-auto py-5")
            styledDiv {
                css.classes = mutableListOf("row")
                styledDiv {
                    css.classes = mutableListOf("col mx-auto")
                    val matrix = transformToMatrix(state.categories)
                    for (row in matrix) {
                        styledDiv {
                            css.classes = mutableListOf("row")
                            for (category in row) {
                                styledDiv {
                                    css.classes = mutableListOf("col-md-2 col-sm-6 mx-auto")
                                    styledImg {
                                        css.classes = mutableListOf("image")
                                        attrs {
                                            src = category.iconUrl
                                            width = "200"
                                            height = "200"
                                        }
                                    }
                                    styledH5 {
                                        css.classes = mutableListOf("text-center mt-2")
                                        +category.name
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun transformToMatrix(list: List<Category>): List<List<Category>> {
        val matrix = mutableListOf<MutableList<Category>>()
        for (index in list.indices) {
            if (index % COLUMNS_COUNT == 0) {
                matrix.add(mutableListOf())
            }
            val row = index / COLUMNS_COUNT
            matrix[row].add(list[index])
        }

        return matrix
    }


    private fun updateCategories() {
        async {
            val fetchedCategories = fetchCategories()
            setState {
                categories = fetchedCategories
            }
        }.catch {
            console.log(it.message)
        }
    }
}

fun RBuilder.categories(handler: RProps.() -> Unit): ReactElement {
    return child(Categories::class) {
        this.attrs(handler)
    }
}