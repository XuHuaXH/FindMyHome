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
import RouteInputForm from "./RouteInputForm"



class UserRoute extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            commuteRoutes:[],
            dialogIsOpen: false,
            nodeInfoList: [],
            routeName: '',
            departureTime: '',
            day: '',
            homeIndex: '',
            route: ''
        }
    }

    sendRouteForm = () => {
        let url = "/api/route";
        let token = localStorage.getItem("token");
        let options = {
            headers: {'Authorization': token}
        };
        axios.post(url, this.state.route, options)
            .then((response) => {
                console.log(response);
            })
            .catch((error) => {
                console.log(error);
            });
    }

    componentDidMount() {
        this.renderRoutes();
    }

    renderRoutes = () => {
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
                    let id = route.id;
                    routes.push(
                        <Grid item xs={6}>
                            <RouteCard
                                renderAction={this.renderRoutes}
                                route={route}
                                id={id}
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
        });
    }

    fillNodeInfoList = (list) => {
        this.setState({
            nodeInfoList: list
        }, this.sendRouteForm);
    }

    fillRouteName = (name) => {
        this.setState({
            name: name
        });
        console.log("route name set to " + name);
    }

    fillDepartureTime = (time) => {
        this.setState({
            departureTime: time
        });
        console.log("departureTime set to " + time);
    }

    fillTravelDay = (day) => {
        this.setState({
            day: day
        });
        console.log("travel day set to " + day);
    }
    
    fillHomeIndex = (index) => {
        this.setState({
            homeIndex: index
        });
    }

    setRouteObject = (route) => {
        this.setState({
            route: route
        }, this.sendRouteForm);
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
                        <RouteInputForm
                            closeAction={this.handleClose}
                            nodeInfoListCallback={this.fillNodeInfoList}
                            routeNameCallback={this.fillRouteName}
                            departureTimeCallback={this.fillDepartureTime}
                            travelDayCallback={this.fillTravelDay}
                            homeIndexCallback={this.fillHomeIndex}
                            sendRouteFormCallback={this.sendRouteForm}
                            routeObjectCallback={this.setRouteObject}
                        />
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

export default UserRoute;