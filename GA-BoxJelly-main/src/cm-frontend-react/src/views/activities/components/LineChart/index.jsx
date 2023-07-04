import React, { Component } from "react";
import { PropTypes } from "prop-types";
import { connect } from "react-redux";
import echarts from "@/lib/echarts";
import { debounce } from "@/utils";
import { generatePreviousWeekDate } from "../../../../utils/date";
import { useState } from "react";
import { RightSquareFilled } from "@ant-design/icons";

class LineChart extends Component {
  static propTypes = {
    width: PropTypes.string,
    height: PropTypes.string,
    className: PropTypes.string,
    styles: PropTypes.object,
    chartData: PropTypes.object,
    title: PropTypes.string,
    chartOption: PropTypes.object,
  };
  static defaultProps = {
    width: "85%",
    height: "220px",
    styles: {},
    className: "",
    chartData: {},
    title: "Title",
    chartOption: {},
  };
  state = {
    chart: null,
    previousWeek: generatePreviousWeekDate(),
  };

  componentDidMount() {
    // console.log(this.state.previousWeek)
    debounce(this.initChart.bind(this), 100)();
    window.addEventListener("resize", () => this.resize());
  }
  componentWillReceiveProps(nextProps) {
    if (nextProps.sidebarCollapsed !== this.props.sidebarCollapsed) {
      this.resize();
    }
    if (nextProps.chartData !== this.props.chartData) {
      debounce(this.initChart.bind(this), 100)();
    }
  }

  componentWillUnmount() {
    this.dispose();
  }

  resize() {
    const chart = this.state.chart;
    if (chart) {
      debounce(chart.resize.bind(this), 100)();
    }
  }

  dispose() {
    if (!this.state.chart) {
      return;
    }
    window.removeEventListener("resize", () => this.resize()); // 移除窗口，变化时重置图表
    this.setState({ chart: null });
  }

  setOptions(lineChartData, chartOption) {
    this.state.chart.setOption({
      backgroundColor: "#fff",
      xAxis: {
        type: "category",
        boundaryGap: false,
        data: lineChartData.xAxis,
        splitNumber: 10,
        axisLine: {
          show: false,
          lineStyle: {
            color: "#808080",
          },
        },
      },
      title: {
        left: "center",
        text: chartOption.title,
        //y: 'top',
        textStyle: {
          fontSize: 15,
          color: "black",
        },
      },
      grid: {
        left: 10,
        right: 10,
        bottom: 10,
        top: 20,
        containLabel: true,
      },
      tooltip: {
        trigger: "axis",
        axisPointer: {
          type: "line",
          animation: false,
          link: {
            xAxisIndex: "all",
          },
        },
        padding: [5, 10],
      },
      toolbox: {
        color: "black",
        feature: {
          //dataZoom: {},
          mark: {},
          saveAsImage: {},
        },
      },
      // axisPointer: {
      //   link: {
      //       xAxisIndex: 'all',
      //   }
      // },
      yAxis: {
        type: "value",
        boundaryGap: [0, "100%"],
        splitArea: { show: false },
        axisLine: {
          show: false,
          lineStyle: {
            color: "#808080",
          },
        },
      },
      series: [
        {
          name: chartOption.title,
          type: "line",
          symbol: "none",
          sampling: "lttb",
          //smooth: true,
          itemStyle: {
            color: chartOption.areaColor,
          },
          areaStyle: {
            color: chartOption.areaColor,
          },
          data: lineChartData.yAxis,
        },
      ],
    });
  }

  initChart() {
    if (!this.el) return;
    this.setState({ chart: echarts.init(this.el, "macarons") }, () => {
      this.setOptions(this.props.chartData, this.props.chartOption);
    });
  }

  render() {
    const { className, height, width, styles } = this.props;

    return (
      <div
        className={className}
        ref={(el) => (this.el = el)}
        style={{
          ...styles,
          height,
          width,
        }}
      />
    );
  }
}

export default connect((state) => state.app)(LineChart);
