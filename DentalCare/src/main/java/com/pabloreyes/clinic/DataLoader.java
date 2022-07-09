package com.pabloreyes.clinic;

import com.pabloreyes.clinic.model.entity.Role;
import com.pabloreyes.clinic.model.entity.User;
import com.pabloreyes.clinic.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

//-------------------*******NOTE*******--------------------------
@Component
public class DataLoader implements ApplicationRunner {


    private UserRespository userRespository;

    @Autowired
    public DataLoader(UserRespository userRespository) {
        this.userRespository = userRespository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        userRespository.save(new User("admin", "admin", Role.ADMIN));
        userRespository.save(new User("user", "user", Role.USER));
    }
}
