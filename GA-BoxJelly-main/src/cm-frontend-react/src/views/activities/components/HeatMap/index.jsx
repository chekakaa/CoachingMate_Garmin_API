/*global google*/

import React, { Component } from 'react';
import GoogleMapReact from 'google-map-react';
import { LoadingOutlined } from "@ant-design/icons";
import img from '../../../../assets/images/img.png'

class SimpleMap extends Component {

	static defaultProps = {
		center: {
			lat: -37.813,
			lng: 144.96
		},
		zoom: 11,
	};

	dataReload = true;

	state = {
		activityId: null,
		color: [],
		fullCoordinates: [],
		coordinates: [-37.813, 144.96],
		loading: true,
		centerLat: -37.813,
		centerLng: 144.96
	}


	processingCoordinate(data, heartRateList) {
		if (!this.dataReload) { return }
		var fullCoordinates = new Array();
		var coordlst = new Array();
		var segmentlst = new Array();
		var colorlst = new Array();
		var colors = ["#4169E1", "#32CD32", "#F1AF09", "#FF8C00", "FF0000"];
		var bpm = [0, 111, 129, 148, 166];

		var flag = false;
		var bpmPtr = -1; // point to index

		var centerLat = 0;
		var centerLng = 0;
		for (let i = 0; i < data.length; i++) {
			if (data[i][0] == 0 && data[i][1] == 0) {
				continue
			}
			fullCoordinates.push({ lat: data[i][0], lng: data[i][1] });

			centerLat += data[i][0];
			centerLng += data[i][1];
			if (flag == false) {
				flag = true;
				bpmPtr = -1;
				for (let j = 0; j < bpm.length; j++) {
					if (heartRateList[i] >= bpm[j]) {
						bpmPtr += 1;
					}
				}
				colorlst.push(colors[bpmPtr]);

				segmentlst.push({ lat: data[i][0], lng: data[i][1] });
			}
			else {
				if (heartRateList[i] >= bpm[bpmPtr + 1] || heartRateList[i] <= bpm[bpmPtr]) {
					// if bpm changed, push current segment into coordlst
					// greater or equals
					//console.log("BPM changed, current: ", bpm[bpmPtr], "\n next: ", heartRateList[i+1]);
					flag = false;
					segmentlst.push({ lat: data[i][0], lng: data[i][1] });
					coordlst.push(segmentlst);
					segmentlst = [];
				}
				segmentlst.push({ lat: data[i][0], lng: data[i][1] });
			}
		}
		coordlst.push(segmentlst);
		centerLat = centerLat / fullCoordinates.length
		centerLng = centerLng / fullCoordinates.length
		this.setState({
			color: colorlst, coordinates: coordlst, fullCoordinates: fullCoordinates,
			center: { lat: centerLat, lng: centerLng }
		})
		this.dataReload = false
	}



	componentWillUnmount() {
		this.dataReload = true
	}

	componentWillReceiveProps(nextProps) {
		console.log("accept",nextProps);
// 		console.log(this.myMap)

		const { id } = nextProps;
		if (id != this.state.activityId) {
			this.setState({ loading: true, activityId: id });
			const { data } = nextProps;
			const { heartRateList } = nextProps;
			this.processingCoordinate(data, heartRateList)
		}

	}


	render() {
		const apiIsLoaded = (map, maps) => {
// 			for (let i = 0; i < this.state.coordinates.length; i++) {
				const polyline = new maps.Polyline({
					path: [
                            [
                              { lat: 37.7749, lng: -122.4194 },
                              { lat: 37.7751, lng: -122.4150 },
                              { lat: 37.7766, lng: -122.4148 },
                              // ...
                            ],
                            [
                              { lat: 37.7831, lng: -122.4039 },
                              { lat: 37.7827, lng: -122.4024 },
                              { lat: 37.7813, lng: -122.4015 },
                              // ...
                            ],
                             [
                                                          { lat: 37.7869, lng: -122.4039 },
                                                          { lat: 37.7877, lng: -122.4024 },
                                                          { lat: 37.7899, lng: -122.4015 },
                                                          // ...
                                                        ],
                            // ...
                          ],

					geodesic: true,
					strokeColor: "#32CD32",
					strokeOpacity: 1.0,
					strokeWeight: 4,
				})
				polyline.setMap(map);
// 			}
// 			if (this.state.coordinates.length != 0) {
				const StartingPoint = new maps.Marker({
					label: { text: 'Start', color: "blue", fontSize: '10' },
					position:[
                               { lat: 37.7749, lng: -122.4194 },
//                                { lat: 37.7813, lng: -122.4015 }
                             ]

				});
				StartingPoint.setMap(map);
				const EndingPoint = new maps.Marker({
					label: { text: 'End', color: "white", fontSize: '10' },
// 					position: this.state.fullCoordinates[this.state.fullCoordinates.length - 1]
                    position:[
                              { lat: 37.7827, lng: -122.4024 },
                              { lat: 37.7813, lng: -122.4015 },
                             ]

				});
				EndingPoint.setMap(map);
// 			}
			if (this.state.coordinates.length != 0) {
				this.setState({ loading: false });
			}
console.log(maps)

		};
		return (
			// Important! Always set the container height explicitly
			<div>
				<div style={{ height: '600px', width: '100%', display: this.state.loading ? 'block' : 'none' }}>
					<LoadingOutlined className="loading-style" spin />
				</div>
				<div style={{ height: '600px', width: '100%'}}>
					<GoogleMapReact
						bootstrapURLKeys={{ key: "AIzaSyBVir4piyrytvVaLuXWORBoiJGu8KFT3xQ" }}
						defaultCenter={{
							lat: 37.7813,
							lng: -122.4015
						}}
						defaultZoom={14}
						center={[37.7813, -122.40156]}
						yesIWantToUseGoogleMapApiInternals
						onGoogleApiLoaded={({ map, maps }) => apiIsLoaded(map, maps)}
					>
					</GoogleMapReact>

				</div>
				<img src={ img } style={{ marginLeft: '38%', display: this.state.loading ? 'none' : 'block' }} />

			</div>
		)
	}
}

export default SimpleMap;
