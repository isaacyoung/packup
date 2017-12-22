import java.io.File
import java.text.SimpleDateFormat
import java.util.*

/**
 *
 * create by isaac at 2017/12/22 8:56
 */
object Main {
    val projectPath = "E:\\workspace"
    val targetPath = "E:\\output"
    val fromDate = "2017-12-21 12:30:00"
    val sf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val filesList = mutableListOf<String>()

    @JvmStatic
    fun main(args: Array<String>) {
        clearTargetPath()
        val files = getFiles()
        copyFiles(files)
        packup(targetPath)
        printFileList()
    }

    fun printFileList() {

    }

    /**
     * pack up war
     */
    fun packup(targetPath: String) {

    }

    fun clearTargetPath() {
        val file = File(targetPath)
        if (file.exists()) {
            file.deleteRecursively()
        }
    }


    fun copyFiles(files: Sequence<File>) {
        files.forEach {
            println(it.path)
            filesList.add(it.path)
            when {
                it.path.endsWith(".java") -> copyJavaFiles(it.path)
                it.path.endsWith(".jsp") -> copyJspFiles(it.path)
                it.path.contains("LIANLIAN_STATIC") -> copyStaticFiles(it.path)
                else -> it.copyTo(File(it.path.replace(projectPath,targetPath)),true)
            }
        }
    }

    fun copyStaticFiles(path: String) {
        var targetFilePath = path.replace(projectPath, targetPath)
        targetFilePath = targetFilePath.replace("\\WebRoot","")
        File(path).copyTo(File(targetFilePath),true)
    }

    fun copyJspFiles(path: String) {
        var targetFilePath = path.replace(projectPath, targetPath)
        targetFilePath = targetFilePath.replace("\\WebRoot","")
        File(path).copyTo(File(targetFilePath),true)
    }

    // TODO
    fun copyJavaFiles(path: String) {
        var targetFilePath = path.replace(projectPath, targetPath)
        File(path).copyTo(File(targetFilePath),true)
    }

    /**
     * get modified files
     */
    fun getFiles() = File(projectPath).walk().maxDepth(Int.MAX_VALUE)
            .filter { !it.path.contains(".metadata") && !it.path.contains(".svn")  && !it.path.contains("\\classes\\")}
            .filter { it.path.contains("\\src\\") || it.path.contains("\\WebRoot\\") }
            .filter { it.isFile }
            .filter { it.lastModified() >= getDate(fromDate) }


    fun showDate(date: Long): String {
        val c = Calendar.getInstance()
        c.timeInMillis = date

        return sf.format(c.time)
    }

    fun getDate(date: String): Long {
        val c = Calendar.getInstance()
        c.time = sf.parse(date)
        return c.timeInMillis
    }
}