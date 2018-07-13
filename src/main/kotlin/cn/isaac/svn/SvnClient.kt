package cn.isaac.svn

import cn.isaac.config.config
import cn.isaac.context.substringSvnPath
import org.tmatesoft.svn.core.SVNURL
import org.tmatesoft.svn.core.wc.SVNClientManager
import org.tmatesoft.svn.core.wc.SVNRevision
import org.tmatesoft.svn.core.wc.SVNWCUtil

/**
 * svn客户端
 * create by isaac at 2017/12/23 8:55
 */
object SvnClient {

    fun getFilesByRevision(): List<String> {
        val result = arrayListOf<String>()
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
                        var path = u.toString()
                        if (path.endsWith(")")) {
                            path = path.substring(0,path.indexOf("("))
                        }
                        result.add((config.projectPath + substringSvnPath(path)).replace("\\","/"))
                    }

                }
            }
        }
        return result
    }

}