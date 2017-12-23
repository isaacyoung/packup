import org.tmatesoft.svn.core.SVNURL
import org.tmatesoft.svn.core.wc.SVNClientManager
import org.tmatesoft.svn.core.wc.SVNRevision
import org.tmatesoft.svn.core.wc.SVNWCUtil
import java.io.File

/**
 * svn客户端
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
                        if (path.endsWith(")")) {
                            return@forEach
                        }
                        result.add(File(config.projectPath + path.substring(path.lastIndexOf("/LIANLIAN_"))))
                    }

                }
            }
        }
        return result
    }

}