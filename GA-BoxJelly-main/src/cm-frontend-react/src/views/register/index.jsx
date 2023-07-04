import React, { useState,useRef,useEffect } from "react";
import { Redirect } from "react-router-dom";
import { useHistory } from 'react-router-dom'
import { Link } from "react-router-dom";
import { Form, Icon, Input, Button, message, Spin } from "antd";
import { connect } from "react-redux";
import DocumentTitle from "react-document-title";
import "./index.less";
import { login, getUserInfo } from "@/store/actions";
import {register,verifyEmail} from '@/api/login'
import {LockOutlined, MailOutlined, UserOutlined} from "@ant-design/icons";
import {validEmail,passwordValidate} from "../../utils/validate";




const Register = (props) => {

  const { token, login, getUserInfo, history } = props;
  const [form] = Form.useForm();
  const formRef = useRef(null);
//   const formRef = React.createRef();
  const [loading, setLoading] = useState(false);
    const [remainingTime, setRemainingTime] = useState(0);
    const [isCodeSent, setIsCodeSent] = useState(false);
     useEffect(() => {
        let timerId;
        if (remainingTime > 0) {
          timerId = setTimeout(() => {
            setRemainingTime((prevTime) => prevTime - 1);
          }, 1000);
        } else {
          setIsCodeSent(false);
        }

        return () => {
          clearTimeout(timerId);
        };
      }, [remainingTime]);
//     const formRef = useRef(null);
  const handleRegister = ( fullname,username, password, email,code) => {
    // after login, send user info and request
    setLoading(true);
    const params = {
      fullname:fullname,
      username: username,
      password: password,
      email: email,
      code:code
    }
    register(params).then((data) => {
        message.success("Register Success!");
        setLoading(false);
        history.push("/login")
      })
      .catch((error) => {
        setLoading(false);
//         message.error(error.response.data);
//            console.log(error.response.data.message)
           message.error('error',error);
      });

  };
    const handleGetEmailClick = (email) => {
     setRemainingTime(60);
     setIsCodeSent(true);
      console.log(email);
      const params={email:email}
          verifyEmail(params).then((data) => {
              message.success("send validation email!");
              setLoading(false);
//               history.push("/login")
            })
            .catch((error) => {
              setLoading(false);
             message.error(error)
//              console.log(error.response.data.data)

            });
    };


  const handleSubmit = (values) => {
    // prevent default event
    // event.preventDefault();
    let {fullname,username, password,email,code} = values

//     console.log(form1.validateFields)
    // validate
//     form1.validateFields((err, values) => {
//       // 检验成功
//       if (!err) {
//       let {fullname,username, password,email,code} = values
        handleRegister(fullname,username, password, email,code);
//       } else {
//         console.log("fail to validate!");
//       }
//     });
  };



  if (token) {
    return <Redirect to="/dashboard" />;
  }
  return (
    <DocumentTitle title={"User Register"}>
      <div className="login-container">
        <Form onFinish={handleSubmit} className="content" form={form} ref={formRef}>
          <div className="title">
            <h2>User Register</h2>
          </div>
          <Spin spinning={loading} tip="Register...">
{/*           cancel fullname */}
            <Form.Item name="fullname" rules={[{
              required: true,
              whitespace: true,
              message: "Please input Full Name.",
            }]}>
              <Input
                prefix={
                  <UserOutlined style={{ color: "rgba(0,0,0,.25)" }} />
                }
                placeholder="Full Name"
              />

            </Form.Item>
            <Form.Item name="username" rules={[{
              required: true,
              whitespace: true,
              message: "Please input username.",
            }]}>
              <Input
                prefix={
                  <UserOutlined style={{ color: "rgba(0,0,0,.25)" }} />
                }
                placeholder="User Name"
              />

            </Form.Item>
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
              {/*<Input*/}
              {/*  prefix={*/}
              {/*    <LockOutlined style={{ color: "rgba(0,0,0,.25)" }} />*/}
              {/*  }*/}
              {/*  type="password"*/}
              {/*  placeholder="Password"*/}
              {/*/>*/}
              <Input.Password prefix={
                <LockOutlined style={{ color: "rgba(0,0,0,.25)" }} />
              }
              placeholder="confirm password"
              />
            </Form.Item>
            <Form.Item name="email"
                            rules={[{
                                          required: true,
                                          whitespace: true,
                                          message: "Please input email address.",
                                        },
                                          ({ getFieldValue }) => ({
                                            validator(_, value) {
                                              if (validEmail(getFieldValue('email'))) {
                                                return Promise.resolve();
                                              }

                                              return Promise.reject(new Error('Please input right email address'));
                                            },
                                          }),]}>
              <Input
                prefix={
                  <MailOutlined style={{ color: "rgba(0,0,0,.25)" }} />
                }

                placeholder="Email"
                 suffix={
                        <Button
                          type="link"
                          onClick={() => {

                            console.log(form.getFieldValue('email'));
                            handleGetEmailClick(form.getFieldValue('email'))
                          }}
                        >
                          {remainingTime > 0 ? `${remainingTime}s` : "send code"}
                        </Button>}
//                 value={email1}
//                 onChange={handleEmailChange}
              />
{/*  verify email*/}


            </Form.Item>


                        <Form.Item name="code" rules={[{
                          required: true,
                          whitespace: true,
                          message: "Please input code.",
                        }]}>
                          <Input
                            prefix={
                              <UserOutlined style={{ color: "rgba(0,0,0,.25)" }} />
                            }
                            placeholder="code"
                          />

                        </Form.Item>


            <Form.Item>

              <Button
                type="primary"
                htmlType="submit"
                className="login-form-button"
              >
                Register
              </Button>
              <Button
                type="primary"
                // htmlType="submit"
                className="login-form-button"
              >
                <Link to="/login">Back</Link>
              </Button>
            </Form.Item>

          </Spin>
        </Form>
      </div>
    </DocumentTitle>
  );
};

// const WrapRegister = Form.create()(Register);

export default connect((state) => state.user, { login, getUserInfo })(
  Register
);
