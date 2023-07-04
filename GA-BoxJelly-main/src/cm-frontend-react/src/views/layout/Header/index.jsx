import React, { useState, Component } from "react";
import { connect } from "react-redux";
import { Menu, Dropdown, Modal, Layout, Avatar ,message,Spin} from "antd";
import {CaretDownOutlined} from '@ant-design/icons';
import { Link } from "react-router-dom";
import { logout, getUserInfo} from "@/store/actions";
import FullScreen from "@/components/FullScreen";
import Settings from "@/components/Settings";
import Hamburger from "@/components/Hamburger";
import BreadCrumb from "@/components/BreadCrumb";
import {getAccessToken} from "@/utils/auth";
//获取disconnect的api
import {reqDisconnect} from "@/api/login"

import "./index.less";
const { Header } = Layout;


const LayoutHeader = (props) => {

  const {
    token,
    avatar,
    history,
    sidebarCollapsed,
    logout,
    getUserInfo,
    showSettings,
    fixedHeader,
  } = props;
  const [loading, setLoading] = useState(false);
//   const [showDisconnect,setShowDisconnect]=useState(true);
  token && getUserInfo(token);

const [tk, setTk] = useState(null);

  const handleLogout = (token) => {
    Modal.confirm({
      title: "Logout",
      content: "Do you want to log out??",
      okText: "Confirm",
      cancelText: "Cancel",
      onOk: () => {
        logout(token);
      },
    });
  };

    const handleDisconnect = (token) => {
      Modal.confirm({
        title: "Disconnect",
        content: "Do you want to disconnect with Garmin?",
        okText: "Confirm",
        cancelText: "Cancel",
        onOk: () => {
         getUserInfo(token)
           .then((data) => {

             const userName=data.username //e.g. 0513
                 reqDisconnect({username:data.username})
                   .then((data) => {
                     // console.log(data)
                     message.success("Disconnect success!");
                     window.location.reload()
                   })
                   .catch((error) => {
                     setLoading(false);
                     message.error("error message: ",error)
                   });

           })
           .catch((error) => {

             console.log('error message: '+error)
           });
        },
      });
    };

  getUserInfo(token)
  .then((data) => {setTk(data.userAccessToken)
})
  .catch((error) => {
    console.log('error message: '+error)
    });


  const onClick = ({ key }) => {
    switch (key) {
      case "logout":
        handleLogout(token);
        break;
      case "disconnect":
      handleDisconnect(token);
      break;
      default:
        break;
    }
  };
  const menu = (
    <Menu onClick={onClick}>
      <Menu.Item key="dashboard">
        <Link to="/dashboard">Dashboard</Link>
      </Menu.Item>
      {/*<Menu.Item key="project">*/}
      {/*  <a*/}
      {/*    target="_blank"*/}
      {/*    href="https://github.com/NLRX-WJC/react-antd-admin-template"*/}
      {/*    rel="noopener noreferrer"*/}
      {/*  >*/}
      {/*    项目地址*/}
      {/*  </a>*/}
      {/*</Menu.Item>*/}
      <Menu.Divider />
      <Menu.Item key="logout">Logout</Menu.Item>
       <Menu.Divider />
        {tk ?  <Menu.Item key="disconnect">Disconnect</Menu.Item> : null }

    </Menu>
  );
  const computedStyle = () => {
    let styles;
    if (fixedHeader) {
      if (sidebarCollapsed) {
        styles = {
          width: "calc(100% - 80px)",
        };
      } else {
        styles = {
          width: "calc(100% - 200px)",
        };
      }
    } else {
      styles = {
        width: "100%",
      };
    }
    return styles;
  };
  return (
    <>
      {/* 这里是仿照antd pro的做法,如果固定header，
      则header的定位变为fixed，此时需要一个定位为relative的header把原来的header位置撑起来 */}
      {fixedHeader ? <Header /> : null}
      <Header
        style={computedStyle()}
        className={fixedHeader ? "fix-header" : ""}
      >
        <Hamburger />
        <BreadCrumb />
        <div className="right-menu">
          <FullScreen />
          {showSettings ? <Settings /> : null}
          <div className="dropdown-wrap">
            <Dropdown overlay={menu}>
              <div>
                <Avatar shape="square" size="medium" src={avatar} />
                {/*<Icon style={{ color: "rgba(0,0,0,.3)" }} type="caret-down" />*/}
                <CaretDownOutlined style={{ color: "rgba(0,0,0,.3)" }}  />
              </div>
            </Dropdown>
          </div>
        </div>
      </Header>
    </>
  );
};

const mapStateToProps = (state) => {
  return {
    ...state.app,
    ...state.user,
    ...state.settings,
  };
};
export default connect(mapStateToProps, { logout, getUserInfo })(LayoutHeader);
