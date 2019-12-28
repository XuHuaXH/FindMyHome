import React from "react";

class PropertyDetails extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return(
            <div>
                <p>Display details for property No. {this.props.match.params.id}</p>
            </div>
        );
    }
}

export default PropertyDetails;