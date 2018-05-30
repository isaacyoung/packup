package cn.isaac.config

/**
 * 配置文件
 * create by isaac at 2017/12/23 11:21
 */

var config = configBuilder {

    // 项目路径
//    projectPath = "E:/workspace"
//    projectPath = "E:/zhhq"
    projectPath = "E:/wxqy"

    // 编译环境 eclipse or idea
    // eclipse 编译后的文件路径须在/WebRoot/WEB-INF/classes
    ide = "idea"

    // 主项目 ide=idea有效
//    mainProject = "LIANLIAN_MNG"
//    mainProject = "LIANLIAN_WXSCHOOL_LOGISTICS"
//    mainProject = "LIANLIAN_MNG_LOGISTICS"
//    mainProject = "LIANLIAN_CARD_MNG"
    mainProject = "LIANLIAN_WXQY"

    // 输出路径
    targetPath = "E:/output"

    // 是否根据svn版本号获取。false：根据文件修改时间获取
    versionControl = "svn"

    // 获取修改时间之后的变动文件
    fromDate = "2018-01-11 16:30:00"

    // svn账号
    svnUser = "yangzh"
    svnPassword = "123456"


    // svn 版本号

//    svn("http://192.168.1.13/svn/LSMART/LIANLIAN_ZHIHUI/trunck/source/") {
//        add(36682)
//        add(36683)
//        add(36684)
//        add(36686)
//        add(36687)
//        add(36705)
//        between(36736,36741)
//        add(36751)
//        add(36752)
//        add(36773)
//        add(36806)
//        add(36810)
//        between(36813,36820)
//        between(36822,36826)
//        add(36831)
//        add(36835)
//        add(36864)
//        add(36872)
//        add(36873)
//        add(36882)
//        add(36887)
//        add(36888)
//        add(36901)
//        add(36921)
//        add(36922)
//        add(36929)
//        add(36931)
//        add(36937)
//        add(36938)
//        between(36940,36942)
//        add(36947)
//        add(36980)
//        add(36981)
//        add(36985)
//        add(36986)
//        add(36990)
//        add(36991)
//        add(36996)
//        add(37006)
//        add(37008)
//        add(37041)
//    }
//
//    svn("http://192.168.1.13/svn/logistics/source/trunk/LIANLIAN_LOGISTICS_COMMON/") {
//        between(181,183)
//        add(218)
//        add(228)
//        add(232)
//        add(233)
//        add(262)
//        add(263)
//        add(264)
//        add(270)
//        add(294)
//        add(316)
//        add(321)
//        add(323)
//        add(324)
//        add(326)
//        add(350)
//        add(406)
//        between(412,445)
//        add(449)
//        add(503)
//        between(515,516)
//        add(542)
//        add(546)
//        add(564)
//        add(592)
//        add(611)
//        add(622)
//        add(651)
//        add(653)
//        add(671)
//    }

    // new wxschool
//    svn("http://192.168.1.13/svn/logistics/source/trunk/LIANLIAN_STATIC/") {
//        add(151)
//        between(153,154)
//        between(157,160)
//        between(171,909)
//    }

    // card mng
//    svn("http://192.168.1.13/svn/LSMART/LIANLIAN_ZHIHUI/trunck/source/") {
//        add(36887)
//    }

    // qiye
    svn("http://192.168.1.13/svn/logistics/source/trunk/LIANLIAN_LOGISTICS_COMMON/") {
        between(151,882)
    }
    svn("http://192.168.1.13/svn/LIANLIAN_WXSCHOOL/trunk/") {
        between(4618,4654)
    }

}
