import javafx.scene.control.Button
import javafx.scene.control.ToolBar
import javafx.scene.image.Image
import javafx.scene.image.ImageView

object CustomToolBar: ToolBar() {
    val dirContent = DirDisplay
    val iconSize = 15.0
    init{
        val home = Button("Home").apply {
            setOnAction{
                dirContent.homeClicked()
            }
            graphic = getIcon("home.png")
        }
        val prev = Button("Prev").apply {
            setOnAction{
                dirContent.prevClicked()
            }
            graphic = getIcon("back.png")
        }
        val next = Button("Next").apply {
            setOnAction{
                println("Next clicked")
                dirContent.nextClicked()
            }
            graphic = getIcon("next.png")
        }

        val delete = Button("Delete").apply {
            setOnAction{
                dirContent.deleteClicked()
            }
            graphic = getIcon("delete.png")
        }

        val rename = Button("Rename").apply {
            setOnAction{
                dirContent.renameClicked()
            }
            graphic = getIcon("rename.png")
        }

        val move = Button("Move").apply {
            setOnAction{
                dirContent.moveClicked()
            }
            graphic = getIcon("move.png")
        }

        items.add(home)
        items.add(prev)
        items.add(next)
        items.add(delete)
        items.add(rename)
        items.add(move)
    }

    fun getIcon(fileName: String): ImageView{
        val image = Image("file:icons/"+ fileName)
        val icon = ImageView(image)
        icon.fitHeight = iconSize
        icon.fitWidth = iconSize
        return icon
    }
}