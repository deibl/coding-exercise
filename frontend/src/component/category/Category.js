import React from 'react';
import Button from "@material-ui/core/Button";
import Box from "@material-ui/core/Box";
import Card from "@material-ui/core/Card";
import CardContent from "@material-ui/core/CardContent";
import Grid from "@material-ui/core/Grid";
import EditIcon from "@material-ui/icons/Edit";
import DeleteIcon from "@material-ui/icons/Delete";
import EditCategory from "./EditCategory";

class Category extends React.Component {

  state = {
    editModeEnabled: false,
  };

  toggleEditMode() {
    this.setState({editModeEnabled: !this.state.editModeEnabled});
  }

  onDelete = (event) => {
    this.props.onCategoryDelete(this.props.category.domainId)
  }

  render() {
    return (
          <Box m={2}>
            <Card variant="outlined">
              <CardContent>
                <Grid container spacing={3}>
                  <Grid item xs={6}>
                    {this.props.category.name}
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
                {this.state.editModeEnabled && <EditCategory
                    categories={this.props.categories}
                    category={this.props.category}
                    onCategoryUpdate={this.props.onCategoryUpdate}/>}
              </CardContent>
            </Card>
          </Box>
    );
  }
}

export default Category;
