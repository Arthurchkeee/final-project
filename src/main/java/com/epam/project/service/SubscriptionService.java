package com.epam.project.service;

import com.epam.project.entities.Status;
import com.epam.project.entities.Subscription;

import java.sql.Date;
import java.util.List;

public interface SubscriptionService {
    /**
     * Finds all available subscribe
     *
     * @return {@code List<Subscription>} wrapped in a{@link List}
     */
    List<Subscription> findAllSubscriptions();

    /**
     * @param id is a primary key of a table 'subscribe'
     * @return the {@link com.epam.project.entities.Subscription}
     */
    Subscription findSubscriptionById(Long id);

    /**
     * @param entity is a {@link com.epam.project.entities.Subscription}
     * @return {@code true} if create was success, otherwise {@code false}
     */
    boolean create(Subscription entity);

    /**
     * @param id is a primary key of a table 'subscribe'
     * @return {@code true} if delete was success. otherwise {@code false}
     */
    boolean delete(Long id);

    /**
     * @param entity is a {@link com.epam.project.entities.Subscription}
     * @return {@link com.epam.project.entities.Subscription} from table 'book'
     */
    Subscription update(Subscription entity);


    /**
     * @param userId is a foreign key of table 'subscribe'
     * @param bookId is a unique index of table 'subscribe'
     * @param to     is a Date parameter
     */
    void orderSubscription(Long userId, Long bookId, Date to);


    /**
     * @param userId is a foreign key of table 'subscribe'
     * @param bookId is a unique index of table 'subscribe'
     */
    void orderRoom(Long userId, Long bookId);


    /**
     * Finds all subscriptions by user
     *
     * @param status is a foreign key of table "subscribe"
     * @return {@code List<Subscription>} wrapped in a{@link List}
     */
    List<Subscription> findAllBooksByStatus(Status status);

    /**
     * Finds all subscriptions by user
     *
     * @param userId is a foreign key of table "subscribe"
     * @return {@code List<Subscription>} wrapped in a{@link List}
     */
    List<Subscription> findAllSubscriptionsByUser(Long userId);

    /**
     * @param id     is a primary key of table 'subscribe'
     * @param bookId is a foreign key of table 'subscribe'
     */
    void deleteSubscription(Long id, Long bookId);

    /**
     * @param id is a primary key of table 'subscribe'
     * @param to is a Date parameter
     * @return * @return {@code true} if update was success. otherwise {@code false}
     */
    boolean updateDateTo(Long id, Date to);

    /**
     * @param statuses is a {@code List<Status>} for select data from table 'subscribe'
     * @param booksAmount counts of subscription on page
     * @param pageNumber number of page to display
     * @return {@code List<Subscription>} wrapped in a{@link List}
     */
    List<Subscription> findSubscriptionsByBookStatuses(List<Status> statuses,Integer booksAmount, Integer pageNumber);

    /**
     * @param statuses is a {@code List<Status>} for select data from table 'subscribe'
     * @param userId is a foreign key of table 'user'
     * @param booksAmount counts of subscription on page
     * @param pageNumber number of page to display
     * @return {@code List<Subscription>} wrapped in a{@link List}
     */
    List<Subscription> findSubscriptionsByBookStatusesAndUser(List<Status> statuses, Long userId,Integer booksAmount, Integer pageNumber);

    /**
     * @param statuses is a {@code List<Status>} for select data from table 'subscribe'
     * @return count of Subscriptions from table 'subscribe'
     */
    Long count(List<Status> statuses);

    /**
     * @param statuses is a {@code List<Status>} for select data from table 'subscribe'
     * @param userId is a foreign key of table 'user'
     * @return count of Subscriptions from table 'subscribe'
     */
    Long countForUser(List<Status> statuses,Long userId);
}
