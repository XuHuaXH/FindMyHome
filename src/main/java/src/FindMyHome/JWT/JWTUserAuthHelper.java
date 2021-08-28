package src.FindMyHome.JWT;

import io.jsonwebtoken.Jwts;

import javax.servlet.http.HttpServletRequest;

public class JWTUserAuthHelper {


    public static String TokenToUser(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null) {
            // parse the token.
            String emailId = Jwts.parser()
                    .setSigningKey("MyJwtSecret")
                    .parseClaimsJws(token.replace("Bearer ", ""))
                    .getBody()
                    .getSubject();

            return emailId;
        }
        return null;
    }
}
