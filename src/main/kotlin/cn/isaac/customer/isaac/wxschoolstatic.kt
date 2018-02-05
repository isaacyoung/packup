package cn.isaac.customer.isaac

import cn.isaac.config.config
import cn.isaac.config.configBuilder
import cn.isaac.manage.PackUpManager
import cn.isaac.tools.Diff

fun main(args: Array<String>) {
    config = configBuilder {

        // 项目路径
        projectPath = "E:/zhhq"

        // 编译环境 eclipse or idea
        // eclipse 编译后的文件路径须在/WebRoot/WEB-INF/classes
        ide = "idea"

        // 主项目 ide=idea有效
        mainProject = "LIANLIAN_STATIC"

        // 输出路径
        targetPath = "E:/output"

        // 是否根据svn版本号获取。false：根据文件修改时间获取
        isFromSvn = true

        // 获取修改时间之后的变动文件
        fromDate = "2018-01-11 16:30:00"

        // svn账号
        svnUser = "yangzh"
        svnPassword = "123456"

        svn("http://192.168.1.13/svn/logistics/source/trunk/LIANLIAN_STATIC/") {
            add(151)
            between(153,154)
            between(157,160)
            between(171,968)
        }

    }

//    PackUpManager.run()


    fun diff() {
        val project = "LIANLIAN_STATIC"
        val oldFile = "E:\\work\\LIANLIAN_DAYLY\\生产环境\\升级申请\\升级文件列表\\智慧后勤\\$project.war.zip"
        val newFile = "E:\\output\\$project.war.zip"

        val diff = Diff(oldFile,newFile)
        diff.run()
    }
    diff()


}

