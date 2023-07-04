package coachingmateanalytics.coachingmate.dao;

import java.util.List;
import coachingmateanalytics.coachingmate.common.utils.Consts;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import coachingmateanalytics.coachingmate.entity.UserPartner;

@Component
public class UserDaoImpl implements UserDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * Saves a user to the MongoDB collection.
     *
     * @param user the user to save
     */
    public void saveUser(UserPartner user) {
        mongoTemplate.save(user, Consts.MONGODB_USER_COLLECTIN_NAME);
    }

    /**
     * Finds a user by username in the MongoDB collection.
     *
     * @param username the username to search for
     * @return the found user or null if not found
     */
    public UserPartner findUserByUsername(String username) {
        Query query = new Query(Criteria.where("username").is(username));
        return mongoTemplate.findOne(query, UserPartner.class, Consts.MONGODB_USER_COLLECTIN_NAME);
    }

    /**
     * Finds a user by email in the MongoDB collection.
     *
     * @param email the email to search for
     * @return the found user or null if not found
     */
    public UserPartner findUserByEmail(String email) {
        Query query = new Query(Criteria.where("email").is(email));
        return mongoTemplate.findOne(query, UserPartner.class, Consts.MONGODB_USER_COLLECTIN_NAME);
    }

    /**
     * Updates a user in the MongoDB collection.
     *
     * @param user the user to update
     * @return the number of updated users
     */
    public int updateUser(UserPartner user) {
        Query query = new Query(Criteria.where("username").is(user.getUsername()));
        Update update = Update.update("userAccessToken", user.getUserAccessToken())
                .set("userAccessSecret", user.getUserAccessSecret())
                .set("token", user.getToken())
                .set("tokenDate", user.getTokenDate())
                .set("verified", true);
        UpdateResult result = mongoTemplate.updateMulti(query, update, UserPartner.class, Consts.MONGODB_USER_COLLECTIN_NAME);
        return (int) result.getMatchedCount();
    }

    /**
     * Retrieves the maximum user ID from the MongoDB collection.
     *
     * @return the maximum user ID
     */
    public int getMaxUserid() {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "userId"));
        List<UserPartner> users = mongoTemplate.find(query, UserPartner.class, Consts.MONGODB_USER_COLLECTIN_NAME);

        if (users.size() == 0) {
            return 0;
        }
        return (int) users.get(0).getUserId();
    }

    /**
     * Deletes a user by username in the MongoDB collection.
     *
     * @param username the username of the user to delete
     */
    public void deleteUserByUsername(String username) {
        Query query = new Query(Criteria.where("username").is(username));
        mongoTemplate.remove(query, UserPartner.class, Consts.MONGODB_USER_COLLECTIN_NAME);
    }

    /**
     * Selects a user by token in the MongoDB collection.
     *
     * @param token the token to search for
     * @return the found user or null if not found
     */
    public UserPartner selectUserByToken(String token) {
        Query query = new Query(Criteria.where("token").is(token));
        return mongoTemplate.findOne(query, UserPartner.class, Consts.MONGODB_USER_COLLECTIN_NAME);
        }
        
    }
    
