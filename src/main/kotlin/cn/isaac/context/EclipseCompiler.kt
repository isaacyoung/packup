package cn.isaac.context

import cn.isaac.config.config
import java.io.File

/**
 * 根据eclipse编译器复制文件
 * create by isaac at 2018/1/13 13:41
 */
class EclipseCompiler {

    /**
     * 复制变动文件
     */
    fun copyFiles(files: List<String>) {
        files.forEach {
            println(it)
            when {
                it.endsWith(".java") -> copyJavaFiles(it)
                it.endsWith(".jsp") -> copyJspFiles(it)
                it.contains("LIANLIAN_STATIC") -> copyStaticFiles(it)
                it.endsWith(".properties") -> null
                it.endsWith(".xml") -> copyXmlFiles(it)
                else -> File(it).copyTo(File(it.replace(config.projectPath, config.targetPath)), true)
            }
        }
    }

    /**
     * 复制xml文件
     */
    fun copyXmlFiles(path: String) {
        var targetFilePath = path.replace(config.projectPath, config.targetPath)
        targetFilePath = targetFilePath.replace("/src/", "/WEB-INF/classes/")
        targetFilePath = targetFilePath.replace("/WebRoot", "")

        if (File(path).exists()) {
            File(path).copyTo(File(targetFilePath), true)
            context.packedFilesList.add(path)
        } else {
            println("$path 未找到")
        }
    }

    /**
     * 复制静态文件
     */
    fun copyStaticFiles(path: String) {
        var targetFilePath = path.replace(config.projectPath, config.targetPath)
        targetFilePath = targetFilePath.replace("/WebRoot", "")

        if (File(path).exists()) {
            File(path).copyTo(File(targetFilePath), true)
            context.packedFilesList.add(path)
        } else {
            println("$path 未找到")
        }
    }

    /**
     * 复制jsp文件
     */
    fun copyJspFiles(path: String) {
        var targetFilePath = path.replace(config.projectPath, config.targetPath)
        targetFilePath = targetFilePath.replace("/WebRoot", "")

        if (File(path).exists()) {
            File(path).copyTo(File(targetFilePath), true)
            context.packedFilesList.add(path)
        } else {
            println("$path 未找到")
        }
    }

    /**
     * 复制java文件
     */
    fun copyJavaFiles(path: String) {
        if (!File(path).exists()) {
            println("$path 不存在")
            return
        }

        val projectName = getProjectName(path)
        var fromFilePath = path.replace(projectName+"/src",projectName+"/WebRoot/WEB-INF/classes")
        fromFilePath = fromFilePath.replace(".java", ".class")
        var targetFilePath = fromFilePath.replace(config.projectPath, config.targetPath)
        targetFilePath = targetFilePath.replace("/WebRoot", "")

        if (File(fromFilePath).exists()) {
            File(fromFilePath).copyTo(File(targetFilePath), true)
            context.packedFilesList.add(path)
        } else {
            println("$fromFilePath 未找到")
        }
    }
}