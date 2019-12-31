import React from "react"
import PropTypes from 'prop-types';
import MapContainer from "./GMaps"
import PItem from "./PItem"
import PList from "./Plist"
import Paper from "@material-ui/core/Paper"
import { makeStyles } from '@material-ui/core/styles';
import Grid from "@material-ui/core/Grid"
import SearchBar from "./SearchBar"
import AppBar from '@material-ui/core/AppBar';

export default class MainShowPage extends React.Component {

    static propTypes = {
        displayState: PropTypes.string.isRequired
    }

    state = {
        propertyList: {},
        searchKeyWord: ""
    }



    render() {
        const useStyles = makeStyles(theme => ({
            root: {
                flexGrow: 1,
            },
            paper: {
                padding: theme.spacing(2),
                textAlign: 'left',
                color: theme.palette.text.secondary,
            },
        }));

        return (
            <div className="main">
                <Paper>
                    <Grid container spacing={0}>
                        <Grid item xs={8} style={{position: 'relative', height: '92vh'}}>
                            <MapContainer markers={this.props.markers} mapCenter={this.props.mapCenter} />
                        </Grid>
                        <Grid item xs={4} style={{position: 'relative', height: '92vh'}}>
                            <PList displayState={this.props.displayState} displayedProperties={this.props.displayedProperties} className="pList"/>
                        </Grid>
                    </Grid>
                </Paper>
            </div>
        )
    }
}