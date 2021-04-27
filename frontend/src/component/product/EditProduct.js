import React from 'react';
import Card from "@material-ui/core/Card";
import CardContent from "@material-ui/core/CardContent";
import Grid from "@material-ui/core/Grid";
import Box from "@material-ui/core/Box";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import SaveIcon from '@material-ui/icons/Save';
import {v4 as uuidv4} from "uuid";
import Select from "@material-ui/core/Select";
import MenuItem from "@material-ui/core/MenuItem";

class EditProduct extends React.Component {

  state = {
    currentName: this.props.product?.name ?? "",
    currentPrice: this.props.product?.price ?? 0,
    currentCurrency: "EUR",
    editMode: this.props.product !== undefined
  };

  handleNameChange = (event) => {
    this.setState({
      currentName: event.target.value,
    });
  };

  handlePriceChange = (event) => {
    this.setState({
      currentPrice: event.target.value,
    });
  };

  handleCurrencyChange = (event) => {
    this.setState({
      currentCurrency: event.target.value,
    });
  };

  onUpdate = (event) => {
    const updatedProduct = {
      domainId: this.props.product.domainId,
      name: this.state.currentName,
      price: this.state.currentPrice,
      currency: this.props.currencies.find(currency => currency.abbreviation === this.state.currentCurrency)
    }
    this.props.onProductUpdate(updatedProduct);
  }

  onAdd = (event) => {
    const newProduct = {
      domainId: uuidv4(),
      name: this.state.currentName,
      price: this.state.currentPrice,
      currency: this.props.currencies.find(currency => currency.abbreviation === this.state.currentCurrency)
    }
    this.props.onProductAdd(newProduct);
  }

  render() {
    return (
        <Box m={2}>
          <Card variant="outlined">
            <CardContent>
              <Grid container spacing={3}>
                <Grid item>
                  <TextField
                      id="standard-basic"
                      label="Name"
                      value={this.state.currentName}
                      onChange={this.handleNameChange}
                  />
                </Grid>
                <Grid item xs>
                  <TextField
                      id="standard-basic"
                      label="Price"
                      value={this.state.currentPrice}
                      onChange={this.handlePriceChange}
                      type="number"
                  />
                </Grid>
                <Grid item xs>
                  <Select
                      value={this.state.currentCurrency}
                      onChange={this.handleCurrencyChange}
                  >
                    {this.props.currencies.map((currency) =>
                        <MenuItem
                            key={currency.abbreviation}
                            value={currency.abbreviation}
                        >{currency.name}</MenuItem>
                    )}
                  </Select>
                </Grid>
              </Grid>
              <Grid container spacing={3}>
                <Grid item>
                  <Button
                      variant="contained"
                      color="primary"
                      startIcon={<SaveIcon/>}
                      onClick={this.state.editMode ? this.onUpdate : this.onAdd}
                  >
                    Save
                  </Button>
                </Grid>
              </Grid>
            </CardContent>
          </Card>
        </Box>
    );
  }
}

export default EditProduct;
