import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.stage.DirectoryChooser
import javafx.stage.Stage
import java.io.File

class Main : Application() {
    override fun start(primaryStage: Stage?) {

        DirChooser.setPrimaryStage(primaryStage)

        val leftPane = DirDisplay
        val topPane = VBox().apply {
            prefHeight = 30.0
            background = Background(BackgroundFill(Color.valueOf("#00ffff"), null, null))
            setOnMouseClicked { println("top pane clicked") }

            children.addAll(
                CustomMenuBar,
                CustomToolBar)
        }

        val centrePane = ContentDisplay
        val bottomPane = SelectedFile

        val root = BorderPane().apply {
            left = leftPane
            center = centrePane
            top = topPane
            bottom = bottomPane
        }

        primaryStage?.apply{
            scene = Scene(root, 600.0, 400.0)
            title = "A1 - Nikolas Milanovic"
            show()
        }


        //Tests:
       // var fileManager = DirManager()
     //   fileManager.renameFile("dir1","Dir1")
//        fileManager.setDir("/dir1")
//        var dirConents = fileManager.getDirContents()
//        dirConents.forEach { println(it) }
//
//        fileManager.setPrevDir()
//        dirConents = fileManager.getDirContents()
//        dirConents.forEach { println(it) }
        //fileManager.moveFile("file2.txt","dir1/dir2/")
        //fileManager.renameFile("file1.txt", "file2.txt")
//        fileManager.deleteFile("dir3/")
//        dirConents = fileManager.getDirContents()
//        dirConents.forEach { println(it) }


    }


}