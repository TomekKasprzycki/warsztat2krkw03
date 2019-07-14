package pl.coderslab.warsztat2krkw03;

import pl.coderslab.warsztat2krkw03.dao.UserDao;
import pl.coderslab.warsztat2krkw03.model.User;

public class test {

    public static void main(String[] args) {
        User u = new User("admin1", "admin1@test.pl", "admin");

        UserDao.create(u);

        System.out.println(u.getId());
        System.out.println(u.getPassword());
    }

}
