import java.io.File

/**
 *
 * create by isaac at 2017/12/22 17:51
 */
object ServicePathUtils {

    fun getSuperInterface(path: String): String {
        // TODO
        var superPath = Main.projectPath + "\\LIANLIAN_COMMON\\src\\"
        when {
            path.contains("com\\weixin\\street\\service\\impl") -> superPath += "com\\jieka\\xiaofubao\\service\\wx\\street\\"
        }

        var name = "I" + File(path).name
        name = name.replace("Impl","")

        return superPath + name
    }

    fun getServiceImpl(path: String): String {
        // TODO
        var implPath = Main.projectPath
        when {
            path.contains("com\\jieka\\xiaofubao\\service\\wx\\street") -> implPath += "\\LIANLIAN_PARKSTREET_SERVICE\\src\\com\\weixin\\street\\service\\impl\\"
            path.contains("com\\jieka\\xiaofubao\\service\\wx\\school\\fleamarket") -> implPath += "\\LIANLIAN_WXSCHOOL_SERVICE\\src\\com\\jieka\\xiaofubao\\service\\wx\\fleamarket\\impl\\"
            path.contains("com\\jieka\\xiaofubao\\service\\wx\\school\\payment") -> implPath += "\\LIANLIAN_WXSCHOOL_SERVICE\\src\\com\\jieka\\xiaofubao\\service\\wx\\payment\\impl\\"
            path.contains("com\\jieka\\xiaofubao\\service\\wx\\school\\raider") -> implPath += "\\LIANLIAN_WXSCHOOL_SERVICE\\src\\com\\jieka\\xiaofubao\\service\\wx\\raider\\impl\\"
            path.contains("com\\jieka\\xiaofubao\\service\\wx\\school\\salary") -> implPath += "\\LIANLIAN_WXSCHOOL_SERVICE\\src\\com\\jieka\\xiaofubao\\service\\wx\\salary\\impl\\"
            path.contains("com\\jieka\\xiaofubao\\service\\wx\\school") -> implPath += "\\LIANLIAN_WXSCHOOL_SERVICE\\src\\com\\jieka\\xiaofubao\\service\\wx\\impl\\"
            path.contains("com\\jieka\\xiaofubao\\service\\wx\\qy") -> implPath += ""
            path.contains("com\\jieka\\xiaofubao\\service\\wx\\grids") -> implPath += ""
            path.contains("com\\jieka\\xiaofubao\\service\\tuoji") -> implPath += ""
            path.contains("com\\jieka\\xiaofubao\\service\\order") -> implPath += ""
            path.contains("com\\jieka\\xiaofubao\\service\\microshop") -> implPath += ""
            path.contains("com\\jieka\\xiaofubao\\service\\credit") -> implPath += ""
            path.contains("com\\jieka\\unified\\service") -> implPath += ""

            path.contains("com\\lianlian\\service\\active") -> implPath += ""
            path.contains("com\\lianlian\\service\\app") -> implPath += ""
            path.contains("com\\lianlian\\service\\b2b") -> implPath += ""
            path.contains("com\\lianlian\\service\\bussiness") -> implPath += ""
            path.contains("com\\lianlian\\service\\cardInterface") -> implPath += ""
            path.contains("com\\lianlian\\service\\card") -> implPath += ""
            path.contains("com\\lianlian\\service\\company") -> implPath += ""
            path.contains("com\\lianlian\\service\\edu") -> implPath += ""
            path.contains("com\\lianlian\\service\\mng") -> implPath += ""
            path.contains("com\\lianlian\\service\\msg") -> implPath += ""
            path.contains("com\\lianlian\\service\\pay") -> implPath += ""
            path.contains("com\\lianlian\\service\\shop") -> implPath += ""
            path.contains("com\\lianlian\\service\\spread") -> implPath += ""
            path.contains("com\\lianlian\\service\\user") -> implPath += ""
        }

        var name = File(path).name.substring(1)
        name = name.replace(".java","Impl.java")

        return implPath + name
    }

}