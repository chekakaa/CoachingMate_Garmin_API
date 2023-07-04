import React, { useState, Component } from "react";
import {Link, Redirect} from "react-router-dom";
import { Form, Icon, Input, Button, message, Spin } from "antd";
import { connect } from "react-redux";
import {resPwd,resetPwd} from '@/api/login'
import DocumentTitle from "react-document-title";
import "./index.less";
import { login, getUserInfo } from "@/store/actions";
import {LockOutlined, UserOutlined} from "@ant-design/icons";
import {validEmail,passwordValidate} from "../../utils/validate";

const Reset=(props)=>{
  const { form, token, login, getUserInfo, history } = props;
  const [loading, setLoading] = useState(false);

  //get the current url and decode it to get the users' email
  const currentURL = window.location.href;
  const encodedString = currentURL.substring(currentURL.indexOf("?") + 1, currentURL.indexOf("#"));
  const decodeString = atob(encodedString);
  const userEmail=decodeString.substring(decodeString.indexOf("=")+1,decodeString.indexOf("&"))
  const security=decodeString.substring(decodeString.indexOf("&")+1)
  const securitycode=security.substring(security.indexOf("=")+1)
  console.log(userEmail);
  console.log(securitycode);

  const handleReset = (values) => {
    setLoading(true);
    const{password}=values
    const params = {
      email: userEmail,
      password: password,
      security:securitycode
    }
    console.log(params)
    resetPwd(params).then((data) => {
        message.success("Reset message sent Success!");
        setLoading(false);
        const currentURL = window.location.href;
        const domain = currentURL.split("/")[2];
        const newURL = `http://${domain}/login`;

        window.location.href ='https://ga-box-jelly-ddbh.vercel.app/#/login';
        console.log('已更新地址')

      })
      .catch((error) => {
        setLoading(false);
           message.error("error exist");
      });

  };


    return (
        <DocumentTitle title={"Reset password"}>
          <div className="login-container">
            <Form onFinish={handleReset} className="content">
              <div className="title">
                <h2>New Password</h2>
              </div>
              <Spin spinning={loading} tip="Loading...">
            <Form.Item name="password" rules={[{
              required: true,
              whitespace: true,
              message: "Please input password.",
            },
             ({ getFieldValue }) => ({
                 validator(_, value) {
                     if (passwordValidate(getFieldValue('password'))) {return Promise.resolve();}
                     if(getFieldValue('password').length<8){
                     return Promise.reject(new Error('Password must longer than 8 characters'))
                     }
                      return Promise.reject(new Error('Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character.'));}
                      }),
                      ]}
                      >
              <Input.Password prefix={
                <LockOutlined style={{ color: "rgba(0,0,0,.25)" }} />
              }/>
            </Form.Item>
            <Form.Item
              name="confirm"
              label=""
              dependencies={['password']}
              hasFeedback
              rules={[
                {
                  required: true,
                  message: 'Please confirm your password!',
                },
                ({ getFieldValue }) => ({
                  validator(_, value) {
                    if (!value || getFieldValue('password') === value) {
                      return Promise.resolve();
                    }

                    return Promise.reject(new Error('The two passwords that you entered do not match!'));
                  },
                }),
              ]}
            >
              <Input.Password prefix={
                <LockOutlined style={{ color: "rgba(0,0,0,.25)" }} />
              }
              placeholder="confirm password"
              />
</Form.Item>
                <Form.Item>
                  <Button
                    type="primary"
                    htmlType="submit"
                    className="login-form-button"
                    // onClick={handleSubmit}
                  >
                    Send New Password
                  </Button>
                </Form.Item>
              </Spin>
            </Form>
          </div>
        </DocumentTitle>
      );
}

export default connect((state) => state.user, { login, getUserInfo })(
  Reset
);
