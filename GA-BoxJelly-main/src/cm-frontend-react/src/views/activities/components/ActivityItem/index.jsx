import React, {Component} from "react";
import {connect} from "react-redux";
import {PropTypes} from "prop-types";
import {Row, Col} from "antd"
import {RunningIcon, SwimmingIcon, RidingIcon} from "../../../../assets/svg";
import LineChart from "../LineChart";

class ActivityItem extends Component {
  static propTypes = {
    width: PropTypes.string,
    height: PropTypes.string,
    className: PropTypes.string,
    styles: PropTypes.object,
  };
  static defaultProps = {
    width: "100%",
    height: "300px",
    styles: {},
    className: "",
  };

  renderActivityType(type){
    if(type === "RUNNING") return <RunningIcon/>
    if(type === "OPEN_WATER_SWIMMING") return <SwimmingIcon/>
    if(type === "CYCLING") return <RidingIcon/>
    return <RunningIcon/>
  }

  render() {
    const {data} = this.props
    // console.log("data:", data)
    return (      

      <Row  className="activity-list-item">
        <Col span={1}>{
          this.renderActivityType(data.activityType)
        }

        </Col>
        <Col span={3}>
          <div className="activity-item-item">
            {data.mm + ' ' + data.yy + ' ' + data.dd}
          </div>
        </Col>
        <Col span={5}>
          <div className="activity-item-item">
            {data.activityName}
          </div>
          <div className="activity-item-item">
            {data.activityType}
          </div>
        </Col>
        <Col span={3}>
          <div className="activity-item-item">
            { data.distanceInMeters === undefined ? "---" : (data.distanceInMeters/1000).toFixed(2) } KM
          </div>
          <div className="activity-item-item">
            {/* Distance */}
          </div>
        </Col>
        <Col span={3}>
          <div className="activity-item-item">
            { data.durationInSeconds }
          </div>
          <div className="activity-item-item">
            {/* Time */}
          </div>
        </Col>
        <Col span={3}>
          <div className="activity-item-item">
            { data.averagePaceInMinutesPerKilometer === undefined ? "---" : data.averagePaceInMinutesPerKilometer.toFixed(2) }
          </div>
          <div className="activity-item-item">
            {/* AVG PACE */}
          </div>
        </Col>
        <Col span={3}>
          <div className="activity-item-item">
            { data.totalElevationGainInMeters === undefined ? "---" : data.totalElevationGainInMeters } m
          </div>
          <div className="activity-item-item">
            {/* TOTAL ASCENT */}
          </div>
        </Col>
        <Col span={3}>
          <div className="activity-item-item">
            { data.averageHeartRateInBeatsPerMinute } bpm
          </div>
          <div className="activity-item-item">
            {/* AVG HR */}
          </div>
        </Col>
      </Row>
    )
  }
}

export default connect((state) => state.app)(ActivityItem);
