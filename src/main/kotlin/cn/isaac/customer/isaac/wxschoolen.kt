package cn.isaac.customer.isaac

import cn.isaac.config.config
import cn.isaac.config.configBuilder
import cn.isaac.manage.PackUpManager
import cn.isaac.tools.Diff

fun main(args: Array<String>) {
    config = configBuilder {

        // 项目路径
        projectPath = "E:/yunma/wxschoolen"

        // 编译环境 eclipse or idea
        // eclipse 编译后的文件路径须在/WebRoot/WEB-INF/classes
        ide = "idea"

        // 主项目 ide=idea有效
        mainProject = "LIANLIAN_WXSCHOOL_EN"

        // 输出路径
        targetPath = "F:/output"

        // 是否根据svn版本号获取。false：根据文件修改时间获取
        versionControl = "none"

        // 获取修改时间之后的变动文件
        fromDate = "2018-09-14 18:06:00"

        // svn账号
        svnUser = "yangzh"
        svnPassword = "123456"

        svn("http://192.168.72.13/svn/LSMART/LIANLIAN_ZHIHUI/trunck/source/") {
//            add(38645)
        }

        svn("http://192.168.72.13/svn/LIANLIAN_WXSCHOOL/trunk/") {
            add(4765)
        }

    }

    PackUpManager.run()


    fun diff() {
        val project = "LIANLIAN_WXSCHOOL"
        val oldFile = "F:\\LIANLIAN_DAYLY\\生产环境\\升级申请\\升级文件列表\\20180427统一支付收银台\\$project.war.zip"
        val newFile = "F:\\output\\$project.war.zip"

        val diff = Diff(oldFile,newFile)
        diff.run()
    }
//    diff()


}

