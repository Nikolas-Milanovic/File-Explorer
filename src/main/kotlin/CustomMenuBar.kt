import javafx.scene.control.Menu
import javafx.scene.control.MenuBar
import javafx.scene.control.MenuItem

object CustomMenuBar: MenuBar() {
    init {

        val file = Menu("File").apply{
            val homeItem = MenuItem("Home")
            homeItem.setOnAction { DirDisplay.homeClicked() }
            items.add(homeItem)
        }

        val action = Menu("Action").apply{
            val deleteItem = MenuItem("Delete")
            deleteItem.setOnAction { DirDisplay.deleteClicked() }
            items.add(deleteItem)

            val moveItem = MenuItem("Move")
            moveItem.setOnAction { DirDisplay.moveClicked() }
            items.add(moveItem)

            val renameItem = MenuItem("Rename")
            renameItem.setOnAction { DirDisplay.renameClicked() }
            items.add(renameItem)

        }
        menus.add(file)
        menus.add(Menu("View"))
        menus.add(action)
        menus.add(Menu("Options"))

    }
}