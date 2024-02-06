package uhang.uhang.Auth;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private static final String SUCCESS = "success";
    private static final String EXPIRED = "expired";
    private static final String DENIED = "denied";
    private final JwtProvider jwtProvider;
    private final static String[] AUTH_WHITE_LIST_IGNORE = {
            "/sign-up"
            ,"/log-in"
    };


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response
            , FilterChain filterChain) throws ServletException, IOException {
        System.out.println("doing jwtFilter");
        try {
            String accessToken = jwtProvider.resolveToken(request, HttpHeaders.AUTHORIZATION);
            Authentication authentication = jwtProvider.getAuthentication(accessToken);

            // access token 검증
            if (StringUtils.hasText(accessToken) && jwtProvider.validateToken(accessToken).equals(SUCCESS)) {
                SecurityContextHolder.getContext().setAuthentication(authentication); // security context에 인증 정보 저장
            }
        } catch (ExpiredJwtException e) {
            //throw JwtException
            request.setAttribute("exception",EXPIRED);
        } catch (IllegalArgumentException  e) {
            //throw JwtException
            request.setAttribute("exception",DENIED);
        }
        filterChain.doFilter(request, response);
    }

    public class CORSInterceptor implements Filter {

        private static final String[] allowedOrigins = {
                "http://localhost:5173"};

        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            HttpServletRequest request = (HttpServletRequest) servletRequest;

            String requestOrigin = request.getHeader("Origin");
            if(isAllowedOrigin(requestOrigin)) {
                // Authorize the origin, all headers, and all methods
                ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Origin", requestOrigin);
                ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Headers", "*");
                ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Methods",
                        "GET, OPTIONS, HEAD, PUT, POST, DELETE");

                HttpServletResponse resp = (HttpServletResponse) servletResponse;

                // CORS handshake (pre-flight request)
                if (request.getMethod().equals("OPTIONS")) {
                    resp.setStatus(HttpServletResponse.SC_ACCEPTED);
                    return;
                }
            }
            // pass the request along the filter chain
            filterChain.doFilter(request, servletResponse);
        }

        private boolean isAllowedOrigin(String origin){
            for (String allowedOrigin : allowedOrigins) {
                if(origin.equals(allowedOrigin)) return true;
            }
            return false;
        }
    }

}