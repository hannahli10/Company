package org.example.repository;

import io.jsonwebtoken.Claims;
import org.example.ApplicationBootstrap;
import org.example.model.User;
import org.example.service.JWTService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootstrap.class)
public class JWTServiceTest {
    @Autowired
    private JWTService jwtService;
    @Test
    public void generateTokenTest(){
        User u = new User();
        u.setId(1L);
        u.setName("Hannah");
        String token = jwtService.generateToken(u);
        String[] array = token.split("\\.");
//        assertion
        assertNotNull(token);
        assertEquals(array.length,3);
    }
    @Test
    public void decryptJwtToken(){
        User u = new User();
        u.setId(1L);
        u.setName("Hannah");
        String token = jwtService.generateToken(u);
        Claims claims = jwtService.decryptJwtToken(token);
        assertNotNull(claims);
        assertEquals(claims.getSubject(),"Hannah");
    }
}
