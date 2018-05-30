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
        projectPath = "E:\\git\\zhhq"

        // 编译环境 eclipse or idea
        // eclipse 编译后的文件路径须在/WebRoot/WEB-INF/classes
        ide = "idea"

        // 主项目 ide=idea有效
        mainProject = "LIANLIAN_WXSCHOOL_LOGISTICS"

        // 输出路径
        targetPath = "F:/output"

        // 版本 none git svn
        versionControl = "git"
        beginGitNum = "48241ffc0957e7821ed6aafddba0fb42c530bbc5"
        endGitNum = "57d87e57baf6d1b45274ad4f29c4d73aa352c117"

        // 获取修改时间之后的变动文件
        fromDate = "2018-01-11 16:30:00"

        // svn账号
        svnUser = "yangzh"
        svnPassword = "123456"

        svn("http://192.168.72.13/svn/logistics/source/trunk/") {
            add(1613)
        }

    }

    PackUpManager.run()


    fun diff() {
        val project = "LIANLIAN_WXSCHOOL_LOGISTICS_SERVICE"
        val oldFile = "F:\\LIANLIAN_DAYLY\\生产环境\\升级申请\\升级文件列表\\20180420广告点击统计\\$project.war.zip"
        val newFile = "F:\\output\\$project.war.zip"

        val diff = Diff(oldFile,newFile)
        diff.run()
    }
//    diff()
}