package ru.secondproject.dao.impl.user;

import ru.secondproject.dao.abstraction.user.UserDao;
import ru.secondproject.util.PropertiesReader;

public class DaoFactory {
    public UserDao makeDao() {
        String useTech = PropertiesReader.getProperties("useTech");
        if ( useTech.equals("hibernate")) {
            return  new UserDaoImplHibernate();
        } else if  ( useTech.equals("jdbc")) {
            return new UserDaoImpljdbc();
        } else {
            return new UserDaoImpljdbc();
        }
    }
}
