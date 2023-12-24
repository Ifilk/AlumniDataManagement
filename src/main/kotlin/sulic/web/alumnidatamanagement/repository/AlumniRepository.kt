package sulic.web.alumnidatamanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository
import sulic.web.alumnidatamanagement.entity.Alumni

interface AlumniRepository : JpaRepository<Alumni, Long> {


    fun findByNumber(number: String): Alumni
}