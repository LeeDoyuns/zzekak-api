package l1a.jjakkak.core.domain.user.repository

interface AuthRepository {
    fun getKakaoLoginPublicKey(): List<String>
}