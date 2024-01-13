package l1a.jjakkak.infra.domain.user.converter

import jakarta.persistence.AttributeConverter
import l1a.jjakkak.core.domain.user.AuthenticationType

class AuthenticationTypeConverter: AttributeConverter<AuthenticationType, String> {
    override fun convertToDatabaseColumn(attribute: AuthenticationType): String = attribute.code

    override fun convertToEntityAttribute(dbData: String): AuthenticationType = AuthenticationType.from(dbData)
}