import React from "react"
import axios from "axios"
import RouteCard from "./RouteCard"
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import Grid from '@material-ui/core/Grid';
import Fab from '@material-ui/core/Fab';
import AddIcon from '@material-ui/icons/Add';
import Typography from "@material-ui/core/Typography"
import {Marker} from "google-maps-react"
import PropertyCard from "./PropertyCard"



class FavProperties extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            favProperties:[]
        }
    }

    componentDidMount() {
        this.renderFavProperties();
    }

    renderFavProperties = () => {
        // remote request to get user's liked properties
        let url = "/api/liked";
        let token = localStorage.getItem("token");
        const options = {
            headers: {'Authorization': token}
        };

        axios.get(url, options)
            .then((response) => {
                console.log(response);

                // display the search results
                let favProperties = [];
                let numOfResults = response.data.length;
                for (let i = 0; i < numOfResults; ++i) {
                    let data = response.data[i];
                    favProperties.push(
                        <Grid item xs={6}>
                            <PropertyCard
                                renderAction={this.renderFavProperties}
                                liked={true}
                                key={data.id}
                                id={data.id}
                                price={data.price}
                                description={data.description}
                                streetNo={data.address.streetNo}
                                roadName={data.address.roadName}
                                city={data.address.city}
                                state={data.address.state}
                                zipCode={data.address.zipCode}
                            />
                        </Grid>);
                }
                this.setState({favProperties: favProperties});
            })
            .catch(function (error) {
                console.log(error);
            });
        console.log("favorite properties rendered...");
    }



    render() {

        return (
            <div>
                <Grid style={styles.grid} container spacing={3}>
                    {this.state.favProperties}
                </Grid>
            </div>
        );
    }
}


const styles = {
    margin: 15,
    root: {
        flexGrow: 1,
    },
    grid: {
        padding: 15
    }
};

export default FavProperties;