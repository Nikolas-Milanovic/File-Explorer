import javafx.stage.DirectoryChooser
import javafx.stage.Stage
import java.io.File

object DirChooser {

    var pmStage: Stage? = null

    fun directoryChooser(): File? {
        if( pmStage == null){
            return null
        }
        val directoryChooser = DirectoryChooser()
        directoryChooser.initialDirectory = File("${System.getProperty("user.dir")}/test/")
        directoryChooser.title = "Select a Directory"
        val selectedDirectory = directoryChooser.showDialog(pmStage)

        if (selectedDirectory != null) {
            // Directory was selected
            println("Selected directory: ${selectedDirectory.absolutePath}")
            return File(selectedDirectory.absolutePath)
        }
        println("Directory selection canceled.")
        return null
    }

    fun setPrimaryStage(primaryStage: Stage?){
        this.pmStage = primaryStage
    }
}