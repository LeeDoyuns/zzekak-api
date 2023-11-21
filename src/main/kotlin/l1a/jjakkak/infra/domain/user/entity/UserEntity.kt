package l1a.jjakkak.infra.domain.user.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import l1a.jjakkak.infra.domain.auth.entity.SocialAuthenticationEntity
import l1a.jjakkak.infra.domain.auth.entity.SocialAuthenticationEntity.Companion.COLUMN_SOCIAL_AUTHENTICATION_ID
import l1a.jjakkak.infra.domain.user.entity.UserEntity.Companion.TABLE_USER
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = TABLE_USER)
class UserEntity(
    @Id @Column(name = COLUMN_USER_ID)
    val userId: UUID,

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = COLUMN_SOCIAL_AUTHENTICATION_ID)
    val socialAuthenticationEntity: SocialAuthenticationEntity,

    @Column(name = CREATED_AT)
    val createdAt: Instant
) {
    companion object {
        const val TABLE_USER = "user"
        const val COLUMN_USER_ID = "userId"
        const val CREATED_AT = "created_at"
    }
}