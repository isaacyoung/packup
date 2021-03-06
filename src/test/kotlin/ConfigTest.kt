
import cn.isaac.config.config
import de.regnis.q.sequence.core.QSequenceAssert.*
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it

/**
 *
 * create by isaac at 2017/12/23 14:18
 */
object ConfigTest : Spek({
    it("values") {
        assertTrue(config.mainProject == "LIANLIAN_MNG")
        config.svnList.forEach {
            println(it.url)
            it.revisionList.forEach {println(it)}
        }
    }

    it("between") {
        val revisionList = arrayListOf<Int>()
        fun between(begin: Int, end: Int) {
            revisionList += begin..end
        }
        between(1,5)
        revisionList.forEach { print(it) }
    }
})