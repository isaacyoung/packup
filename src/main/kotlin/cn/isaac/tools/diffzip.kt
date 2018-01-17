package cn.isaac.tools

import java.io.File
import java.util.zip.ZipFile

/**
 * zip包对比
 * create by isaac at 2018/1/17 10:18
 */

class Diff(oldFile: String, newFile: String) {
    private val newZipFiles = hashSetOf<String>()
    private val oldZipFiles = hashSetOf<String>()
    private val newDiffFiles = hashSetOf<String>()
    private val oldDiffFiles = hashSetOf<String>()
    private var oldZip: ZipFile = ZipFile(File(oldFile))
    private var newZip: ZipFile = ZipFile(File(newFile))

    init {
        newZip.stream().forEach {
            newZipFiles.add(it.name)
        }

        oldZip.stream().forEach {
            oldZipFiles.add(it.name)
        }
    }

    fun run() {

        newZipFiles.forEach {
            if (!oldZipFiles.contains(it)) {
                newDiffFiles.add(it)
            }
        }

        oldZipFiles.forEach {
            if (!newZipFiles.contains(it)) {
                oldDiffFiles.add(it)
            }
        }

        if (newDiffFiles.size > 0) {
            println("----------------- 新增 ----------------------")
            newDiffFiles.sorted().forEach { println(it) }
        }
        if (oldDiffFiles.size > 0) {
            println("----------------- 减少 ----------------------")
            oldDiffFiles.sorted().forEach { println(it) }
        }
    }
}

fun main(args: Array<String>) {
//    val project = "LIANLIAN_CARD_SERVICE"
    val project = "LIANLIAN_CARD_SERVICE"
    val oldFile = "E:\\work\\LIANLIAN_DAYLY\\生产环境\\升级申请\\升级文件列表\\智慧后勤\\$project.war.zip"
    val newFile = "E:\\output\\$project.war.zip"

    val diff = Diff(oldFile,newFile)
    diff.run()

}