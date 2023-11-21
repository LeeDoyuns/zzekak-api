package l1a.jjakkak.infra.domain.auth.dao

import l1a.jjakkak.infra.domain.auth.entity.SocialAuthenticationEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

interface SocialAuthenticationDao {
    fun save(socialAuthenticationEntity: SocialAuthenticationEntity): SocialAuthenticationEntity
}

@Repository
interface SocialAuthenticationEntityJpaRepository : JpaRepository<SocialAuthenticationEntity, UUID>

@Repository
class SocialAuthenticationDaoImpl(
    val delegate: SocialAuthenticationEntityJpaRepository
) : SocialAuthenticationDao {
    override fun save(
        socialAuthenticationEntity: SocialAuthenticationEntity
    ): SocialAuthenticationEntity = delegate.save(socialAuthenticationEntity)
}
