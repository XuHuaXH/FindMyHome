import React from "react";
import ImageCarousel from "./ImageCarousel"
import axios from 'axios';
import Typography from "@material-ui/core/Typography"

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
            noBathroom: ''

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

    }

    render() {

        return(
            <div>
                <ImageCarousel />
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

            </div>
        );
    }
}

const styles = {
    pos: {
        marginBottom: 12,
    },
}

export default PropertyDetails;