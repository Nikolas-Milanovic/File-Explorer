import javafx.beans.binding.Bindings
import javafx.scene.Node
import javafx.scene.control.Label
import javafx.scene.control.ScrollPane
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.text.Text
import java.awt.TextArea
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.util.Objects

object ContentDisplay: Pane()  {

    init{
        prefWidth = 100.0
        background = Background(BackgroundFill(Color.valueOf("#ffffff"), null, null))
    }
    fun display(file: File){
        println("Displaying File: " + file.absolutePath)
        if(file.isDirectory){
            children.clear()
            return
        }
        val dotIndex = file.absolutePath.lastIndexOf('.')
        if(dotIndex == - 1){
            displayError("Unsupported type")
            return
        }
        if(!file.canRead()){
            displayError("File Cannot Be Read")
            return
        }
        val fileType = file.absolutePath.substring(dotIndex)
        if(fileType == ".png" || fileType == ".jpg" || fileType == ".bmp"){
            displayImage(file)
            return
        }
        if(fileType == ".txt" || fileType == ".md" ){
            displayText(file)
            return
        }
        displayError("Unsupported type")
    }

    private fun displayError(error: String) {
        displayText(error)
    }

    private fun displayText(file: File) {
        displayText(file.readText())
    }

    fun displayText(text: String){
        val textNode = Text(text)
        val scrollPane = ScrollPane(textNode)
        scrollPane.prefWidthProperty().bind(Bindings.createDoubleBinding(
            {width},
            widthProperty()
        ))
        scrollPane.prefHeightProperty().bind(Bindings.createDoubleBinding(
            {height},
            heightProperty()
        ))
        scrollPane.hbarPolicy = ScrollPane.ScrollBarPolicy.AS_NEEDED
        scrollPane.vbarPolicy = ScrollPane.ScrollBarPolicy.AS_NEEDED
        updatePane(scrollPane)
    }

    fun displayImage(file: File){
        val image = Image("file:"+file.absolutePath)
        val imageView = ImageView(image)
        imageView.fitWidthProperty().bind(
            Bindings.createDoubleBinding(
            { width },
            widthProperty()
        ))
        imageView.fitHeightProperty().bind(
            Bindings.createDoubleBinding(
                { height },
                heightProperty()
            ))
        updatePane(imageView)
    }

    fun updatePane(obj: Node){
        children.clear()
        children.add(obj)
    }
}