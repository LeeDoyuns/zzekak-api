package l1a.jjakkak.infra.domain.user.dao

import l1a.jjakkak.core.domain.auth.AuthenticationId
import l1a.jjakkak.infra.domain.user.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

interface UserEntityDao {
    fun save(entity: UserEntity): UserEntity

    fun findUserByAuthenticationId(authenticationId: AuthenticationId): UserEntity?
}

@Repository
interface UserEntityJpaRepository: JpaRepository<UserEntity, UUID> {
    fun findByAuthenticationEntity_AuthenticationId(authenticationId: String): UserEntity?
}

@Repository
internal class UserEntityDaoImpl(
    val delegate: UserEntityJpaRepository
): UserEntityDao {
    override fun save(entity: UserEntity): UserEntity = delegate.save(entity)

    override fun findUserByAuthenticationId(authenticationId: AuthenticationId): UserEntity? =
        delegate.findByAuthenticationEntity_AuthenticationId(authenticationId.value)
}