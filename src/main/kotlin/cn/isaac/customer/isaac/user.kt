package cn.isaac.customer.isaac

import cn.isaac.config.config
import cn.isaac.config.configBuilder
import cn.isaac.manage.PackUpManager
import cn.isaac.tools.Diff

fun main(args: Array<String>) {
    config = configBuilder {

        // 项目路径
        projectPath = "E:/yunma/user"

        // 编译环境 eclipse or idea
        // eclipse 编译后的文件路径须在/WebRoot/WEB-INF/classes
        ide = "idea"

        // 主项目 ide=idea有效
        mainProject = "LIANLIAN_USER"

        // 输出路径
        targetPath = "F:/output"

        // 是否根据svn版本号获取。false：根据文件修改时间获取
        versionControl = "svn"

        // 获取修改时间之后的变动文件
        fromDate = "2018-06-06 10:00:00"

        // svn账号
        svnUser = "yangzh"
        svnPassword = "123456"

        svn("http://192.168.72.13/svn/LSMART/LIANLIAN_ZHIHUI/trunck/source/") {
            add(38901)
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

