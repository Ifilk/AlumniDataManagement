package xyz.ifilk.com.example.labmembermanagersystem.config

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.MDC
import org.springframework.lang.Nullable
import org.springframework.web.servlet.HandlerInterceptor
import java.util.*


/**
 * @Author: JCccc
 * @Date: 2022-5-30 10:45
 * @Description:
 */
class LogInterceptor : HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        MDC.put(TRACE_ID, UUID.randomUUID().toString().replace("-", ""))
        return true
    }

    override fun afterCompletion(
        request: HttpServletRequest, response: HttpServletResponse, handler: Any,
        @Nullable ex: Exception?
    ) {
        MDC.remove(TRACE_ID)
    }

    companion object {
        private const val TRACE_ID = "TRACE_ID"
    }
}