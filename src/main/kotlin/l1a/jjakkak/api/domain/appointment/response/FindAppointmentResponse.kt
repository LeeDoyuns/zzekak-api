package l1a.jjakkak.api.domain.appointment.response

import com.fasterxml.jackson.annotation.JsonProperty
import l1a.jjakkak.api.domain.common.response.AddressResponse
import l1a.jjakkak.core.domain.appointment.model.AppointmentId
import l1a.jjakkak.core.domain.appointment.model.AppointmentQuery
import l1a.jjakkak.core.domain.user.UserId
import java.time.Instant

data class FindAppointmentResponse(
    @JsonProperty("content")
    val content: List<FindAppointmentContent>
) {
    companion object {
        fun from(src: Collection<AppointmentQuery>): FindAppointmentResponse =
            FindAppointmentResponse(
                content = src.map {
                    FindAppointmentContent(
                        id = it.id,
                        appointmentName = it.name,
                        address = AddressResponse.from(it.address),
                        appointmentTime = it.appointmentTime,
                        participants = it.participants
                    )
                }
            )
    }
}

data class FindAppointmentContent(
    @JsonProperty("id")
    val id: AppointmentId,
    @JsonProperty("appointmentName")
    val appointmentName: String,
    @JsonProperty("address")
    val address: AddressResponse,
    @JsonProperty("appointmentTime")
    val appointmentTime: Instant,
    @JsonProperty("participants")
    val participants: List<UserId>,
)

data class ParticipantContent(
    @JsonProperty("name")
    val name: String
)
