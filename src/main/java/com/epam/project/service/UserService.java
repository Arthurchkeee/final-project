package com.epam.project.service;

import com.epam.project.entities.User;

import java.util.List;

public interface UserService {

    /**
     * Finds all available users
     *
     * @return {@code List<User>} wrapped in a{@link List}
     */
    List<User> findAllUsers();

    /**
     * @param id the primary key of table 'user'
     * @return {@link com.epam.project.entities.User}
     */
    User findUserById(Long id);

    /**
     * @param entity is a {@link com.epam.project.entities.User}
     * @return {@code true} if create was success, otherwise {@code false}
     */
    boolean create(User entity);

    /**
     * @param id is a primary key of a table 'user'
     * @return {@code true} if delete was success. otherwise {@code false}
     */
    boolean delete(Long id);

    /**
     * @param entity is a {@link com.epam.project.entities.User}
     * @return {@link com.epam.project.entities.User} from table 'user'
     */
    User update(User entity);

    /**
     * @param login is a unique key of table 'user'
     * @param password password key of table 'user'
     *                       create MD5 hash of password using {@link org.apache.commons.codec.digest.DigestUtils}
     * @return {@code true} if access was success. otherwise {@code false}
     */
    boolean getAccess(String login, String password);

    /**
     * @param login is a unique key of table 'user'
     * @return {@link com.epam.project.entities.User} from table 'user'
     */
    User findUserByLogin(String login);

    /**
     * @return count of Users from table 'user'
     */
    Integer count();

    /**
     * @param booksAmount counts of users on page
     * @param pageNumber number of page to display
     * @return {@code List<User>} wrapped in a{@link List}
     */
    List<User> selectUsersForPages(Integer booksAmount, Integer pageNumber);
}
