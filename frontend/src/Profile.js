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


class Profile extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            commuteRoutes:[],
            dialogIsOpen: false,
            nodeInputForms: [],
            addressList: []
        }
    }

    componentDidMount() {

        // remote request to get user's saved routes
        let url = "/api/route";
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
                            <RouteCard
                                route={route}
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

    handleClickOpen = () => {
        this.setState({
            dialogIsOpen: true
        })
    };

    handleClose = () => {
        this.setState({
            dialogIsOpen: false
        })
    };

    addNode = () => {

        // add new input field to the form
        let addressIndex = this.state.addressList.length;
        let newInput =
            <div>
                <Typography>Origin</Typography>
                <TextField id="standard-basic" label="Address" onChange = {
                    (event) => {
                        const addresses = this.state.addressList;
                        addresses[addressIndex] = event.target.value;
                        this.setState({
                            addressList: addresses
                        });
                    }
                }/>
            </div>;
        this.setState(prevState => ({
            nodeInputForms: prevState.nodeInputForms.concat([newInput]),
            addressList: prevState.addressList.concat([""])
        }));
    }

    handleAddressChange = (event, newValue, addressIndex) => {
        const addresses = this.state.addressList;
        addresses[addressIndex] = newValue;
        this.setState({
            addressList: addresses
        });
        console.log("new value is " + newValue);
    }



    render() {

        return (
            <div>
                <div style={{display: "block", float: "right", margin: 30}}>
                    <Fab color="primary" aria-label="add" onClick={() => this.handleClickOpen()}>
                        <AddIcon/>
                    </Fab>
                </div>
                <Dialog open={this.state.dialogIsOpen} onClose={() => this.handleClose()} aria-labelledby="form-dialog-title">
                    <DialogTitle id="form-dialog-title">Add a new route</DialogTitle>
                    <DialogContent>
                        <DialogContentText>
                            Fill out this form to add a new route to your profile.
                            The estimated commute time of this route will appear when you view each property.
                        </DialogContentText>
                        <form style={styles.form}>
                            <TextField id="standard-basic" label="Name" />
                            <TextField id="standard-basic" label="Departure Time" />
                            {this.state.nodeInputForms}
                            <Button variant="contained" color="primary" onClick={() => this.addNode()}>
                                Add a stop
                            </Button>
                        </form>
                    </DialogContent>
                    <DialogActions>
                        <Button onClose={() => this.handleClose()} color="primary">
                            Cancel
                        </Button>
                        <Button onClose={() => this.handleClose()} color="primary">
                            Save
                        </Button>
                    </DialogActions>
                </Dialog>
                <Grid style={styles.grid} container spacing={3}>
                    {this.state.commuteRoutes}
                </Grid>
            </div>
        );
    }

    handleClick = () => {
        let url = "/hello/name";
        let token = localStorage.getItem("token");
        const options = {
            headers: {'Authorization': token}
        };

        axios.get(url, options)
            .then((response) => {
                console.log(response);
                let username = response.data;
                this.setState({
                    username: username
                });
            })
            .catch(function (error) {
                console.log(error);
            });
    }
}
const styles = {
    margin: 15,
    root: {
        flexGrow: 1,
    },
    form: {
        margin: 10,
        width: 200
    },
    grid: {
        padding: 15
    }
};

export default Profile;