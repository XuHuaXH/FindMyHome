import React from 'react';
import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import RaisedButton from "material-ui/RaisedButton"
import Stepper from '@material-ui/core/Stepper';
import Step from '@material-ui/core/Step';
import StepLabel from '@material-ui/core/StepLabel';
import StepContent from '@material-ui/core/StepContent';
import axios from "axios"


class TravelTimeCard extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            displayedNodes: [],
            optimisticTime: [],
            pessimisticTime: []
        }
    }

    componentDidMount() {

        // request backend for travel time estimation
        let url = "/api/estimate-travel-time";
        let payload = {
            "id" : this.props.id
        }
        let token = localStorage.getItem("token");
        const options = {
            headers: {'Authorization': token}
        };

        axios.post(url, payload, options)
            .then((response) => {
                console.log(response);
                // arrays of length = number of trips in the route
                let optimisticTime = response.data[this.props.index].optimisticTime;
                let pessimisticTime = response.data[this.props.index].pessimisticTime;
                console.log(optimisticTime);
                console.log(pessimisticTime);
                this.setState({
                    optimisticTime: optimisticTime,
                    pessimisticTime: pessimisticTime
                }, this.displayRoutes);
            })
            .catch(function (error) {
                console.log(error);
            });

    }

    displayRoutes = () => {
        // display user saved routes
        let displayedNodes = [];
        let route = this.props.route;
        let modes = this.props.route.travelModes;
        let numOfNodes = route.nodes.length;

        for (let i = 0; i < numOfNodes - 1; ++i) {
            let node = route.nodes[i];
            let address;
            if (node.name === "home") {
                address = "Home";
            } else {
                address = this.addressToString(node.address);
            }
            displayedNodes.push(
                <Step active="true">
                    <StepLabel>
                        <Typography style={styles.title} color="textSecondary">
                            {address}
                        </Typography>
                    </StepLabel>
                    <StepContent>
                        <Typography style={styles.title} color="textSecondary">
                            {this.state.optimisticTime[i]} - {this.state.pessimisticTime[i]}
                        </Typography>
                    </StepContent>
                </Step>
            );
        }

        // process the last node
        let node = route.nodes[numOfNodes - 1];
        let address;
        if (node.name === "home") {
            address = "Home";
        } else {
            address = this.addressToString(node.address);
        }
        displayedNodes.push(
            <Step active="true">
                <StepLabel>
                    <Typography style={styles.title} color="textSecondary">
                        {address}
                    </Typography>
                </StepLabel>
            </Step>
        );
        this.setState({
            displayedNodes:displayedNodes
        })
    }


    render() {
        let route = this.props.route;
        let frequency = "";
        for (let i = 0; i < route.days.length; ++i) {
            frequency += route.days[i];
            frequency += " ";
        }
        return (
            <MuiThemeProvider>
                <Card style={styles.card}>
                    <CardContent>
                        <Typography style={styles.title} color="textSecondary">
                            Name: {route.name}
                        </Typography>
                        <Typography style={styles.title} color="textSecondary">
                            Leaving at {route.departureTime}
                        </Typography>
                        {/*<Typography style={styles.title} color="textSecondary">*/}
                        {/*    Frequency: {frequency}*/}
                        {/*</Typography>*/}
                        <Stepper orientation="vertical">
                            {this.state.displayedNodes}
                        </Stepper>
                    </CardContent>
                    <CardActions>
                        <RaisedButton label="Edit" primary={true} style={styles}/>
                        <RaisedButton label="Delete" primary={true} style={styles} onClick={() => this.deleteRoute()}/>
                    </CardActions>
                </Card>
            </MuiThemeProvider>
        );
    }

    addressToString = (address) => {
        return address.streetNo + " " + address.roadName + ", "
            + address.city + ", " + address.state + " "
            + address.zipCode
    }

}

const styles = {
    card: {
        minWidth: 275,
        margin: 20
    },
    title: {
        fontSize: 18,
    },
    pos: {
        marginBottom: 12,
    },
    media: {
        height: 0,
        paddingTop: '56.25%', // 16:9
    }
}

export default TravelTimeCard;