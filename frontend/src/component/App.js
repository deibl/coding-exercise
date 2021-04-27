import React from 'react';
import Drawer from '@material-ui/core/Drawer';
import CssBaseline from '@material-ui/core/CssBaseline';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import Link from '@material-ui/core/Link';
import ProductList from "./product/ProductList";
import ProductDetails from "./product/ProductDetails";
import CategoryTree from "./category/CategoryTree";
import Categories from "./category/Categories";
import {
  BrowserRouter as Router,
  Link as RouterLink,
  Route,
  Switch
} from 'react-router-dom';
import withStyles from "@material-ui/core/styles/withStyles";
import {Box} from "@material-ui/core";
import {
  createCategory,
  deleteCategory,
  getCategories,
  updateCategory
} from "../client/CategoryClient";
import {getCurrencies} from "../client/CurrencyClient";
import {
  createProduct,
  deleteProduct,
  getProductsByCategory,
  updateProduct
} from "../client/ProductClient";

const drawerWidth = 240;

const styles = (theme) => ({
  root: {
    display: 'flex',
  },
  appBar: {
    width: `calc(100% - ${drawerWidth}px)`,
    marginLeft: drawerWidth,
  },
  drawer: {
    width: drawerWidth,
    flexShrink: 0,
  },
  drawerPaper: {
    width: drawerWidth,
  },
  // necessary for content to be below app bar
  toolbar: theme.mixins.toolbar,
  content: {
    flexGrow: 1,
    backgroundColor: theme.palette.background.default,
    padding: theme.spacing(3),
  },
});

class App extends React.Component {

  state = {
    products: [],
    categories: [],
    currencies: [],
    currentCategoryId: undefined
  };

  baseUrl() {
    return process.env.REACT_APP_BACKEND_API_BASE_URL;
  }

  componentDidMount() {
    getCategories()
    .then(data => this.setState({categories: data}));
    getCurrencies()
    .then(data => this.setState({currencies: data}))
  }

  handleCategorySelect(newCategoryId) {
    getProductsByCategory(newCategoryId)
    .then((data) => {
      this.setState({products: data})
      this.setState({currentCategoryId: newCategoryId})
    })
  }

  handleProductAdd(newProduct) {
    createProduct(newProduct, this.state.currentCategoryId)
    .then((data) => {
      let productsCopy = [...this.state.products];
      productsCopy.push(data);
      this.setState({products: productsCopy});
    })
  }

  handleProductUpdate(updatedProduct) {
    updateProduct(updatedProduct)
    .then((data) => {
      let productsCopy = [...this.state.products];
      const indexUpdatedProduct = productsCopy.findIndex(
          product => product.domainId === data.domainId);
      let productToBeUpdated = {...productsCopy[indexUpdatedProduct]};
      productToBeUpdated.name = data.name;
      productToBeUpdated.price = data.price;
      productsCopy[indexUpdatedProduct] = productToBeUpdated;
      this.setState({products: productsCopy});
    })
  }

  handleProductDelete(productToDeleteId) {
    deleteProduct(productToDeleteId)
    .then(() => {
      const indexDeleteProduct = this.state.products.findIndex(
          product => product.id === productToDeleteId);
      let productsCopy = [...this.state.products];
      productsCopy.splice(indexDeleteProduct, 1)
      this.setState({products: productsCopy});
    })
  }

  handleCategoryAdd(newCategory) {
    createCategory(newCategory)
    .then(() => {
      let categoriesCopy = [...this.state.categories];
      categoriesCopy.push(newCategory);
      this.setState({categories: categoriesCopy});
    })
  }

  handleCategoryUpdate(updatedCategory) {
    updateCategory(updatedCategory)
    .then(() => {
      let categoriesCopy = [...this.state.categories];
      const indexUpdatedCategory = categoriesCopy.findIndex(
          category => category.domainId === updatedCategory.domainId);
      let categoryToBeUpdated = {...categoriesCopy[indexUpdatedCategory]};
      categoryToBeUpdated.name = updatedCategory.name;
      categoryToBeUpdated.parentId = updatedCategory.parentId;
      categoriesCopy[indexUpdatedCategory] = categoryToBeUpdated;
      this.setState({categories: categoriesCopy});
    })
  }

  handleCategoryDelete(categoryToDeleteId) {
    deleteCategory(categoryToDeleteId)
    .then(() => {
      const indexDeleteProduct = this.state.categories.findIndex(
          category => category.domainId === categoryToDeleteId);
      let categoriesCopy = [...this.state.categories];
      categoriesCopy.splice(indexDeleteProduct, 1)
      this.setState({categories: categoriesCopy});
    })
  }

  render() {
    const {classes} = this.props;
    return (
        <Router>
          <div className={classes.root}>
            <CssBaseline/>
            <AppBar position="fixed" className={classes.appBar}>
              <Toolbar>
                <Typography variant="h6" noWrap>
                  Demo
                </Typography>
              </Toolbar>
            </AppBar>
            <Drawer
                className={classes.drawer}
                variant="permanent"
                classes={{
                  paper: classes.drawerPaper,
                }}
                anchor="left"
            >
              <div className={classes.toolbar}/>
              <CategoryTree
                  categories={this.state.categories}
                  onCategorySelect={this.handleCategorySelect.bind(this)}
              />
              <Box m={2}>
                <Link component={RouterLink} to="/categories/edit">Manage
                  Categories</Link>
              </Box>
            </Drawer>

            <main className={classes.content}>
              <div className={classes.toolbar}/>
              <Switch>
                <Route path="/details/:productId">
                  <ProductDetails
                      currencies={this.state.currencies}
                      allProducts={this.state.products}
                      onProductUpdate={this.handleProductUpdate.bind(this)}
                      onProductDelete={this.handleProductDelete.bind(this)}
                  />
                </Route>
                <Route path="/categories/edit">
                  <Categories
                      categories={this.state.categories}
                      onCategoryDelete={this.handleCategoryDelete.bind(this)}
                      onCategoryAdd={this.handleCategoryAdd.bind(this)}
                      onCategoryUpdate={this.handleCategoryUpdate.bind(this)}
                  />
                </Route>
                <Route path="/">
                  <ProductList
                      currencies={this.state.currencies}
                      products={this.state.products}
                      onProductAdd={this.handleProductAdd.bind(this)}
                      categoryId={this.state.currentCategoryId}
                  />
                </Route>
              </Switch>
            </main>
          </div>
        </Router>
    );
  }
}

export default withStyles(styles)(App);
