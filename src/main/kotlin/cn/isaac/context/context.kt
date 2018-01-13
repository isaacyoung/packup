package cn.isaac.context

import cn.isaac.config.config
import java.io.File

/**
 *
 * create by isaac at 2018/1/13 10:29
 */

class ContextBuild {

    // 变动项目
    val changedProjectsList = hashSetOf<String>()
    // 变动文件
    val changedFilesList = hashSetOf<String>()
    // 复制文件
    val packedFilesList = hashSetOf<String>()

    /**
     * 获取变动文件
     */
    fun getAllChangedFiles() {
        changedFilesList.addAll(getChangedFiles())
    }

    /**
     * 复制变动文件
     */
    fun copyAllChangedFiles() {
        when (config.ide) {
            "idea" -> IdeaCompiler().copyFiles(changedFilesList.toList())
            "eclipse" -> EclipseCompiler().copyFiles(changedFilesList.toList())
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

}

val context = ContextBuild()