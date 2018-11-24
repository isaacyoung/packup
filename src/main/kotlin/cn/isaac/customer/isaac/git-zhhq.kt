package cn.isaac.customer.isaac

import cn.isaac.config.config
import cn.isaac.config.configBuilder
import cn.isaac.manage.PackUpManager
import cn.isaac.tools.Diff

/**
 *
 * @author yzh
 * @date 2018/5/30
 */

fun main(args: Array<String>) {
    config = configBuilder {

        // 项目路径
        projectPath = "E:/git/zhhq"

        // 编译环境 eclipse or idea
        // eclipse 编译后的文件路径须在/WebRoot/WEB-INF/classes
        ide = "idea"

        // 主项目 ide=idea有效
        mainProject = "LIANLIAN_WXSCHOOL_LOGISTICS"

        // 输出路径
        targetPath = "F:/output"

        // 版本 none git svn
        versionControl = "git"
        beginGitNum = "7bf0994b8fdc773c8eed681518772e4ed6fc6516"
        endGitNum = "b541085920287aed40dca7caf0a97dfbd776db85"

        isWarPackage = false

    }

    PackUpManager.run()


    fun diff() {
        val project = "LIANLIAN_MNG_LOGISTICS"
        val oldFile = "F:\\LIANLIAN_DAYLY\\生产环境\\升级申请\\升级文件列表\\20180709身份认证中心数据同步\\$project.war.zip"
        val newFile = "F:\\output\\$project.war.zip"

        val diff = Diff(oldFile,newFile)
        diff.run()
    }
//    diff()
}