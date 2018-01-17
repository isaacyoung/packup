package cn.isaac.config

/**
 * 配置文件
 * create by isaac at 2017/12/23 11:21
 */

val config = configBuilder {

    // 项目路径
    projectPath = "E:/workspace"
//    projectPath = "E:/zhhq"

    // 编译环境 eclipse or idea
    // eclipse 编译后的文件路径须在/WebRoot/WEB-INF/classes
    ide = "idea"

    // 主项目 ide=idea有效
    mainProject = "LIANLIAN_MNG"
//    mainProject = "LIANLIAN_WXSCHOOL_LOGISTICS"

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
        add(36864)
        add(36872)
        add(36873)
        add(36882)
        add(36887)
        add(36888)
        add(36901)
        add(36921)
        add(36922)
        add(36929)
        add(36931)
        add(36937)
        add(36938)
        add(36940)
        add(36941)
        add(36942)
        add(36947)
    }

    svn("http://192.168.1.13/svn/logistics/source/trunk/LIANLIAN_LOGISTICS_COMMON/") {
        add(181)
        add(182)
        add(183)
        add(218)
        add(228)
        add(232)
        add(233)
        add(262)
        add(263)
        add(264)
        add(270)
        add(294)
        add(316)
        add(321)
        add(323)
        add(324)
        add(326)
        add(350)
        add(406)
        add(412)
        add(413)
        add(414)
        add(445)
        add(449)
    }

    // new wxschool
//    svn("http://192.168.1.13/svn/logistics/source/trunk/") {
//        add(160)
//        add(202)
//        add(203)
//        add(204)
//        add(205)
//        add(206)
//        add(218)
//        add(220)
//        add(240)
//        add(252)
//        add(253)
//        add(265)
//        add(286)
//        add(293)
//        add(298)
//        add(311)
//        add(312)
//        add(313)
//        add(328)
//        add(337)
//        add(354)
//        add(358)
//        add(378)
//        add(388)
//        add(399)
//        add(405)
//        add(417)
//        add(418)
//        add(419)
//        add(420)
//        add(421)
//        add(422)
//        add(423)
//        add(424)
//        add(425)
//        add(426)
//        add(427)
//        add(428)
//        add(429)
//        add(430)
//        add(431)
//        add(432)
//        add(433)
//        add(434)
//        add(435)
//        add(436)
//        add(437)
//        add(438)
//        add(439)
//        add(440)
//        add(441)
//        add(442)
//        add(443)
//        add(444)
//        add(445)
//        add(446)
//        add(447)
//        add(448)
//        add(449)
//        add(450)
//        add(451)
//        add(452)
//        add(453)
//        add(454)
//        add(455)
//        add(456)
//        add(457)
//        add(458)
//    }

}
