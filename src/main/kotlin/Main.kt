import java.io.File
import java.text.SimpleDateFormat
import java.util.*

/**
 *
 * create by isaac at 2017/12/22 8:56
 */
object Main {
    val sf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val changedList = mutableListOf<String>()
    val packedList = mutableListOf<String>()

    @JvmStatic
    fun main(args: Array<String>) {
        // 删除目标文件夹
        clearTargetPath()
        // 获取变动文件
        val files = getFiles()
        // 复制到目标文件夹
        copyFiles(files)
        // 打包
        packup(config.targetPath)
        // 核对清单
        printFileList()
    }

    /**
     * 核对清单
     */
    fun printFileList() {
        val file = File("${config.targetPath}\\readme.txt")
        file.appendText("变动文件：\r\n")
        changedList.forEach { file.appendText("$it\r\n") }
        file.appendText("\r\n")

        file.appendText("打包文件：\r\n")
        packedList.forEach { file.appendText("$it\r\n") }

        file.appendText("\r\n")
        file.appendText("如有properties配置文件，需另外处理")

    }

    /**
     * 打包
     * pack up zip
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

    /**
     * 删除目标文件夹
     */
    fun clearTargetPath() {
        val file = File(config.targetPath)
        if (file.exists()) {
            file.deleteRecursively()
        }
    }

    /**
     * 复制变动文件
     */
    fun copyFiles(files: List<File>) {
        files.forEach {
            println(it.path)
            changedList.add(it.path)
            when {
                it.path.endsWith(".java") -> copyJavaFiles(it.path, !config.isFromSvn)
                it.path.endsWith(".jsp") -> copyJspFiles(it.path)
                it.path.contains("LIANLIAN_STATIC") -> copyStaticFiles(it.path)
                it.path.endsWith(".properties") -> null
                it.path.endsWith(".xml") -> copyXmlFiles(it.path)
                else -> it.copyTo(File(it.path.replace(config.projectPath,config.targetPath)),true)
            }
        }
    }

    /**
     * 复制xml文件
     */
    fun copyXmlFiles(path: String) {
        var targetFilePath = path.replace(config.projectPath, config.targetPath)
        targetFilePath = targetFilePath.replace("\\src\\","\\WEB-INF\\classes\\")
        File(path).copyTo(File(targetFilePath),true)
        packedList.add(targetFilePath)
    }

    /**
     * 复制静态文件
     */
    fun copyStaticFiles(path: String) {
        var targetFilePath = path.replace(config.projectPath, config.targetPath)
        targetFilePath = targetFilePath.replace("\\WebRoot","")
        File(path).copyTo(File(targetFilePath),true)
        packedList.add(targetFilePath)
    }

    /**
     * 复制jsp文件
     */
    fun copyJspFiles(path: String) {
        var targetFilePath = path.replace(config.projectPath, config.targetPath)
        targetFilePath = targetFilePath.replace("\\WebRoot","")
        File(path).copyTo(File(targetFilePath),true)
        packedList.add(targetFilePath)
    }

    /**
     * 复制java文件
     */
    fun copyJavaFiles(path: String,getRelated: Boolean =false) {
        if (!File(path).exists()) {
            println("$path 不存在")
            return
        }

        // service impl
        if (path.contains("_SERVICE")) {
            var fromFilePath = path.replace("\\src\\","\\WebRoot\\WEB-INF\\classes\\")
            fromFilePath = fromFilePath.replace(".java",".class")
            var targetFilePath = fromFilePath.replace(config.projectPath, config.targetPath)
            targetFilePath = targetFilePath.replace("\\WebRoot","")
            File(fromFilePath).copyTo(File(targetFilePath),true)
            packedList.add(targetFilePath)

            // IService
            if (getRelated) {
                copyJavaFiles(ServicePathUtils.getSuperInterface(path))
            }

            return
        }

        // IService
        if (path.contains("LIANLIAN_COMMON") && path.endsWith("Service.java")) {
            // impl
            if (getRelated) {
                copyJavaFiles(ServicePathUtils.getServiceImpl(path))
            }
        }

        var fromFilePath = config.classPath + path.substring(path.indexOf("\\src\\")+4)
        fromFilePath = fromFilePath.replace(".java",".class")
        var targetFilePath = fromFilePath.replace(config.projectPath, config.targetPath)
        targetFilePath = targetFilePath.replace("\\WebRoot","")
        File(fromFilePath).copyTo(File(targetFilePath),true)
        packedList.add(targetFilePath)
    }

    /**
     * 获取变动文件
     * get modified files
     */
    fun getFiles(): List<File> {
        return when {
            config.isFromSvn -> getSvnFiles()
            else -> getModifiedFiles()
        }
    }

    /**
     * 根据修改日期获取
     */
    fun getModifiedFiles() = File(config.projectPath).walk().maxDepth(Int.MAX_VALUE)
            .filter { !it.path.contains(".metadata") && !it.path.contains(".svn")  && !it.path.contains("\\classes\\")}
            .filter { it.path.contains("\\src\\") || it.path.contains("\\WebRoot\\") }
            .filter { it.isFile }
            .filter { it.lastModified() >= getDate(config.fromDate) }
            .toList()

    /**
     * 根据svn版本号获取
     */
    fun getSvnFiles(): List<File> {
        return SvnClient.getFilesByRevision()
    }


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