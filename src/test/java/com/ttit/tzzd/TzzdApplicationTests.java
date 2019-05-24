package com.ttit.tzzd;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TzzdApplicationTests {

    @Test
    public void contextLoads() {
        ClassPathResource resource=new ClassPathResource("application.yml");
        assert resource.exists();
        System.out.println(resource.getFilename());
    }
}
