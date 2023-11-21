package l1a.jjakkak.infra.domain.auth.converter

import jakarta.persistence.AttributeConverter
import l1a.jjakkak.core.domain.auth.SocialType
import org.springframework.core.convert.converter.Converter

class SocialTypeConverter: AttributeConverter<SocialType, String> {
    override fun convertToDatabaseColumn(attribute: SocialType): String = attribute.code

    override fun convertToEntityAttribute(dbData: String): SocialType = SocialType.from(dbData)
}