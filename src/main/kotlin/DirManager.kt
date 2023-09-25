
import javafx.scene.control.Alert
import javafx.stage.DirectoryChooser
import javafx.stage.Stage
import java.io.File
import java.util.Stack

class DirManager(){

    private val dirStack = Stack<File>()
    private var currDir = File("${System.getProperty("user.dir")}/test/")
    private val homeDir = File("${System.getProperty("user.dir")}/test/")

    fun setDir(relPath: String){
        var dir =  File(currDir.absolutePath + "/" + relPath)
        if(!dir.isDirectory){
            errorPrompt("Not a Directory")
            return
        }
        dirStack.push(currDir)
        currDir = dir
    }

    fun setHome(){
        dirStack.clear()
        currDir = homeDir
    }
     fun getDirContents(): List<String> {

        val dirContents = mutableListOf<String>()
         currDir.listFiles().forEach{
             if(it.isDirectory){
                 dirContents.add(it.name + "/")
             }else {
                 dirContents.add(it.name)
             }
         }
         dirContents.sort()
         return dirContents
    }

    fun setPrevDir(){
        if(dirStack.isEmpty()){
            return
        }
        currDir = dirStack.pop()
    }

    // Note: fileName could also be a directory
    fun deleteFile(fileName: String) {
        var file = File(currDir.absolutePath + "/" + fileName)
        println(file.absolutePath)
        if(!file.exists()){
            errorPrompt("Delete Failed - File Not Found")
            return
        }
        if(file.isDirectory){
            deleteDir(file)
        }else {
            file.delete()
        }
    }

    fun deleteDir(dir: String){
        deleteDir(File(currDir.absolutePath + dir))
    }

    //Recursively delete starting at dir
    fun deleteDir(dir: File){
        var files = dir.listFiles()
        if(files != null){
            for (file in files){
                if(file.isDirectory){
                    deleteDir(file)
                }else{
                    file.delete()
                }
            }
        }
        dir.delete()
    }

    fun moveFile(fileName: String, dest: String){

        var dest = DirChooser.directoryChooser() ?: return

        dest = File(dest.absolutePath + "/" + fileName)

        File(currDir.absolutePath + "/" + fileName).let { sourceFile ->
            try{
                sourceFile.copyRecursively(dest)
                if(sourceFile.isDirectory){
                    deleteDir((sourceFile))
                }else{
                    sourceFile.delete()
                }
            }catch(ex: Exception){
                errorPrompt("Move Failed " + ex.message)
            }
        }
    }

    fun renameFile(oldFileName: String, newFileName: String) {
        val oldFile = File(currDir.absolutePath + "/" + oldFileName)
        val newFile = File(currDir.absolutePath + "/" + newFileName)
        try{
            oldFile.renameTo(newFile)
        }catch(ex: Exception){
            errorPrompt("Rename Failed " + ex.message)
        }
    }

    fun errorPrompt(errorMsg: String){
        println("Error: " + errorMsg)
        val alert = Alert(Alert.AlertType.ERROR)
        alert.title = "Error:"
        alert.headerText = null
        alert.contentText = errorMsg
        alert.showAndWait()
    }

    fun isDir(fileName: String): Boolean{
        val file = File(currDir.absolutePath + "/" + fileName)
        return file.isDirectory
    }

    fun getFile(fileName: String): File{
        return File (currDir.absolutePath + "/" + fileName)
    }
}