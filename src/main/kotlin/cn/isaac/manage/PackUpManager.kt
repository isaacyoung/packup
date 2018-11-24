package cn.isaac.manage

import cn.isaac.config.config
import cn.isaac.context.context
import java.io.File

/**
 * 打包管理
 * create by isaac at 2017/12/23 16:59
 */
object PackUpManager {


    // 入口
    fun run() {
        // 删除目标文件夹
        clearTargetPath()

        // 获取变动文件
        context.getAllChangedFiles()

        // 复制变动文件
        context.copyAllChangedFiles()

        // 打包
        copyCommonFiles()
        packup()

        // 核对清单
        printChangedFiles()
        printCopiedFiles()
        printUnCopiedFiles()
    }

    /**
     * 项目依赖
     */
    fun copyCommonFiles() {
        if (context.packedFilesList.size == 0) {
            return
        }
        val fromFolder = hashSetOf<String>()
        val toFolder = hashSetOf<String>()
        File(config.targetPath).listFiles()
                .filter { it.isDirectory }
                .filter { it.name.endsWith("_COMMON") || it.name.endsWith("_UTILITY") }
                .forEach { fromFolder.add(it.name) }

        File(config.targetPath).listFiles()
                .filter { it.isDirectory }
                .filter { !it.name.endsWith("_COMMON") && !it.name.endsWith("_UTILITY") && !it.name.endsWith("_STATIC") }
                .forEach { toFolder.add(it.name) }

        fromFolder.forEach { f ->
            File("${config.targetPath}/$f").walk().maxDepth(Int.MAX_VALUE)
                    .filter { it.isFile }
                    .forEach { p ->
                        toFolder.forEach { t ->
                            p.copyTo(File(p.path.replace(f, t)), true)
                        }
                    }
        }
    }

    /**
     * 创建目标文件夹
     */
    fun createTargetPath() {
        val file = File("${config.targetPath}")
        if (!file.exists()) {
            file.mkdirs()
        }
    }


    /**
     * 打包
     * pack up zip
     */
    fun packup() {
        val root = File(config.targetPath).name
        val rt = Runtime.getRuntime()
        if (config.isWarPackage) {
            File(config.targetPath).walk().maxDepth(1)
                    .filter { it.isDirectory }
                    .filter { it.name != root }
                    .filter { !it.name.endsWith("_COMMON") && !it.name.endsWith("_UTILITY") }
                    .forEach { it.renameTo(File("$it.war")) }
        }

        File(config.targetPath).walk().maxDepth(1)
                .filter { it.isDirectory }
                .filter { it.name != root }
                .filter { !it.name.endsWith("_COMMON") && !it.name.endsWith("_UTILITY") }
                .forEach {
                    rt.exec("cmd /C cd /D ${config.targetPath} && jar cfM ${it.name}.zip ${it.name}/")
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

}