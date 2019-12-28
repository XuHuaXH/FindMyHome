import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Grid from '@material-ui/core/Grid';
import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';
import ButtonBase from '@material-ui/core/ButtonBase';

const useStyles = makeStyles(theme => ({
    root: {
        flexGrow: 2,
    },
    paper: {
        padding: theme.spacing(2),
        margin: 'auto',
        maxWidth: 500,
    },
    image: {
        width: 128,
        height: 128,
    },
    img: {
        margin: 'auto',
        display: 'block',
        maxWidth: '100%',
        maxHeight: '100%',
    },
}));

export default function ComplexGrid() {
    const classes = useStyles();

    return (
        <div className={classes.root}>
            <Paper className={classes.paper} width={400}>
                <Grid container spacing={2}>
                    <Grid item>
                        <ButtonBase className={classes.image}>
                            <img className={classes.img} alt="complex" src="/static/images/grid/complex.jpg" />
                        </ButtonBase>
                    </Grid>
                    <Grid item xs={12} sm container>
                        <Grid item xs container direction="column" spacing={2}>
                            <Grid item xs>
                                <Typography variant="body1" alignLeft noWrap>
                                    Address
                                </Typography>
                                <Typography variant="body2" alignLeft noWrap>
                                    $233333333
                                </Typography>
                                <Typography variant="body2" color="textSecondary" noWrap>
                                    Bed:3 Bath:2 Sqft:2333
                                </Typography>
                            </Grid>
                            <Grid item>
                                <Typography variant="body2" >
                                    Remove
                                </Typography>
                            </Grid>
                        </Grid>
                    </Grid>
                </Grid>
            </Paper>
        </div>
    );
}