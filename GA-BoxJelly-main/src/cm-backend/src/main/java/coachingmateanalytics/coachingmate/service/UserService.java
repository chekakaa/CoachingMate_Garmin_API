package coachingmateanalytics.coachingmate.service;

import coachingmateanalytics.coachingmate.entity.UserPartner;

/**
 * UserService interface defines methods for user operations, such as
 * authentication, registration, and token validation.
 */
public interface UserService {

    /**
     * Validates the provided username and password for login.
     *
     * @param username The user's username.
     * @param password The user's password.
     * @return A UserPartner object if login is successful, otherwise null.
     */
    UserPartner loginCheck(String username, String password);

    /**
     * Registers a new user with the provided information.
     *
     * @param fullname The user's full name.
     * @param username The user's username.
     * @param password The user's password.
     * @param email    The user's email.
     * @return A UserPartner object representing the newly registered user.
     */
    UserPartner register(String fullname, String username, String password, String email);

    /**
     * Retrieves user information by username.
     *
     * @param username The user's username.
     * @return A UserPartner object with the user's information.
     */
    UserPartner getUser(String username);

    /**
     * Retrieves user information by token.
     *
     * @param token The user's token.
     * @return A UserPartner object with the user's information.
     */
    UserPartner getUserByToken(String token);

    /**
     * Checks if the provided token is valid.
     *
     * @param token The user's token.
     * @return true if the token is valid, false otherwise.
     */
    Boolean tokenCheck(String token);

    void updateUser(UserPartner userPartner);

    UserPartner getUserByEmail(String email);
}
