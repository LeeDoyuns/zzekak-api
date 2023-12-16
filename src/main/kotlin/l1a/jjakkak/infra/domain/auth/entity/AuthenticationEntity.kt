package l1a.jjakkak.infra.domain.auth.entity

import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import l1a.jjakkak.core.domain.auth.AuthenticationType
import l1a.jjakkak.infra.domain.auth.converter.AuthenticationTypeConverter
import l1a.jjakkak.infra.domain.auth.entity.AuthenticationEntity.Companion.TABLE_AUTHENTICATION
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant

@Entity
@Table(name = TABLE_AUTHENTICATION)
class AuthenticationEntity(
    @Id @Column(name = COLUMN_AUTHENTICATION_ID)
    val providerId: String,

    @Column(name = COLUMN_TYPE)
    @Convert(converter = AuthenticationTypeConverter::class)
    val type: AuthenticationType,

    @Column(name = COLUMN_CREATED_AT)
    @CreationTimestamp
    val createdAt: Instant? = null,

    @Column(name = COLUMN_UPDATED_AT)
    @UpdateTimestamp
    val updatedAt: Instant? = null
) {
    companion object {
        const val TABLE_AUTHENTICATION = "authentication"
        const val COLUMN_AUTHENTICATION_ID = "authentication_id"
        const val COLUMN_TYPE = "type"
        const val COLUMN_CREATED_AT = "created_at"
        const val COLUMN_UPDATED_AT = "updated_at"
    }
}