import React from 'react';
import './App.css';
import RouterPage from "./Router"
import TemporaryDrawer from "./Drawer"
import ButtonAppBar from "./AppBar"
import MainShowPage from "./Main"
import axios from 'axios';
import {Map, Marker, GoogleApiWrapper} from 'google-maps-react'
import PropertyCard from "./PropertyCard"

class Home extends React.Component {
    state = {
        "loginToken": "",
        "displayState": "Welcome To FindMyHome!",
        "drawerIsOpen": false,
        "searchWord": "",
        "searchRange": 50,
        "markers": [],
        "displayedProperties": [],
        "mapCenter": {lat: 40.854885, lng: -88.081807}
    };

    handleDrawerOpen = (value) => {
        this.setState(prevState => ({
            drawerIsOpen: !prevState.drawerIsOpen
        }))
    }

    handleSearchWordChange = (value) => {
        this.setState({"searchWord": value})
    }

    handleSetMainState = (value) => {
        this.setState({"displayState": value})
    }

    handleSetToken = (value) => {
        this.setState({"loginToken": value})
        localStorage.setItem('token', value)
    }

    onMarkerClick = (props, marker, e) => {
        this.setState({
            selectedPlace: props,
            activeMarker: marker,
            showingInfoWindow: true
        });
    }

    handleSearch = (value) => {
        let url = "/search";
        this.setState({"displayState": "Search"})

        axios.get(url + "?keyword=" + this.state.searchWord + "&range=" + this.state.searchRange)
            .then((response) => {
                console.log(response);
                if (response.status === 200) {
                    console.log("Search successful");
                } else {
                    console.log("An error occurred");
                }

                // display the search results
                let properties = [];
                let dmarkers = []
                let numOfResults = response.data.length;
                for (let i = 0; i < numOfResults; ++i) {
                    let data = response.data[i];
                    if (i == 0) {
                        this.setState({"mapCenter":{lat: data.address.latitude, lng: data.address.longitude}})
                    }
                    dmarkers.push(<Marker key={i}
                                          position={{lat: data.address.latitude, lng: data.address.longitude}}
                                          name={data.name}
                                          onclick={this.onMarkerClick}/>
                    )
                    properties.push(<PropertyCard
                        key={data.id}
                        id={data.id}
                        price={data.price}
                        description={data.description}
                        streetNo={data.address.streetNo}
                        roadName={data.address.roadName}
                        city={data.address.city}
                        state={data.address.state}
                        zipCode={data.address.zipCode}
                    />);
                }
                this.setState({markers: dmarkers})
                this.setState({displayedProperties: properties});
            })
            .catch(function (error) {
                console.log(error);
            });

    }


    handleLike = () => {
        let url = "/api/liked";
        let token = localStorage.getItem("token");
        const options = {
            headers: {'Authorization': token}
        };

        axios.get(url, options)
            .then((response) => {
                console.log(response);
                if (response.status === 200) {
                    console.log("get like successful");
                } else {
                    console.log("An error occurred");
                }

                // display the search results
                let properties = [];
                let dmarkers = []
                let numOfResults = response.data.length;
                for (let i = 0; i < numOfResults; ++i) {
                    let data = response.data[i];
                    if (i == 0) {
                        this.setState({"mapCenter":{lat: data.address.latitude, lng: data.address.longitude}})
                    }
                    dmarkers.push(<Marker key={i}
                                          position={{lat: data.address.latitude, lng: data.address.longitude}}
                                          name={data.name}
                                          onClick = { this.onMarkerClick }/>)
                    properties.push(<PropertyCard
                        liked={false}
                        key={data.id}
                        id={data.id}
                        price={data.price}
                        description={data.description}
                        streetNo={data.address.streetNo}
                        roadName={data.address.roadName}
                        city={data.address.city}
                        state={data.address.state}
                        zipCode={data.address.zipCode}
                    />);
                }
                this.setState({markers: dmarkers})
                this.setState({displayedProperties: properties});
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    componentDidUpdate(prevProps, prevState) {
        if (this.state.displayState !== prevState.displayState) {
            if(this.state.displayState === "Starred"){
                this.handleLike()
            }
        }
    }


    render() {
        return (
            <div className="App">
                <ButtonAppBar handleDrawerOpen={this.handleDrawerOpen} loginToken={this.state.loginToken}
                              handleSetToken={this.handleSetToken} handleSearchWordChange={this.handleSearchWordChange}
                              handleSearch={this.handleSearch} />
                {/*<TemporaryDrawer drawerIsOpen={this.state.drawerIsOpen} handleDrawerOpen={this.handleDrawerOpen}*/}
                {/*                 handleSetMainState={this.handleSetMainState} handleLike={this.handleLike}/>*/}
                <MainShowPage displayState={this.state.displayState} markers={this.state.markers} mapCenter={this.state.mapCenter} displayedProperties={this.state.displayedProperties}/>
            </div>
        );
    }
}


export default Home;