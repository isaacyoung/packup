import java.io.File
import java.text.SimpleDateFormat
import java.util.*

/**
 * 主逻辑
 * create by isaac at 2017/12/23 16:59
 */
object PackUpManager {
    val sf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val changedProjectsList = hashSetOf<String>()
    val changedFilesList = hashSetOf<String>()
    val packedFilesList = hashSetOf<String>()


    // 入口
    fun run() {
        // 删除目标文件夹
        clearTargetPath()
        // 获取变动文件
        val files = getFiles()
        // 统计变动项目
        statProjects(files)
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
        changedFilesList.forEach { file.appendText("$it\r\n") }
        file.appendText("\r\n")

        file.appendText("打包文件：\r\n")
        packedFilesList.forEach { file.appendText("$it\r\n") }

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
                .filter { it.name != "output" }
                .forEach { it.renameTo(File("$it.war")) }

        File(targetPath).walk().maxDepth(1)
                .filter { it.isDirectory }
                .filter { it.name != "output" }
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
     * 统计变动项目
     */
    fun statProjects(files: List<File>) {
        files.forEach {
            val r = "LIANLIAN(\\w)+".toRegex()
            val result = r.find(it.path)
            result?.let { changedProjectsList.add(result.value)}
        }
    }

    /**
     * 复制变动文件
     */
    fun copyFiles(files: List<File>) {
        files.forEach {
            println(it.path)
            changedFilesList.add(it.path)
            if (it.isDirectory) {
                return@forEach
            }
            when {
                it.path.endsWith(".java") -> copyJavaFiles(it.path)
                it.path.endsWith(".jsp") -> copyJspFiles(it.path)
                it.path.contains("LIANLIAN_STATIC") -> copyStaticFiles(it.path)
                it.path.endsWith(".properties") -> null
                it.path.endsWith(".xml") -> copyXmlFiles(it.path)
                else -> it.copyTo(File(it.path.replace(config.projectPath, config.targetPath)), true)
            }
        }
    }

    /**
     * 复制xml文件
     */
    fun copyXmlFiles(path: String) {
        var targetFilePath = path.replace(config.projectPath, config.targetPath)
        targetFilePath = targetFilePath.replace("\\src\\", "\\WEB-INF\\classes\\")

        if (File(path).exists()) {
            File(path).copyTo(File(targetFilePath), true)
            packedFilesList.add(targetFilePath)
        } else {
            println("$path 未找到")
        }
    }

    /**
     * 复制静态文件
     */
    fun copyStaticFiles(path: String) {
        var targetFilePath = path.replace(config.projectPath, config.targetPath)
        targetFilePath = targetFilePath.replace("\\WebRoot", "")

        if (File(path).exists()) {
            File(path).copyTo(File(targetFilePath), true)
            packedFilesList.add(targetFilePath)
        } else {
            println("$path 未找到")
        }
    }

    /**
     * 复制jsp文件
     */
    fun copyJspFiles(path: String) {
        var targetFilePath = path.replace(config.projectPath, config.targetPath)
        targetFilePath = targetFilePath.replace("\\WebRoot", "")

        if (File(path).exists()) {
            File(path).copyTo(File(targetFilePath), true)
            packedFilesList.add(targetFilePath)
        } else {
            println("$path 未找到")
        }
    }

    /**
     * 复制java文件
     */
    fun copyJavaFiles(path: String) {
        if (!File(path).exists()) {
            println("$path 不存在")
            return
        }

        // _SERVICE
        if (path.contains("_SERVICE\\")) {
            var fromFilePath = path.replace("\\src\\", "\\WebRoot\\WEB-INF\\classes\\")
            fromFilePath = fromFilePath.replace(".java", ".class")
            var targetFilePath = fromFilePath.replace(config.projectPath, config.targetPath)
            targetFilePath = targetFilePath.replace("\\WebRoot", "")

            if (File(fromFilePath).exists()) {
                File(fromFilePath).copyTo(File(targetFilePath), true)
                packedFilesList.add(targetFilePath)
            } else {
                println("$fromFilePath 未找到")
            }

            return
        }

        // LIANLIAN_COMMON || LIANLIAN_UTILITY
        if (path.contains("LIANLIAN_COMMON\\") || path.contains("LIANLIAN_UTILITY")) {
            var fromFilePath = config.classPath + path.substring(path.indexOf("\\src\\") + 5)
            fromFilePath = fromFilePath.replace(".java", ".class")

            changedProjectsList
                    .filter { it != "LIANLIAN_UTILITY" }
                    .filter { it != "LIANLIAN_COMMON" }
                    .filter { it != "LIANLIAN_STATIC" }
                    .forEach {
                        var targetFilePath = fromFilePath.replace(config.projectPath, config.targetPath)
                        targetFilePath = targetFilePath.replace("\\WebRoot", "")
                        targetFilePath = targetFilePath.replace(config.mainProject, it)
                        File(fromFilePath).copyTo(File(targetFilePath), true)
                        packedFilesList.add(targetFilePath)
                    }

            return
        }

        var fromFilePath = config.classPath + path.substring(path.indexOf("\\src\\") + 5)
        fromFilePath = fromFilePath.replace(".java", ".class")
        var targetFilePath = fromFilePath.replace(config.projectPath, config.targetPath)
        targetFilePath = targetFilePath.replace("\\WebRoot", "")

        if (File(fromFilePath).exists()) {
            File(fromFilePath).copyTo(File(targetFilePath), true)
            packedFilesList.add(targetFilePath)
        } else {
            println("$fromFilePath 未找到")
        }
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
            .filter { !it.path.contains(".metadata") && !it.path.contains(".svn") && !it.path.contains("\\classes\\") }
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