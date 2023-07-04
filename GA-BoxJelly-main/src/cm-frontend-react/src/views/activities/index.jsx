import React, { useState, Component } from "react";
import { Avatar, Row, Col, Input, Space, Segmented, Modal, Button } from "antd";
import Icon, { HomeOutlined } from "@ant-design/icons";
import { RunningIcon, RidingIcon, SwimmingIcon } from "../../assets/svg";
import { Tabs } from "antd";
import { Link, Redirect } from "react-router-dom";
import { connect } from "react-redux";
import DocumentTitle from "react-document-title";
import "./index.less";
import { login, getUserInfo } from "@/store/actions";
import { LoadingOutlined } from "@ant-design/icons";
import ActivityItem from "./components/ActivityItem";
import { getAccessToken } from "../../utils/auth";
import {
  getActivityByAccessToken,
  getActivityDetailsByActivityId,
} from "@/api/activity";
import HeatMap from "./components/HeatMap";
import LineChart from "./components/LineChart";
import { getLocalTime, secondToDate } from "@/utils/date";
import { getActivityByAccessTokenAndType } from "../../api/activity";
import { Spin } from "antd";

const { TabPane } = Tabs;

const { Search } = Input;
const onSearch = (value) => console.log(value);

const Activities = () => {
  const [activityList, setActivityList] = React.useState(false);
  const [loading, setLoading] = React.useState(false);
  const [detailLoading, setDetailLoading] = React.useState(false);
  const [activityDetails, setActivityDetails] = React.useState(false);
  const [activitySummary, setActivitySummary] = React.useState(false);
  const [isModalVisible, setIsModalVisible] = useState(false);
  const [totalAscent, setTotalAscent] = useState(false);
  const [totalDescent, setTotalDescent] = useState(false);
  const [minEle, setminEle] = useState(false);
  const [maxEle, setMaxEle] = useState(false);
  const [avgTemp, setAvgTemp] = useState(false);
  const [minTemp, setMinTemp] = useState(false);
  const [maxTemp, setMaxTemp] = useState(false);
  const [heartRate, setHeartRate] = useState(false);
  const [temperature, setTemperature] = useState(false);
  const [elevation, setElevation] = useState(false);
  const [pace, setPace] = useState(false);
  const [avgPace, setAvgPace] = useState(false);
  const [coordlst, setCoordlst] = useState(false);
  const [timeList, setTime] = useState(false);
  const [showDistance, setButton] = useState(false);
  const [distanceList, setDistance] = useState(true);
  const [cadence, setCadence] = useState(false);
  const [strokes, setStrokes] = useState(false);
  const [activityType, setActivityType] = useState(false);
  const [power, setPower] = useState(false);
  const [speed, setSpeed] = useState(false);
  const [avgSpeed, setAvgSpeed] = useState(false);
  const [avgCadence, setAvgCadence] = useState(false);
  const [avgStroke, setAvgStroke] = useState(false);
  const [avgPower, setAvgPower] = useState(false);
  const [avgElev, setAvgElev] = useState(false);
  const [chartLoading, setChartLoading] = useState(true);

  const showActivityDetail = (v) => {
    setActivitySummary(v);
    setChartLoading(false);
    getActivityDetailsByActivityId({ activityId: v.activityId })
      .then((response) => {
      console.log(response)
        let data = response.data;
        setActivityDetails(data);

        setActivityType(data.summary.activityType);
        setTotalAscent(data.summary.totalElevationGainInMeters.toFixed(1));
        setTotalDescent(data.summary.totalElevationLossInMeters);
        let eleLst = [];
        let tempLst = [];
        let coordlst = [];
        let heartRateList = [];
        let paceList = [];
        let timeList = [];
        let distanceList = [];
        let cadenceList = [];
        let strokesList = [];
        let powerList = [];
        let speedList = [];
        for (let i = 0; i < data.samples.length; i++) {
          let tmp = data.samples[i];
          eleLst.push(formatNumber(tmp.elevationInMeters));
          tempLst.push(tmp.airTemperatureCelcius);
//           coordlst.push(23.3333, 23.33333);
          heartRateList.push(tmp.heartRate);
          paceList.push(speedToPace(tmp.speedMetersPerSecond));
          timeList.push(secondToDate(tmp.timerDurationInSeconds));
          distanceList.push((tmp.totalDistanceInMeters / 1000).toFixed(2));
          cadenceList.push(tmp.bikeCadenceInRPM);
          strokesList.push(tmp.swimCadenceInStrokesPerMinute);
          powerList.push(tmp.powerInWatts);
          speedList.push(formatNumber(tmp.speedMetersPerSecond));
        }

        setminEle(Math.min(...eleLst).toFixed(1));
        setMaxEle(Math.max(...eleLst).toFixed(1));

        setMinTemp(Math.min(...tempLst).toFixed(1));
        setMaxTemp(Math.max(...tempLst).toFixed(1));
        setHeartRate(heartRateList);
        setTemperature(tempLst);
        setElevation(eleLst);
        setPace(paceList);
//         setCoordlst(coordlst);
        setTime(timeList);
        setDistance(distanceList);
        setCadence(cadenceList);
        setStrokes(strokesList);
        setPower(powerList);
        setSpeed(speedList);
        // console.log(paceList);
        console.log(data.samples[0])
        console.log(formatNumber(10.324566))

        let tempSum = 0;
        for (let i = 0; i < tempLst.length; i++) {
          tempSum += tempLst[i];
        }
        setAvgTemp((tempSum / tempLst.length).toFixed(1));

        let paceSum = 0;
        for (let i = 0; i < paceList.length; i++) {
          paceSum += paceList[i];
        }
        setAvgPace((paceSum / paceList.length).toFixed(1));

        let speedSum = 0;
        for (let i = 0; i < speedList.length; i++) {
          speedSum += speedList[i];
        }
        setAvgSpeed((speedSum / speedList.length).toFixed(1));

        let cadenceSum = 0;
        for (let i = 0; i < cadenceList.length; i++) {
          cadenceSum += cadenceList[i];
        }
        setAvgCadence((cadenceSum / cadenceList.length).toFixed(1));

        let strokeSum = 0;
        for (let i = 0; i < strokesList.length; i++) {
          strokeSum += strokesList[i];
        }
        setAvgStroke((strokeSum / strokesList.length).toFixed(1));

        let powerSum = 0;
        for (let i = 0; i < powerList.length; i++) {
          powerSum += powerList[i];
        }
        setAvgPower((powerSum / powerList.length).toFixed(1));

        let elevSum = 0;
        for (let i = 0; i < eleLst.length; i++) {
          elevSum += eleLst[i];
        }
        setAvgElev((elevSum / eleLst.length).toFixed(1));
        setChartLoading(false);
        console.log(chartLoading);

        this.activityDetailsVisible = true;
      })
      .catch((error) => {});
    setIsModalVisible(true);
  };

  const handleOk = () => {
    setIsModalVisible(false);
  };

  const handleCancel = () => {
    setIsModalVisible(false);
  };
  // let activityList = []
  React.useEffect(() => {
    setLoading(true);
    getActivityByAccessToken({ accessToken: getAccessToken() })
      .then((response) => {
        let data = response.data;
        for (let i = 0; i < data.length; i++) {
          let tmp = data[i];

          data[i].date = getLocalTime(tmp.startTimeInSeconds).split(",")[0];
          data[i].durationInSeconds = secondToDate(tmp.durationInSeconds);
          let date = data[i].date.split(" ");
          // console.log(data[i].date)
          data[i].dd = date[0];
          data[i].mm = date[1];
          data[i].yy = date[2];
        }
        data.reverse();
        setActivityList(data);
        setLoading(false);
      })
      .catch();
  }, []);
  const callback = (key) => {
    console.log(key);
  };

  const changeActivityType = (activityType) => {
    setLoading(true);
    getActivityByAccessTokenAndType({
      accessToken: getAccessToken(),
      activityType: activityType,
    })
      .then((response) => {
        let data = response.data;
        for (let i = 0; i < data.length; i++) {
          let tmp = data[i];

          data[i].date = getLocalTime(tmp.startTimeInSeconds).split(",")[0];
          data[i].durationInSeconds = secondToDate(tmp.durationInSeconds);
          let date = data[i].date.split(" ");
          // console.log(data[i].date)
          data[i].dd = date[0];
          data[i].mm = date[1];
          data[i].yy = date[2];
        }

        setActivityList(data);
        setLoading(false);
      })
      .catch((error) => {});
  };
  const divStyle = {
    color: "blue",
  };

  const handleOnClick = () => {
    setButton(!showDistance);
  };

  const speedToPace = (speed) => {
    if (speed === 0) {
      return 0;
    }

    return formatNumber((1 / ((60 * speed) / 1000)));
  };

  const formatNumber = (number) => {
    return parseFloat(number.toFixed(2))
  }

  if (!activityList) return null;
  return (
    <div className="activities-container">
      <Row className="activities-bar">
        {/* <Col span={6}>
          <Search
            placeholder="input search text"
            allowClear
            enterButton="Search"
            size="large"
            onSearch={onSearch}
          />
        </Col> */}
        <Col span={24} offset={0}>
          <Segmented
            size="small"
            block
            onChange={changeActivityType}
            options={[
              "All",
              {
                value: "WALKING",
                icon: <RunningIcon />,
              },
              {
                value: "OPEN_WATER_SWIMMING",
                icon: <SwimmingIcon color={"#fff"} />,
              },
              {
                value: "CYCLING",
                icon: <RidingIcon color={"#1776d0"} />,
              },
            ]}
          />
        </Col>
      </Row>

      <Modal
        title="Activity Detail"
        visible={isModalVisible}
        onOk={handleOk}
        onCancel={handleCancel}
        className="activity_detail_modal"
        width={1000}
      >
        <div className="this-activity">
          <ActivityItem
            className="this-activity-header"
            data={activitySummary}
          />
          <Row className="this-activity-detail">
            <Col span={9}></Col>
            <Col span={3}>Distance</Col>
            <Col span={3}>Time</Col>
            <Col span={3}>AVG Pace</Col>
            <Col span={3}>Total Ascent</Col>
            <Col span={3}>AVG Heart Rate</Col>
          </Row>
        </div>

        <HeatMap
//           key={coordlst}

          id={activitySummary.activityId}
          data={[37.7749,-122.4194]}
          heartRateList={heartRate}
        />

        <div className="chart-by-button">
          <span className="chart-by-text">Visualise charts by</span>
          <Button type="default" className="button-item" onClick={handleOnClick}>
            {showDistance ? "Distance" : "Time"}
          </Button>
          <span className="switch-text">Click to switch on TIME / DISTANCE</span>
        </div>

        <Spin spinning={chartLoading} size="large">
          <Row>
            <LineChart
              chartData={{
                xAxis: showDistance ? distanceList : timeList,
                yAxis: heartRate,
              }}
              chartOption={{
                title: "Heart Rate",
                areaColor: "rgb(219, 96, 96)",
              }}
              styles={{
                padding: 12,
                backgroundColor: "#fff",
                marginTop: "10px",
                marginBottom: "15px",
              }}
            />
            <span className="avg_data_chart">
              Avg HR:{" "}
              {activitySummary.averageHeartRateInBeatsPerMinute === undefined
                ? "0"
                : activitySummary.averageHeartRateInBeatsPerMinute}{" "}
              bpm
            </span>
          </Row>
        </Spin>

        <Spin spinning={chartLoading} size="large">
          <Row>
            <LineChart
              chartData={{
                xAxis: showDistance ? distanceList : timeList,
                yAxis: temperature,
              }}
              chartOption={{
                title: "Temperature",
                areaColor: "rgb(143, 145, 142)",
              }}
              styles={{
                padding: 12,
                backgroundColor: "#fff",
                marginBottom: "15px",
              }}
            />
            <span className="avg_data_chart">Avg Temp: {avgTemp}°C</span>
          </Row>
        </Spin>

        {activityType != "OPEN_WATER_SWIMMING" ? (
          <Spin spinning={chartLoading} size="large">
            <Row>
              <LineChart
                chartOption={{
                  title: "Elevation",
                  areaColor: "rgb(102, 209, 86)",
                }}
                chartData={{
                  xAxis: showDistance ? distanceList : timeList,
                  yAxis: elevation,
                }}
                styles={{
                  padding: 12,
                  backgroundColor: "#fff",
                  marginBottom: "15px",
                }}
              />
              <span className="avg_data_chart">Avg Elev: {avgElev} m</span>
            </Row>
          </Spin>
        ) : null}

        {activityType === "WALKING" ? (
          <Spin spinning={chartLoading} size="large">
            <Row>
              <LineChart
                chartOption={{
                  title: "Pace",
                  areaColor: "rgb(144, 225, 245)",
                }}
                chartData={{
                  xAxis: showDistance ? distanceList : timeList,
                  yAxis: pace,
                }}
                styles={{
                  padding: 12,
                  backgroundColor: "#fff",
                  marginBottom: "15px",
                }}
              />

              <span className="avg_data_chart">Avg Pace: {avgPace}</span>
            </Row>
          </Spin>
        ) : null}

        {activityType === "CYCLING" ? (
          <Spin spinning={chartLoading} size="large">
            <Row>
              <LineChart
                chartOption={{
                  title: "Cadence",
                  areaColor: "rgb(161, 121, 181)",
                }}
                chartData={{
                  xAxis: showDistance ? distanceList : timeList,
                  yAxis: cadence,
                }}
                styles={{
                  padding: 12,
                  backgroundColor: "#fff",
                  marginBottom: "15px",
                }}
              />
              <span className="avg_data_chart">
                Avg Cadence: {avgCadence} RPM
              </span>
            </Row>
          </Spin>
        ) : null}

        {activityType === "CYCLING" ? (
          <Spin spinning={chartLoading} size="large">
            <Row>
              <LineChart
                chartOption={{
                  title: "Power",
                  areaColor: "rgb(255, 130, 60)",
                }}
                chartData={{
                  xAxis: showDistance ? distanceList : timeList,
                  yAxis: power,
                }}
                styles={{
                  padding: 12,
                  backgroundColor: "#fff",
                  marginBottom: "15px",
                }}
              />

              <span className="avg_data_chart">Avg Power: {avgPower} w</span>
            </Row>
          </Spin>
        ) : null}

        {activityType != "WALKING" ? (
          <Spin spinning={chartLoading} size="large">
            <Row>
              <LineChart
                chartOption={{
                  title: "Speed",
                  areaColor: "rgb(144, 225, 245)",
                }}
                chartData={{
                  xAxis: showDistance ? distanceList : timeList,
                  yAxis: speed,
                }}
                styles={{
                  padding: 12,
                  backgroundColor: "#fff",
                  marginBottom: "15px",
                }}
              />
              <span className="avg_data_chart">Avg Speed: {avgSpeed} m/s</span>
            </Row>
          </Spin>
        ) : null}

        {activityType === "OPEN_WATER_SWIMMING" ? (
          <Spin spinning={chartLoading} size="large">
            <Row>
              <LineChart
                chartOption={{
                  title: "Stroke Rate",
                  areaColor: "rgb(255, 160, 228)",
                }}
                chartData={{
                  xAxis: showDistance ? distanceList : timeList,
                  yAxis: strokes,
                }}
                styles={{
                  padding: 12,
                  backgroundColor: "#fff",
                  marginBottom: "40px",
                }}
              />

              <span className="avg_data_chart">Avg SR: {avgStroke} / min</span>
            </Row>
          </Spin>
        ) : null}

{/* stat */}
        <Tabs defaultActiveKey="1" onChange={callback}>
          <TabPane tab="Stat" key="1">
            <Row>
              <Col span={5}>
                <span className="detail-item-name">Distance</span>
                <span className="detail-item">
                  {activitySummary.distanceInMeters === undefined
                    ? "0"
                    : (activitySummary.distanceInMeters / 1000).toFixed(2)}{" "}
                  KM
                </span>
                <span className="detail-item-name">Calories</span>
                <span className="detail-item">
                  {activitySummary.activeKilocalories === undefined
                    ? "0"
                    : activitySummary.activeKilocalories}
                </span>
                <span className="detail-name">Calories</span>
              </Col>
              <Col span={5} className="detail-col">
                <span className="detail-item-name">Heart Rate</span>
                <span className="detail-item">
                  {activitySummary.averageHeartRateInBeatsPerMinute ===
                  undefined
                    ? "0"
                    : activitySummary.averageHeartRateInBeatsPerMinute}{" "}
                  bpm
                </span>

                <span className="detail-name">Avg HR </span>
                <span className="detail-item">
                  {activitySummary.maxHeartRateInBeatsPerMinute === undefined
                    ? "0"
                    : activitySummary.maxHeartRateInBeatsPerMinute}{" "}
                  bpm
                </span>

                <span className="detail-name">Max HR </span>
                <span className="detail-item-name">Timing</span>
                {activitySummary.durationInSeconds === undefined
                  ? "0"
                  : activitySummary.durationInSeconds}
                <span className="detail-name">Second</span>
              </Col>
              <Col span={5} className="detail-col">
                <span className="detail-item-name">Elevation</span>
                <span className="detail-item">
                  {totalAscent === "" ? "0" : totalAscent} meters
                </span>
                <span className="detail-name">Total Ascent</span>
                <span className="detail-item">
                  {totalDescent === undefined ? "0" : totalDescent} meters
                </span>
                <span className="detail-name">Total Descent</span>
                <span className="detail-item">{minEle} meters</span>
                <span className="detail-name">Min Elev</span>
                <span className="detail-item">{maxEle} meters</span>
                <span className="detail-name">Max Elev</span>
              </Col>
              <Col span={5} className="detail-col">
                <span className="detail-item-name">Temperature</span>
                <span className="detail-item">{avgTemp} °C</span>
                <span className="detail-name">Avg Temp</span>
                <span className="detail-item">{minTemp} °C</span>
                <span className="detail-name">Min Temp</span>
                <span className="detail-item">{maxTemp} °C</span>
                <span className="detail-name">Max Temp</span>
              </Col>
            </Row>
          </TabPane>
          {/* <TabPane tab="Heart Rate Zone" key="2">
						Content of Tab Pane 2
					</TabPane>
					<TabPane tab="Laps" key="3">
						Content of Tab Pane 3
					</TabPane> */}
        </Tabs>
      </Modal>
      <div>
        <Row className="activity-header">
          <Col span={9}>Activity</Col>
          <Col span={3}>Distance</Col>
          <Col span={3}>Time</Col>
          <Col span={3}>AVG Pace</Col>
          <Col span={3}>Total Ascent</Col>
          <Col span={3}>AVG Heart Rate</Col>
        </Row>
        {loading ? (
          <LoadingOutlined className="loading-style" spin />
        ) : (
          activityList.map((v, i) => {
            return (
              <div key={i} onClick={() => showActivityDetail(v)}>
                <ActivityItem data={v} />
              </div>
            );
          })
        )}
      </div>
    </div>
  );
};
export default Activities;
// export default connect((state) => state.app)(Activities);
