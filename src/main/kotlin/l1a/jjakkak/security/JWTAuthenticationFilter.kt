package l1a.jjakkak.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.filter.OncePerRequestFilter

internal class JWTAuthenticationFilter(private val jwtTokenProvider: JwtTokenProvider): OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = request.getHeader(HEADER_AUTHENTICATION)

        jwtTokenProvider.validationToken(token)
    }

    companion object {
        const val HEADER_AUTHENTICATION = "Authorization"
    }
}