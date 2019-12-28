import React from 'react';
import {Map, Marker, GoogleApiWrapper} from 'google-maps-react'

export class MapContainer extends React.Component {
    state

    // handleLoadSearch (keyword) => {
    //
    // }

    render() {
        const style = {
            width: "70%",
            height: "92%"
        }

        return (
            <div className="map">
                <Map google={this.props.google} zoom={14} style={style}>

                    <Marker onClick={this.onMarkerClick}
                            name={'Current location'}/>

                </Map>
            </div>
        );
    }
}

export default GoogleApiWrapper({
    apiKey: ("AIzaSyB47LoiIS7tq_JeLpibiJzex0r8174t4TQ")
})(MapContainer)

