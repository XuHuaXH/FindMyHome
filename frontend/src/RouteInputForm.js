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

class RouteInputForm extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            nextAvailableId: 1,
            nodeInfoList: []
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

    handleClose = () => {
        this.setState({
            dialogIsOpen: false
        });
    }

    fillNodeInfoList = () => {
        this.props.fillNodeInfoList(this.state.nodeInfoList);
    }

    handleSave = () => {
        this.fillNodeInfoList();
        this.handleClose();
    }

    render() {
        return(
            <div>
                <DialogContent>
                    <DialogContentText>
                        Fill out this form to add a new route to your profile.
                        The estimated commute time of this route will appear when you view each property.
                    </DialogContentText>
                        <form style={styles.form}>
                            <TextField id="standard-basic" label="Name" />
                            <TextField id="standard-basic" label="Departure Time" />
                            {this.state.nodeInfoList.map((form, index) => (
                                <div key={index}>
                                    <Typography>Stop {index + 1}</Typography>
                                    <TextField id="standard-basic" label="Address" onChange = {
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
                            ))}
                            <Button variant="contained" color="primary" style={styles} onClick={() => this.addNode()}>
                                Add a stop
                            </Button>
                        </form>
                </DialogContent>
                <DialogActions>
                    <Button onClick={() => this.props.handleClose} color="primary">
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


export default RouteInputForm;