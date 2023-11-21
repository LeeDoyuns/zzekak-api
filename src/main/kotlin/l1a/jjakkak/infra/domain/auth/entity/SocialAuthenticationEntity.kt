package l1a.jjakkak.infra.domain.auth.entity

import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import l1a.jjakkak.core.domain.auth.SocialType
import l1a.jjakkak.infra.domain.auth.converter.SocialTypeConverter
import l1a.jjakkak.infra.domain.auth.entity.SocialAuthenticationEntity.Companion.TABLE_SOCIAL_AUTHENTICATION
import java.util.UUID

@Entity
@Table(name = TABLE_SOCIAL_AUTHENTICATION)
class SocialAuthenticationEntity(
    @Id @Column(name = COLUMN_SOCIAL_AUTHENTICATION_ID)
    val id: UUID,

    @Column(name = COLUMN_PROVIDER_ID)
    val providerId: String,

    @Column(name = COLUMN_TYPE)
    @Convert(converter = SocialTypeConverter::class)
    val type: SocialType
) {
    companion object {
        const val TABLE_SOCIAL_AUTHENTICATION = "social_authentication"
        const val COLUMN_SOCIAL_AUTHENTICATION_ID = "social_authentication_id"
        const val COLUMN_PROVIDER_ID = "provider_id"
        const val COLUMN_TYPE = "type"
    }
}