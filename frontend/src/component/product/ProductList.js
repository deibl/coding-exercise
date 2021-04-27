import React from 'react';
import ProductListItem from "./ProductListItem";
import EditProduct from "./EditProduct";
import Button from "@material-ui/core/Button";
import AddIcon from '@material-ui/icons/Add';

class ProductList extends React.Component {

  state = {
    addModeEnabled: false,
  };

  toggleAddMode() {
    this.setState({ addModeEnabled: !this.state.addModeEnabled });
  }

  render() {
    return (
        <div>
          {this.props.products.map((product) =>
              <ProductListItem key={product.domainId} product={product} />
          )}
          {this.state.addModeEnabled && <EditProduct
              currencies={this.props.currencies}
              onProductAdd={this.props.onProductAdd}
              categoryId={this.props.categoryId}
          />}
          <Button
              variant="contained"
              color="primary"
              startIcon={<AddIcon/>}
              onClick={() => this.toggleAddMode()}
              disabled={!this.props.categoryId}
          >
            Add
          </Button>
        </div>
    );
  }
}

export default ProductList;
