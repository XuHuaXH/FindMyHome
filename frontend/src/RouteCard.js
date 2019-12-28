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


class RouteCard extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            displayedNodes: []
        }
    }

    componentDidMount() {
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
                    <StepLabel>{address}</StepLabel>
                    <StepContent>
                        <Typography>{modes[i]}</Typography>
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
                <StepLabel>{address}</StepLabel>
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
                        <Typography style={styles.title} color="textSecondary">
                            Frequency: {frequency}
                        </Typography>
                        <Stepper orientation="vertical">
                            {this.state.displayedNodes}
                        </Stepper>
                    </CardContent>
                    <CardActions>
                        <RaisedButton label="Edit" primary={true} style={styles}/>
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

export default RouteCard;