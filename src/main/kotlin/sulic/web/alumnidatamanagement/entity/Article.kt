package sulic.web.alumnidatamanagement.entity

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant

@Entity
@Table(name = "article")
@EntityListeners(value = [AuditingEntityListener::class])
@Schema
open class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    @CreatedDate
    @Column(name = "created_date")
    open var createdDate: Instant? = null

    @Lob
    @Column(name = "content")
    open var content: String? = null

    @Column(name = "title")
    open var title: String? = null

    @Column(name = "description")
    open var description: String? = null
}