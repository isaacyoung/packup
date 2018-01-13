import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it
import java.io.File

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

    it("last folder") {
        val s = "E:\\output"
        println(File(s).name)
    }

    it("pack up zip") {
        val rt = Runtime.getRuntime()
        rt.exec("cmd /C cd /D E:\\output && jar cfM LIANLIAN_WXSCHOOL_LOGISTICS_SERVICE.war.zip LIANLIAN_WXSCHOOL_LOGISTICS_SERVICE.war/")
    }
})