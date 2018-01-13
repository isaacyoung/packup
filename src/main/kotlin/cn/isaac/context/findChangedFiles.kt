package cn.isaac.context

import cn.isaac.config.config
import cn.isaac.svn.SvnClient
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

/**
 *
 * create by isaac at 2018/1/13 12:00
 */

val sf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

/**
 * 获取变动文件
 * get modified files
 */
fun getChangedFiles(): List<String> {
    return when {
        config.isFromSvn -> getSvnFiles()
        else -> getModifiedFiles()
    }
}

/**
 * 根据修改日期获取
 */
fun getModifiedFiles() = File(config.projectPath).walk().maxDepth(Int.MAX_VALUE)
        .filter { notContainPath(it.path) }
        .filter { containPath(it.path) }
        .filter { it.isFile }
        .filter { it.lastModified() >= getDate(config.fromDate) }
        .map { it.path }
        .toList()

/**
 * 根据svn版本号获取
 */
fun getSvnFiles(): List<String> {
    return SvnClient.getFilesByRevision()
}

/**
 * 不包含目录
 */
fun notContainPath(path: String): Boolean {
    return !path.contains(".metadata")
            && !path.contains(".svn")
            && !path.contains("\\classes\\")
}

/**
 * 包含目录
 */
fun containPath(path: String): Boolean {
    return path.contains("\\src\\")
            || path.contains("\\WebRoot\\")
}

/**
 * 获取svn有效路径
 */
fun substringSvnPath(path: String): String {
    return when {
        path.contains("/LIANLIAN") -> path.substring(path.lastIndexOf("/LIANLIAN_"))
        else -> path.substring(path.lastIndexOf("/JIEKA_"))
    }
}

fun getDate(date: String): Long {
    val c = Calendar.getInstance()
    c.time = sf.parse(date)
    return c.timeInMillis
}