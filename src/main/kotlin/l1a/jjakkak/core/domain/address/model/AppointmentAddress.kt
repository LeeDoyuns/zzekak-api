package l1a.jjakkak.core.domain.address.model

import l1a.jjakkak.core.domain.common.IdTypeUUID
import java.util.*

@JvmInline
value class AppointmentAddressId(override val value: UUID) : IdTypeUUID

interface AppointmentAddress {
    val id: AppointmentAddressId
    val address: Address
    val coordinate: Coordinate

    companion object {
        fun create(
            id: AppointmentAddressId,
            cityOrProvince: String,
            districtOrCity: String,
            postalCode: String,
            jibunAddress: String,
            roadAddress: String,
            x: String,
            y: String
        ): AppointmentAddress =
            AppointmentAddressImpl(
                id = id,
                address = Address.create(
                    cityOrProvince = cityOrProvince,
                    districtOrCity = districtOrCity,
                    postalCode = postalCode,
                    jibunAddress = jibunAddress,
                    roadAddress = roadAddress
                ),
                coordinate = Coordinate.create(x = x, y = y)
            )
    }
}

internal data class AppointmentAddressImpl(
    override val id: AppointmentAddressId,
    override val address: Address,
    override val coordinate: Coordinate,
) : AppointmentAddress
