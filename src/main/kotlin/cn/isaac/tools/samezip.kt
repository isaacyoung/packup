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
    val project = "LIANLIAN_MNG_LOGISTICS"
    val oldFile = "F:\\LIANLIAN_DAYLY\\生产环境\\升级申请\\升级文件列表\\20180709身份认证中心数据同步\\$project.war.zip"
    val newFile = "F:\\LIANLIAN_DAYLY\\生产环境\\升级申请\\升级文件列表\\20180713缴费大厅优化\\$project.war.zip"

    val same = SameZip(oldFile,newFile)
    same.run()

}