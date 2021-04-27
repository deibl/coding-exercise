import React from 'react';
import Card from "@material-ui/core/Card";
import CardContent from "@material-ui/core/CardContent";
import Grid from "@material-ui/core/Grid";
import Typography from "@material-ui/core/Typography";
import Box from "@material-ui/core/Box";
import {withRouter} from "react-router";
import EditProduct from "./EditProduct";
import Button from "@material-ui/core/Button";
import DeleteIcon from '@material-ui/icons/Delete';
import EditIcon from '@material-ui/icons/Edit';
import { Redirect } from "react-router-dom";

class ProductDetails extends React.Component {

  state = {
    editModeEnabled: false,
    redirect: false
  };

  getProduct() {
    const productIdFromRoute = this.props.match.params.productId;
    return this.props.allProducts.find(product => product.domainId === productIdFromRoute);
  }

  toggleEditMode() {
    this.setState({ editModeEnabled: !this.state.editModeEnabled });
  }

  onDelete = (event) => {
    this.props.onProductDelete(this.getProduct().domainId);
    this.setState({redirect: true})
  }

  render() {
    const { editModeEnabled } = this.state;
    if (this.state.redirect) {
      return <Redirect to="/" />
    }
    return (
        <>
          <Box m={2}>
            <Card variant="outlined">
              <CardContent>
                <Grid container spacing={3}>
                  <Grid item xs={6}>
                    Just imagine i am a BIG product picture
                  </Grid>
                  <Grid item xs={6} sm container>
                    <Grid item container direction="column" spacing={2}>
                      <Grid item xs>
                        <Typography gutterBottom variant="subtitle1">
                          Name: {this.getProduct().name}
                        </Typography>
                        <Typography variant="body2" gutterBottom>
                          Price: {this.getProduct().price} {this.getProduct().currency.abbreviation}
                        </Typography>
                        <Typography variant="body2" color="textSecondary">
                          ID: {this.getProduct().domainId}
                        </Typography>
                      </Grid>
                    </Grid>
                  </Grid>
                </Grid>
                <Grid container spacing={3}>
                  <Grid item>
                    Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.
                  </Grid>
                </Grid>
                <Grid container spacing={3}>
                  <Grid item>
                    <Button
                        variant="contained"
                        color="primary"
                        startIcon={<EditIcon/>}
                        onClick={() => this.toggleEditMode()}
                    >
                      Edit
                    </Button>
                  </Grid>
                  <Grid item>
                    <Button
                        variant="contained"
                        color="secondary"
                        startIcon={<DeleteIcon/>}
                        onClick={this.onDelete}
                    >
                      Delete
                    </Button>
                  </Grid>
                </Grid>
              </CardContent>
            </Card>
          </Box>
          {editModeEnabled && <EditProduct
              onProductUpdate={this.props.onProductUpdate}
              product={this.getProduct()}
              currencies={this.props.currencies}
          />}
        </>
    );
  }
}

export default withRouter(ProductDetails);
