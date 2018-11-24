package cn.isaac.config

/**
 *
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
    lateinit var projectPath: String
    lateinit var mainProject: String
    lateinit var ide: String
    lateinit var targetPath: String
    lateinit var fromDate: String
    var versionControl = "git" // none git svn
    lateinit var beginGitNum: String
    lateinit var endGitNum: String
    lateinit var svnUser: String
    lateinit var svnPassword: String
    var isWarPackage = true

    val svnList = arrayListOf<Svn>()

    fun svn(url: String, init: Svn.() -> Unit): Svn {
        val svn = Svn()
        svn.url = url
        svn.init()
        svnList.add(svn)
        return svn
    }
}

class Svn : Tag("svn") {
    lateinit var url: String
    val revisionList = arrayListOf<Int>()

    fun add(value: Int) {
        revisionList.add(value)
    }

    fun between(begin: Int, end: Int) {
        revisionList += begin..end
    }

}
