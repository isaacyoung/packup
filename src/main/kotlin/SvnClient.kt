import org.tmatesoft.svn.core.SVNURL
import org.tmatesoft.svn.core.wc.SVNClientManager
import org.tmatesoft.svn.core.wc.SVNRevision
import org.tmatesoft.svn.core.wc.SVNWCUtil
import java.io.File

/**
 *
 * create by isaac at 2017/12/23 8:55
 */
object SvnClient {

    fun getFilesByRevision(): List<File> {
        val result = arrayListOf<File>()
        val options = SVNWCUtil.createDefaultOptions(true)
        val clientManager = SVNClientManager.newInstance(options, config.svnUser, config.svnPassword)
        config.svnList.forEach {
            val url = it.url
            it.revisionList.forEach {
                clientManager.logClient.doLog(
                        SVNURL.parseURIEncoded(url),
                        arrayOf("."),
                        SVNRevision.create(it.toLong()), SVNRevision.create(it.toLong()), SVNRevision.create(it.toLong()),
                        true, true, Long.MAX_VALUE
                ) {
                    it.changedPaths.forEach { _, u ->
                        val path = u.toString()
                        result.add(File(config.projectPath + path.substring(path.lastIndexOf("/LIANLIAN_"))))
                    }

                }
            }
        }
        return result
    }


    @JvmStatic
    fun main(args: Array<String>) {

        val options = SVNWCUtil.createDefaultOptions(true)
        val clientManager = SVNClientManager.newInstance(options, "yangzh", "123456")
        clientManager.logClient.doLog(
                SVNURL.parseURIEncoded("http://192.168.1.13/svn/LSMART/LIANLIAN_ZHIHUI/trunck/source/LIANLIAN_MNG/"),
                arrayOf("."),
                SVNRevision.create(36525), SVNRevision.create(36525), SVNRevision.create(36525), true, true, Long.MAX_VALUE
        ) {
            println(it.author)
            println(it.message)
            it.changedPaths.forEach { _, u -> println(u) }

        }

    }
}