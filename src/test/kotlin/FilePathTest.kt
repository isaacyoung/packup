import de.regnis.q.sequence.core.QSequenceAssert.assertTrue
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it

/**
 *
 * create by isaac at 2018/1/13 14:03
 */
object FilePathTest : Spek({
    it("project name") {
        val s = "E:\\zhhq\\LIANLIAN_WXSCHOOL_LOGISTICS\\out\\production"
        val r = "[/|\\\\]([LIANLIAN|JIEKA](\\w)+)[/|\\\\]".toRegex()
        val result = r.find(s)
        result?.let {
            val re = result.value.replace("/","").replace("\\","")
            println(re)
        }

    }
})