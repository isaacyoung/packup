import java.io.File
import java.text.SimpleDateFormat
import java.util.*

/**
 *
 * create by isaac at 2017/12/22 8:56
 */
object Main {
    val projectPath = "E:\\workspace"
    val classPath = "$projectPath\\LIANLIAN_MNG\\WebRoot\\WEB-INF\\classes\\"
    val targetPath = "E:\\output"
    val fromDate = "2017-12-22 12:30:00"
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
        val file = File("$targetPath\\readme.txt")
        filesList.forEach { file.appendText("$it\r\n") }
        file.appendText("\r\n")
        file.appendText("如有properties配置文件，需另外处理")
    }

    /**
     * pack up war
     */
    fun packup(targetPath: String) {
        val rt = Runtime.getRuntime()
        File(targetPath).walk().maxDepth(1)
                .filter { it.isDirectory }
                .filter { it.name != "output"}
                .forEach { it.renameTo(File("$it.war")) }

        File(targetPath).walk().maxDepth(1)
                .filter { it.isDirectory }
                .filter { it.name != "output"}
                .forEach {
                    rt.exec("cmd /C cd /D $targetPath && jar cfM ${it.name}.zip ${it.name}/")
                }
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
                it.path.endsWith(".properties") -> null
                it.path.endsWith(".xml") -> copyXmlFiles(it.path)
                else -> it.copyTo(File(it.path.replace(projectPath,targetPath)),true)
            }
        }
    }

    fun copyXmlFiles(path: String) {
        var targetFilePath = path.replace(projectPath, targetPath)
        targetFilePath = targetFilePath.replace("\\src\\","\\WEB-INF\\classes\\")
        File(path).copyTo(File(targetFilePath),true)
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


    fun copyJavaFiles(path: String) {
        var fromFilePath = classPath + path.substring(path.indexOf("\\src\\")+4)
        fromFilePath = fromFilePath.replace(".java",".class")
        var targetFilePath = fromFilePath.replace(projectPath, targetPath)
        targetFilePath = targetFilePath.replace("\\WebRoot","")
        File(fromFilePath).copyTo(File(targetFilePath),true)
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