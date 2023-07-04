package coachingmateanalytics.coachingmate.service.serviceImpl;

import coachingmateanalytics.coachingmate.dao.UserDao;
import coachingmateanalytics.coachingmate.entity.JwtAuthUser;
import coachingmateanalytics.coachingmate.entity.UserPartner;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("UserDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserPartner user = userDao.findUserByUsername(s);

        if (user == null) {
            throw new UsernameNotFoundException("user not exist！");
        }
        JwtAuthUser jwtAuthUser = new JwtAuthUser();
        BeanUtils.copyProperties(user, jwtAuthUser);
        return jwtAuthUser;
    }
}
