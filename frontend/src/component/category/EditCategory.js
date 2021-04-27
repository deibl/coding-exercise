import React from 'react';
import Card from "@material-ui/core/Card";
import CardContent from "@material-ui/core/CardContent";
import Grid from "@material-ui/core/Grid";
import Box from "@material-ui/core/Box";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import SaveIcon from '@material-ui/icons/Save';
import MenuItem from "@material-ui/core/MenuItem";
import Select from "@material-ui/core/Select";
import {v4 as uuidv4} from "uuid";

class EditCategory extends React.Component {

  state = {
    currentName: this.props.category?.name ?? "",
    selectedParent: this.props.category?.parentId ?? "",
    editMode: this.props.category !== undefined
  };

  handleNameChange = (event) => {
    this.setState({
      currentName: event.target.value,
    });
  };

  handleParentChange = (event) => {
    this.setState({
      selectedParent: event.target.value,
    });
  };

  onUpdate = (event) => {
    const updatedCategory = {
      domainId: this.props.category.domainId,
      name: this.state.currentName,
      parentId: (this.state.selectedParent !== "") ? this.state.selectedParent : null,
    }
    this.props.onCategoryUpdate(updatedCategory);
  }

  onAdd = (event) => {
    const newCategory = {
      domainId: uuidv4(),
      name: this.state.currentName,
      parentId: (this.state.selectedParent !== "") ? this.state.selectedParent : null,
    }
    this.props.onCategoryAdd(newCategory);
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
              </Grid>
              <Grid container spacing={3}>
                <Grid item>
                  Parent:
                </Grid>
                <Grid item>
                  <Select
                      value={this.state.selectedParent}
                      onChange={this.handleParentChange}
                  >
                    <MenuItem value="">None</MenuItem>
                    {this.props.categories.map((category) =>
                        <MenuItem
                            key={category.domainId}
                            value={category.domainId}
                        >{category.name}</MenuItem>
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

export default EditCategory;
