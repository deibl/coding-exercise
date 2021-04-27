import React from 'react';
import Button from "@material-ui/core/Button";
import AddIcon from "@material-ui/icons/Add";
import Category from "./Category";
import EditCategory from "./EditCategory";

class Categories extends React.Component {

  state = {
    addModeEnabled: false,
  };

  toggleAddMode() {
    this.setState({addModeEnabled: !this.state.addModeEnabled});
  }

  render() {
    return (
        <div>
          {this.props.categories
          .map((category) =>
              <Category
                  key={category.domainId}
                  category={category}
                  categories={this.props.categories}
                  onCategoryDelete={this.props.onCategoryDelete}
                  onCategoryUpdate={this.props.onCategoryUpdate}
              />
          )}
          {this.state.addModeEnabled && <EditCategory
              key={this.props.categories}
              categories={this.props.categories}
              onCategoryAdd={this.props.onCategoryAdd}/>}
          <Button
              variant="contained"
              color="primary"
              startIcon={<AddIcon/>}
              onClick={() => this.toggleAddMode()}
          >
            Add
          </Button>
        </div>
    );
  }
}

export default Categories;
