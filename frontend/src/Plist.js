import React from 'react';
import PItem from "./PItem"
import List from "@material-ui/core/List"
import Paper from "@material-ui/core/Paper"
import Typography from "@material-ui/core/Typography"


export default class PList extends React.Component {



    render(){
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
                flexGrow: 1
            },
            media: {
                height: 0,
                paddingTop: '56.25%', // 16:9
            },
            icon: {
                margin: 15
            }
        }
        let toRender
        if(this.props.displayedProperties.length === 0){
            if (this.props.displayState === "Welcome To FindMyHome!"){
                toRender=<Typography style={styles.pos} color="textSecondary">
                    Search Something To Get Started!
                </Typography>
            }else {
                toRender =
                    <Typography style={styles.pos} color="textSecondary">
                        It's empty! Try Search Something Else?
                    </Typography>
            }
        }else{
            toRender= <List>
                {this.props.displayedProperties}
            </List>
        }
        return(<div>
            <Typography style={styles.pos} variant="h4" color="textSecondary">
                {this.props.displayState}
            </Typography>

            <Paper style={{maxHeight: '85vh', minHeight:'85vh', overflow: 'auto'}}>
                {toRender}
            </Paper>

        </div>)
    }
}