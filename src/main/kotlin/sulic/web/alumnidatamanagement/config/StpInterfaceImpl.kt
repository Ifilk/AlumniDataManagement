package sulic.web.alumnidatamanagement.config

import cn.dev33.satoken.stp.StpInterface
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import sulic.web.alumnidatamanagement.repository.AlumniRepository

@Component
class StpInterfaceImpl @Autowired constructor(private val alumniRepository: AlumniRepository) : StpInterface {
    override fun getPermissionList(loginId: Any, loginType: String): List<String> {
        return ArrayList()
    }
    override fun getRoleList(loginId: Any, loginType: String): List<String> {
        val u = alumniRepository.findById(loginId.toString().toLong()).get()
        val list: MutableList<String> = ArrayList()
        u.role?.ordinal.let { list.add(if (it == 0) "User" else "Admin") }
        return list
    }
}

