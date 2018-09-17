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
        beginGitNum = "70bb477e46c9072c038230328a65810b46719121"
        endGitNum = "1fe5ab8fdee8a409fe228d234b103e15a997cb7d"

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
        val project = "LIANLIAN_MNG_LOGISTICS"
        val oldFile = "F:\\LIANLIAN_DAYLY\\生产环境\\升级申请\\升级文件列表\\20180709身份认证中心数据同步\\$project.war.zip"
        val newFile = "F:\\output\\$project.war.zip"

        val diff = Diff(oldFile,newFile)
        diff.run()
    }
//    diff()
}