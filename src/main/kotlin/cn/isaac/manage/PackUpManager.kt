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

        // 复制变动文件
        context.getAllChangedFiles()

        // 复制变动文件
        context.copyAllChangedFiles()

//        // 打包
//        packup(config.targetPath)

        // 核对清单
        printChangedFiles()
        printCopiedFiles()
    }

    /**
     * 打印变动文件
     */
    fun printChangedFiles() {
        createTargetPath()
        val file = File("${config.targetPath}\\changed.txt")
        file.appendText("变动文件：\r\n")
        context.changedFilesList.sorted().forEach { file.appendText("$it\r\n") }
        file.appendText("\r\n")
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
     * 核对清单
     */
    fun printCopiedFiles() {
        val file = File("${config.targetPath}\\packed.txt")
        file.appendText("复制文件：\r\n")
        context.packedFilesList.sorted().forEach { file.appendText("$it\r\n") }
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

}