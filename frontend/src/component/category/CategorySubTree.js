import React from 'react';
import {Link as RouterLink, withRouter} from 'react-router-dom';
import Link from "@material-ui/core/Link";

class CategorySubTree extends React.Component {

  onCategorySelect() {
    this.props.onCategorySelect(this.props.parentId);
  }
  
  render() {
    return (
        <div>
          <Link
              component={RouterLink}
              to="/"
              onClick={this.onCategorySelect.bind(this)}
              style={{ marginLeft: (10 * this.props.layer)  + "px", cursor:'pointer' }}
          >
            {this.props.parentName}
          </Link>
          {this.props.allCategories
          .filter(category => category.parentId === this.props.parentId)
          .map((category) =>
              <CategorySubTree
                  key={category.domainId}
                  parentId={category.domainId}
                  parentName={category.name}
                  allCategories={this.props.allCategories}
                  layer={this.props.layer + 1}
                  onCategorySelect={this.props.onCategorySelect}
              />
          )}
        </div>
    );
  }
}

export default withRouter(CategorySubTree);
