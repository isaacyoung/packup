package cn.isaac.customer.isaac

import cn.isaac.config.config
import cn.isaac.config.configBuilder
import cn.isaac.manage.PackUpManager
import cn.isaac.tools.Diff

fun main(args: Array<String>) {
    config = configBuilder {

        // 项目路径
        projectPath = "E:/wxqy"

        // 编译环境 eclipse or idea
        // eclipse 编译后的文件路径须在/WebRoot/WEB-INF/classes
        ide = "idea"

        // 主项目 ide=idea有效
        mainProject = "LIANLIAN_WXQY"

        // 输出路径
        targetPath = "E:/output"

        // 是否根据svn版本号获取。false：根据文件修改时间获取
        isFromSvn = true

        // 获取修改时间之后的变动文件
        fromDate = "2018-01-11 16:30:00"

        // svn账号
        svnUser = "yangzh"
        svnPassword = "123456"

        // common
        svn("http://192.168.1.13/svn/logistics/source/trunk/LIANLIAN_LOGISTICS_COMMON/") {
            between(151,882)
        }

        svn("http://192.168.1.13/svn/LIANLIAN_WXSCHOOL/trunk/") {
            between(4618,4654)
        }

    }

    PackUpManager.run()

//    diff()

}

fun diff() {
    val project = "LIANLIAN_WXQY_SERVICE"
    val oldFile = "E:\\work\\LIANLIAN_DAYLY\\生产环境\\升级申请\\升级文件列表\\20180129萧山机场后勤服务\\$project.war.zip"
    val newFile = "E:\\output\\$project.war.zip"

    val diff = Diff(oldFile,newFile)
    diff.run()
}