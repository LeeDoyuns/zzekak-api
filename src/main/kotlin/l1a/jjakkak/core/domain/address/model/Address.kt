package l1a.jjakkak.core.domain.address.model

import l1a.jjakkak.core.domain.common.IdTypeUUID
import java.util.*

interface Address {
    /** 시, 도 */
    val cityOrProvince: String

    /** 구, 시 */
    val districtOrCity: String

    /** 우편번호 */
    val postalCode: String

    /** 지번주소 */
    val jibunAddress: String

    /** 도로명주소 */
    val roadAddress: String

    companion object {
        fun create(
            cityOrProvince: String,
            districtOrCity: String,
            postalCode: String,
            jibunAddress: String,
            roadAddress: String
        ): Address =
            AddressImpl(
                cityOrProvince = cityOrProvince,
                districtOrCity = districtOrCity,
                postalCode = postalCode,
                jibunAddress = jibunAddress,
                roadAddress = roadAddress
            )
    }
}

internal data class AddressImpl(
    override val cityOrProvince: String,
    override val districtOrCity: String,
    override val postalCode: String,
    override val jibunAddress: String,
    override val roadAddress: String
) : Address
