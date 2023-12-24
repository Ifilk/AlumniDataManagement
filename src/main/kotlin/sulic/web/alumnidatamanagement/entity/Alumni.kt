package sulic.web.alumnidatamanagement.entity

import jakarta.persistence.*
import sulic.web.alumnidatamanagement.entity.common.College
import sulic.web.alumnidatamanagement.entity.common.Gender
import sulic.web.alumnidatamanagement.entity.common.Role

@Entity
@Table(name = "alumni")
open class Alumni {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    @Column(name = "number", unique = true, length = 63)
    open var number: String? = null

    @Column(name = "pwd_hash", unique = true)
    open var pwdHash: String? = null

    @Column(name = "salt", length = 15)
    open var salt: String? = null

    @Enumerated
    @Column(name = "gender")
    open var gender: Gender? = null

    @Column(name = "birthday")
    open var birthday: String? = null

    @Enumerated
    @Column(name = "college")
    open var college: College? = null

    @Column(name = "klass", length = 15)
    open var klass: String? = null

    @Column(name = "company")
    open var company: String? = null

    @Column(name = "phone", length = 31)
    open var phone: String? = null

    @Column(name = "email", length = 63)
    open var email: String? = null

    @Column(name = "major", length = 31)
    open var major: String? = null

    @Enumerated
    @Column(name = "role")
    open var role: Role? = null

    @Column(name = "name", length = 31)
    open var name: String? = null
}