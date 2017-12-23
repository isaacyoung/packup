/**
 * 配置文件
 * create by isaac at 2017/12/23 11:21
 */

val config = configBuilder {

    // 项目路径
    projectPath = "E:\\workspace"
    // 主项目
    mainProject = "LIANLIAN_MNG"
    // 主项目java文件编译路径
    classPath = "$projectPath\\$mainProject\\WebRoot\\WEB-INF\\classes\\"

    // 输出路径
    targetPath = "E:\\output"

    // 是否根据svn版本号获取。false：根据文件修改时间获取
    isFromSvn = true

    // 获取修改时间之后的变动文件
    fromDate = "2017-12-22 16:30:00"

    // svn账号
    svnUser = "yangzh"
    svnPassword = "123456"


    // svn 版本号

    svn("http://192.168.1.13/svn/LSMART/LIANLIAN_ZHIHUI/trunck/source/LIANLIAN_MNG/") {
        add(36439)
        add(36455)
    }

    svn("http://192.168.1.13/svn/LIANLIAN_WXSCHOOL/trunk/LIANLIAN_WXSCHOOL_SERVICE/") {
        add(4589)
    }

}
