package sulic.web.alumnidatamanagement.controller
import cn.dev33.satoken.util.SaResult
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import java.util.stream.Collectors

@ControllerAdvice
@ResponseBody
class ExceptionController {
    private var log: Logger = LoggerFactory.getLogger(ExceptionController::class.java)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): SaResult{
        log.warn("Exception: ", e)
        return SaResult.error(e.allErrors.stream().map { it.defaultMessage }.collect(Collectors.joining(";")))
    }
    @ExceptionHandler(Exception::class)
    fun handleException(request: HttpServletRequest?, e: Exception): SaResult {
        log.warn("Exception: ", e)
        return SaResult.error(e.message)
    }
}
