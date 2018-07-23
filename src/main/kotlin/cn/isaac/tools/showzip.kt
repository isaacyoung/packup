package cn.isaac.tools

import org.apache.http.client.utils.DateUtils
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.zip.ZipFile

/**
 *
 * @author yzh
 * @date 2018/7/23
 */
object ShowZipFiles {
    @JvmStatic
    fun main(args: Array<String>) {
        val filePath = "F:\\LIANLIAN_DAYLY\\生产环境\\升级申请\\升级文件列表\\待测试\\20180710缴费大厅订单统计"
        File(filePath).walk().maxDepth(1).forEach {
            if (it.isFile && it.path.endsWith(".zip")) {
                ZipFile(it).stream().forEach {
                    if (!it.isDirectory) {
                        println("${it.name}  ${showDate(it.lastModifiedTime.toMillis())}")
                    }
                }
                println()
            }
        }
    }

    fun showDate(mill: Long): String {
        val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return df.format(Date(mill))
    }
}

