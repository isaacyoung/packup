package cn.isaac.manage

import cn.isaac.config.config
import cn.isaac.context.context
import cn.isaac.context.getProjectName
import java.io.File

/**
 *
 * create by isaac at 2018/1/17 11:03
 */

/**
 * 打印变动文件
 */
fun printChangedFiles() {
    PackUpManager.createTargetPath()
    val file = File("${config.targetPath}/changed.txt")
    file.appendText("变动文件：\r\n")
    context.changedFilesList.sorted().forEach { file.appendText("$it\r\n") }
    file.appendText("\r\n")
}

/**
 * 输出打包的文件
 */
fun printCopiedFiles() {
    val file = File("${config.targetPath}/packed.txt")
    file.appendText("复制文件：\r\n")
    context.packedFilesList.sorted().forEach { file.appendText("$it\r\n") }
}

/**
 * 输出未打包的文件
 */
fun printUnCopiedFiles() {
    val file = File("${config.targetPath}/unpacked.txt")
    file.appendText("遗漏文件：\r\n")
    context.changedFilesList
            .filter { File(it).isFile }
            .filter { !it.endsWith(".properties") }
            .sorted()
            .forEach {
                when {
                    it.endsWith(".java") -> {
                        val fromTargetPath = config.projectPath + "/" + config.mainProject + "/out/production"
                        var fromFilePath = fromTargetPath + "/" + getProjectName(it) + it.substring(it.indexOf("/src/") + 4)
                        fromFilePath = fromFilePath.replace(".java", ".class")
                        if (!context.packedFilesList.contains(fromFilePath)) {
                            file.appendText("$it\r\n")
                        }
                    }
                    else -> {
                        if (!context.packedFilesList.contains(it)) {
                            file.appendText("$it\r\n")
                        }
                    }
                }
            }
}