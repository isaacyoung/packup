package cn.isaac.context

import cn.isaac.config.config
import java.io.File

/**
 * 根据idea编译器复制文件
 * create by isaac at 2018/1/13 12:01
 */
class IdeaCompiler {

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
                else -> if (File(it).exists() && File(it).isFile) File(it).copyTo(File(it.replace(config.projectPath, config.targetPath).replace("/WebRoot", "")), true)
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
        if (File(path).isDirectory) {
            return
        }
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

        val fromTargetPath = (config.projectPath + "/" + config.mainProject + "/out/production").replace("\\","/")
        var fromFilePath = fromTargetPath + "/" + getProjectName(path) + path.substring(path.indexOf("/src/") + 4)
        fromFilePath = fromFilePath.replace(".java", ".class")
        if (!File(fromFilePath).exists()) {
            println("$fromFilePath 未找到")
            return
        }

        val folderPath = fromFilePath.substring(0, fromFilePath.lastIndexOf("/")).replace("\\", "/");
        val fileName = fromFilePath.substring(fromFilePath.lastIndexOf("/")+1).replace(".class","")
        File(folderPath).walk().maxDepth(1)
                .filter { it.path.replace("\\","/").startsWith("$folderPath/$fileName") }
                .forEach {
                    val tempPath = it.path.replace("\\","/")
                    var targetFilePath = tempPath.replace(fromTargetPath, config.targetPath)
                    val projectName = getProjectName(targetFilePath)
                    targetFilePath = targetFilePath.replace(projectName, "$projectName/WEB-INF/classes")
                    File(tempPath).copyTo(File(targetFilePath), true)
                    context.packedFilesList.add(tempPath)
                }
    }

}