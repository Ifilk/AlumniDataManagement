package sulic.web.alumnidatamanagement.common

import cn.hutool.core.util.RandomUtil
import cn.hutool.crypto.SecureUtil

object HashUtil {
    /**
     * 盐值
     */
    fun generateSalt(): String{
        return RandomUtil.randomString(15)
    }

    /**
     * SHA256 suffix
     */
    fun hash(sou: String, salt: String): String{
        return SecureUtil.sha256(sou + salt)
    }
}