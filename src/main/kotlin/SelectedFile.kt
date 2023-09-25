import javafx.scene.control.Label
import javafx.scene.text.Font
import java.io.File

object SelectedFile: Label() {
    init {
        text = "/"
        padding = javafx.geometry.Insets(5.0)
        font = Font(font.name, 10.0)
    }

    fun setContents(file : File){
        text = file.absolutePath
    }


}