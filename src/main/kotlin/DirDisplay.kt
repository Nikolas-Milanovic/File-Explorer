import javafx.scene.control.ListView
import javafx.scene.control.SelectionMode
import javafx.scene.control.TextInputDialog
import javafx.scene.input.KeyCode

object DirDisplay: ListView<String>() {
    val dirManger = DirManager()
    init{
        selectionModel.selectionMode = SelectionMode.SINGLE
        updateView()
        selectionModel.selectedItemProperty().addListener { _, _, newValue ->
            if(newValue!=null){
                selectionUpdate(newValue)
            }
        }

        setOnKeyPressed {
            event ->
            if(event.code == KeyCode.RIGHT || event.code == KeyCode.ENTER){
                nextClicked()
            }else if(event.code == KeyCode.LEFT){
                prevClicked()
            }
        }

        setOnMouseClicked {
            if(it.clickCount == 2) {
                nextClicked()
            }
        }
    }

     fun selectionUpdate(fileName: String){
         println("New slection: " + fileName)
         val file = dirManger.getFile(fileName)
         ContentDisplay.display(file)
         SelectedFile.setContents(file)
    }

    fun updateView(){
        items.setAll(dirManger.getDirContents())
        selectionModel.select(0);
        selectionUpdate(focusModel.focusedItem)
    }
    fun homeClicked(){
        println("homeClicked Invoked")
        dirManger.setHome()
        updateView()
    }

    fun prevClicked(){
        dirManger.setPrevDir()
        updateView()
    }

    fun nextClicked(){
        println("nextClick Invoked")
        val focusedItem = focusModel.focusedItem
        if(!dirManger.isDir(focusedItem)){
            return //Do Nothing - Action Invalid
        }
        dirManger.setDir(focusedItem)
        updateView()
    }

    fun deleteClicked(){
        dirManger.deleteFile(focusModel.focusedItem)
        updateView()
    }

    fun renameClicked(){
        val oldFileName = focusModel.focusedItem
        val dialog = TextInputDialog()
        dialog.headerText = "Rename To:"
        val result = dialog.showAndWait()
        result.ifPresent { newFileName ->
            println("New File Name Entered: , ${newFileName}")
            dirManger.renameFile(sanatize(oldFileName), newFileName)
        }
        updateView()
    }

    fun moveClicked(){
        val fileName = focusModel.focusedItem
//        val dialog = TextInputDialog()
//        dialog.headerText = "Move To Dir:"
//        val result = dialog.showAndWait()
//        result.ifPresent { dirTarget->
//            println("Dir Name Entered: , ${dirTarget}")
//            dirManger.moveFile(fileName, dirTarget)
//        }
        dirManger.moveFile(fileName, "NotUsed")
        updateView()
    }

    fun sanatize(fileName: String): String{
        val dashIndex = fileName.length-1
        if(fileName[dashIndex] == '/'){
            return fileName.substring(0,dashIndex)
        }
        return fileName
    }

}