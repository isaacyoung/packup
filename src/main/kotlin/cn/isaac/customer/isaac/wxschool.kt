package cn.isaac.customer.isaac

import cn.isaac.config.config
import cn.isaac.config.configBuilder
import cn.isaac.manage.PackUpManager
import cn.isaac.tools.Diff

fun main(args: Array<String>) {
    config = configBuilder {

        // 项目路径
        projectPath = "E:\\yunma\\zhhq"

        // 编译环境 eclipse or idea
        // eclipse 编译后的文件路径须在/WebRoot/WEB-INF/classes
        ide = "idea"

        // 主项目 ide=idea有效
        mainProject = "LIANLIAN_WXSCHOOL_LOGISTICS"

        // 输出路径
        targetPath = "F:/output"

        // 是否根据svn版本号获取。false：根据文件修改时间获取
        isFromSvn = true

        // 获取修改时间之后的变动文件
        fromDate = "2018-01-11 16:30:00"

        // svn账号
        svnUser = "yangzh"
        svnPassword = "123456"

        svn("http://192.168.72.13/svn/logistics/source/trunk/") {
            add(1418)
            add(1419)
            add(1424)
            add(1425)
            add(1427)
            add(1428)
            between(1450,1455)
            between(1471,1476)
        }

    }

    PackUpManager.run()


    fun diff() {
        val project = "LIANLIAN_MNG_LOGISTICS"
        val oldFile = "F:\\LIANLIAN_DAYLY\\生产环境\\升级申请\\升级文件列表\\20180427统一支付收银台\\$project.war.zip"
        val newFile = "F:\\output\\$project.war.zip"

        val diff = Diff(oldFile,newFile)
        diff.run()
    }
//    diff()


}

