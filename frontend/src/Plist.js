import React from 'react';
import PItem from "./PItem"
import List from "@material-ui/core/List"
import Paper from "@material-ui/core/Paper"


export default class PList extends React.Component {
    render(){
        return(<div>
            <Paper style={{maxHeight: '600px', overflow: 'auto'}}>
                <List>
                    <PItem/>
                    <PItem/>
                    <PItem/>
                    <PItem/>
                    <PItem/>
                    <PItem/>
                    <PItem/>
                    <PItem/>
                    <PItem/>
                </List>
            </Paper>

        </div>)
    }
}