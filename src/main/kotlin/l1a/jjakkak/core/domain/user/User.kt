package l1a.jjakkak.core.domain.user

import java.util.UUID

interface User {
    val id: UUID
    val authentication: List<SocialAuthentication>
}