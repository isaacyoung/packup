package cn.isaac.manage

import cn.isaac.config.config
import cn.isaac.context.context
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
                if (!context.packedFilesList.contains(it)) {
                    file.appendText("$it\r\n")
                }
            }
}