import React from 'react';
import {Map, Marker, GoogleApiWrapper} from 'google-maps-react'

export class MapContainer extends React.Component {
    constructor(props){
        super(props);
        this.state={
            displayedProperties:[],
            keyword:'',
            range:'',
        }
    }

    addMarker = (lat, long) => {

    }

    render() {
        const style = {
            width: "100%",
            height: "100%",
            position: 'relative'
        }

        const displayMarker = this.props.markers
        // for (let i = 0; i < this.props.markers.length; ++i){
        //     let m = this.props.markers[i]
        //     displayMarker.push(<Marker key={i} position={{lat: m[0], lng: m[1]}}/>)
        // }

        const center = this.props.mapCenter

        return (
            <div className="map">
                <Map google={this.props.google} zoom={10} style={style}
                center={center}>

                    {displayMarker}

                </Map>
            </div>
        );
    }
}

export default GoogleApiWrapper({
    apiKey: ("AIzaSyDVhmSs_lIao49lTSN2LMOXdKQrkU5xHGo")
})(MapContainer)

