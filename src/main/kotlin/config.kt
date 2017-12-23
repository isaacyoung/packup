/**
 *
 * create by isaac at 2017/12/23 11:21
 */

val config = configBuilder {
    projectPath = "E:\\workspace"
    mainProject = "LIANLIAN_MNG"
    classPath = "$projectPath\\$mainProject\\WebRoot\\WEB-INF\\classes\\"

    targetPath = "E:\\output"

    isFromSvn =  true

    fromDate = "2017-12-22 16:30:00"

    svnUser = "yangzh"
    svnPassword = "123456"

    svn("http://192.168.1.13/svn/LSMART/LIANLIAN_ZHIHUI/trunck/source/LIANLIAN_MNG/") {
        add(36525)
    }

}
