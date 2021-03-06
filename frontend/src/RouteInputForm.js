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
import {node} from "prop-types"
import Radio from '@material-ui/core/Radio';
import FormControlLabel from "@material-ui/core/FormControlLabel"
import FormControl from "@material-ui/core/FormControl"
import Select from "@material-ui/core/Select"
import MenuItem from "@material-ui/core/MenuItem"
import FormHelperText from "@material-ui/core/FormHelperText"
import InputLabel from "@material-ui/core/InputLabel"

class RouteInputForm extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            nextAvailableId: 1,
            nodeInfoList: [],
            routeName: '',
            departureTime: '',
            day: '',
            selectedRadio: 0
        }
    }

    addNode = () => {

        const nodeInfo = {
            id: this.state.nextAvailableId,
            address: '',
            travelMode: ''
        }
        let nodeInfoList = this.state.nodeInfoList;
        nodeInfoList.push(nodeInfo);
        let nextAvailableId = this.state.nextAvailableId;
        nextAvailableId += 1;

        this.setState({
            nodeInfoList: nodeInfoList,
            nextAvailableId: nextAvailableId
        });
        console.log("now the form has " + this.state.nextAvailableId + " nodes");
    }


    sendRouteObject = () => {
        let nodeInfoList = this.state.nodeInfoList;
        let travelModes = [];
        let nodes = [];
        for (let i = 0; i < nodeInfoList.length; ++i) {
            let node = {
                "name" : this.state.selectedRadio === i ? "home" : nodeInfoList[i].id,
                "simpleAddress" : {
                    "address": nodeInfoList[i].address
                }
            };
            nodes.push(node);
            if (i !== 0) {
                travelModes.push(nodeInfoList[i].travelMode);
            }
        }
        let days = [];
        days.push(this.state.day);
        console.log("days is " + days);

        let route = {
            name: this.state.routeName,
            departureTime: this.state.departureTime,
            days: days,
            travelModes: travelModes,
            nodes: nodes
        };
        this.props.routeObjectCallback(route);
    }

    handleSave = () => {
        this.sendRouteObject();
        this.props.closeAction();
    }


    render() {
        return(
            <div>
                <DialogContent>
                    <DialogContentText>
                        Fill out this form to add a new route to your profile.
                        The estimated commute time of this route will appear when you view each property.
                    </DialogContentText>
                    <TextField style={styles.form} id="standard-basic" label="Name" onChange = {
                        (event) => {
                            this.setState({
                                routeName: event.target.value
                            });
                        }
                    }/>
                    <TextField style={styles.form} id="standard-basic" label="Departure Time" onChange = {
                        (event) => {
                            this.setState({
                                departureTime: event.target.value
                            });
                        }
                    }/>
                    <FormControl style={styles.form}>
                        <InputLabel id="demo-simple-select-label">Traveling on</InputLabel>
                        <Select
                            labelId="demo-simple-select-label"
                            id="demo-simple-select"
                            onChange ={
                                (event) => {
                                    this.setState({
                                        day: event.target.value
                                    });
                                }
                            }
                        >
                            <MenuItem value="Monday">Monday</MenuItem>
                            <MenuItem value="Tuesday">Tuesday</MenuItem>
                            <MenuItem value="Wednesday">Wednesday</MenuItem>
                            <MenuItem value="Thursday">Thursday</MenuItem>
                            <MenuItem value="Friday">Friday</MenuItem>
                            <MenuItem value="Saturday">Saturday</MenuItem>
                            <MenuItem value="Sunday">Sunday</MenuItem>
                        </Select>
                    </FormControl>
                            <Grid style={styles.grid} container spacing={3}>
                            {this.state.nodeInfoList.map((form, index) => (
                                <Grid item xs={6}>
                                <div key={index}>
                                    <Typography style={{marginLeft: 15}}>Stop {index + 1}</Typography>
                                    <FormControl
                                        style={index === 0 ? styles.noTravelMode : styles.travelMode}>
                                        <InputLabel id="demo-simple-select-label">Travel Mode</InputLabel>
                                        <Select
                                            labelId="demo-simple-select-label"
                                            id="demo-simple-select"
                                                       onChange = {
                                                           (event) => {
                                                               let nodeInfoList = this.state.nodeInfoList;
                                                               nodeInfoList[index].travelMode = event.target.value;
                                                               this.setState({
                                                                   nodeInfoList: nodeInfoList
                                                               });
                                                               console.log("the " + index + "th item in the list changed");
                                                           }
                                                       }
                                            >
                                                    <MenuItem value="driving">driving to</MenuItem>
                                                    <MenuItem value="transit">transit to</MenuItem>
                                                    <MenuItem value="walking">walking to</MenuItem>
                                                    <MenuItem value="bicycling">bicycling to</MenuItem>
                                        </Select>
                                    </FormControl>
                                    <TextField  disabled={this.state.selectedRadio === index}
                                                style={styles.form}
                                                id='standard-basic'
                                                label={this.state.selectedRadio === index ? "Home" : "Address"}
                                                onChange = {
                                        (event) => {
                                            let nodeInfoList = this.state.nodeInfoList;
                                            nodeInfoList[index].address = event.target.value;
                                            this.setState({
                                                nodeInfoList: nodeInfoList
                                            });
                                            console.log("the " + index + "th item in the list changed");
                                        }
                                    }/>
                                </div>
                                    <FormControlLabel control={<Radio
                                        style={{marginLeft: 15}}
                                        checked={this.state.selectedRadio === index}
                                        onChange={() => this.setState({
                                        selectedRadio: index
                                    })} />} label="Select as Home"/>
                                </Grid>
                            ))}
                            </Grid>
                            <Button
                                variant="contained"
                                style={{marginLeft: 15}}
                                color="primary"
                                onClick={() => this.addNode()}>
                                Add a stop
                            </Button>
                </DialogContent>
                <DialogActions>
                    <Button onClick={this.props.closeAction} color="primary">
                        Cancel
                    </Button>
                    <Button onClick={() => this.handleSave()} color="primary">
                        Save
                    </Button>
                </DialogActions>
            </div>
        );
    }
}

const styles = {
    root: {
        flexGrow: 1,
    },
    form: {
        marginLeft: 15,
        marginRight: 15,
        width: 150
    },
    travelMode: {
        marginLeft: 15,
        marginRight: 15,
        width: 150,
        visibility: 'visible'
    },
    noTravelMode: {
        marginLeft: 15,
        marginRight: 15,
        width: 150,
        visibility: 'hidden'
    },
    grid: {
        padding: 15
    },
};


export default RouteInputForm;