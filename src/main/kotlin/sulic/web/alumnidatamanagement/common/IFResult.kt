package sulic.web.alumnidatamanagement.common

import io.swagger.v3.oas.annotations.media.Schema
import java.io.Serializable

typealias EMPTY = IFResult<Void>
typealias MESSAGE = IFResult<Void>
typealias STRING = IFResult<Void>

class IFResult<T>(
    @field:Schema(description = "响应码")
    val code: Int,
    @field:Schema(description = "信息")
    val msg: String?,
    @field:Schema(description = "数据")
    val data: T?) : Serializable {
    companion object{
        private const val serialVersionUID = 1L
        private const val CODE_SUCCESS = 200
        private const val CODE_ERROR = 500
        fun ok(): IFResult<Void>{
            return IFResult(CODE_SUCCESS, "ok", null)
        }

        fun ok(msg: String?): IFResult<Void>{
            return IFResult(CODE_SUCCESS, msg, null)
        }

        fun <T> data(data: T?): IFResult<T>{
            return IFResult(CODE_SUCCESS, "ok", data)
        }

        fun <T> data(data: T?, msg: String?): IFResult<T>{
            return IFResult(CODE_SUCCESS, msg, data)
        }

        fun error(msg: String?): IFResult<Void>{
            return IFResult(CODE_ERROR, msg, null)
        }
    }
}