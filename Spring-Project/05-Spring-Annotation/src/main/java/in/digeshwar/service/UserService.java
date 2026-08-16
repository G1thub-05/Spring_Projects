package in.digeshwar.service;

import in.digeshwar.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired // Without this userDao is null and give NullPointerException
    private UserDao userDao;
    public UserService() {
        System.out.println("UserService :: Constructor");
    }

    public void getName() {
        String nameById = userDao.getNameById(100);
        System.out.println(nameById);
    }
}