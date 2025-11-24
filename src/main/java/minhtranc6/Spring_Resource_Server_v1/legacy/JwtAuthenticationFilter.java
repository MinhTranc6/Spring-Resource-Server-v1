package minhtranc6.Spring_Resource_Server_v1.legacy;

import java.io.IOException;

@Deprecated
public class JwtAuthenticationFilter {
//
//    @Autowired
//    private JwtService jwtService;
//    @Autowired
//    private UserDetailService userDetailService;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
//        String authHeader = request.getHeader("Authorization");
//        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        String jwt = authHeader.substring(7);
//        String username = jwtService.extractUsername(jwt);
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails = userDetailService.loadUserByUsername(username);
//            if (userDetails != null && jwtService.isTokenValid(jwt)){
//                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
//                  username,
//                    userDetails.getPassword(),
//                    userDetails.getAuthorities()
//                );
//                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//            }
//        }
//        filterChain.doFilter(request, response);
//    }

}
