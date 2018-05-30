package cn.isaac.git

import cn.isaac.config.config
import cn.isaac.context.substringSvnPath
import org.eclipse.jgit.api.Git
import org.eclipse.jgit.dircache.DirCache
import org.eclipse.jgit.dircache.DirCacheIterator
import java.io.File
import org.eclipse.jgit.storage.file.FileRepositoryBuilder
import org.eclipse.jgit.treewalk.CanonicalTreeParser


/**
 * git
 * @author yzh
 * @date 2018/5/30
 */
object GitClient {
    fun getFilesByRevision(): List<String> {
        val result = arrayListOf<String>()
        val existingRepo = FileRepositoryBuilder().findGitDir(File(config.projectPath)).build()
        val git = Git(existingRepo)

        val read = existingRepo.newObjectReader()

        val oldTreeIter = CanonicalTreeParser()
        oldTreeIter.reset(read,existingRepo.resolve("${config.beginGitNum}^{tree}"))

        val newTreeIter = CanonicalTreeParser()
        newTreeIter.reset(read,existingRepo.resolve("${config.endGitNum}^{tree}"))

        val log = git.diff().setOldTree(oldTreeIter).setNewTree(newTreeIter).call()
        log.forEach {
            result.add(config.projectPath + "\\"+ it.newPath)
        }

        git.close()
        return result
    }
}

fun main(args: Array<String>) {
    GitClient.getFilesByRevision()
}