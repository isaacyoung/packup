package cn.isaac.config

/**
 * 配置文件生成器
 * create by isaac at 2017/12/23 11:22
 */

@DslMarker
annotation class TagMarker

@TagMarker
abstract class Tag(val name: String)

fun configBuilder(init: Config.() -> Unit): Config {
    val config = Config()
    config.init()
    return config
}

class Config : Tag("cn.isaac.config.getConfig") {
    /**
     * 项目路径
     * e.g. "E:/git/zhhq"
     */
    lateinit var projectPath: String
    /**
     * 主项目 ide=idea有效
     * e.g. LIANLIAN_WXSCHOOL_LOGISTICS
     */
    lateinit var mainProject: String
    /**
     * 开发工具
     * eclipse|idea
     */
    lateinit var ide: String
    /**
     * 输出路径
     */
    lateinit var targetPath: String
    /**
     * 版本管理工具
     * none|git|svn
     */
    var versionControl = "git"

    /**
     * versionControl==none时，根据文件修改时间查找文件
     * e.g. "2018-09-10 16:30:00"
     */
    lateinit var fromDate: String

    /**
     * versionControl==git时，起始版本
     */
    lateinit var beginGitNum: String
    /**
     * versionControl==git时，终止版本
     */
    lateinit var endGitNum: String

    /**
     * versionControl==svn时，svn用户名
     */
    lateinit var svnUser: String
    /**
     * versionControl==svn时，svn密码
     */
    lateinit var svnPassword: String
    /**
     * versionControl==svn时，svn版本号集合
     */
    val svnList = arrayListOf<Svn>()

    /**
     * 收集svn版本号，可多个
     * e.g.
     * svn("http://192.168.72.13/svn/LSMART/LIANLIAN_ZHIHUI/trunck/source/") {
     *      add(39436)
     *      between(39462,39466)
     *  }
     */
    fun svn(url: String, init: Svn.() -> Unit): Svn {
        val svn = Svn()
        svn.url = url
        svn.init()
        svnList.add(svn)
        return svn
    }

    /**
     * 文件夹是否带 ".war"
     */
    var isWarPackage = true
}

class Svn : Tag("svn") {
    /**
     * svn url
     */
    lateinit var url: String
    /**
     * 版本集合
     */
    val revisionList = arrayListOf<Int>()

    /**
     * 添加单个版本号
     */
    fun add(value: Int) {
        revisionList.add(value)
    }

    /**
     * 添加区间版本号
     */
    fun between(begin: Int, end: Int) {
        revisionList += begin..end
    }

}
