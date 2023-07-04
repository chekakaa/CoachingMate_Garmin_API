import React, { useState, Component } from "react";
import {Link, Redirect} from "react-router-dom";
import { Form, Icon, Input, Button, message, Spin } from "antd";
import { connect } from "react-redux";
import {resPwd} from '@/api/login'
import DocumentTitle from "react-document-title";
import "./index.less";
import { login, getUserInfo } from "@/store/actions";
import {LockOutlined, UserOutlined} from "@ant-design/icons";

const Resetreq=(props)=>{
  const { form, token, login, getUserInfo, history } = props;
  const [loading, setLoading] = useState(false);

  const handleReset = (email) => {
    // after login, send user info and request
    setLoading(true);
    const params = {
      email: email
    }
    resPwd(params).then((data) => {
        message.success("Reset msg sent Success!");
        setLoading(false);
        history.push("/login")
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
                <h2>Reset Password</h2>
              </div>
              <Spin spinning={loading} tip="Loading...">
                <Form.Item name="email" rules={[{
                  required: true,
                  whitespace: true,
                  message: "Please input your email address.",
                }]}>
                    <Input
                      prefix={
                        <UserOutlined style={{ color: "rgba(0,0,0,.25)" }} />
                      }
                      placeholder="Email Address"
                    />

                </Form.Item>
                <Form.Item>
                  <Button
                    type="primary"
                    htmlType="submit"
                    className="login-form-button"
                    // onClick={handleSubmit}
                  >
                    Send Validation
                  </Button>
                </Form.Item>
              </Spin>
            </Form>
          </div>
        </DocumentTitle>
      );
}

export default connect((state) => state.user, { login, getUserInfo })(
  Resetreq
);