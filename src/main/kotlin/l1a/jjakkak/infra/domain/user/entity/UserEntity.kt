package l1a.jjakkak.infra.domain.user.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import l1a.jjakkak.infra.domain.auth.entity.AuthenticationEntity
import l1a.jjakkak.infra.domain.user.entity.UserEntity.Companion.TABLE_USER
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = TABLE_USER)
class UserEntity(
    @Id @Column(name = COLUMN_USER_ID)
    val userId: UUID,

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = AuthenticationEntity.COLUMN_AUTHENTICATION_ID)
    val authenticationEntity: AuthenticationEntity,

    @Column(name = CREATED_AT)
    @CreationTimestamp
    val createdAt: Instant? = null,

    @Column(name = COLUMN_UPDATED_AT)
    @UpdateTimestamp
    val updatedAt: Instant? = null
) {
    companion object {
        const val TABLE_USER = "user"
        const val COLUMN_USER_ID = "userId"
        const val CREATED_AT = "created_at"
        const val COLUMN_UPDATED_AT = "updated_at"

    }
}