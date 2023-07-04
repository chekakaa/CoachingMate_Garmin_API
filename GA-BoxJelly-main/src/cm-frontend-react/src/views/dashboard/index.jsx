import React, {useLayoutEffect} from "react";
import {Avatar, Modal, Button, Image, Row, Col, message} from "antd";
import "./index.less";
import { Spin, Space } from 'antd';
import PanelGroup from "./components/PanelGroup";
import LineChart from "./components/LineChart";
import BarChart from "./components/BarChart";
import RaddarChart from "./components/RaddarChart";
import PieChart from "./components/PieChart";
import TransactionTable from "./components/TransactionTable";
import BoxCard from "./components/BoxCard";
import {getAccessToken} from "../../utils/auth";
import {requestToken} from '@/api/auth'
import store from "@/store";
// import { login, getUserInfo } from "@/store/actions";
import {getToken} from "@/utils/auth"
import {login, getUserInfo} from "@/store/actions";
import {getDashboardStatisticsByAccessToken} from "@/api/activity"
const defaultData = {
  "userAccessToken": "889f8e7f-9993-4e05-964f-445d4198bb72",
  "ttlActivityTimes": 1000,
  "ttlRunningTimes": 700,
  "ttlRiddingTimes": 200,
  "ttlSwimmingTimes": 100,
  "ttlActivityTime": 831,
  "ttlRunningTime": 78,
  "ttlRiddingTime": 738,
  "ttlSwimmingTime": 15,
  "allActivityTime": null,
  "runningActivityTime": null,
  "swimmingActivityTime": null,
  "riddingActivityTime": null,
  "activityTimeChartMap": {
    "RiddingTime": {
      "actual": [100, 120, 161, 134, 105, 160, 165],
      "except": [120, 82, 91, 154, 162, 140, 145]
    },
    "AllTime": {
      "actual": [200, 192, 120, 144, 160, 130, 140],
      "except": [180, 160, 151, 106, 145, 150, 130]
    },
    "SwimmingTime": {
      "actual": [80, 100, 121, 104, 105, 90, 100],
      "except": [120, 90, 100, 138, 142, 130, 130]
    },
    "RunningTime": {
      "actual":  [130, 140, 141, 142, 145, 150, 160],
      "except": [120, 82, 91, 154, 162, 140, 130]
    }
  },
  "radarActivities": {
    "RUNNING": {
      "time": 78,
      "calories": 1011,
      "distance": 14009.34,
      "avgSpeed": 179.60692307692307,
      "peakSpeed": 4.255,
      "avgHeartRate": 158
    },
    "OPEN_WATER_SWIMMING": {
      "time": 15,
      "calories": 152,
      "distance": 895.11,
      "avgSpeed": 59.674,
      "peakSpeed": 22.562,
      "avgHeartRate": 142
    },
    "ROAD_BIKING": {
      "time": 738,
      "calories": 6697,
      "distance": 236205.5,
      "avgSpeed": 320.06165311653115,
      "peakSpeed": 0,
      "avgHeartRate": 155
    }
  },
  "hearRateZones": [
    [100, 100, 100, 100, 100],
    [100, 100, 100, 100, 100],
    [100, 100, 100, 100, 100],
    [100, 100, 100, 100, 100],
    [100, 100, 100, 100, 100],
    [100, 100, 100, 100, 100],
    [100, 100, 100, 100, 100]
  ]
}
const Dashboard = () => {

  // const [lineChartData, setLineChartData] = useState(
  //   lineChartDefaultData["All Activities"]
  // );
  let [lineChartData, setLineChartData] = React.useState( {
    "actual": [100, 120, 161, 134, 105, 160, 165],
    "except": [120, 82, 91, 154, 162, 140, 145]
  });
  let [heartRateZones, setHeartRateZones] = React.useState( false);
  let [allLineChartData, setAllLineChartData] = React.useState(false);
  let [dashboardData, setDashboardData] = React.useState(false);
  const [loading, setLoading] = React.useState(true);
  const [visible, setVisible] = React.useState(false);
  const [subVisible, setSubVisible] = React.useState(false);
  const [confirmLoading, setConfirmLoading] = React.useState(false);
  const [modalText, setModalText] = React.useState('Content of the modal');
  const {user} = store.getState()
  const token = getToken()
  // console.log(user)
  const showModal = () => {
    setVisible(true);
  };

  const showSubModal = () => {
    setSubVisible(true);
  };

  const handleConnect = () => {
    setModalText('The modal will be closed after two seconds');
    requestToken({username: user.username}).then(response => {
      setSubVisible(true)
      const data = response.data
      window.open(data.url)
    }).catch(error => {

      }
    )

    // setConfirmLoading(true);
    // setTimeout(() => {
    //   setVisible(false);
    //   setConfirmLoading(false);
    // }, 2000);
  };

  const handleCancel = () => {
    // console.log('Clicked cancel button');
    setVisible(false);
  };

  const handleHaveConnected = () => {
    setSubVisible(false);
    setVisible(false);
    // console.log(token)
    window.location.reload()
  };
  const handleCancelConnected = () => {
    setSubVisible(false);
    setVisible(false);
  };
  // const {props} = props
  // console.log("getUserInfo")

  const handleSetLineChartData = (type) => {
    setLineChartData(allLineChartData[type]);
  }
  // console.log(userAccessToken)
  let userAccessToken = getAccessToken()
  // let userAccessToken = "getAccessToken()"
  // console.log(userAccessToken)
  const fetchDashboardData = () => {
    if (userAccessToken === undefined || userAccessToken === "" || userAccessToken === 'null') {

    } else {
      getDashboardStatisticsByAccessToken({accessToken: userAccessToken}).then(response => {
        let data = response.data
        // console.log(data)

        if(response.data === null){
          response.data = defaultData
        }
        setDashboardData(response.data)
        // setLineChartData(response.data.activityTimeChartMap)
        setAllLineChartData(response.data.activityTimeChartMap)
        setHeartRateZones(response.data.hearRateZones)
        setLineChartData( response.data.activityTimeChartMap["AllTime"])
        setHeartRateZones(response.data.hearRateZones)
        setLoading(false);

      }).catch(error => {

      })

    }
  }
  useLayoutEffect(() => {
    fetchDashboardData()
  }, [])
  if (userAccessToken === undefined || userAccessToken === "" || userAccessToken === 'null') {
    return (

      <div className="dashboard-visitor-container">
        <div>
          <Avatar size={150}
                  src="https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif"/>
          <span className="display_name">User</span>
          <Button className="connect_garmin_button" onClick={showModal} type="primary">Connect to Garmin</Button>
          {/*  <span style="font-size:20px;padding-top:20px;display:inline-block;">*/}
          {/*    <Button type="primary">Connect to Garmin</Button>*/}
          {/*</span>*/}
        </div>
        <div>
          <img src={"https://wpimg.wallstcn.com/0e03b7da-db9e-4819-ba10-9016ddfdaed3"}/>
        </div>
        <Modal
          title=""
          visible={visible}
          onOk={handleConnect}
          confirmLoading={confirmLoading}
          onCancel={handleCancel}
          okText="Sure, connect now!"
          cancelText="No, will try later!"
        >
          <p>Would you like to give permission to your Garmin Connect account? If we cannot connect to your Garmin
            account,
            this may affect your experience.</p>
        </Modal>
        <Modal
          title=""
          visible={subVisible}
          onOk={handleHaveConnected}
          confirmLoading={confirmLoading}
          onCancel={handleCancelConnected}
          cancelText="Connected with error"
          okText="I have connected."
        >
          <p>{modalText}</p>
        </Modal>
      </div>

    )
  }
  // //
  // if (!dashboardData) {
  //   console.log(dashboardData)
  //   //
  //   // lineChartData = dashboardData.activityTimeChartMap["AllTime"]
  //   // heartRateZones = dashboardData.hearRateZones
  // }

  return (
    !dashboardData?<Space className="wait-for-data" size="middle">
        <Spin size="large" />
      </Space>:
    <div className="app-container">
      {/*<a*/}
      {/*  href="https://github.com/NLRX-WJC/react-antd-admin-template"*/}
      {/*  target="_blank"*/}
      {/*  rel="noopener noreferrer"*/}
      {/*  className="github-corner"*/}
      {/*></a>*/}

      <PanelGroup data={{
        ttlActivityTimes: dashboardData.ttlActivityTimes,
        ttlRunningTimes: dashboardData.ttlRunningTimes,
        ttlRiddingTimes: dashboardData.ttlRiddingTimes,
        ttlSwimmingTimes: dashboardData.ttlSwimmingTimes
      }} handleSetLineChartData={handleSetLineChartData}/>

      <LineChart
        chartData={lineChartData}
        styles={{
          padding: 12,
          backgroundColor: "#fff",
          marginBottom: "25px",
        }}
      />

      <Row gutter={32}>
        <Col xs={24} sm={24} lg={8}>
          <div className="chart-wrapper">
            <RaddarChart data={dashboardData.radarActivities}/>
          </div>
        </Col>
        <Col xs={24} sm={24} lg={8}>
          <div className="chart-wrapper">
            <PieChart data={{
              ttlRiddingTime: dashboardData.ttlRiddingTime,
              ttlRunningTime: dashboardData.ttlRunningTime,
              ttlSwimmingTime: dashboardData.ttlSwimmingTime
            }}/>
          </div>
        </Col>
        <Col xs={24} sm={24} lg={8}>
          <div className="chart-wrapper">
            <BarChart data={heartRateZones}/>
          </div>
        </Col>
      </Row>

      {/*<Row gutter={8}>*/}
      {/*  <Col*/}
      {/*    xs={24}*/}
      {/*    sm={24}*/}
      {/*    md={24}*/}
      {/*    lg={12}*/}
      {/*    xl={12}*/}
      {/*    style={{ paddingRight: "8px", marginBottom: "30px" }}*/}
      {/*  >*/}
      {/*    <TransactionTable />*/}
      {/*  </Col>*/}
      {/*  <Col*/}
      {/*    xs={24}*/}
      {/*    sm={24}*/}
      {/*    md={24}*/}
      {/*    lg={12}*/}
      {/*    xl={12}*/}
      {/*    style={{ marginBottom: "30px" }}*/}
      {/*  >*/}
      {/*    <BoxCard />*/}
      {/*  </Col>*/}
      {/*</Row>*/}
    </div>
  );
};

export default Dashboard;
