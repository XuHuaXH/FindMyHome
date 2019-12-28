import React from 'react';
import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import CardMedia from '@material-ui/core/CardMedia';
import Fab from '@material-ui/core/Fab';
import FavoriteIcon from '@material-ui/icons/Favorite';
import FavoriteBorderIcon from '@material-ui/icons/FavoriteBorder';
import {Link} from "react-router-dom";
import axios from 'axios';


class PropertyCard extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            favIcon: <FavoriteBorderIcon/>,
            liked: false
        }

    }


    render() {
        let id = this.props.id;
        return (
            <Card style={styles.card}>
                <CardContent>
                    <Typography color="textSecondary" gutterBottom>
                        {this.props.streetNo} {this.props.roadName}, {this.props.city}, {this.props.state} {this.props.zipCode}
                    </Typography>
                    <Typography style={styles.pos} color="textSecondary">
                        Price: {this.props.price}
                    </Typography>
                    <Typography style={styles.pos} color="textSecondary">
                        {this.props.description}
                    </Typography>
                    <Fab aria-label="like" style={styles.icon} onClick={() => this.favIconAction()}>
                        {this.state.favIcon}
                    </Fab>
                    <CardMedia
                        style={styles.media}
                        image="/image/0"
                        title="Property Image"
                    />
                </CardContent>
                <CardActions>
                    {/*<Link to={"/property-details/"+id}>*/}
                    {/*<Button size="small">View Details</Button>*/}
                    {/*</Link>*/}
                </CardActions>
            </Card>
        );
    }


    favIconAction = () => {

        let url = "/api/";
        let path;
        let token = localStorage.getItem("token");
        let payload = {
            "id" : this.props.id
        }

        const options = {
            headers: {'Authorization': token}
        };

        if (this.state.liked) {
            path = "unlike";
            this.setState({
                favIcon: <FavoriteBorderIcon/>,
                liked: false
            });
            console.log("unliked");
        } else {
            path = "like";
            this.setState({
                favIcon: <FavoriteIcon/>,
                liked: true
            });
            console.log("liked");
        }

        // update the backend
        axios.post(url + path, payload, options)
            .then((response) => {
                console.log(response);
            })
            .catch((error) => {
                console.log(error);
            });

    }

}

const styles = {
    card: {
        minWidth: 275,
        margin: 20
    },
    title: {
        fontSize: 14,
    },
    pos: {
        marginBottom: 12,
    },
    media: {
        height: 0,
        paddingTop: '56.25%', // 16:9
    },
    icon: {
        margin: 15
    }
}

export default PropertyCard;