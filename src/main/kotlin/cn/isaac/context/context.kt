package cn.isaac.context

import cn.isaac.config.config

/**
 *
 * create by isaac at 2018/1/13 10:29
 */

class ContextBuild {

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

}

/**
 * 从路径中获取项目名称
 */
fun getProjectName(path: String): String {
    val r = "[/|\\\\]([LIANLIAN|JIEKA](\\w)+)[/|\\\\]".toRegex()
    val result = r.find(path)
    var s = ""
    result?.let {
        s = result.value.replace("/","").replace("\\","")
    }
    return s
}

val context = ContextBuild()