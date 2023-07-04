import { setUserToken, resetUser } from "./user";
import { reqLogin, reqLogout, reqDisconnect } from "@/api/login";
import { setToken, removeToken, removeAccessToken } from "@/utils/auth";

export const login = (username, password) => (dispatch) => {
  return new Promise((resolve, reject) => {
    reqLogin({ username: username.trim(), password: password })
      .then((response) => {
        const data  = response.data;
        if (response.statusCode === 200) {
          const token = data.token;
          // debugger
          dispatch(setUserToken(token));
          setToken(token);

          resolve(data);
        } else {
          const msg = data.message;
          reject(msg);
        }
      })
      .catch((error) => {
        reject(error);
      });
  });
};

export const disconnect = (username) => (dispatch) => {
console.log("disconnect: "+username)
  return new Promise((resolve, reject) => {
    reqDisconnect( username )
      .then((response) => {
        const data  = response.data;
        if (response.statusCode === 200) {
//          const token = data.token;
//          // debugger
//          dispatch(setUserToken(token));
//          setToken(token);
//
          resolve(data);

        } else {
          const msg = data.message;
          reject(msg);
        }
      })
      .catch((error) => {
        reject(error);
      });
  });
};

export const logout = (token) => (dispatch) => {
  return new Promise((resolve, reject) => {
    dispatch(resetUser());
    removeToken();
    removeAccessToken();
    // reqLogout(token)
    //   .then((response) => {
    //     const { data } = response;
    //     if (data.status === 0) {
    //       dispatch(resetUser());
    //       removeToken();
    //       resolve(data);
    //     } else {
    //       const msg = data.message;
    //       reject(msg);
    //     }
    //   })
    //   .catch((error) => {
    //     reject(error);
    //   });
  });

};
//export const disconnect = (username) => (dispatch) => {
//    return new Promise((resolve, reject) => {
//      dispatch(resetUser());
////      removeToken();
////      removeAccessToken();
//            reqDisconnect(username).then((data) => {
//                message.success("disconnect success!");
//                setLoading(false);
////                history.push("/login")
//              })
//              .catch((error) => {
//                setLoading(false);
//                   message.error("error exist");
//              });
//    });}

