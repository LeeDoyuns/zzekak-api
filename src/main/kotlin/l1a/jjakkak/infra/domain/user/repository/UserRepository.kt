package l1a.jjakkak.infra.domain.user.repository

import l1a.jjakkak.core.domain.user.User
import l1a.jjakkak.core.domain.user.repository.UserRepository
import l1a.jjakkak.infra.domain.user.dao.UserEntityDao
import org.springframework.stereotype.Repository

@Repository
internal class UserRepositoryImpl(
   val userEntityDao: UserEntityDao
): UserRepository {
    override fun createUser(user: User): User {
        userEntityDao.save(user)
    }
}