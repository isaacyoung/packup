package cn.isaac.config

/**
 * 配置文件
 * create by isaac at 2017/12/23 11:21
 */

val config = configBuilder {

    // 项目路径
    projectPath = "E:/workspace"

    // eclipse or idea
    ide = "eclipse"

    // 主项目 ide=idea有效
    mainProject = "LIANLIAN_MNG"

    // 输出路径
    targetPath = "E:/output"

    // 是否根据svn版本号获取。false：根据文件修改时间获取
    isFromSvn = true

    // 获取修改时间之后的变动文件
    fromDate = "2018-01-11 16:30:00"

    // svn账号
    svnUser = "yangzh"
    svnPassword = "123456"


    // svn 版本号


    svn("http://192.168.1.13/svn/LSMART/LIANLIAN_ZHIHUI/trunck/source/") {
        add(36682)
        add(36683)
        add(36684)
        add(36686)
        add(36687)
        add(36705)
        add(36736)
        add(36737)
        add(36738)
        add(36739)
        add(36740)
        add(36741)
        add(36751)
        add(36752)
        add(36773)
        add(36806)
        add(36810)
        add(36813)
        add(36814)
        add(36815)
        add(36816)
        add(36817)
        add(36818)
        add(36819)
        add(36820)
        add(36822)
        add(36823)
        add(36824)
        add(36825)
        add(36826)
        add(36831)
        add(36835)
    }


}
