package cn.isaac.config

/**
 * 配置文件
 * create by isaac at 2017/12/23 11:21
 */

val config = configBuilder {

    // 项目路径
    projectPath = "E:\\zhhq"

    // eclipse or idea
    ide = "idea"

    // 主项目
    mainProject = "LIANLIAN_WXSCHOOL_LOGISTICS"

    // 输出路径
    targetPath = "E:\\output"

    // 是否根据svn版本号获取。false：根据文件修改时间获取
    isFromSvn = false

    // 获取修改时间之后的变动文件
    fromDate = "2018-01-11 16:30:00"

    // svn账号
    svnUser = "yangzh"
    svnPassword = "123456"


    // svn 版本号


    svn("http://192.168.1.13/svn/LSMART/LIANLIAN_ZHIHUI/trunck/source/LIANLIAN_CARD_SERVICE/") {
        add(36686)
        add(36736)
        add(36737)
        add(36741)
        add(36806)
        add(36810)
        add(36815)
        add(36816)
        add(36817)
    }


}
