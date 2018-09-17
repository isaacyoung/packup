package cn.isaac.tools

import java.io.File
import java.util.zip.ZipFile

/**
 *
 * @author yzh
 * @date 2018/7/18
 */

class SameZip(oldFile: String, newFile: String) {
    private val newZipFiles = hashSetOf<String>()
    private val oldZipFiles = hashSetOf<String>()
    private val sameFiles = hashSetOf<String>()

    init {
        File(oldFile).walk().maxDepth(1).filter { it.endsWith(".zip") }.forEach {
            ZipFile(it).stream().forEach {
                oldZipFiles.add(it.name)
            }
        }
        File(newFile).walk().maxDepth(1).filter { it.endsWith(".zip") }.forEach {
            ZipFile(it).stream().forEach {
                newZipFiles.add(it.name)
            }
        }

    }

    fun run() {
        newZipFiles.forEach {
            if (oldZipFiles.contains(it) && File(it).isFile) {
                sameFiles.add(it)
            }
        }

        if (sameFiles.size > 0) {
            println("----------------- 相同 ----------------------")
            sameFiles.sorted().forEach { println(it) }
        }
    }
}

fun main(args: Array<String>) {
    val oldFile = "F:\\LIANLIAN_DAYLY\\生产环境\\升级申请\\升级文件列表\\01-待测试\\20180728交易统计"
    val newFile = "F:\\LIANLIAN_DAYLY\\生产环境\\升级申请\\升级文件列表\\01-待测试\\20180802财务需求"

    val same = SameZip(oldFile,newFile)
    same.run()

}