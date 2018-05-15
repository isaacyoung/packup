package cn.isaac.customer.isaac

import cn.isaac.config.config
import cn.isaac.config.configBuilder
import cn.isaac.manage.PackUpManager
import cn.isaac.tools.Diff

fun main(args: Array<String>) {
    config = configBuilder {

        // 项目路径
        projectPath = "E:/yunma/qiye"

        // 编译环境 eclipse or idea
        // eclipse 编译后的文件路径须在/WebRoot/WEB-INF/classes
        ide = "idea"

        // 主项目 ide=idea有效
        mainProject = "LIANLIAN_WXQY"

        // 输出路径
        targetPath = "F:/output"

        // 是否根据svn版本号获取。false：根据文件修改时间获取
        isFromSvn = true

        // 获取修改时间之后的变动文件
        fromDate = "2018-04-12 12:00:00"

        // svn账号
        svnUser = "yangzh"
        svnPassword = "123456"

        // common
        svn("http://192.168.72.13/svn/logistics/source/trunk/LIANLIAN_LOGISTICS_COMMON/") {
//            between(1139,1161)
        }

        // static
        svn("http://192.168.72.13/svn/logistics/source/trunk/LIANLIAN_STATIC/") {
            add(1516)
        }

        svn("http://192.168.72.13/svn/LIANLIAN_WXSCHOOL/trunk/") {
            add(4742)
            between(4743,4746)
        }

    }

    PackUpManager.run()


    fun diff() {
        val project = "LIANLIAN_STATIC"
        val oldFile = "E:\\work\\LIANLIAN_DAYLY\\生产环境\\升级申请\\升级文件列表\\企业微信后台二级宫格\\$project.war.zip"
        val newFile = "E:\\output\\$project.war.zip"

        val diff = Diff(oldFile,newFile)
        diff.run()
    }

//    diff()

}

