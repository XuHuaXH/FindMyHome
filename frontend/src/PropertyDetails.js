import React from "react";
import ImageCarousel from "./ImageCarousel"
import axios from 'axios';
import Typography from "@material-ui/core/Typography"
import Grid from '@material-ui/core/Grid';
import TravelTimeCard from "./TravelTimeCard"

class PropertyDetails extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            address: '',
            price: '',
            utilityCost: '',
            maintenanceCost: '',
            area: '',
            noBedroom: '',
            noBathroom: '',
            commuteRoutes:[]
        }
    }

    componentDidMount() {

        // send request to backend for property details
        let url = "/property-details?id=";
        let id = this.props.match.params.id;

        axios.get(url + id)
            .then((response) => {
                let address = response.data.address.streetNo + " " + response.data.address.roadName
                    + ", " + response.data.address.city + ", " + response.data.address.state + " "
                    + response.data.address.zipCode;
                console.log(address);
                this.setState((prevState) => ({
                    address: address,
                    price: response.data.price,
                    utilityCost: response.data.utilityCost,
                    maintenanceCost: response.data.maintenanceCost,
                    area: response.data.area,
                    noBedroom: response.data.noBedroom,
                    noBathroom: response.data.noBathroom
                }));
            })
            .catch(function (error) {
                console.log(error);
            });

        // remote request to get user's saved routes
        url = "/api/route";
        let token = localStorage.getItem("token");
        const options = {
            headers: {'Authorization': token}
        };

        axios.get(url, options)
            .then((response) => {
                console.log(response);

                // display the returned routes
                let routes = [];
                let numOfResults = response.data.length;
                for (let i = 0; i < numOfResults; ++i) {
                    let route = response.data[i];
                    routes.push(
                        <Grid item xs={6}>
                            <TravelTimeCard
                                route={route}
                                id={this.props.match.params.id}
                                index={i}
                            />
                        </Grid>
                    );
                }
                this.setState({commuteRoutes:routes});
            })
            .catch(function (error) {
                console.log(error);
            });


    }

    render() {

        return(
            <div>
                <Grid style={styles.grid} justify="center" container spacing={3}>
                    <Grid style={styles.grid} item spacing={3}>
                        <ImageCarousel />
                    </Grid>
                </Grid>
                <Typography style={styles.pos} color="textSecondary">
                    Address: {this.state.address}
                </Typography>
                <Typography style={styles.pos} color="textSecondary">
                    Price: {this.state.price}
                </Typography>
                <Typography style={styles.pos} color="textSecondary">
                    Number of Bedroom: {this.state.noBedroom}
                </Typography>
                <Typography style={styles.pos} color="textSecondary">
                    Number of Bathroom: {this.state.noBathroom}
                </Typography>
                <Typography style={styles.pos} color="textSecondary">
                    Utility Cost: {this.state.utilityCost}
                </Typography>
                <Typography style={styles.pos} color="textSecondary">
                    Maintenance Cost: {this.state.maintenanceCost}
                </Typography>
                <Grid style={styles.grid} container spacing={3}>
                    {this.state.commuteRoutes}
                </Grid>
            </div>
        );
    }
}

const styles = {
    pos: {
        marginBottom: 12,
    },
    grid: {
        marginBottom: 20,
        marginTop: 20
    }
}

export default PropertyDetails;