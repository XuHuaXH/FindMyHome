import React from 'react';
import { fade, makeStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import Popover from '@material-ui/core/Popover';
import Button from '@material-ui/core/Button';
import IconButton from '@material-ui/core/IconButton';
import MenuIcon from '@material-ui/icons/Menu';
import Login from "./Login"
import SearchIcon from '@material-ui/icons/Search';
import InputBase from '@material-ui/core/InputBase';
import RaisedButton from "material-ui/RaisedButton"
import MuiThemeProvider from "material-ui/styles/MuiThemeProvider"

const useStyles = makeStyles(theme => ({
    root: {
        flexGrow: 1,
    },
    menuButton: {
        marginRight: theme.spacing(2),
    },
    title: {
        flexGrow: 1,
    },
    search: {
        position: 'relative',
        borderRadius: theme.shape.borderRadius,
        backgroundColor: fade(theme.palette.common.white, 0.15),
        '&:hover': {
            backgroundColor: fade(theme.palette.common.white, 0.25),
        },
        marginRight: theme.spacing(2),
        marginLeft: 0,
        width: '100%',
        [theme.breakpoints.up('sm')]: {
            marginLeft: theme.spacing(3),
            width: 'auto',
        },
    },
    searchIcon: {
        width: theme.spacing(7),
        height: '100%',
        position: 'absolute',
        pointerEvents: 'none',
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
    },
    inputRoot: {
        color: 'inherit',
    },
    inputInput: {
        padding: theme.spacing(1, 1, 1, 7),
        transition: theme.transitions.create('width'),
        width: '100%',
        [theme.breakpoints.up('md')]: {
            width: 200,
        },
}}));

export default function ButtonAppBar(props) {
    const classes = useStyles();

    const [anchorEl, setAnchorEl] = React.useState(null);
    const handleClick = event => {
        setAnchorEl(event.currentTarget);
    };

    const handleClose = () => {
        setAnchorEl(null);
    };

    const open = Boolean(anchorEl);
    const id = open ? 'simple-popover' : undefined;

    function handleLogout(){
        localStorage.removeItem("token")
        handleClose()
    }

    let loginButton;
    let token = localStorage.getItem("token");
    if(token === null){
        loginButton = <div><Button aria-describedby={id} color="inherit" onClick={handleClick}>
            Login
        </Button>
            <Popover
                id={id}
                open={open}
                anchorEl={anchorEl}
                onClose={handleClose}
                anchorOrigin={{
                    vertical: 'bottom',
                    horizontal: 'center',
                }}
                transformOrigin={{
                    vertical: 'top',
                    horizontal: 'center',
                }}
            >
                <Login handleSetToken={props.handleSetToken} handleClose={handleClose}/>
            </Popover>
        </div>
    }else{
        loginButton = <div><Button aria-describedby={id} color="inherit" onClick={handleClick}>
            User
        </Button>
            <Popover
                id={id}
                open={open}
                anchorEl={anchorEl}
                onClose={handleClose}
                anchorOrigin={{
                    vertical: 'bottom',
                    horizontal: 'center',
                }}
                transformOrigin={{
                    vertical: 'top',
                    horizontal: 'center',
                }}
            >
                <MuiThemeProvider>
                <Typography>{token}</Typography>
                <RaisedButton label="Logout!" primary={true}  onClick={() => handleLogout()}/>
                </MuiThemeProvider>

            {/*    TODO: add user detail page here*/}
            </Popover>
        </div>
    }

    const onChange = (event) => {
        props.handleSearchWordChange(event.target.value)
    };

    return (
        <div className={classes.root}>
            <AppBar position="static">
                <Toolbar>
                    <IconButton edge="start" className={classes.menuButton} color="inherit" aria-label="menu">
                        <MenuIcon onClick={props.handleDrawerOpen} />
                    </IconButton>
                    <div className={classes.search}>
                        {/*<div className={classes.searchIcon}>*/}

                        {/*</div>*/}
                        <InputBase
                            placeholder="Search…"
                            classes={{
                                root: classes.inputRoot,
                                input: classes.inputInput,
                            }}
                            inputProps={{ 'aria-label': 'search' }}
                            onChange={onChange}
                        />
                        <IconButton>
                            <SearchIcon onClick={props.handleSearch} />
                        </IconButton>
                    </div>
                    <Typography variant="h6" className={classes.title}>
                        Find My Home
                    </Typography>
                    {loginButton}
                </Toolbar>
            </AppBar>
        </div>
    );
}