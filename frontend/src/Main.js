import React from "react"
import PropTypes from 'prop-types';
import MapContainer from "./GMaps"
import PItem from "./PItem"
import PList from "./Plist"
import Paper from "@material-ui/core/Paper"
import { makeStyles } from '@material-ui/core/styles';
import Grid from "@material-ui/core/Grid"

export default class MainShowPage extends React.Component {

    static propTypes = {
        PageType: PropTypes.string.isRequired
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
                        <Grid item xs={8}>
                            <MapContainer />
                        </Grid>
                        <Grid item xs={4}>
                            <PList className="pList"/>
                        </Grid>
                        {this.props.PageType}
                    </Grid>
                </Paper>
            </div>
        )
    }
}