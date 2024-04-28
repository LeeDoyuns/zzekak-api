package l1a.jjakkak.infra.domain.user.repository

import l1a.jjakkak.core.domain.user.UserCommand
import l1a.jjakkak.core.domain.user.UserId
import l1a.jjakkak.core.domain.user.repository.UserCommandRepository
import l1a.jjakkak.infra.domain.user.dao.UserEntityDao
import l1a.jjakkak.infra.domain.user.entity.UserEntity
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
internal class UserCommandRepositoryImpl(
    val userEntityDao: UserEntityDao,
) : UserCommandRepository {
    @Transactional
    override fun save(user: UserCommand): UserCommand = userEntityDao.save(UserEntity.from(user)).toDomain()

    @Transactional
    override fun findById(userId: UserId): UserCommand? =
        userEntityDao.findUserByUserIdAndIsRemoved(userId.value, false)?.toDomain()
}
