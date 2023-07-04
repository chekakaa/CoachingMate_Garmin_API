package coachingmateanalytics.coachingmate.dao;

import coachingmateanalytics.coachingmate.entity.UserPartner;

/**
 * Implements MongoDB Data Access Object for UserPartner in the MongoDB database
 * for the CoachingMate application.
 */
public interface UserDao {
    /**
     * Retrieves the maximum user ID.
     *
     * @return the maximum user ID
     */
    int getMaxUserid();

    /**
     * Saves the given user.
     *
     * @param user the user to save
     */
    void saveUser(UserPartner user);

    /**
     * Finds a user by username.
     *
     * @param username the username to search for
     * @return the found user or null if not found
     */
    UserPartner findUserByUsername(String username);

    /**
     * Finds a user by email.
     *
     * @param email the email to search for
     * @return the found user or null if not found
     */
    UserPartner findUserByEmail(String email);

    /**
     * Updates the given user.
     *
     * @param user the user to update
     * @return the number of updated users
     */
    int updateUser(UserPartner user);

    /**
     * Deletes a user by username.
     *
     * @param username the username of the user to delete
     */
    void deleteUserByUsername(String username);

    /**
     * Selects a user by token.
     *
     * @param token the token to search for
     * @return the found user or null if not found
     */
    UserPartner selectUserByToken(String token);

}
